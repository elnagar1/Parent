package com.parent.tests;

public class BaseApi {
  
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;
    // Initialize RestAssured configuration if not already done
        if (requestSpec == null) {
            RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
            requestSpecBuilder.setBaseUri("YOUR_BASE_URI"); // Replace with your actual base URI
            requestSpecBuilder.addHeader("Content-Type", "application/json"); // Add common headers
            requestSpecBuilder.addFilter(new RequestLoggingFilter()); // Log requests
            requestSpec = requestSpecBuilder.build();
        }

        if (responseSpec == null) {
            ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
            responseSpecBuilder.expectStatusCode(200); // Add common response expectations
            responseSpecBuilder.addFilter(new ResponseLoggingFilter()); // Log responses
            responseSpec = responseSpecBuilder.build();
        }
    }

    // Additional helper methods can be added here
    // ...
}
