package api.utils;

import java.util.Random;

public class Generator {

	public static long generateRandomId() {
		Random random = new Random();
		long id = 1000000L + random.nextInt(9000000);
		return id;
	}

}
