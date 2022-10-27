package pl.wswoimtempie.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.wswoimtempie.pages.AddressDetailsPage;
import pl.wswoimtempie.pages.HomePage;
import pl.wswoimtempie.utils.SeleniumHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValidateOfDataOrderTest extends BaseTest {

    @Test(dataProvider = "data-provider")
    public void shoppingThreeProductOverShopPageTest(String fName, String lName, String country,
                                                     String addres, String zCode, String city,
                                                     String phone, String email) throws IOException {

        ExtentTest test = extentReports.createTest("Shopping Three Prod Over Shop Page Test - Data: " + fName);
        AddressDetailsPage addressDetailsPage = new HomePage(driver)
                .openShopPage(test)
                .addOneProductToCart(test)
                .setTotalPrice(test)
                .openOrdersBillingDetails(test)
                .validateAddressDetails(fName, lName, country, addres, zCode, city, phone, email, test);

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

                if (flag == false) {
                    test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
                    Assert.assertTrue(flag == false);
                } else {
                    test.log(Status.FAIL, "Assertions failed", SeleniumHelper.getScreenshot(driver));
                    Assert.assertTrue(flag == false);
                }
                break;

            case "1":
                if ("Billing Phone is not a valid phone number.".equals(errors.get(0))) {
                    test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
                    Assert.assertEquals(errors.get(0), "Billing Phone is not a valid phone number.");
                } else {
                    test.log(Status.FAIL, "Assertions failed", SeleniumHelper.getScreenshot(driver));
                    Assert.assertEquals(errors.get(0), "Billing Phone is not a valid phone number.");
                }
                break;

            case "2":
                if ("Invalid billing email address".equals(errors.get(0))) {
                    System.out.println(errors.get(0));

                    test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
                    Assert.assertEquals(errors.get(0), "Invalid billing email address");
                } else {
                    test.log(Status.FAIL, "Assertions failed", SeleniumHelper.getScreenshot(driver));
                    Assert.assertEquals(errors.get(0), "Invalid billing email address");
                }
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