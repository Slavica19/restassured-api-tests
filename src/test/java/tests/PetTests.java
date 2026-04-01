package tests;

import Base.BaseTest;
import client.PetClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.AllureTestNg;
import model.Category;
import model.Pet;
import model.Tags;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners({AllureTestNg.class})
public class PetTests extends BaseTest {

    PetClient petClient = new PetClient();


    private Pet  buildPet(long id) {
        Category category = new Category();
        category.setId(0);
        category.setName("string");
        Tags tag = new Tags();
        tag.setId(0);
        tag.setName("string");
        Pet pet = new Pet();
        pet.setId(id);
        pet.setCategory(category);
        pet.setName("doggie");
        pet.setPhotoUrls(List.of("string"));
        pet.setTags(List.of(tag));
        pet.setStatus("available");

        return pet;
    }

    @Test(description = "Create a pet and verify status code 200")
    @Epic("PetStore API")
    @Feature("Pet Management")
    @Story("Create Pet")
    public void createPet_shouldReturn200() {
        long petId = System.currentTimeMillis();

        Pet pet = buildPet(petId);

        var response = petClient.createPet(pet);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getLong("id"), petId);
    }

    @Test(description = "Get pet by ID and verify details", dependsOnMethods = "createPet_shouldReturn200")
    @Epic("PetStore API")
    @Feature("Pet Management")
    @Story("Get Pet by ID")
    public void getPetById_shouldReturnPet() {
        long petId = System.currentTimeMillis();

        Pet pet = buildPet(petId);
        petClient.createPet(pet);

        var response = petClient.getPetId(petId);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "doggie");
        Assert.assertEquals(response.jsonPath().getLong("id"), petId);
    }
}


