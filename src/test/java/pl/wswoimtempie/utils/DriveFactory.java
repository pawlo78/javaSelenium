package pl.wswoimtempie.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class DriveFactory {

    public static WebDriver getDriver() throws IOException {

        String name = PropertiesLoader.loadProperty("browser.name");

        switch (name) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            default:
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
        }

    }
}
