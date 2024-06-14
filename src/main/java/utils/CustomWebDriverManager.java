package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomWebDriverManager {
    private static final String CHROMDRIVER_INITIATED = "ChromeDriver has been initiated successfully.";
    private static final String CHROMDRIVER_TERMINATED = "ChromeDriver has been terminated.";
    private static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(CustomWebDriverManager.class);

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            logger.info(CHROMDRIVER_INITIATED);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info(CHROMDRIVER_TERMINATED);
        }
    }
}
