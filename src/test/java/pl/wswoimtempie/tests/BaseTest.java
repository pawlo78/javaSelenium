package pl.wswoimtempie.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.wswoimtempie.utils.DriveFactory;

import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {

        driver = DriveFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("http://www.seleniumdemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        //driver.quit();
    }


}
