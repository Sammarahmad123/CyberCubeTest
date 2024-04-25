package tools;

import enums.PropertyType;

import static tools.Logger.report;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyReader {

	private static Map<PropertyType, Properties> propertiesMap = new HashMap<>();

	static {
		// Load all property files at the beginning
		for (PropertyType type : PropertyType.values()) {
			loadProperties(type);
		}
	}

	private static void loadProperties(PropertyType type) {
		Properties prop = new Properties();
		String filePath = "src/test/resources/saucedemo/" + type.getProperty();
		try (FileInputStream inputStream = new FileInputStream(filePath)) {
			prop.load(inputStream);
			propertiesMap.put(type, prop);
		} catch (IOException e) {
			System.out.println("Failed to load properties from " + type.getProperty() + ": " + e.getMessage());
		}
	}

	public static String getProperty(PropertyType type, String key) {
		Properties prop = propertiesMap.get(type);
		if (prop != null) {
			return prop.getProperty(key);
		}
		return null; // or throw an exception if the property file is critical
	}
}
