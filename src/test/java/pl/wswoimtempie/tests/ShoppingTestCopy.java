package pl.wswoimtempie.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.wswoimtempie.models.Customer;
import pl.wswoimtempie.pages.HomePage;
import pl.wswoimtempie.pages.OrderDetailsPage;
import pl.wswoimtempie.utils.PropertiesLoader;
import pl.wswoimtempie.utils.Storage;

import java.io.IOException;

public class ShoppingTestCopy extends BaseTest{

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

        //pobranie ceny ksiązki
        System.out.println(Storage.getPriceDouble(Storage.priceValue));


        Assert.assertEquals(orderDetailsPage.getProductName().getText(), product1Name+"  × "+noProducts1);
        Assert.assertEquals(orderDetailsPage.getOrderNotice().getText(), "Thank you. Your order has been received.");
        Assert.assertEquals(Storage.priceValue, "9,99 zł");
    }
}
