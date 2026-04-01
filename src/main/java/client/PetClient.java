package client;

import io.restassured.response.Response;
import model.Pet;

import static io.restassured.RestAssured.given;

public class PetClient {

    public Response createPet(Pet pet){

        return given()
                .spec(utils.SpecBuilder.getRequestSpec())
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .extract().response();

    }


    public Response getPetId(long petId){

        return given()
                .spec(utils.SpecBuilder.getRequestSpec())
                .pathParam("petId", petId)
                .when()
                .get("/pet/{petId}")
                .then()
                .extract().response();
    }
}
