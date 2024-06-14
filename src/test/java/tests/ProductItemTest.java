package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import pages.AuthenticationPage;
import pages.ProductItemPage;
import pages.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
public class ProductItemTest extends BaseTest {
    private static final String VALID_USER_NAME = "standard_user";
    private static final String VALID_USER_PASSWORD = "secret_sauce";
    private static final String VALID_LOGIN_MESSAGE = "Login should be successful";
    private ProductsPage productsPage;

    @BeforeEach
    public void login() {
        AuthenticationPage loginPage = new AuthenticationPage(driver);
        loginPage.login(VALID_USER_NAME, VALID_USER_PASSWORD);
        assertTrue(loginPage.isLoginSuccessful(), VALID_LOGIN_MESSAGE);
        productsPage = new ProductsPage(driver);
        WebElement productLink = productsPage.getProductLink(0);
        productLink.click();
    }

    @Test
    @Description("Testing product item link")
    public void testProductItemLink() {
        assertTrue(driver.getCurrentUrl().contains("inventory-item"), "URL should contain 'inventory-item'");
    }

    @Test
    @Description("Testing product title navigation")
    public void testProductTitleNavigationAndBack() {
        ProductItemPage productItemPage = new ProductItemPage(driver);
        assertTrue(productItemPage.isOnProductItemPage(), "Should be on product item page");

        productItemPage.goBackToProducts();
        assertFalse(
                driver.getCurrentUrl().contains("inventory-item"),
                "URL should no longer contain 'inventory-item'"
        );
    }
}
