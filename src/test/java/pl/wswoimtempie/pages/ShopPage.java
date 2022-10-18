package pl.wswoimtempie.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopPage {

    private WebDriver driver;

    public ShopPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public ProductPage openProductPage(String title) {
        driver.findElement(By.xpath("//h2[text() = '" + title + "']"))
                .click();
        return new ProductPage(driver);
    }








}
