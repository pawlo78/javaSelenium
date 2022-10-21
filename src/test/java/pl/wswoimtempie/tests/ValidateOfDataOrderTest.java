package pl.wswoimtempie.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.wswoimtempie.pages.AddressDetailsPage;
import pl.wswoimtempie.pages.HomePage;

import java.util.ArrayList;
import java.util.List;

public class ValidateOfDataOrderTest extends BaseTest {

    @Test(dataProvider = "data-provider")
    public void shoppingThreeProductOverShopPageTest(String fName, String lName, String country,
                                                     String addres, String zCode, String city,
                                                     String phone, String email) {

        AddressDetailsPage addressDetailsPage = new HomePage(driver)
                .openShopPage()
                .addOneProductToCart()
                .setTotalPrice()
                .openOrdersBillingDetails()
                .validateAddressDetails(fName, lName, country, addres, zCode, city, phone, email);

        List<String> errors = addressDetailsPage.getErrorList();
        List<String> nameErr = getNameErrors();

        switch (fName) {
            case "":
                boolean flag = false;
                for (int i = 0; i < nameErr.size(); i++) {
                    if (!nameErr.get(i).equals(errors.get(i))) {
                        flag = true;
                        System.out.println(errors.get(i));
                    }
                }
                Assert.assertTrue(flag == false);
                break;

            case "1":
                Assert.assertEquals(errors.get(0), "Billing Phone is not a valid phone number.");
                break;
            case "2":
                Assert.assertEquals(errors.get(0), "Invalid billing email address");
                break;
        }
    }

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{
                {"", "", "", "", "", "", "", ""},
                {"1", "QWE", "Poland", "qwe 2", "33-330", "zxc", "asd", "abc@abc.pl"},
                {"2", "QWE", "Poland", "qwe 2", "33-330", "zxc", "111111111", "abcabc.pl"}
        };
    }

    private List<String> getNameErrors() {
        List<String> error = new ArrayList<>();
        error.add("Billing First name is a required field.");
        error.add("Billing Last name is a required field.");
        error.add("Billing Street address is a required field.");
        error.add("Billing Town / City is a required field.");
        error.add("Billing Phone is a required field.");
        error.add("Billing Email address is a required field.");
        error.add("Billing Postcode / ZIP is not a valid postcode / ZIP.");
        return error;
    }
}