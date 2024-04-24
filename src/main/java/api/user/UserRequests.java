package api.user;

import api.utils.ApiUtils;
import dto.user.UserDTO;
import enums.StatusCode;
import io.restassured.response.Response;

import java.util.List;

public class UserRequests {

	private static final String USER_ENDPOINT = "/user";

	public static Response createUserWithList(List<UserDTO> userList, StatusCode expectedStatusCode) {
		return ApiUtils.post(USER_ENDPOINT, userList, "/createWithList", expectedStatusCode);
	}

	public static Response getUserByUsername(String username, StatusCode expectedStatusCode) {
		return ApiUtils.get(USER_ENDPOINT, username, expectedStatusCode);
	}

	public static Response updateUserByUsername(String username, UserDTO user, StatusCode expectedStatusCode) {
		return ApiUtils.put(USER_ENDPOINT + "/" + username, user, "", expectedStatusCode);
	}

	public static Response deleteUserByUsername(String username, StatusCode expectedStatusCode) {
		return ApiUtils.delete(USER_ENDPOINT + "/" + username,"", expectedStatusCode);
	}

	public static Response loginUser(String username, String password, StatusCode expectedStatusCode) {
		String url = USER_ENDPOINT +"/login"+ "?username=" + username + "&password=" + password;
		return ApiUtils.get(url, "", expectedStatusCode);
	}
	public static Response logoutUser(StatusCode expectedStatusCode) {
		return ApiUtils.get(USER_ENDPOINT, "logout", expectedStatusCode);
	}
	public static Response createUser(UserDTO user, StatusCode expectedStatusCode) {
		return ApiUtils.post(USER_ENDPOINT, user, "", expectedStatusCode);
	}
}
