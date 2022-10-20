package pl.wswoimtempie.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.wswoimtempie.utils.SeleniumHelper;

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


    public CartPage addThreeProductsToCart()
    {
        addProductId8.click();
        SeleniumHelper.waitForElementToBeVisible(driver, visibleAfterAddToCartProductId8);
        addProductId27.click();
        SeleniumHelper.waitForElementToBeVisible(driver, visibleAfterAddToCartProductId27);
        addProductId29.click();
        SeleniumHelper.waitForElementToBeVisible(driver, visibleAfterAddToCartProductId29);

        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCart).perform();
        shoppingCart.click();


        return new CartPage(driver);
    }






    public ProductPage openProductPage(String title) {
        driver.findElement(By.xpath("//h2[text() = '" + title + "']"))
                .click();
        return new ProductPage(driver);
    }
}
