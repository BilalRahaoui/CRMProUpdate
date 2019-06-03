package com.updateclassiccrm.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


public class LoginPageTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        //Setup relative path to browser to start testing using selected browser
        String chromeDriverPath = "src/main/resources/WebDrivers/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        //Initialize webdriver for selected browser
        driver = new ChromeDriver();
        //maximize window
        driver.manage().window().maximize();
        //navigate to application utl
        driver.get("https://www.crmpro.com/index.html");
    }

    @AfterMethod
    public void teardown() {
        //delete cookies and close browser
        driver.manage().deleteAllCookies();
        driver.quit();
        driver = null;
    }

    @Test(priority = 1)
    public void urlTest() {
        //actualURL should return valid application url
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://www.crmpro.com/index.html";
        Assert.assertEquals(actualURL, expectedURL, "URL not match");
    }

    @Test(priority = 2)
    public void titleTest() {
        //actualTitle should return valid application title
        String actualTitle = driver.getTitle();
        String expectedTitle = "CRMPRO - CRM software for customer relationship management, sales, and support.";
        Assert.assertEquals(actualTitle, expectedTitle, "Title not match");
    }

    @Test(priority = 3)
    public void logoTest() {
        //Test if logo appear in home page
        WebElement logo = driver.findElement(By.xpath("//img[@class='img-responsive']"));
        Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");
    }

    @Test(priority = 4)
    public void loginTest() {
        //Testing login functionality with valid username <rahaouitesting> and valid password <Bmn123456>
        WebElement loginTextBox = driver.findElement(By.name("username"));
        WebElement passwordTextBox = driver.findElement(By.name("password"));
        WebElement loginButtonSubmit = driver.findElement(By.xpath("//input[@value='Login']"));

        //Sending given data to login form
        loginTextBox.sendKeys("rahaouitesting");
        passwordTextBox.sendKeys("Bmn123456");

        //Click on Login button,it's a input of type submit
        loginButtonSubmit.submit();

        //Assert that title is 'CRMPRO' using soft assertion
        SoftAssert soft = new SoftAssert();

        String expectedloginTitle = "CRMPRO";
        String actualLoginTitle = driver.getTitle();
        soft.assertEquals(expectedloginTitle, actualLoginTitle, "Title not match");
        /*Assert that login was been successful 'Check if logo appears'
        Logo is inside a frame,need to switch to frame mainpanel*/
        driver.switchTo().frame("mainpanel");
        WebElement logoCrmInLoginPage = driver.findElement(By.className("logo_text"));
        Boolean logoCrmInLoginPageIsDisplayed = logoCrmInLoginPage.isDisplayed();
        soft.assertTrue(logoCrmInLoginPageIsDisplayed, "Logo is not visible, so login test failed");
        soft.assertAll();
    }

}
