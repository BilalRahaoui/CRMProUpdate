package com.updateclassiccrm.testcases;

import atu.testrecorder.ATUTestRecorder;

import com.relevantcodes.extentreports.LogStatus;
import com.updateclassiccrm.Pages.HomePage;
import com.updateclassiccrm.Pages.LoginPage;
import com.updateclassiccrm.base.TestBase;
import com.updateclassiccrm.testData.Data;
import com.updateclassiccrm.util.TestUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
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
        String methodName = this.getClass().getSimpleName() + "--" + method.getName();
        logger = extent.startTest(methodName);

        initialize(browser);
        loginPage = new LoginPage();
        homePage = new HomePage();
        String pathname = "test-output/Report/Videos";
        recorder = new ATUTestRecorder(pathname, methodName, false);
        recorder.start();
    }

    @AfterMethod()
    public void teardown(Method method,ITestResult result) throws Exception {
        recorder.stop();
        recorder = null;
        File photo = new File("test-output/Report/SnapShot/" + this.getClass().getSimpleName() + "--" + method.getName() + ".png");
        File video = new File("test-output/Report/Videos/" + this.getClass().getSimpleName() + "--" + method.getName() + ".mov");
        String photopath = photo.getAbsolutePath();
        String videopath = video.getAbsolutePath();

        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "Test success!");
            logger.log(LogStatus.PASS, "<a href='" + photopath + "'><span class='lable info'>Download Snapshot</span></a>");
            logger.log(LogStatus.PASS, "<a href='" + videopath + "'><span class='lable info'>Download Video</span></a>");
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(LogStatus.SKIP, "Test skipped!");
            logger.log(LogStatus.PASS, "<a href='" + photopath + "'><span class='lable info'>Download Snapshot</span></a>");
            logger.log(LogStatus.PASS, "<a href='" + videopath + "'><span class='lable info'>Download Video</span></a>");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, result.getThrowable());
            logger.log(LogStatus.PASS, "<a href='" + photopath + "'><span class='lable info'>Download Snapshot</span></a>");
            logger.log(LogStatus.PASS, "<a href='" + videopath + "'><span class='lable info'>Download Video</span></a>");
        }
        terminate();
    }

    @Test(priority = 1)
    public void urlTest(Method method) throws Exception {
        //actualURL should return valid application url
        String actualURL = loginPage.getURL();
        String expectedURL = "https://www.crmpro.com/index.html";
        Assert.assertEquals(actualURL, expectedURL, "URL not match");
        TestUtils.takeScreenSHot(this.getClass().getSimpleName() + "--" + method.getName());


    }

    @Test(priority = 2)
    public void titleTest(Method method) throws Exception {
        //actualTitle should return valid application title
        String actualTitle = loginPage.getPageTitle();
        String expectedTitle = "CRMPRO - CRM software for customer relationship management, sales, and support.";
        Assert.assertEquals(actualTitle, expectedTitle, "Title not match");
        TestUtils.takeScreenSHot(this.getClass().getSimpleName() + "--" + method.getName());
    }

    @Test(priority = 3)
    public void logoTest(Method method) throws Exception {
        //Test if logo appear in home page
        Assert.assertTrue(loginPage.logoIsDisplayed(), "Logo is not displayed");
        TestUtils.takeScreenSHot(this.getClass().getSimpleName() + "--" + method.getName());
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
        TestUtils.takeScreenSHot(this.getClass().getSimpleName() + "--" + method.getName());
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
        TestUtils.takeScreenSHot(this.getClass().getSimpleName() + "--" + method.getName());

    }

    @DataProvider
    public Object[][] invalidLoginData() throws Exception {

        return Data.getDataFromExcel("invalid_login_data");
    }
}
