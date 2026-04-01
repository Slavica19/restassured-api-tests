package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){

        return new RequestSpecBuilder().setBaseUri(config.ConfigManager.getBaseUrl())
                .addHeader("Content-Type","application/json")
                .build();

    }
}
