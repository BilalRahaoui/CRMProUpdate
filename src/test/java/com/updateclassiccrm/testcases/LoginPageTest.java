package com.updateclassiccrm.testcases;

import atu.testrecorder.ATUTestRecorder;

import com.updateclassiccrm.Pages.HomePage;
import com.updateclassiccrm.Pages.LoginPage;
import com.updateclassiccrm.base.TestBase;
import com.updateclassiccrm.testData.Data;
import com.updateclassiccrm.util.TestUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;


public class LoginPageTest extends TestBase {

    public LoginPageTest() throws Exception {
        super();
    }

    private LoginPage loginPage;
    private HomePage homePage;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(Method method, String browser) throws Exception {
        initialize(browser);
        loginPage = new LoginPage();
        homePage = new HomePage();
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
        String actualURL = loginPage.getURL();
        String expectedURL = "https://www.crmpro.com/index.html";
        Assert.assertEquals(actualURL, expectedURL, "URL not match");
        TestUtils.takeScreenSHot(method.getName());


    }

    @Test(priority = 2)
    public void titleTest(Method method) throws Exception {
        //actualTitle should return valid application title
        String actualTitle = loginPage.getPageTitle();
        String expectedTitle = "CRMPRO - CRM software for customer relationship management, sales, and support.";
        Assert.assertEquals(actualTitle, expectedTitle, "Title not match");
        TestUtils.takeScreenSHot(method.getName());
    }

    @Test(priority = 3)
    public void logoTest(Method method) throws Exception {
        //Test if logo appear in home page
        Assert.assertTrue(loginPage.logoIsDisplayed(), "Logo is not displayed");
        TestUtils.takeScreenSHot(method.getName());
    }

    @Test(priority = 4)
    public void validLoginTest(Method method) throws Exception {
        //Testing login functionality with valid username <rahaouitesting> and valid password <Bmn123456> from properties file
        String validUserName = properties.getProperty("UserName");
        String validUPassword = properties.getProperty("Password");
        homePage=loginPage.performLogin(validUserName,validUPassword);
        //Assert that title for Home Page is 'CRMPRO' using soft assertion
        SoftAssert soft = new SoftAssert();
        String expectedLoginTitle = "CRMPRO";
        String actualLoginTitle = homePage.getPageTitle();
        soft.assertEquals(expectedLoginTitle, actualLoginTitle, "Title not match");
        TestUtils.takeScreenSHot(method.getName());
    }

    @Test(priority = 5, dataProvider = "invalidLoginData")
    public void invalidLoginTest(Method method, String UserName, String Password) throws Exception {
        //Testing login functionality with valid username <rahaouitesting> and valid password <Bmn123456> from properties file
        homePage=loginPage.performLogin(UserName,Password);
        //Assert that login not pass 'Check that title is not CRMPRO'
        SoftAssert soft = new SoftAssert();

        String expectedTitle = "CRMPRO";
        String actualLoginTitle = homePage.getPageTitle();
        soft.assertNotEquals(actualLoginTitle, expectedTitle, "Testing with invalid data failed");
        soft.assertAll();
        TestUtils.takeScreenSHot(method.getName());

    }

    @DataProvider
    public Object[][] invalidLoginData() throws Exception {

        return Data.getDataFromExcel("invalid_login_data");
    }
}
