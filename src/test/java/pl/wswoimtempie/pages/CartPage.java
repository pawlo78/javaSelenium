package pl.wswoimtempie.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.wswoimtempie.utils.SeleniumHelper;
import pl.wswoimtempie.utils.Storage;

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


    public CartPage addCoupon(String couponValue) {
        couponInput.sendKeys(couponValue);
        couponApplyButton.click();
        return this;
    }

    public WebElement getAlertCoupon() {
        SeleniumHelper.waitForElementToBeVisible(driver, alertCouponLabel);
        return alertCouponLabel;
    }

    public CartPage setTotalPrice() {
        SeleniumHelper.waitForNotEmptyList(driver, By.xpath("//tbody//td[@data-title = 'Price']//span[contains(@class, 'amount')]"));
        List<String> priceList = prices.stream().map(WebElement::getText).collect(Collectors.toList());
        SeleniumHelper.waitForNotEmptyList(driver, By.xpath("//td[@data-title = 'Quantity']//input"));
        List<String> noProductList = noProducts.stream().map(el -> el.getAttribute("value")).collect(Collectors.toList());

        Double wynik = 0.0;
        for (int i = 0; i < priceList.size(); i++) {
            wynik += Storage.getPriceDouble(priceList.get(i)) * Integer.parseInt(noProductList.get(i));
        }

        Storage.priceTotalValue = wynik;
        return this;
    }

    public AddressDetailsPage openOrdersBillingDetails() {
        SeleniumHelper.waitForElementToBeVisible(driver, proceedToCheckout);
        SeleniumHelper.waitForClicableElement(driver, proceedToCheckout);
        proceedToCheckout.click();
        return new AddressDetailsPage(driver);
    }


}
