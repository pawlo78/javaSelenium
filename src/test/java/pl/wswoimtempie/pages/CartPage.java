package pl.wswoimtempie.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.wswoimtempie.utils.Storage;

public class CartPage {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[contains(text(), 'Proceed to checkout')]")
    private WebElement proceedToCheckout;

    @FindBy(xpath = "//td[@data-title = 'Price']//span[contains(@class, 'amount')]")
    private WebElement priceLabel;

    public AddressDetailsPage openOrdersBillingDetails() {
        //ustawienie ceny książki w storage
        Storage.priceValue = priceLabel.getText();
        proceedToCheckout.click();
        return new AddressDetailsPage(driver);
    }








}
