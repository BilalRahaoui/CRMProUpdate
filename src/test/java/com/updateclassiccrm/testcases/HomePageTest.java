package com.updateclassiccrm.testcases;

import atu.testrecorder.ATUTestRecorder;
import com.updateclassiccrm.Pages.HomePage;
import com.updateclassiccrm.Pages.LoginPage;
import com.updateclassiccrm.base.TestBase;
import com.updateclassiccrm.util.TestUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class HomePageTest extends TestBase {


    public HomePageTest() throws Exception {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;

    @Parameters({"browser"})
    @BeforeMethod
    public void setupAndLogin(Method method, String browser) throws Exception {
        initialize(browser);
        String pathname = "test-output/video";
        loginPage = new LoginPage();
        recorder = new ATUTestRecorder(pathname, this.getClass().getSimpleName() + "--" + method.getName(), false);
        recorder.start();
        //Perform login with valid data
        String validUserName = properties.getProperty("UserName");
        String validUPassword = properties.getProperty("Password");
        homePage = loginPage.performLogin(validUserName, validUPassword);
        homePage.switchToFrame("mainpanel");
    }

    @AfterMethod
    public void teardown() throws Exception {
        recorder.stop();
        recorder = null;
        terminate();
    }

    @Test(priority = 6)
    public void clickOnContact(Method method) throws Exception {
        homePage.clickOnContact();
        boolean statusIsDisplayed = homePage.isStatueDisplayed();
        Assert.assertTrue(statusIsDisplayed, "Click on contact failed,status is not displayed");
        TestUtils.takeScreenSHot(method.getName());

    }

    @Test(priority = 7)
    public void clickOnDeals(Method method) throws Exception {
        homePage.clickOnDeals();
        boolean keywordIsDisplayed = homePage.isKeywordDisplayed();
        Assert.assertTrue(keywordIsDisplayed, "Click on Deals failed,keyword is not displayed");
        TestUtils.takeScreenSHot(method.getName());

    }

    @Test(priority = 8)
    public void clickOnTasks(Method method) throws Exception {
        homePage.clickOnTasks();
        boolean keywordIsDisplayed = homePage.isKeywordDisplayed();
        Assert.assertTrue(keywordIsDisplayed, "Click on Tasks failed,keyword is not displayed");
        TestUtils.takeScreenSHot(method.getName());

    }
}


