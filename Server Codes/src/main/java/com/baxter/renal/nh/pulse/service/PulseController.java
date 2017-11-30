/*
 * BAXTER CONFIDENTIAL - Highly Restricted: Do not distribute without prior approval
 *
 * Project: New Horizons
 *
 * Copyright Â© 2017 Baxter Healthcare Corporation. All rights reserved.
 */
package com.baxter.renal.nh.pulse.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baxter.renal.nh.pulse.service.dto.Pulse;
import com.baxter.renal.nh.pulse.service.exception.InvalidRequestException;
import com.baxter.renal.nh.pulse.service.exception.PulseNotStoredException;

/**
 * Rest controller for PAC
 * @author shetev
 */
@RestController
@CrossOrigin
public class PulseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PulseController.class);
	
	Pulse lastPulse = new Pulse("Dev1",100);
	
	@Autowired
	private PulseService pulseService;
	
	/**
	 * @return PACLookup domain object as ResponseEntity
	 */
	@RequestMapping(value="/pulse", method = RequestMethod.GET)
	public ResponseEntity<Double> getTest(){
		//PACLookup pacLookup = pulseService.getPacInfo(pulse);
		//LOGGER.debug("PACController.getPACInfo :: Pac request: Sending {}", pacLookup.toSend());
		System.out.println("Sending last pulse value:");
		return new ResponseEntity<Double>(lastPulse.getData(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public ResponseEntity<String> getPulse(){
		//PACLookup pacLookup = pulseService.getPacInfo(pulse);
		//LOGGER.debug("PACController.getPACInfo :: Pac request: Sending {}", pacLookup.toSend());
		
		return new ResponseEntity<String>("true", HttpStatus.OK);
	}
	
	@RequestMapping(value="/pulse", method = RequestMethod.POST)
	public ResponseEntity<String> storePulse(@RequestBody Pulse pulse){
		this.lastPulse = pulse;
		//PACLookup pacLookup = pulseService.getPacInfo(pulse);
		//LOGGER.debug("PACController.getPACInfo :: Pac request: Sending {}", pacLookup.toSend());
		System.out.println(pulse.toString());
		return new ResponseEntity<String>("true", HttpStatus.OK);
	}
	
	/**
	 * Exception handler for PacNotFoundException.
	 *
	 * @param e Exception to handle
	 * @return ResponseEntity with HttpStatus NOT_FOUND
	 */
	@ExceptionHandler(PulseNotStoredException.class)
	public ResponseEntity<ErrorDetails> pulseNotStored(PulseNotStoredException e){
		LOGGER.error("PACController.pacNotFound :: Could not find PAC. Generating ErrorResponse: " + e.getError());
		return new ResponseEntity<ErrorDetails>(new ErrorDetails(e.getError()), HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Exception handler for InvalidRequestException
	 * 
	 * @param e Exception to handle
	 * @return ResponseEntity with HttpStatus BAD_REQUEST
	 */
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ErrorDetails> invalidRequest(InvalidRequestException e){
		LOGGER.error("PACController.invalidRequest :: Invalid Request. Generating ErrorResponse: " + e.getError());
		return new ResponseEntity<ErrorDetails>(new ErrorDetails(e.getError()), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Exception handler for Exception
	 * 
	 * @param e Exception to handle
	 * @return ResponseEntity with HttpStatus INTERNAL_SERVER_ERROR
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> allExceptions(Exception e){
		LOGGER.error("PACController.allExceptions :: Internal Server Error. Generating ErrorResponse: " + e.getMessage());
		return new ResponseEntity<ErrorDetails>(new ErrorDetails(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * @author shetev
	 * ErrorDetails class
	 */
	static class ErrorDetails{
		private String error;
		
		/**
         * Returns the error.
         *
         * @return String containing the error
         */
		public String getError() {
			return error;
		}
		
		/**
         * Sets the error.
         *
         * @param error the error to set
         */
		public void setError(String error) {
			this.error = error;
		}
		
		/**
		 * Constructor
		 * 
		 * @param error
		 */
		public ErrorDetails(String error) {
			super();
			this.error = error;
		}
	}
}
