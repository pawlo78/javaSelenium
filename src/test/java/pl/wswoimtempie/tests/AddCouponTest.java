package pl.wswoimtempie.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.wswoimtempie.pages.CartPage;
import pl.wswoimtempie.pages.HomePage;
import pl.wswoimtempie.utils.SeleniumHelper;

import java.io.IOException;

public class AddCouponTest extends BaseTest {

    @Test(dataProvider = "data-provider")
    public void addCouponShopTest(String coupon) throws IOException {
        ExtentTest test = extentReports.createTest("Add coupon Test - Data: " + coupon);
        CartPage orderDetailsPage = new HomePage(driver)
                .openShopPage(test)
                .addOneProductToCart(test)
                .addCoupon(coupon, test);

        switch (coupon) {
            case "12345":
                if (orderDetailsPage.getAlertCoupon(test).getText().equals("Coupon \"12345\" does not exist!")) {
                    test.log(Status.PASS, "Assert Add Coupon Test " + coupon, SeleniumHelper.getScreenshot(driver));
                    Assert.assertEquals(orderDetailsPage.getAlertCoupon(test).getText(), "Coupon \"12345\" does not exist!");
                } else {
                    test.log(Status.FAIL, "Assert Add Coupon Test " + coupon, SeleniumHelper.getScreenshot(driver));
                    Assert.assertEquals(orderDetailsPage.getAlertCoupon(test).getText(), "Coupon \"12345\" does not exist!");
                }
                break;
            case "":
                if (orderDetailsPage.getAlertCoupon(test).getText().equals("Please enter a coupon code.")) {
                    test.log(Status.PASS, "Assert Add Coupon Test " + coupon, SeleniumHelper.getScreenshot(driver));
                    Assert.assertEquals(orderDetailsPage.getAlertCoupon(test).getText(), "Please enter a coupon code.");
                } else {
                    test.log(Status.FAIL, "Assert Add Coupon Test " + coupon, SeleniumHelper.getScreenshot(driver));
                    Assert.assertEquals(orderDetailsPage.getAlertCoupon(test).getText(), "Please enter a coupon code.");
                }
                break;
        }
    }

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{"12345"}, {""}
        };
    }


}