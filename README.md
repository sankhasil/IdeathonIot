# PACRestService Specifications

The PAC Service schema is defined as below:

- basePath: /delegate
- schemes: [https]

_Endpoint URL:_ /pulse

_Method:_ POST

_Success Response:_

- Code: 200
- Content: {patientId: 1000003, activationCode: 1000067620, regionCode: R1}
  - Match all:
    - Pass JWT verification
    - Pass parameters value verify
    - Find activation code

_Error Response:_

- Code: 404 NOT FOUND
- Content: { error: &quot;Patient Activation Code 1000067621 doesn&#39;t exist&quot; }
  - Match:
    - Fail to find Patient Activation code.

- Code: 400 INVALID REQUEST
- Content: { error: &quot;Invalid request, check parameters or token payload.&quot; }
  - Match One of them:
    - Fail to verify require information in JWT payload.
    - Fail to verify JWT signature
    - Fail to verify JWT issuer
    - Fail to verify JWT expiration
    - Fail to verify JWT audience
    - Pass non-applicable parameter values

- Code: 403 FORBIDDEN
- Content: { error: &quot;You don&#39;t have permissions to access this resource.&quot; }
  - Match:
    - Failed on JWT verification for endpoint access.

- Code: 500 INTERNAL SERVER ERROR
- Content: {error: &quot;Internal Server Error. Please try again later.&quot;}
  - Match:
    - Any runtime exception.

_Sample Call:_

 $.ajax({

        url: localhost:8091/delegate/,

        dataType: json,

        type : POST,

        success : function(data) {

         console.log(data);

        }

});


# How to run:
- Command line (JAVA 8 must be installed)
  - mvn clean install
  - cd target
  - java -jar "target/NH-PMA-PACRestService-Service.jar" --spring.profiles.active=development