package ui.functional;

import com.codeborne.selenide.logevents.SelenideLogger;
import enums.PropertyType;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import tools.PropertyReader;

import java.lang.reflect.Method;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

public class LoginTest {
    private LoginPage loginPage;
    private ProductPage productPage;

    @Step("setUp")
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        loginPage = new LoginPage();
        productPage = new ProductPage();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(false)
        );
    }

    @DataProvider(name = "userData", parallel = true)
    public Object[][] userData() {
        return new Object[][]{
                {PropertyReader.getProperty(PropertyType.LOGIN_CREDENTIALS, "user1"),
                        PropertyReader.getProperty(PropertyType.LOGIN_CREDENTIALS, "common_password"), "success"},
                {PropertyReader.getProperty(PropertyType.LOGIN_CREDENTIALS, "user2"),
                        PropertyReader.getProperty(PropertyType.LOGIN_CREDENTIALS, "common_password"), "locked_out"},
                {PropertyReader.getProperty(PropertyType.LOGIN_CREDENTIALS, "user3"),
                        PropertyReader.getProperty(PropertyType.LOGIN_CREDENTIALS, "common_password"), "success"},
                {PropertyReader.getProperty(PropertyType.LOGIN_CREDENTIALS, "user4"),
                        PropertyReader.getProperty(PropertyType.LOGIN_CREDENTIALS, "common_password"), "performance_issue"},
                {PropertyReader.getProperty(PropertyType.LOGIN_CREDENTIALS, "user5"),
                        PropertyReader.getProperty(PropertyType.LOGIN_CREDENTIALS, "common_password"), "success"},
                {PropertyReader.getProperty(PropertyType.LOGIN_CREDENTIALS, "user6"),
                        PropertyReader.getProperty(PropertyType.LOGIN_CREDENTIALS, "common_password"), "success"},
        };
    }

    @Test(dataProvider = "userData")
    public void login_test(String username, String password, String expectedOutcome) {
        loginPage.openSauceLabAppAndAttemptLogin(username, password);
        switch (expectedOutcome) {
            case "success":
                assertTrue("User should be on products page after successful login.", productPage.isOnProductPage());
                break;
            case "locked_out":
                String lockedOutMessage = loginPage.getErrorMessage();
                assertTrue("Locked out user should see the correct error message.",
                        lockedOutMessage.contains("Epic sadface: Sorry, this user has been locked out."));
                break;
            case "performance_issue":
                boolean isLongerLogin = loginPage.isLoginTakingLonger(username, password);
                assertTrue("The login should take longer for performance_glitch_user.", isLongerLogin);
                break;
            default:
                fail("Unexpected outcome type: " + expectedOutcome);
        }

    }


}
