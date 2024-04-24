import static api.utils.Generator.generateRandomId;

import api.pet.PetRequests;
import enums.StatusCode;
import dto.pet.CategoryDTO;
import dto.pet.PetDTO;
import dto.pet.TagDTO;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

public class Hello {


	@Test
	public  void main() {
		String request = "{\n"
				+ "  \"id\": 3,\n"
				+ "  \"category\": {\n"
				+ "    \"id\": 0,\n"
				+ "    \"name\": \"string\"\n"
				+ "  },\n"
				+ "  \"name\": \"doggie\",\n"
				+ "  \"photoUrls\": [\n"
				+ "    \"string\"\n"
				+ "  ],\n"
				+ "  \"tags\": [\n"
				+ "    {\n"
				+ "      \"id\": 0,\n"
				+ "      \"name\": \"string\"\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"status\": \"available\"\n"
				+ "}";
//		Response r = ApiUtils.postToPet(request,"/pet",200);
//		System.out.println(r.prettyPrint());
		List<TagDTO> tags = Arrays.asList(
				TagDTO.builder().id(generateRandomId()).name("tag1").build(),
				TagDTO.builder().id(generateRandomId()).name("tag2").build(),
				TagDTO.builder().id(generateRandomId()).name("tag3").build()
		);

		PetDTO petDTO= PetDTO.builder()
				.id(generateRandomId())
				.category(CategoryDTO.builder().id(generateRandomId()).name("category").build())
				.name("")
				.tags(tags)
				.status("available")
				.build();

		System.out.println(petDTO.toString());


		Response r =PetRequests.addPet(petDTO, StatusCode.OK);
		System.out.println(r.prettyPrint());
	}

}
