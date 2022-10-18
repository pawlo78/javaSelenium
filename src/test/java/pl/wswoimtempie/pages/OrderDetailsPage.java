package pl.wswoimtempie.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.wswoimtempie.utils.SeleniumHelper;

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

    public WebElement getOrderNotice() {
        SeleniumHelper.waitForElementToBeVisible(driver, orderNotice);
        return orderNotice;
    }

    public WebElement getProductName() {
        SeleniumHelper.waitForElementToBeVisible(driver, productName);
        return productName;
    }

    public WebElement getTotalPrice() {
        SeleniumHelper.waitForElementToBeVisible(driver, totalPrice);
        return totalPrice;
    }



}
