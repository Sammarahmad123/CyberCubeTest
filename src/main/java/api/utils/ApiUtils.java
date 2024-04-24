package api.utils;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import enums.StatusCode;
import io.restassured.response.Response;

public class ApiUtils {

	private static final String BASE_URI = "https://petstore.swagger.io/";
	private static final String BASE_PATH = "v2";

	public static Response post(String module, Object request, String endpoint, StatusCode expectedStatusCode) {
		Response response = given()
				.contentType(JSON)
				.baseUri(BASE_URI)
				.basePath(BASE_PATH)
				.body(request)
				.post(module + endpoint);
		response.then().statusCode(expectedStatusCode.getCode());
		return response;
	}

	public static Response put(String module, Object request, String endpoint, StatusCode expectedStatusCode) {
		Response response = given()
				.contentType(JSON)
				.baseUri(BASE_URI)
				.basePath(BASE_PATH)
				.body(request)
				.put(module + endpoint);
		response.then().statusCode(expectedStatusCode.getCode());
		return response;
	}

	public static Response get(String module, String endpoint, StatusCode expectedStatusCode) {
		Response response = given()
				.contentType(JSON)
				.baseUri(BASE_URI)
				.basePath(BASE_PATH)
				.get(module + "/" + endpoint);
		response.then().statusCode(expectedStatusCode.getCode());
		return response;
	}

	public static Response delete(String module, String endpoint, StatusCode expectedStatusCode) {
		Response response = given()
				.contentType(JSON)
				.baseUri(BASE_URI)
				.basePath(BASE_PATH)
				.delete(module + "/" + endpoint);
		response.then().statusCode(expectedStatusCode.getCode());
		return response;
	}
}
