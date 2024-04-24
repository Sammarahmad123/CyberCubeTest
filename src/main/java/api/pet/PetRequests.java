package api.pet;

import api.utils.ApiUtils;
import dto.pet.PetDTO;
import enums.PetStatus;
import enums.StatusCode;
import io.restassured.response.Response;

public class PetRequests {

	private static final String ADD_PET_ENDPOINT = "/pet";

	public static Response addPet(PetDTO pet, StatusCode expectedStatusCode) {
		return ApiUtils.post(ADD_PET_ENDPOINT, pet, "", expectedStatusCode);
	}

	public static Response putPet(PetDTO pet, StatusCode expectedStatusCode) {
		return ApiUtils.put(ADD_PET_ENDPOINT, pet, "", expectedStatusCode);
	}

	public static Response getPetByID(String petId, StatusCode expectedStatusCode) {
		return ApiUtils.get(ADD_PET_ENDPOINT, petId, expectedStatusCode);
	}

	public static Response filterPetsByStatus(PetStatus status, StatusCode expectedStatusCode) {
		return ApiUtils.get(ADD_PET_ENDPOINT+"/findByStatus?status=", status.getStatus(), expectedStatusCode);
	}

	public static Response deletePetByID(String petId, StatusCode expectedStatusCode) {
		return ApiUtils.delete(ADD_PET_ENDPOINT, petId, expectedStatusCode);
	}
}
