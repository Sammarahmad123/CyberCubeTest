package enums;

public enum PropertyType {

    CONFIG("config.properties"),
    LOGIN_CREDENTIALS("login-credentials.properties");

    private final String property;

    PropertyType(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
