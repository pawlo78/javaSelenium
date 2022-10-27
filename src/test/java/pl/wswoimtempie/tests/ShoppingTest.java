package pl.wswoimtempie.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.wswoimtempie.models.Customer;
import pl.wswoimtempie.pages.HomePage;
import pl.wswoimtempie.pages.OrderDetailsPage;
import pl.wswoimtempie.utils.PropertiesLoader;
import pl.wswoimtempie.utils.SeleniumHelper;
import pl.wswoimtempie.utils.Storage;

import java.io.IOException;

public class ShoppingTest extends BaseTest {

    @Test
    public void shoppingOneProductOverProductPageTest() throws IOException {
        ExtentTest test = extentReports.createTest("Shopping One Product Over Product Page Test");
        String product1Name = PropertiesLoader.loadProperty("product1.name");
        String noProducts1 = PropertiesLoader.loadProperty("no.products1");
        Customer customer = new Customer();

        OrderDetailsPage orderDetailsPage = new HomePage(driver)
                .openShopPage(test)
                .openProductPage(product1Name, test)
                .addNoProductsToMainProductCart(noProducts1, test)
                .viewCart(test)
                .setTotalPrice(test)
                .openOrdersBillingDetails(test)
                .fillAddressDetails(customer, test);

        if (orderDetailsPage.getOrderNotice(test).getText().equals("Thank you. Your order has been received.")) {
            //order information
            test.log(Status.PASS, "Assert Shopping One Product Over Product Page Assert 1", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(orderDetailsPage.getOrderNotice(test).getText(), "Thank you. Your order has been received.");
        } else {
            //order information
            test.log(Status.FAIL, "Assert Shopping One Product Over Product Page Assert 1", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(orderDetailsPage.getOrderNotice(test).getText(), "Thank you. Your order has been received.");
        }

        if (Storage.getPriceDouble(orderDetailsPage.getTotalPrice(test).getText()).equals(Storage.priceTotalValue)) {
            //total price
            test.log(Status.PASS, "Assert Shopping One Product Over Product Page Assert 2", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(Storage.getPriceDouble(orderDetailsPage.getTotalPrice(test).getText()), Storage.priceTotalValue);
        } else {
            //total price
            test.log(Status.FAIL, "Assert Shopping One Product Over Product Page Assert 2", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(Storage.getPriceDouble(orderDetailsPage.getTotalPrice(test).getText()), Storage.priceTotalValue);
        }

    }

    @Test
    public void shoppingThreeProductOverProductPageTest() throws IOException {
        ExtentTest test = extentReports.createTest("Shopping Three Product Over Product Page Test");
        String product1Name = PropertiesLoader.loadProperty("product1.name");
        String noProducts1 = PropertiesLoader.loadProperty("no.products1");
        String noProducts2 = PropertiesLoader.loadProperty("no.products2");
        String noProducts3 = PropertiesLoader.loadProperty("no.products3");

        Customer customer = new Customer();

        OrderDetailsPage orderDetailsPage = new HomePage(driver)
                .openShopPage(test)
                .openProductPage(product1Name, test)
                .addNoProductsToMainProductCart(noProducts1, test)
                .addNoProductsToCart(noProducts2, 2, test)
                .addNoProductsToCart(noProducts3, 3, test)
                .viewCart(test)
                .setTotalPrice(test)
                .openOrdersBillingDetails(test)
                .fillAddressDetails(customer, test);

        if (orderDetailsPage.getOrderNotice(test).getText().equals("Thank you. Your order has been received.")) {
            //order information
            test.log(Status.PASS, "Assert Shopping Three Product Over Product Page Assert 1", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(orderDetailsPage.getOrderNotice(test).getText(), "Thank you. Your order has been received.");
        } else {
            //order information
            test.log(Status.FAIL, "Assert Shopping Three Product Over Product Page Assert 1", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(orderDetailsPage.getOrderNotice(test).getText(), "Thank you. Your order has been received.");
        }

        if (Storage.getPriceDouble(orderDetailsPage.getTotalPrice(test).getText()).equals(Storage.priceTotalValue)) {
            //total price
            test.log(Status.PASS, "Assert Shopping Three Product Over Product Page Assert 2", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(Storage.getPriceDouble(orderDetailsPage.getTotalPrice(test).getText()), Storage.priceTotalValue);
        } else {
            //total price
            test.log(Status.FAIL, "Assert Shopping Three Product Over Product Page Assert 2", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(Storage.getPriceDouble(orderDetailsPage.getTotalPrice(test).getText()), Storage.priceTotalValue);
        }

    }

    @Test
    public void shoppingThreeProductOverShopPageTest() throws IOException {
        ExtentTest test = extentReports.createTest("Shopping Three Product Over Shop Page Test");
        Customer customer = new Customer();

        OrderDetailsPage orderDetailsPage = new HomePage(driver)
                .openShopPage(test)
                .addThreeProductsToCart(test)
                .setTotalPrice(test)
                .openOrdersBillingDetails(test)
                .fillAddressDetails(customer, test);


        if (orderDetailsPage.getOrderNotice(test).getText().equals("Thank you. Your order has been received.")) {
            //order information
            test.log(Status.PASS, "Assert 1 Shopping Three Product Over Shop Page", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(orderDetailsPage.getOrderNotice(test).getText(), "Thank you. Your order has been received.");
        } else {
            //order information
            test.log(Status.FAIL, "Assert 1 Shopping Three Product Over Shop Page", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(orderDetailsPage.getOrderNotice(test).getText(), "Thank you. Your order has been received.");
        }

        if (Storage.getPriceDouble(orderDetailsPage.getTotalPrice(test).getText()).equals(Storage.priceTotalValue)) {
            //total price
            test.log(Status.PASS, "Assert 2 Shopping Three Product Over Shop Page", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(Storage.getPriceDouble(orderDetailsPage.getTotalPrice(test).getText()), Storage.priceTotalValue);
        } else {
            //total price
            test.log(Status.FAIL, "Assert 2 Shopping Three Product Over Shop Page", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(Storage.getPriceDouble(orderDetailsPage.getTotalPrice(test).getText()), Storage.priceTotalValue);
        }

    }
}