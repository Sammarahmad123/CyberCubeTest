package api.negative;

import api.store.StoreRequests;
import dto.order.OrderDTO;
import enums.StatusCode;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StoreNegativeTest {


	@Test(groups = { "TC05", "negative" })
	public void TC05_testFindPurchaseOrderByInvalidID() {
		long invalidOrderId = 999999999L;

		Response getOrderResponse = StoreRequests.getOrderById(invalidOrderId, StatusCode.NOT_FOUND);
		Assert.assertEquals(getOrderResponse.getStatusCode(), 404);

		String expectedErrorMessage = "Order not found";
		Assert.assertTrue(getOrderResponse.getBody().asString().contains(expectedErrorMessage),
				"Expected error message not found in the response.");
	}

	@Test(groups = { "TC08", "negative" })
	public void TC08_testDeletePurchaseOrderByInvalidID() {
		long invalidOrderId = -1;

		Response deleteResponse = StoreRequests.deleteOrderById(invalidOrderId, StatusCode.NOT_FOUND);
		Assert.assertEquals(deleteResponse.getStatusCode(), 404);

		String expectedErrorMessage = "Order Not Found";
		Assert.assertTrue(deleteResponse.jsonPath().getString("message").contains(expectedErrorMessage),
				"Expected error message not found in the response.");
	}
}
