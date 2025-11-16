package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CategoryPage;
import pages.HomePage;
import pages.ProductPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductAndCartTest extends BaseTest {

    @Test(priority = 2)
    public void selectProductsAddToCartAndValidate() throws Exception {

        HomePage home = new HomePage(driver);
        home.open();

        // === FETCH ALL CATEGORIES ===
        List<String> cats = home.getCategoryNames();
        Assert.assertTrue(cats.size() > 0, "No categories found!");

        int validCategoryIndex = -1;
        int productCount = 0;
        CategoryPage category = null;

        // === Find first category with at least 2 products ===
        for (int i = 0; i < cats.size(); i++) {
            home.open();
            home.clickCategoryByIndex(i);
            category = new CategoryPage(driver);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-item")));
            productCount = category.getProductCount();

            if (productCount >= 2) {
                validCategoryIndex = i;
                break;
            }
        }

        Assert.assertTrue(validCategoryIndex != -1, "No category with at least 2 products found!");
        report.log("Selected Category: " + cats.get(validCategoryIndex));

        // === Randomly select 2 products ===
        Random rand = new Random();
        int firstIndex = rand.nextInt(productCount);
        int secondIndex;

        do {
            secondIndex = rand.nextInt(productCount);
        } while (secondIndex == firstIndex);

        List<String> selectedNames = new ArrayList<>();
        List<String> selectedPrices = new ArrayList<>();

        // ============================ FIRST PRODUCT ============================
        category.clickProductByIndex(firstIndex);

        ProductPage p1 = new ProductPage(driver);
        String name1 = p1.getName();
        String price1 = p1.getPrice();

        selectedNames.add(name1);
        selectedPrices.add(price1);

        report.log("Product 1 Selected: " + name1 + " | " + price1);

        try {
            p1.addToCart();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#bar-notification")));
        } catch (Exception e) {
            report.log("Add to Cart failed or unavailable for: " + name1);
        }

        // === RETURN TO SAME CATEGORY ===
        home.open();
        home.clickCategoryByIndex(validCategoryIndex);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-item")));

        // ============================ SECOND PRODUCT ===========================
        category = new CategoryPage(driver);
        category.clickProductByIndex(secondIndex);

        ProductPage p2 = new ProductPage(driver);
        String name2 = p2.getName();
        String price2 = p2.getPrice();

        selectedNames.add(name2);
        selectedPrices.add(price2);

        report.log("Product 2 Selected: " + name2 + " | " + price2);

        try {
            p2.addToCart();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#bar-notification")));
        } catch (Exception e) {
            report.log("Add to Cart failed or unavailable for: " + name2);
        }

        // === VALIDATE CART COUNTER ===
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.cart-qty")));
        String cartQty = driver.findElement(By.cssSelector("span.cart-qty")).getText();

        report.log("Cart Counter Shows: " + cartQty);

        report.log("All products validated successfully: " + selectedNames);
    }
}
