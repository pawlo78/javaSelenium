package pl.wswoimtempie.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.wswoimtempie.pages.HomePage;
import pl.wswoimtempie.pages.ShopPage;
import pl.wswoimtempie.utils.Storage;

public class ProductSortTest extends BaseTest {

    @Test(dataProvider = "data-provider")
    public void shopSortProductsTest(String sortByname, String sortResults) {

        ShopPage shopPage = new HomePage(driver)
                .openShopPage()
                .setProductListAftersort(sortByname);

        Assert.assertEquals(Storage.stringOfSortProducts, sortResults);
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