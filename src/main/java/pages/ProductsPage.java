package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage {
    @FindBy(className = "inventory_item")
    private List<WebElement> productList;

    @FindBy(css = ".product_sort_container")
    private WebElement filterDropdown;

    @FindBy(css = ".social_facebook a")
    private WebElement facebookIcon;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = ".bm-burger-button")
    private WebElement burgerMenuIcon;


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public int getProductCount() {
        return productList.size();
    }

    public boolean isCartIcon() {
        return cartIcon.isDisplayed();
    }

    public void addProductToCartByIndex(int index) {
        productList.get(index).findElement(By.cssSelector(".btn_inventory")).click();
    }

    public WebElement getCartBadge() {
        return cartBadge;
    }

    public int getCartItemCount() {
        return Integer.parseInt(cartBadge.getText());
    }

    public WebElement getAddToCartButton(int index) {
        return productList.get(index).findElement(By.cssSelector(".btn_inventory"));
    }

    public WebElement getProductLink(int index) {
        return productList.get(index).findElement(By.cssSelector(".inventory_item_name"));
    }

    public WebElement getProductImage(int index) {
        return productList.get(index).findElement(By.cssSelector(".inventory_item_img"));
    }

    public void hoverOverProductTitle(int index) {
        WebElement productTitle = productList.get(index).findElement(By.cssSelector(".inventory_item_name"));
        Actions actions = new Actions(driver);
        actions.moveToElement(productTitle).perform();
    }

    public WebElement getFilterDropdown() {
        return filterDropdown;
    }

    public void openFilterDropdown() {
        filterDropdown.click();
    }

    public void selectFilterOption(String option) {
        filterDropdown.findElement(By.xpath("//option[text()='" + option + "']")).click();
    }

    public void clickFacebookIcon() {
        facebookIcon.click();
    }

    public void clickCartIcon() {
        cartIcon.click();
    }

    public boolean isBurgerMenuIconPresent() {
        return burgerMenuIcon.isDisplayed();
    }
}
