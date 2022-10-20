package pl.wswoimtempie.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.wswoimtempie.utils.SeleniumHelper;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//div[@class = 'woocommerce-message']//a[text() = 'View cart']")
    private WebElement viewCart;

    @FindBy(xpath = "//button[@name = 'add-to-cart']")
    private WebElement addToCartMainProduct;

    @FindBy(xpath = "//a[@data-product_id = '27']")
    private WebElement addToCartProduct2;

    @FindBy(xpath = "//a[contains(@class, 'added') and @data-product_id = '27']")
    private WebElement visibleAfterAddToCartProduct2;

    @FindBy(xpath = "//a[@data-product_id = '8']")
    private WebElement addToCartProduct3;

    @FindBy(xpath = "//a[contains(@class, 'added') and @data-product_id = '8']")
    private WebElement visibleAfterAddToCartProduct3;




    public CartPage viewCart() {
        viewCart.click();
        return new CartPage(driver);
    }

    public ProductPage addNoProductsToMainProductCart(String noProducts) {
        int noProductsInt = Integer.parseInt(noProducts);
        for (int i = 0; i < noProductsInt; i++) {
            SeleniumHelper.waitForClicableLocator(driver, By.xpath("//button[@name = 'add-to-cart']"));
            addToCartMainProduct.click();
        }
        return this;
    }

    public ProductPage addNoProductsToProduct2Cart(String noProducts) {
        int noProductsInt = Integer.parseInt(noProducts);
        for (int i = 0; i < noProductsInt; i++) {
            SeleniumHelper.waitForClicableLocator(driver, By.xpath("//a[@data-product_id = '27']"));
            addToCartProduct2.click();
            SeleniumHelper.waitForElementToBeVisible(driver, visibleAfterAddToCartProduct2);
        }
        return this;
    }

    public ProductPage addNoProductsToProduct3Cart(String noProducts) {
        int noProductsInt = Integer.parseInt(noProducts);
        for (int i = 0; i < noProductsInt; i++) {
            SeleniumHelper.waitForClicableLocator(driver, By.xpath("//a[@data-product_id = '8']"));
            addToCartProduct3.click();
            SeleniumHelper.waitForElementToBeVisible(driver, visibleAfterAddToCartProduct3);
        }
        return this;
    }


}
