package api.functional;

import static api.utils.Generator.generateRandomId;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;

import api.pet.PetRequests;
import dto.pet.CategoryDTO;
import dto.pet.PetDTO;
import dto.pet.TagDTO;
import enums.PetStatus;
import enums.StatusCode;
import io.restassured.response.Response;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PetFunctionalTest {

	PetDTO pet;

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		CategoryDTO category = CategoryDTO.builder()
				.id(generateRandomId())
				.name("Dogs")
				.build();

		TagDTO tag = TagDTO.builder()
				.id(generateRandomId())
				.name("Tag1")
				.build();

		pet = PetDTO.builder()
				.id(generateRandomId())
				.category(category)
				.name("Doggie")
				.tags(Arrays.asList(tag))
				.status(PetStatus.AVAILABLE.getStatus())
				.build();
	}

	@Test(groups = { "TC01", "functional" })
	public void TC01_testAddNewPetWithCompleteDetails() {
		Response response = PetRequests.addPet(pet, StatusCode.OK);
		Assert.assertEquals(response.getStatusCode(), StatusCode.OK.getCode());
		Assert.assertEquals(response.jsonPath().getString("name"), pet.getName());
		Assert.assertEquals(response.jsonPath().getString("status"), pet.getStatus());
	}

	@DataProvider(name = "toUpdatePetData")
	public Object[][] petStatusAndNameProvider() {
		return new Object[][] {
				{ PetStatus.SOLD.getStatus(), "DoggieSold" },
				{ PetStatus.PENDING.getStatus(), "DoggiePending" }
		};
	}

	/**
	 * Tests the update functionality of an existing pet by changing its status and name.
	 * It verifies that the changes persist by performing a subsequent GET request.
	 *
	 * @param newStatus The new status to set for the pet.
	 * @param newName   The new name to set for the pet.
	 */
	@Test(dataProvider = "toUpdatePetData", groups = { "TC02", "TC04", "functional" } , dependsOnMethods = "TC01_testAddNewPetWithCompleteDetails")
	public void TC02_TC04_testUpdateExistingPetByChangingStatus(String newStatus, String newName) {
		pet = PetDTO.builder()
				.id(pet.getId())
				.status(newStatus)
				.name(newName)
				.build();

		Response response = PetRequests.putPet(pet, StatusCode.OK);
		Response getResponse = PetRequests.getPetByID(String.valueOf(pet.getId()), StatusCode.OK);
		PetDTO updatedPet = response.as(PetDTO.class);
		PetDTO updatedPetFromGetRequest = getResponse.as(PetDTO.class);
		Assert.assertEquals(updatedPet.getStatus(), updatedPetFromGetRequest.getStatus());
		Assert.assertEquals(updatedPet.getName(), updatedPetFromGetRequest.getName());
	}

	@Test(groups = { "TC09", "functional" })
	public void TC09_testFindByStatus() {
		Response response = PetRequests.filterPetsByStatus(PetStatus.AVAILABLE, StatusCode.OK);
		Assert.assertEquals(response.getStatusCode(), StatusCode.OK.getCode());
		PetDTO[] pets = response.as(PetDTO[].class);

		for (PetDTO pet : pets) {
			Assert.assertEquals(pet.getStatus(), PetStatus.AVAILABLE.getStatus());
		}

		response.then().body("status", everyItem(is(PetStatus.AVAILABLE.getStatus())));
	}

	@Test(groups = { "TC10", "functional" } , dependsOnMethods = "TC01_testAddNewPetWithCompleteDetails")
	public void TC10_testDeletePetById() {
		PetRequests.deletePetByID(String.valueOf(pet.getId()), StatusCode.NOT_FOUND);
		Response getResponse = PetRequests.getPetByID(String.valueOf(pet.getId()), StatusCode.NOT_FOUND);
		Assert.assertEquals(getResponse.jsonPath().getString("message"), "Pet not found");
		Assert.assertEquals(getResponse.jsonPath().getString("type"), "error");
	}
}
