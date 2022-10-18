package pl.wswoimtempie.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//button[@name = 'add-to-cart']")
    private WebElement addTocart;

    @FindBy(xpath = "//div[@class = 'woocommerce-message']//a[text() = 'View cart']")
    private WebElement viewCart;


    public CartPage viewCart() {
        viewCart.click();
        return new CartPage(driver);
    }

    public ProductPage addNoProductsToCart(String noProducts) {
        int noProductsInt = Integer.parseInt(noProducts);
        for(int i = 0; i < noProductsInt; i++) {
            addTocart.click();
        }
        return this;
    }




}
