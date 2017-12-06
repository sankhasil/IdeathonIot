import time
import RPi.GPIO as GPIO
import requests

INPUT_PIN = 8
INPUT_SWITCH = 40
Out_3 = 3
Out_5 = 5
Out_7 = 7
PULSE_REST_URL = 'http://INBANSHETEV:8091/delegate/'
GPIO.setwarnings(False)

GPIO.setmode(GPIO.BOARD)
GPIO.setup(INPUT_PIN,GPIO.IN)
GPIO.setup(INPUT_SWITCH,GPIO.IN)
GPIO.setup(Out_3, GPIO.OUT)
GPIO.setup(Out_5, GPIO.OUT)
GPIO.setup(Out_7, GPIO.OUT)

t1=0
t2=0
count_beats=0
interval = 5

def inputLow(channel):
    global t1
    global t2
    global count_beats
    global interval
    if GPIO.input(INPUT_PIN):
        GPIO.output(Out_7,True)
        #print('0') 
        count_beats = count_beats+1
        if count_beats%interval == 0:
            t2=time.time()
            print("Beats Per Min: ")
            #print(t2-t1)
            pulse = (interval*60)/(t2-t1)
            if GPIO.input(INPUT_SWITCH):
               pulsePrePost='pre'
            else:
               pulsePrePost='post' 
            pulse_data = {
                                'device':'PULSE01',
                                'patientActivationCode':'1000067620',
                                'pulsePrePost' : pulsePrePost,
                                'data' : pulse
                            }
            print(pulse_data)
            if pulse > 73.0 :
                GPIO.output(Out_3,True)
            else:
                GPIO.output(Out_3,False)
            try:
                response = requests.post(PULSE_REST_URL+'pulse', json=pulse_data)
                print('Response From Pulse Server')
                print(response.status_code)
                GPIO.output(Out_5,False)
            except Exception:
                print("Exception in Pulse Data Sending")
                GPIO.output(Out_5,True)
               
            t2=0
            t1=time.time()
            
    else:
        #print('1')
        GPIO.output(Out_7,False)
    
try:
    
    if(requests.get(PULSE_REST_URL+'test').status_code == 200):
        GPIO.output(Out_5,False)
        GPIO.add_event_detect(INPUT_PIN, GPIO.BOTH, callback=inputLow, bouncetime=200)
    else:
        print('REST Server Not Reachable')
        GPIO.output(Out_5,True)
        GPIO.cleanup()
    
except Exception:
    print("Exception in Pulse Sensing")
    GPIO.output(Out_5,True)
    GPIO.cleanup()

