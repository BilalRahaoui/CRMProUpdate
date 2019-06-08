package com.updateclassiccrm.testcases;

import atu.testrecorder.ATUTestRecorder;
import com.updateclassiccrm.Pages.ContactPage;
import com.updateclassiccrm.Pages.HomePage;
import com.updateclassiccrm.Pages.LoginPage;
import com.updateclassiccrm.base.TestBase;

import com.updateclassiccrm.testData.Data;
import com.updateclassiccrm.util.TestUtils;
import org.testng.Assert;
import org.testng.annotations.*;

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

    @Test(priority = 9, dataProvider = "contactData")
    public void addNewContact(Method method, String firstName, String lastName) throws Exception {
        contactPage = homePage.moveToContactAndClickNewContact();
        contactPage.sendDataToContact(firstName, lastName);
        String expectedNameText = contactPage.checkIfDataSent();
        String actualNameText = firstName + " " + lastName;
        Assert.assertTrue(expectedNameText.contains(actualNameText), "Test failed because name is not match");
        TestUtils.takeScreenSHot(method.getName());

    }

    @DataProvider
    public Object[][] contactData() throws Exception {

        return Data.getDataFromExcel("ContactData");
    }


}
