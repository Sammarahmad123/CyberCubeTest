package api.negative;

import api.pet.PetRequests;
import enums.StatusCode;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PetNegativeTest {

	@Test(groups = { "TC03", "negative" })
	public void TC03_testRetrievePetWithNonExistingId() {

		String nonExistingPetId = "999999";

		Response response = PetRequests.getPetByID(nonExistingPetId, StatusCode.NOT_FOUND);

		Assert.assertEquals(response.getStatusCode(), StatusCode.NOT_FOUND.getCode());
	}

	@Test(groups = { "TC11", "negative" })
	public void TC11_testDeletePetWithoutValidPetId() {
		String petId = "123";

		Response response = PetRequests.deletePetByID(petId, StatusCode.NOT_FOUND);

		Assert.assertEquals(response.getStatusCode(), StatusCode.NOT_FOUND.getCode());
	}
}
