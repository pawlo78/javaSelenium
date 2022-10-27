package pl.wswoimtempie.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.wswoimtempie.utils.SeleniumHelper;

import java.io.IOException;

public class OrderDetailsPage {

    private WebDriver driver;

    public OrderDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//p[contains(text(), 'has been received')]")
    private WebElement orderNotice;

    @FindBy(xpath = "//td[contains(@class, 'product-name')]")
    private WebElement productName;

    @FindBy(xpath = "//li[contains(@class, 'total')]//span[contains(@class, 'amount')]")
    private WebElement totalPrice;

    public WebElement getOrderNotice(ExtentTest test) throws IOException {
        try {
            SeleniumHelper.waitForElementToBeVisible(driver, orderNotice);
            test.log(Status.PASS, "Get Order Notice", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Get Order Notice", SeleniumHelper.getScreenshot(driver));
        }
        return orderNotice;
    }

    public WebElement getProductName(ExtentTest test) throws IOException {
        try {
            SeleniumHelper.waitForElementToBeVisible(driver, productName);
            test.log(Status.PASS, "Get Product Name", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Get Product Name", SeleniumHelper.getScreenshot(driver));
        }
        return productName;
    }

    public WebElement getTotalPrice(ExtentTest test) throws IOException {
        try {
            SeleniumHelper.waitForElementToBeVisible(driver, totalPrice);
            test.log(Status.PASS, "Get Total Price", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Get Total Price", SeleniumHelper.getScreenshot(driver));
        }
        return totalPrice;
    }
}
