package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.AuthenticationPage;
import pages.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
public class ProductsTest extends BaseTest {
    private static final String VALID_USER_NAME = "standard_user";
    private static final String VALID_USER_PASSWORD = "secret_sauce";
    private static final int NUMBER_PRODUCTS_PER_PAGE = 6;
    private static final int ONE_CART_ITEM = 1;
    private ProductsPage productsPage;

    @BeforeEach
    public void login() {
        AuthenticationPage loginPage = new AuthenticationPage(driver);
        loginPage.login(VALID_USER_NAME, VALID_USER_PASSWORD);
        productsPage = new ProductsPage(driver);
    }

    @Test
    @Description("Testing product link image navigation")
    public void testProductLinkImageNavigation() {
        WebElement productImage = productsPage.getProductImage(0);
        productImage.click();
        assertTrue(driver.getCurrentUrl().contains("inventory-item"), "URL should contain 'inventory-item'");
    }

    @Test
    @Description("Testing add to cart functionality")
    public void testAddToCartButton() {
        WebElement addToCartButton = productsPage.getAddToCartButton(0);
        String initialButtonText = addToCartButton.getText();
        assertEquals("Add to cart", initialButtonText, "Button text should be 'Add to cart'");
        addToCartButton.click();

        WebElement cartBadge = productsPage.getCartBadge();
        assertEquals("1", cartBadge.getText(), "Cart badge should show 1 item");

        addToCartButton = productsPage.getAddToCartButton(0);
        assertEquals("Remove", addToCartButton.getText(), "Button text should change to 'Remove'");
        String buttonColor = cartBadge.getCssValue("background-color");
        assertEquals("rgba(226, 35, 26, 1)", buttonColor, "Button color should change to red");
    }

    @Test
    @Description("Testing product link navigation")
    public void testProductLinkNavigation() {
        WebElement productLink = productsPage.getProductLink(0);
        productLink.click();
        assertTrue(driver.getCurrentUrl().contains("inventory-item"), "URL should contain 'inventory-item'");

        driver.navigate().back();

        WebElement productImage = productsPage.getProductImage(0);
        productImage.click();
        assertTrue(
                driver.getCurrentUrl().contains("inventory-item"),
                "URL should contain 'inventory-item'"
        );
    }

    @Test
    @Description("Testing product count")
    public void testProductCount() {
        ProductsPage productsPage = new ProductsPage(driver);
        int productCount = productsPage.getProductCount();

        assertEquals(
                NUMBER_PRODUCTS_PER_PAGE, productCount,
                "Product count should be ${NUMBER_PRODUCTS_PER_PAG}"
        );
    }

    @Test
    @Description("Testing badge functionality")
    public void testBadgeShouldThrowNoSuchElementExceptionWhenCartEmpty() {
        assertThrows(NoSuchElementException.class,
                () -> {
                    productsPage.getCartBadge().getText();
                });
    }

    @Test
    @Description("Testing add product to cart functionality")
    public void testAddProductToCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        if (productsPage.getProductCount() > 0) {
            productsPage.addProductToCartByIndex(0);
        }
        int cartItemCount = productsPage.getCartItemCount();

        assertEquals(ONE_CART_ITEM, cartItemCount, "Cart item count should be ${ONE_CART_ITEM}");
    }

    @Test
    @Description("Testing cart icon navigation")
    public void testCartIconNavigation() {
        productsPage.clickCartIcon();
        assertEquals(
                "https://www.saucedemo.com/cart.html",
                driver.getCurrentUrl(),
                "URL should be Cart page"
        );
    }

    @Test
    @Description("Testing filter dropdown functionality")
    public void testFilterDropdown() {
        productsPage.openFilterDropdown();
        productsPage.selectFilterOption("Price (low to high)");

        String selectedOption = productsPage.getFilterDropdown().getAttribute("value");
        assertEquals("lohi", selectedOption, "Selected option should be 'Price (low to high)'");
    }

    @Test
    @Description("Testing product name hover")
    public void testHoverOverProductTitle() {
        WebElement productLink = productsPage.getProductLink(0);

        Actions actions = new Actions(driver);
        actions.moveToElement(productLink).perform();

        String cursorStyle = productLink.getCssValue("cursor");
        assertEquals("pointer", cursorStyle, "Cursor should change to pointer");

        String color = productLink.getCssValue("color");
        assertEquals("rgba(61, 220, 145, 1)", color, "Text color should change to green");
    }

    @Test
    @Description("Testing Facebook icon functionality")
    public void testFacebookIcon() {
        String originalWindow = driver.getWindowHandle();
        productsPage.clickFacebookIcon();

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
        }

        assertEquals(
                "https://www.facebook.com/saucelabs",
                driver.getCurrentUrl(),
                "URL should be Facebook page"
        );

        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    @Description("Testing the presence of the burger menu icon")
    public void testBurgerMenuIconPresence() {
        assertTrue(
                productsPage.isBurgerMenuIconPresent(),
                "Burger menu icon should be present on the page"
        );
    }
}
