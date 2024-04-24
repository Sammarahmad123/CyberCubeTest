package api.negative;

import api.user.UserRequests;
import enums.StatusCode;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UserNegativeTest {

	@Test(groups = { "TC08", "negative" })
	public void TC08_retrieveNonExistingUser() {
		String nonExistingUsername = "noSuchUser123456";

		Response response = UserRequests.getUserByUsername(nonExistingUsername, StatusCode.NOT_FOUND);
		Assert.assertEquals(response.getStatusCode(), 404, "API should return a 404 Not Found status for non-existing users.");

		String responseBody = response.getBody().asString();
		Assert.assertTrue(responseBody.contains("User not found"), "Expected error message 'User not found' is not present.");
	}
}
