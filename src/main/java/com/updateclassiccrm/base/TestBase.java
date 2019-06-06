package com.updateclassiccrm.base;

import com.updateclassiccrm.util.WebListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
    protected static WebDriver driver = null;
    protected static Properties properties = null;
    protected static EventFiringWebDriver e_driver = null;
    protected static WebListener webListener = null;

    public TestBase() throws Exception {
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/Configuration/prop.properties");
        properties.load(fileInputStream);
    }

    public static void initialize() {

        //Setup relative path to browser to start testing using selected browser
        String chromeDriverPath = "src/main/resources/WebDrivers/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        //Initialize webdriver for selected browser
        driver = new ChromeDriver();
        //Link WebListener to driver
        e_driver = new EventFiringWebDriver(driver);
        webListener = new WebListener();
        e_driver.register(webListener);
        driver = e_driver;
        //maximize window
        driver.manage().window().maximize();
        //navigate to application utl
        String url = properties.getProperty("URL");
        driver.get(url);
    }

    public static void terminate() {
        //delete cookies and close browser
        driver.manage().deleteAllCookies();
        driver.quit();
        driver = null;
    }
}
