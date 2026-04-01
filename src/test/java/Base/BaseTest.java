package Base;

import io.restassured.RestAssured;

public class BaseTest {

    public void setUp(){

        RestAssured.baseURI = config.ConfigManager.getBaseUrl();
    }
}
