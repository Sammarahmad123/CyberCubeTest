package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.PropertyType;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import tools.PropertyReader;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tools.Logger.report;

public class LoginPage {

    private ProductPage productPage = new ProductPage();
    private SelenideElement usernameField = $(By.id("user-name"));
    private SelenideElement passwordField = $(By.id("password"));
    private SelenideElement loginButton = $(By.id("login-button"));
    private SelenideElement errorMessage = $(By.xpath("//h3[@data-test=\"error\"]"));


    @Attachment(value = "Sauce Labs URL")
    @Step(value = "Open Sauce Labs login page")
    public void openSauceLabAppAndAttemptLogin(String username, String password) {
        report("Open Sauce Labs login page ");
        open(PropertyReader.getProperty(PropertyType.CONFIG, "baseUrl"));
        login(username, password);

    }

    public void login(String username, String password) {
        usernameField.shouldBe(Condition.visible, Duration.ofMillis(10000)).setValue(username);
        passwordField.shouldBe(Condition.visible, Duration.ofMillis(10000)).setValue(password);
        loginButton.shouldBe(Condition.visible, Duration.ofMillis(10000)).click();
    }

    public String getErrorMessage() {
        return errorMessage.shouldBe(Condition.visible, Duration.ofMillis(2500)).getText();
    }

    public boolean isLoginTakingLonger(String username, String password) {
        long startTime = System.currentTimeMillis();
        long MAX_LOGIN_DURATION = 200;
        productPage.isOnProductPage();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        return duration < MAX_LOGIN_DURATION;
    }


}
