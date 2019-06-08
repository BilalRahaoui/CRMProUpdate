package com.updateclassiccrm.testcases;

import atu.testrecorder.ATUTestRecorder;
import com.relevantcodes.extentreports.LogStatus;
import com.updateclassiccrm.Pages.HomePage;
import com.updateclassiccrm.Pages.LoginPage;
import com.updateclassiccrm.base.TestBase;
import com.updateclassiccrm.util.TestUtils;
import org.testng.Assert;
import org.testng.ITestResult;
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
        String methodName = this.getClass().getSimpleName() + "--" + method.getName();
        logger = extent.startTest(methodName);
        initialize(browser);
        String pathname = "test-output/Report/Videos";
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
    public void teardown(Method method,ITestResult result) throws Exception {
        recorder.stop();
        recorder = null;

        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "Test success!");
            logger.log(LogStatus.PASS, "<a href='test-output/Report/SnapShot/" + result.getName() + ".png" + "'><span class='lable info'>Download Snapshot</span></a>");
            logger.log(LogStatus.PASS, "<a href='test-output/Report/Videos/" + result.getName() + ".mov" + "'><span class='lable info'>Download Video</span></a>");
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(LogStatus.SKIP, "Test skipped!");
            logger.log(LogStatus.PASS, "<a href='test-output/Report/SnapShot/" + result.getName() + ".png" + "'><span class='lable info'>Download Snapshot</span></a>");
            logger.log(LogStatus.PASS, "<a href='test-output/Report/Videos/" + result.getName() + ".mov" + "'><span class='lable info'>Download Video</span></a>");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, result.getThrowable());
            logger.log(LogStatus.PASS, "<a href='test-output/Report/SnapShot/" + result.getName() + ".png" + "'><span class='lable info'>Download Snapshot</span></a>");
            logger.log(LogStatus.PASS, "<a href='test-output/Report/Videos/" + result.getName() + ".mov" + "'><span class='lable info'>Download Video</span></a>");
        }
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


