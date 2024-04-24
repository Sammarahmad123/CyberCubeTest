package configuration;


import common.Module;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;

public final class EnvironmentUri {
	private static final Config CONFIG = ConfigFactory.load();

	/**
	 * Method creates an url of a desired module.
	 * The above builds a ready to use url for any module you pass
	 */
	public static AddressHolder getModuleConfig(Module module) {
		Config moduleConfig = CONFIG.getConfig(module.name());
		System.out.println("moduleConfig"+moduleConfig);
		return ConfigBeanFactory.create(moduleConfig, AddressHolder.class);
	}

}
