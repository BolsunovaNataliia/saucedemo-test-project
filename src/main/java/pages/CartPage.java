package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    @FindBy(className = "cart_item")
    private List<WebElement> cartItemList;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = ".cart_button")
    private WebElement removeButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isItemInCart() {
        return !cartItemList.isEmpty();
    }

    public void removeItemFromCart() {
        removeButton.click();
    }

    public WebElement getRemoveButton() {
        return removeButton;
    }

    public boolean isRemoveButtonDisplayed() {
        return removeButton.isDisplayed();
    }

    public int getCartItemCount() {
        return Integer.parseInt(cartBadge.getText());
    }

    public WebElement getCartItem(int index) {
        return cartItemList.get(index);
    }

    public boolean isCheckoutButtonDisplayed() {
        return checkoutButton.isDisplayed();
    }

    public boolean isContinueShoppingButtonDisplayed() {
        return continueShoppingButton.isDisplayed();
    }
}
