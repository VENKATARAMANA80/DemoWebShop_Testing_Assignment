package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    private By mainCategories = By.xpath("//ul[@class='top-menu']//a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(baseUrl);
    }

    public List<String> getCategoryNames() {
        List<WebElement> items = driver.findElements(mainCategories);
        return items.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickCategoryByIndex(int idx) {
        driver.findElements(mainCategories).get(idx).click();
    }
}
