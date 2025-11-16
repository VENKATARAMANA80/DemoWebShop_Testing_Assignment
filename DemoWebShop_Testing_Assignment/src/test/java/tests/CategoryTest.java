package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;

import java.util.List;
import java.util.Random;

public class CategoryTest extends BaseTest {

    @Test(priority=1)
    public void detectAndSelectCategory() throws Exception {
        HomePage home = new HomePage(driver);
        home.open();
        List<String> cats = home.getCategoryNames();
        report.log("Detected categories: " + cats);
        Assert.assertTrue(cats.size() > 0, "No categories found");
        Random r = new Random();
        int idx = r.nextInt(cats.size());
        String selected = cats.get(idx);
        report.log("Selected category: " + selected);
        Thread.sleep(5000);
        home.clickCategoryByIndex(idx);
        CategoryPage cat = new CategoryPage(driver);
        Assert.assertTrue(cat.isLoaded(), "Category page did not load or has no products");
        int count = cat.getProductCount();
        report.log("Product count in selected category: " + count);
        Assert.assertTrue(count >= 4, "Expected at least 4 products, found: " + count);
        Assert.assertTrue(driver.getTitle().toLowerCase().contains(selected.toLowerCase()));
    }
}
