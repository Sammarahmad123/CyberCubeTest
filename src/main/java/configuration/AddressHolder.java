package configuration;

public class AddressHolder {

	public String protocol;
	public String host;
	public int port;

	public String buildUrl() {
		return String.format("%s://%s:%d", protocol, host, port);
	}

	public String buildUrlWithPath(String path) {
		return buildUrl() + path;
	}
}
