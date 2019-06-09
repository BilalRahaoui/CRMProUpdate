package com.updateclassiccrm.testcases;

import atu.testrecorder.ATUTestRecorder;
import com.relevantcodes.extentreports.LogStatus;
import com.updateclassiccrm.Pages.ContactPage;
import com.updateclassiccrm.Pages.HomePage;
import com.updateclassiccrm.Pages.LoginPage;
import com.updateclassiccrm.base.TestBase;

import com.updateclassiccrm.testData.Data;
import com.updateclassiccrm.util.TestUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

public class ContactPageTest extends TestBase {
    public ContactPageTest() throws Exception {
        super();
    }

    LoginPage loginPage;
    HomePage homePage;
    ContactPage contactPage;

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

    @Test(priority = 9, dataProvider = "contactData")
    public void addNewContact(Method method, String firstName, String lastName) throws Exception {
        contactPage = homePage.moveToContactAndClickNewContact();
        contactPage.sendDataToContact(firstName, lastName);
        String expectedNameText = contactPage.checkIfDataSent();
        String actualNameText = firstName + " " + lastName;
        Assert.assertTrue(expectedNameText.contains(actualNameText), "Test failed because name is not match");
        TestUtils.takeScreenSHot(this.getClass().getSimpleName() + "--" + method.getName());

    }

    @DataProvider
    public Object[][] contactData() throws Exception {

        return Data.getDataFromExcel("ContactData");
    }


}
