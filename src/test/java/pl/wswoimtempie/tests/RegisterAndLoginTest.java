package pl.wswoimtempie.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.wswoimtempie.pages.HomePage;
import pl.wswoimtempie.utils.SeleniumHelper;

import java.io.IOException;

public class RegisterAndLoginTest extends BaseTest {

    int randomNumber;

    @Test(priority = 0)
    public void registerUserTest() throws IOException {
        ExtentTest test = extentReports.createTest("Register user Test");
        randomNumber = (int) (Math.random() * 1000);
        WebElement dashboardLink = new HomePage(driver)
                .openMyAccountPage(test)
                .registerUserValidData("testowy" + randomNumber + "@testowy.pl", "testowy@testowy.pl", test)
                .getDashboardLink(test);

        if (dashboardLink.getText().equals("Dashboard")) {
            test.log(Status.PASS, "Assert Register User Test", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(dashboardLink.getText(), "Dashboard");
        } else {
            test.log(Status.FAIL, "Assert Register User Test", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(dashboardLink.getText(), "Dashboard");
        }
    }

    @Test(priority = 1)
    public void registerUserWithSameTest() throws IOException {
        ExtentTest test = extentReports.createTest("Register user with exist login");
        WebElement errorInfo = new HomePage(driver)
                .openMyAccountPage(test)
                .registerUserInvalidData("testowy" + randomNumber + "@testowy.pl", "testowy@testowy.pl", test)
                .getError(test);

        if (errorInfo.getText().equals("Error: An account is already registered with your email address. Please log in.")) {
            test.log(Status.PASS, "Assert Register User With Same Data Test", SeleniumHelper.getScreenshot(driver));
            Assert.assertTrue(errorInfo.getText().contains("An account is already registered with your email address"));
        } else {
            test.log(Status.FAIL, "Assert Register User With Same Data Test", SeleniumHelper.getScreenshot(driver));
            Assert.assertTrue(errorInfo.getText().contains("An account is already registered with your email address"));
        }
    }

    @Test(priority = 2)
    public void loginTest() throws IOException {
        ExtentTest test = extentReports.createTest("Login Test");
        WebElement dashboardLink = new HomePage(driver)
                .openMyAccountPage(test)
                .logInValidData("testowy" + randomNumber + "@testowy.pl", "testowy@testowy.pl", test)
                .getDashboardLink(test);

        if (dashboardLink.getText().equals("Dashboard")) {
            test.log(Status.PASS, "Assert Login test", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(dashboardLink.getText(), "Dashboard");
        } else {
            test.log(Status.FAIL, "Assert Login test", SeleniumHelper.getScreenshot(driver));
            Assert.assertEquals(dashboardLink.getText(), "Dashboard");
        }
    }

    @Test(priority = 3)
    public void loginWithInvalidDataTest() throws IOException {
        ExtentTest test = extentReports.createTest("Login with Invalid Data Test");
        WebElement errorInfo = new HomePage(driver)
                .openMyAccountPage(test)
                .logInInvalidData("testowyqaz@testowy.pl", "testowy55@testowy.pl", test)
                .getError(test);

        if (errorInfo.getText().contains("Incorrect username or password")) {
            test.log(Status.PASS, "Assert Login With Invalid Data Test", SeleniumHelper.getScreenshot(driver));
            Assert.assertTrue(errorInfo.getText().contains("Incorrect username or password"));
        } else {
            test.log(Status.FAIL, "Assert Login With Invalid Data Test", SeleniumHelper.getScreenshot(driver));
            Assert.assertTrue(errorInfo.getText().contains("Incorrect username or password"));
        }
    }
}
