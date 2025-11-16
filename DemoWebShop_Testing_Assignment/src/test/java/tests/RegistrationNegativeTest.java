package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utils.CsvUtil;
import utils.ScreenshotUtil;

import java.util.Map;

public class RegistrationNegativeTest extends BaseTest {

    @Test(priority=3)
    public void negativeRegistrationMissingField() throws Exception {
        RegistrationPage r = new RegistrationPage(driver);
        r.open();

        Map<String,String> data = CsvUtil.readFirstRow("testdata.csv");
        String first = ""; 
        String last = data.getOrDefault("lastName", "Doe");
        String email = data.getOrDefault("email", "test@example.com").replace("%d","_neg");
        String pass = data.getOrDefault("password", "Password123!");

        r.register(first, last, email, pass, pass);
        String err = r.getErrorText();
        if (err == null || err.isEmpty()) {
            String path = ScreenshotUtil.take(driver, "registration_missing_firstname_failure");
            report.log("Registration negative test failed to show error. Screenshot: " + path);
            Assert.fail("Expected validation error but none shown");
        } else {
            report.log("Captured registration error: " + err);
            Assert.assertTrue(err.length() > 0);
        }
    }
}
