package pl.wswoimtempie.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pl.wswoimtempie.models.Customer;
import pl.wswoimtempie.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AddressDetailsPage {

    private WebDriver driver;

    public AddressDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "billing_first_name")
    private WebElement firstNameInput;

    @FindBy(id = "billing_last_name")
    private WebElement lastNameInput;

    @FindBy(id = "billing_company")
    private WebElement companyNameInput;

    @FindBy(id = "billing_country")
    private WebElement billingCountrySelect;

    @FindBy(id = "billing_address_1")
    private WebElement billingAddress1Input;

    @FindBy(id = "billing_postcode")
    private WebElement billingPostcodeInput;

    @FindBy(id = "billing_city")
    private WebElement billingCityInput;

    @FindBy(id = "billing_phone")
    private WebElement billingPhoneInput;

    @FindBy(id = "billing_email")
    private WebElement billingEmailInput;

    @FindBy(id = "order_comments")
    private WebElement orderCommentsInput;

    @FindBy(id = "place_order")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//ul[@role = 'alert']//li")
    private List<WebElement> errorValidate;

    //problem with displayed button place order
    @FindBy(xpath = "//span[@class = 'czr-rights-text']")
    private WebElement spanForVisibleButtonPlaceOrder;

    @FindBy(xpath = "//form[@name = 'checkout']//ul[@class = 'woocommerce-error']//li[1]")
    private WebElement aaa;

    public OrderDetailsPage fillAddressDetails(Customer customer, ExtentTest test) throws IOException {

        try {
            firstNameInput.sendKeys(customer.getFirstName());
            lastNameInput.sendKeys(customer.getLastName());
            companyNameInput.sendKeys(customer.getCompanyName());
            Select countrySelect = new Select(billingCountrySelect);
            countrySelect.selectByVisibleText(customer.getCountry());
            billingAddress1Input.sendKeys(String.format("%s %s", customer.getStreet(), customer.getFlatNumber()));
            billingAddress1Input.sendKeys(String.format("%s %s", customer.getStreet(), customer.getFlatNumber()));
            billingPostcodeInput.sendKeys(customer.getZipCode());
            billingCityInput.sendKeys(customer.getCity());
            billingPhoneInput.sendKeys(customer.getPhone());
            billingEmailInput.sendKeys(customer.getEmail());
            orderCommentsInput.sendKeys(customer.getComments());
            SeleniumHelper.waitForElementToBeVisible(driver, placeOrderButton);
            spanForVisibleButtonPlaceOrder.click();
            placeOrderButton.click();
            test.log(Status.PASS, "Add all data to the form order", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Add all data to the form order", SeleniumHelper.getScreenshot(driver));
        }

        return new OrderDetailsPage(driver);
    }

    public AddressDetailsPage validateAddressDetails(String fName, String lName, String country,
                                                     String addres, String zCode, String city,
                                                     String phone, String email, ExtentTest test) throws IOException {

        try {
            if (fName != "") {
                firstNameInput.sendKeys(fName);
                lastNameInput.sendKeys(lName);
                Select countrySelect = new Select(billingCountrySelect);
                countrySelect.selectByVisibleText(country);
                billingAddress1Input.sendKeys(String.format(addres));
                billingPostcodeInput.sendKeys(zCode);
                billingCityInput.sendKeys(city);
                billingPhoneInput.sendKeys(phone);
                billingEmailInput.sendKeys(email);
                spanForVisibleButtonPlaceOrder.click();
                SeleniumHelper.waitForElementToExist(driver, By.id("place_order"));
                SeleniumHelper.waitForElementToBeVisible(driver, placeOrderButton);
                SeleniumHelper.waitForClicableElement(driver, placeOrderButton);
                spanForVisibleButtonPlaceOrder.click();
                placeOrderButton.click();
                test.log(Status.PASS, "Add data to the form order", SeleniumHelper.getScreenshot(driver));
            } else {
                SeleniumHelper.waitForElementToExist(driver, By.id("place_order"));
                SeleniumHelper.waitForElementToBeVisible(driver, placeOrderButton);
                SeleniumHelper.waitForClicableElement(driver, placeOrderButton);
                spanForVisibleButtonPlaceOrder.click();
                placeOrderButton.click();
                test.log(Status.PASS, "Add data to the form order", SeleniumHelper.getScreenshot(driver));
            }
        } catch (IOException e) {
            test.log(Status.FAIL, "Add data to the form order", SeleniumHelper.getScreenshot(driver));
        }


        return this;
    }

    public List<String> getErrorList() {
        SeleniumHelper.waitForElementToBeVisible(driver, aaa);
        return errorValidate.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
