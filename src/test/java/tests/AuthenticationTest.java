package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.AuthenticationPage;
import utils.CustomWebDriverManager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationTest extends BaseTest {
    private static final String WEB_LINK = "https://www.saucedemo.com/";
    private static final String VALID_USER_NAME = "standard_user";
    private static final String VALID_USER_PASSWORD = "secret_sauce";
    private static final String VALID_LOGIN_MASSAGE = "Login should be successful";
    private static final String INVALID_USER_NAME = "invalid_user";
    private static final String INVALID_USER_PASSWORD = "invalid_password";
    private static final String INVALID_LOGIN_MASSAGE = "Error message should not be displayed";
    private static final String PART_PRODUCTS_URL = "inventory";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = CustomWebDriverManager.getDriver();
        driver.get(WEB_LINK);
    }

    @Test
    public void testValidLogin() {
        AuthenticationPage loginPage = new AuthenticationPage(driver);
        loginPage.login(VALID_USER_NAME, VALID_USER_PASSWORD);

        assertTrue(loginPage.isLoginSuccessful(), VALID_LOGIN_MASSAGE);
    }

    @Test
    public void testInvalidLogin() {
        AuthenticationPage loginPage = new AuthenticationPage(driver);
        loginPage.login(INVALID_USER_NAME, INVALID_USER_PASSWORD);

        assertFalse(loginPage.isLoginSuccessful(), VALID_LOGIN_MASSAGE);
        assertTrue(loginPage.isErrorMessageDisplayed(), INVALID_LOGIN_MASSAGE);
    }

    @Test
    public void testValidProductPageUrl() {
        AuthenticationPage loginPage = new AuthenticationPage(driver);
        loginPage.login(VALID_USER_NAME, VALID_USER_PASSWORD);

        assertTrue(driver.getCurrentUrl().contains(PART_PRODUCTS_URL));
    }

    @AfterEach
    public void tearDown() {
        CustomWebDriverManager.quitDriver();
    }
}

