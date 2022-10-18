package pl.wswoimtempie.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//nav[@id = 'topbar-nav']//span[text() = 'Shop']")
    private WebElement shopLink;

    public ShopPage openShopPage() {
        shopLink.click();
        return new ShopPage(driver);
    }


}
