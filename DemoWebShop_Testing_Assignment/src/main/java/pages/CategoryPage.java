package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryPage extends BasePage {
    private By productItems = By.xpath("//div[@class='product-item']");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return !driver.findElements(productItems).isEmpty();
    }

    public int getProductCount() {
        return driver.findElements(productItems).size();
    }

    public List<WebElement> getProducts() {
        return driver.findElements(productItems);
    }

    public void clickProductByIndex(int idx) {
        List<WebElement> products = getProducts();
        products.get(idx).findElement(By.cssSelector("h2.product-title a")).click();
    }
}
