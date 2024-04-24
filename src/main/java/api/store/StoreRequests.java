package api.store;

import api.utils.ApiUtils;
import dto.order.OrderDTO;
import enums.StatusCode;
import io.restassured.response.Response;

public class StoreRequests {

	private static final String STORE_INVENTORY_ENDPOINT = "/store";

	public static Response getInventory(StatusCode expectedStatusCode) {
		return ApiUtils.get(STORE_INVENTORY_ENDPOINT, "inventory", expectedStatusCode);
	}

	public static Response placeOrder(OrderDTO order, StatusCode expectedStatusCode) {
		return ApiUtils.post(STORE_INVENTORY_ENDPOINT, order, "/order", expectedStatusCode);
	}

	public static Response getOrderById(long orderId, StatusCode expectedStatusCode) {
		return ApiUtils.get(STORE_INVENTORY_ENDPOINT + "/order", String.valueOf(orderId), expectedStatusCode);
	}

	public static Response deleteOrderById(long orderId, StatusCode expectedStatusCode) {
		return ApiUtils.delete(STORE_INVENTORY_ENDPOINT + "/order", String.valueOf(orderId), expectedStatusCode);
	}
}
