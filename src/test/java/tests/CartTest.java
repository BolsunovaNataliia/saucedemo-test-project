package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pages.AuthenticationPage;
import pages.CartPage;
import pages.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {
    private static final String VALID_USER_NAME = "standard_user";
    private static final String VALID_USER_PASSWORD = "secret_sauce";

    private ProductsPage productsPage;
    private CartPage cartPage;

    @BeforeEach
    public void login() {
        AuthenticationPage loginPage = new AuthenticationPage(driver);
        loginPage.login(VALID_USER_NAME, VALID_USER_PASSWORD);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
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
    public void testAddToCartAndCheckCart() {
        productsPage.addProductToCartByIndex(0);
        productsPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isItemInCart(), "First item should be in the cart");

        assertTrue(cartPage.isRemoveButtonDisplayed(), "Remove button should be displayed");
        String buttonColor = cartPage.getRemoveButton().getCssValue("background-color");
        assertEquals("rgba(255, 255, 255, 1)", buttonColor, "Remove button should be red");

        assertTrue(cartPage.isContinueShoppingButtonDisplayed(), "Continue Shopping button should be displayed");

        assertEquals(1, cartPage.getCartItemCount(), "Cart badge should show 1 item");
        cartPage.removeItemFromCart();
        Throwable throwable = assertThrows(Throwable.class, () -> {
            cartPage.getCartItemCount();
        });
        assertEquals(NoSuchElementException.class, throwable.getClass());
    }

    @Test
    public void testBadgeShouldThrowNoSuchElementExceptionWhenCartEmpty() {
        productsPage.addProductToCartByIndex(0);
        productsPage.clickCartIcon();
        CartPage cartPage = new CartPage(driver);
        while (cartPage.isItemInCart()) {
            cartPage.removeItemFromCart();
        }

        assertThrows(
                NoSuchElementException.class,
                () -> {
                    cartPage.getCartItemCount();
                });
    }

    @Test
    public void testCartIconNavigation() {
        productsPage.clickCartIcon();
        assertEquals(
                "https://www.saucedemo.com/cart.html",
                driver.getCurrentUrl(),
                "URL should be Cart page");

        CartPage cartPage = new CartPage(driver);
        assertTrue(cartPage.isCheckoutButtonDisplayed(), "Checkout button should be displayed");
        assertTrue(
                cartPage.isContinueShoppingButtonDisplayed(),
                "Continue Shopping button should be displayed"
        );
    }
}
