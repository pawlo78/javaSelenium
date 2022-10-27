package pl.wswoimtempie.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.wswoimtempie.utils.SeleniumHelper;

import java.io.IOException;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//nav[@id = 'topbar-nav']//span[text() = 'Shop']")
    private WebElement shopLink;

    @FindBy(xpath = "//nav[@id = 'topbar-nav']//span[text() = 'My account']")
    private WebElement myAccountLink;

    private static final Logger logger = LogManager.getLogger();

    public ShopPage openShopPage(ExtentTest test) throws IOException {
        try {
            shopLink.click();
            test.log(Status.PASS, "Shop Page Open", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Shop Page Open", SeleniumHelper.getScreenshot(driver));
        }
        return new ShopPage(driver);
    }

    public MyAccountPage openMyAccountPage(ExtentTest test) throws IOException {
        try {
            myAccountLink.click();
            test.log(Status.PASS, "Account Page Open", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Account Page Open", SeleniumHelper.getScreenshot(driver));
        }
        return new MyAccountPage(driver);
    }


}
