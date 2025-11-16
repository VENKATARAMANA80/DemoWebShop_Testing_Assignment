package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {
    private By productName = By.cssSelector("h2.product-title");
    private By price = By.xpath("//span[@class='price actual-price']");
    private By addToCartBtn = By.xpath("//input[@value='Add to cart']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getName() {
        return driver.findElement(productName).getText();
    }

    public String getPrice() {
        try {
            return driver.findElement(price).getText();
        } catch (Exception e) {
            return driver.findElement(By.cssSelector("span.price")).getText();
        }
    }

    public void addToCart() {
        driver.findElement(addToCartBtn).click();
    }
}
