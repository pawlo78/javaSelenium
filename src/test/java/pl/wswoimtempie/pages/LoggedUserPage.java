package pl.wswoimtempie.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.wswoimtempie.utils.SeleniumHelper;

import java.io.IOException;

public class LoggedUserPage {

    private WebDriver driver;

    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//a[text() = 'Dashboard']")
    private WebElement dashboardLink;


    public WebElement getDashboardLink(ExtentTest test) throws IOException {
        try {
            test.log(Status.PASS, "Get dashboard link", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Get dashboard link", SeleniumHelper.getScreenshot(driver));
        }
        return dashboardLink;
    }


}
