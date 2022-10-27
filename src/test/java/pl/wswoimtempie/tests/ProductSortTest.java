package pl.wswoimtempie.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.wswoimtempie.pages.HomePage;
import pl.wswoimtempie.pages.ShopPage;
import pl.wswoimtempie.utils.SeleniumHelper;
import pl.wswoimtempie.utils.Storage;

import java.io.IOException;

public class ProductSortTest extends BaseTest {

    @Test(dataProvider = "data-provider")
    public void shopSortProductsTest(String sortByname, String sortResults) throws IOException {
        ExtentTest test = extentReports.createTest("Shop sort product Test - data: " + sortByname);

        ShopPage shopPage = new HomePage(driver)
                .openShopPage(test)
                .setProductListAfterSort(sortByname, test);


        if (Storage.stringOfSortProducts.equals(sortResults)) {
            test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(Storage.stringOfSortProducts, sortResults);
        } else {
            test.log(Status.FAIL, "Assertions failed", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(Storage.stringOfSortProducts, sortResults);
        }
    }

    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{
                {"popularity", "BDD CucumberGIT basicsJava Selenium WebDriver"},
                {"rating", "Java Selenium WebDriverGIT basicsBDD Cucumber"},
                {"date", "BDD CucumberGIT basicsJava Selenium WebDriver"},
                {"price", "Java Selenium WebDriverGIT basicsBDD Cucumber"},
                {"price-desc", "BDD CucumberGIT basicsJava Selenium WebDriver"}
        };
    }

}