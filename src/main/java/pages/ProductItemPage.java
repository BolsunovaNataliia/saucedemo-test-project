package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductItemPage extends BasePage {

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    public ProductItemPage(WebDriver driver) {
        super(driver);
    }

    public void goBackToProducts() {
        backToProductsButton.click();
    }

    public boolean isOnProductItemPage() {
        return driver.getCurrentUrl().contains("inventory-item");
    }
}

