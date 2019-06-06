package com.updateclassiccrm.testcases;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import com.updateclassiccrm.base.TestBase;
import com.updateclassiccrm.util.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;


public class LoginPageTest extends TestBase {

    public LoginPageTest() throws Exception {
        super();
    }


    @BeforeMethod
    public void setup(Method method) throws Exception {
        initialize();
        String pathname = "test-output/video";
        recorder = new ATUTestRecorder(pathname, this.getClass().getSimpleName() + "--" + method.getName(), false);
        recorder.start();
    }

    @AfterMethod()
    public void teardown() throws Exception {
        recorder.stop();
        recorder = null;
        terminate();
    }

    @Test(priority = 1)
    public void urlTest(Method method) throws Exception {
        //actualURL should return valid application url
        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://www.crmpro.com/index.html";
        Assert.assertEquals(actualURL, expectedURL, "URL not match");
        TestUtils.takeScreenSHot(method.getName());


    }

    @Test(priority = 2)
    public void titleTest(Method method) throws Exception {
        //actualTitle should return valid application title
        String actualTitle = driver.getTitle();
        String expectedTitle = "CRMPRO - CRM software for customer relationship management, sales, and support.";
        Assert.assertEquals(actualTitle, expectedTitle, "Title not match");
        TestUtils.takeScreenSHot(method.getName());
    }

    @Test(priority = 3)
    public void logoTest(Method method) throws Exception {
        //Test if logo appear in home page
        WebElement logo = driver.findElement(By.xpath("//img[@class='img-responsive']"));
        Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");
        TestUtils.takeScreenSHot(method.getName());
    }

    @Test(priority = 4)
    public void loginTest(Method method) throws Exception {
        //Testing login functionality with valid username <rahaouitesting> and valid password <Bmn123456>
        WebElement loginTextBox = driver.findElement(By.name("username"));
        WebElement passwordTextBox = driver.findElement(By.name("password"));
        WebElement loginButtonSubmit = driver.findElement(By.xpath("//input[@value='Login']"));
        //Sending given data to login form
        String userName = properties.getProperty("UserName");
        String passWord = properties.getProperty("Password");
        loginTextBox.sendKeys(userName);
        passwordTextBox.sendKeys(passWord);

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
        TestUtils.takeScreenSHot(method.getName());

    }


}
