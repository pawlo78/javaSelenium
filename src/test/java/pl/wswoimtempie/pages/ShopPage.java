package pl.wswoimtempie.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pl.wswoimtempie.utils.SeleniumHelper;
import pl.wswoimtempie.utils.Storage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ShopPage {

    private WebDriver driver;

    public ShopPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@data-product_id = '27']")
    private WebElement addProductId27;

    @FindBy(xpath = "//a[@data-product_id = '29']")
    private WebElement addProductId29;

    @FindBy(xpath = "//a[@data-product_id = '8']")
    private WebElement addProductId8;

    @FindBy(xpath = "//a[contains(@class, 'added') and @data-product_id = '27']")
    private WebElement visibleAfterAddToCartProductId27;

    @FindBy(xpath = "//a[contains(@class, 'added') and @data-product_id = '29']")
    private WebElement visibleAfterAddToCartProductId29;

    @FindBy(xpath = "//a[contains(@class, 'added') and @data-product_id = '8']")
    private WebElement visibleAfterAddToCartProductId8;

    @FindBy(xpath = "//a[@data-toggle = 'czr-dropdown']")
    private WebElement shoppingCart;

    @FindBy(xpath = "//ul[contains(@class, 'products')]//h2")
    private List<WebElement> products;

    @FindBy(xpath = "//select[@name = 'orderby']")
    private WebElement sortBy;


    public CartPage addThreeProductsToCart(ExtentTest test) throws IOException {
        try {
            addProductId8.click();
            SeleniumHelper.waitForElementToBeVisible(driver, visibleAfterAddToCartProductId8);
            addProductId27.click();
            SeleniumHelper.waitForElementToBeVisible(driver, visibleAfterAddToCartProductId27);
            addProductId29.click();
            test.log(Status.INFO, "Add three prod done");
            SeleniumHelper.waitForElementToBeVisible(driver, visibleAfterAddToCartProductId29);
            Actions actions = new Actions(driver);
            actions.moveToElement(shoppingCart).perform();
            shoppingCart.click();
            test.log(Status.INFO, "Shopping cart click");
            test.log(Status.PASS, "Add Three Products To Cart", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Add Three Products To Cart", SeleniumHelper.getScreenshot(driver));
        }
        return new CartPage(driver);
    }

    public CartPage addOneProductToCart(ExtentTest test) throws IOException {
        try {
            addProductId8.click();
            test.log(Status.INFO, "Add product done");
            SeleniumHelper.waitForElementToBeVisible(driver, visibleAfterAddToCartProductId8);
            Actions actions = new Actions(driver);
            actions.moveToElement(shoppingCart).perform();
            shoppingCart.click();
            test.log(Status.INFO, "Shopping cart pressed");
            test.log(Status.PASS, "Add One Products To Cart", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Add One Products To Cart", SeleniumHelper.getScreenshot(driver));
        }
        return new CartPage(driver);
    }

    public ShopPage setProductListAfterSort(String sortByname, ExtentTest test) throws IOException {
        try {
            Select productSelect = new Select(sortBy);
            productSelect.selectByValue(sortByname);
            test.log(Status.INFO, "Select product by: " + sortByname);
            Actions actions = new Actions(driver);
            actions.moveToElement(sortBy).click();
            test.log(Status.INFO, "Click to sorted element");

            List<String> productsList = products.stream().map(WebElement::getText).collect(Collectors.toList());

            String wynik = "";
            for (String x : productsList) {
                wynik = wynik + x;
            }

            Storage.stringOfSortProducts = wynik;
            test.log(Status.INFO, "Save data to storage");
            test.log(Status.PASS, "Set Product List After Sort", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Set Product List After Sort", SeleniumHelper.getScreenshot(driver));
        }
        return this;
    }

    public ProductPage openProductPage(String title, ExtentTest test) throws IOException {
        try {
            driver.findElement(By.xpath("//h2[text() = '" + title + "']"))
                    .click();
            test.log(Status.PASS, "Open product page", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Open product page", SeleniumHelper.getScreenshot(driver));
        }
        return new ProductPage(driver);
    }
}
