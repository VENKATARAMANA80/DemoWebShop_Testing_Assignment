package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {
	private By Gender = By.xpath("//input[@id='gender-male']");
    private By firstName = By.xpath("//input[@id='FirstName']");
    private By lastName = By.xpath("//input[@id='LastName']");
    private By email = By.xpath("//input[@id='Email']");
    private By password = By.xpath("//input[@id='Password']");
    private By confirmPassword = By.xpath("//input[@id='ConfirmPassword']");
    private By registerBtn = By.xpath("//input[@id='register-button']");
    private By summaryError = By.cssSelector("span[for='FirstName']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(baseUrl + "/register");
    }

    public void register(String f, String l, String e, String p, String cp) {
    	driver.findElement(Gender).click();
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(f);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(l);
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(e);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(p);
        driver.findElement(confirmPassword).clear();
        driver.findElement(confirmPassword).sendKeys(cp);
        driver.findElement(registerBtn).click();
    }

    public String getErrorText() {
        try {
            return driver.findElement(summaryError).getText();
        } catch (Exception ex) {
            return "";
        }
    }
}
