package pl.wswoimtempie.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.wswoimtempie.pages.CartPage;
import pl.wswoimtempie.pages.HomePage;

public class AddCouponTest extends BaseTest {

    @Test(dataProvider = "data-provider")
    public void addCouponshopSortProductsTest(String coupon) {

        CartPage orderDetailsPage = new HomePage(driver)
                .openShopPage()
                .addOneProductToCart()
                .addCoupon(coupon);

        switch (coupon) {
            case "12345":
                Assert.assertEquals(orderDetailsPage.getAlertCoupon().getText(), "Coupon \"12345\" does not exist!");
                break;
            case "":
                Assert.assertEquals(orderDetailsPage.getAlertCoupon().getText(), "Please enter a coupon code.");
                break;
        }
    }

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{"12345"}, {""}
        };
    }


}