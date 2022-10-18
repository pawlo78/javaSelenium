package pl.wswoimtempie.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.wswoimtempie.models.Customer;
import pl.wswoimtempie.pages.*;
import pl.wswoimtempie.utils.PropertiesLoader;
import pl.wswoimtempie.utils.Storage;

import java.io.IOException;
import java.text.DecimalFormat;

public class ShoppingTest extends BaseTest{

    @Test
    public void shoppingOneProductOverProductPageTest() throws IOException, InterruptedException {
        String product1Name = PropertiesLoader.loadProperty("product1.name");
        String noProducts1 = PropertiesLoader.loadProperty("no.products1");
        Customer customer = new Customer();

        OrderDetailsPage orderDetailsPage = new HomePage(driver)
                .openShopPage()
                .openProductPage(product1Name)
                .addNoProductsToCart(noProducts1)
                .viewCart()
                .openOrdersBillingDetails()
                .fillAddressDetails(customer);

        //final price - static method in Storage
        Double totalPrice = Storage.getPriceDouble(Storage.priceValue);
        totalPrice = (totalPrice * Double.parseDouble(noProducts1));

        //product name
        Assert.assertEquals(orderDetailsPage.getProductName().getText(), product1Name+" × "+noProducts1);
        //order information
        Assert.assertEquals(orderDetailsPage.getOrderNotice().getText(), "Thank you. Your order has been received.");
        //total price
        Assert.assertEquals(Storage.getPriceDouble(orderDetailsPage.getTotalPrice().getText()), totalPrice);
    }
}
