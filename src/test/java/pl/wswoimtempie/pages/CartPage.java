package pl.wswoimtempie.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.wswoimtempie.utils.SeleniumHelper;
import pl.wswoimtempie.utils.Storage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[contains(text(), 'Proceed to checkout')]")
    private WebElement proceedToCheckout;

    @FindBy(xpath = "//td[@data-title = 'Price']//span[contains(@class, 'amount')]")
    private WebElement priceMainProductLabel;

    @FindBy(xpath = "//tbody//td[@data-title = 'Price']//span[contains(@class, 'amount')]")
    private List<WebElement> prices;

    @FindBy(xpath = "//td[@data-title = 'Quantity']//input")
    private List<WebElement> noProducts;

    @FindBy(id = "coupon_code")
    private WebElement couponInput;

    @FindBy(xpath = "//button[@name = 'apply_coupon']")
    private WebElement couponApplyButton;

    @FindBy(xpath = "//ul[@role = 'alert']//li")
    private WebElement alertCouponLabel;


    public CartPage addCoupon(String couponValue, ExtentTest test) throws IOException {
        try {
            couponInput.sendKeys(couponValue);
            test.log(Status.INFO, "Add code done");
            couponApplyButton.click();
            test.log(Status.INFO, "Coupon Apply Button done");
            test.log(Status.PASS, "Add coupon", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Add coupon", SeleniumHelper.getScreenshot(driver));
        }
        return this;
    }

    public WebElement getAlertCoupon(ExtentTest test) throws IOException {
        try {
            SeleniumHelper.waitForElementToBeVisible(driver, alertCouponLabel);
            test.log(Status.PASS, "Get Alert Coupon", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Get Alert Coupon", SeleniumHelper.getScreenshot(driver));
        }
        return alertCouponLabel;
    }

    public CartPage setTotalPrice(ExtentTest test) throws IOException {

        try {
            SeleniumHelper.waitForNotEmptyList(driver, By.xpath("//tbody//td[@data-title = 'Price']//span[contains(@class, 'amount')]"));
            List<String> priceList = prices.stream().map(WebElement::getText).collect(Collectors.toList());
            test.log(Status.INFO, "Get Price done");
            SeleniumHelper.waitForNotEmptyList(driver, By.xpath("//td[@data-title = 'Quantity']//input"));
            List<String> noProductList = noProducts.stream().map(el -> el.getAttribute("value")).collect(Collectors.toList());
            test.log(Status.INFO, "Get noProduct done");

            Double wynik = 0.0;
            for (int i = 0; i < priceList.size(); i++) {
                wynik += Storage.getPriceDouble(priceList.get(i)) * Integer.parseInt(noProductList.get(i));
            }
            Storage.priceTotalValue = wynik;
            test.log(Status.INFO, "Total price save in storage");
            test.log(Status.PASS, "Set total Price", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Set total Price", SeleniumHelper.getScreenshot(driver));
        }
        return this;
    }

    public AddressDetailsPage openOrdersBillingDetails(ExtentTest test) throws IOException {
        try {
            SeleniumHelper.waitForElementToBeVisible(driver, proceedToCheckout);
            SeleniumHelper.waitForClicableElement(driver, proceedToCheckout);
            proceedToCheckout.click();
            test.log(Status.PASS, "Open Billing Details", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Open Billing Details", SeleniumHelper.getScreenshot(driver));
        }
        return new AddressDetailsPage(driver);
    }
}
