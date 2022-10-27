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

public class MyAccountPage {

    private WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(id = "reg_email")
    private WebElement regEmailInput;

    @FindBy(id = "reg_password")
    private WebElement regPassEmailInput;

    @FindBy(xpath = "//button[text() = 'Register']")
    private WebElement registerButton;

    @FindBy(id = "username")
    private WebElement logUserNameInput;

    @FindBy(id = "password")
    private WebElement logPasswordInput;

    @FindBy(name = "login")
    private WebElement logInButton;

    @FindBy(id = "remembere")
    private WebElement remembereCheckBox;

    @FindBy(xpath = "//ul[@class = 'woocommerce-error']//li")
    private WebElement error;

    public WebElement getError(ExtentTest test) {
        test.log(Status.PASS, "Get errors done");
        return error;
    }

    private static final Logger logger = LogManager.getLogger();

    public LoggedUserPage registerUserValidData(String email, String password, ExtentTest test) throws IOException {
        try {
            logger.info("Setting data to register with ValidData");
            registerUser(email, password, test);
            logger.info("Setting data to register with ValidData done");
            test.log(Status.PASS, "Setting data to register with ValidData", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Setting data to register with ValidData", SeleniumHelper.getScreenshot(driver));
        }
        return new LoggedUserPage(driver);
    }

    public MyAccountPage registerUserInvalidData(String email, String password, ExtentTest test) throws IOException {
        try {
            logger.info("Setting data to register with InvalidData");
            registerUser(email, password, test);
            logger.info("Setting data to register with InvalidData done");
            test.log(Status.PASS, "Setting data to register with InvalidData", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Setting data to register with InvalidData", SeleniumHelper.getScreenshot(driver));
        }
        return this;
    }

    public void registerUser(String email, String password, ExtentTest test) throws IOException {
        try {
            logger.info("Setting username: " + email);
            regEmailInput.sendKeys(email);
            test.log(Status.INFO, "Username done");
            logger.info("Username done");
            logger.info("Setting password" + password);
            regPassEmailInput.sendKeys(password);
            test.log(Status.INFO, "Password done");
            logger.info("Password done");
            registerButton.click();
            test.log(Status.INFO, "Button Register done");
            logger.info("Button register OK");
            test.log(Status.PASS, "Register user", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Register user", SeleniumHelper.getScreenshot(driver));
        }
    }

    public LoggedUserPage logInValidData(String username, String password, ExtentTest test) throws IOException {
        try {
            logger.info("Setting data to login with ValidData");
            logIn(username, password, test);
            logger.info("Setting data to login with ValidData done");
            test.log(Status.PASS, "Setting data to login with ValidData", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Setting data to login with ValidData", SeleniumHelper.getScreenshot(driver));
        }
        return new LoggedUserPage(driver);
    }

    public MyAccountPage logInInvalidData(String username, String password, ExtentTest test) throws IOException {
        try {
            logger.info("Setting data to login with InvalidData");
            logIn(username, password, test);
            logger.info("Setting data to login with InvalidData done");
            test.log(Status.PASS, "Setting data to login with InvalidData", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Setting data to login with InvalidData", SeleniumHelper.getScreenshot(driver));
        }
        return this;
    }

    private void logIn(String username, String password, ExtentTest test) throws IOException {
        try {
            logger.info("Setting username: " + username);
            logUserNameInput.sendKeys(username);
            test.log(Status.INFO, "Username done");
            logger.info("Username done");
            logger.info("Setting password" + password);
            logPasswordInput.sendKeys(password);
            test.log(Status.INFO, "Password done");
            logger.info("Password done");
            logInButton.click();
            test.log(Status.INFO, "Button Login done");
            logger.info("Button register OK");
            test.log(Status.PASS, "Log In", SeleniumHelper.getScreenshot(driver));
        } catch (IOException e) {
            test.log(Status.FAIL, "Log In", SeleniumHelper.getScreenshot(driver));
        }
    }

}