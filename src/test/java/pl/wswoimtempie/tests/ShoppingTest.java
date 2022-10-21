package pl.wswoimtempie.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.wswoimtempie.models.Customer;
import pl.wswoimtempie.pages.HomePage;
import pl.wswoimtempie.pages.OrderDetailsPage;
import pl.wswoimtempie.utils.PropertiesLoader;
import pl.wswoimtempie.utils.Storage;

import java.io.IOException;

public class ShoppingTest extends BaseTest {

    @Test
    public void shoppingOneProductOverProductPageTest() throws IOException {
        String product1Name = PropertiesLoader.loadProperty("product1.name");
        String noProducts1 = PropertiesLoader.loadProperty("no.products1");
        Customer customer = new Customer();

        OrderDetailsPage orderDetailsPage = new HomePage(driver)
                .openShopPage()
                .openProductPage(product1Name)
                .addNoProductsToMainProductCart(noProducts1)
                .viewCart()
                .setTotalPrice()
                .openOrdersBillingDetails()
                .fillAddressDetails(customer);

        SoftAssert softAssert = new SoftAssert();
        //product name
        softAssert.assertEquals(orderDetailsPage.getProductName().getText(), product1Name + "  × " + noProducts1);
        //order information
        softAssert.assertEquals(orderDetailsPage.getOrderNotice().getText(), "Thank you. Your order has been received.");
        //total price
        softAssert.assertEquals(Storage.getPriceDouble(orderDetailsPage.getTotalPrice().getText()), Storage.priceTotalValue);
    }

    @Test
    public void shoppingThreeProductOverProductPageTest() throws IOException {
        String product1Name = PropertiesLoader.loadProperty("product1.name");
        String noProducts1 = PropertiesLoader.loadProperty("no.products1");
        String noProducts2 = PropertiesLoader.loadProperty("no.products2");
        String noProducts3 = PropertiesLoader.loadProperty("no.products3");

        Customer customer = new Customer();

        OrderDetailsPage orderDetailsPage = new HomePage(driver)
                .openShopPage()
                .openProductPage(product1Name)
                .addNoProductsToMainProductCart(noProducts1)
                .addNoProductsToCart(noProducts2, 2)
                .addNoProductsToCart(noProducts3, 3)
                .viewCart()
                .setTotalPrice()
                .openOrdersBillingDetails()
                .fillAddressDetails(customer);

        SoftAssert softAssert = new SoftAssert();
        //order information
        softAssert.assertEquals(orderDetailsPage.getOrderNotice().getText(), "Thank you. Your order has been received.");
        //total price
        softAssert.assertEquals(Storage.getPriceDouble(orderDetailsPage.getTotalPrice().getText()), Storage.priceTotalValue);
    }

    @Test
    public void shoppingThreeProductOverShopPageTest() {

        Customer customer = new Customer();

        OrderDetailsPage orderDetailsPage = new HomePage(driver)
                .openShopPage()
                .addThreeProductsToCart()
                .setTotalPrice()
                .openOrdersBillingDetails()
                .fillAddressDetails(customer);

        SoftAssert softAssert = new SoftAssert();
        //order information
        softAssert.assertEquals(orderDetailsPage.getOrderNotice().getText(), "Thank you. Your order has been received.");
        //total price
        softAssert.assertEquals(Storage.getPriceDouble(orderDetailsPage.getTotalPrice().getText()), Storage.priceTotalValue);
    }
}