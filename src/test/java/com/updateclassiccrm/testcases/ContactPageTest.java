package com.updateclassiccrm.testcases;

import atu.testrecorder.ATUTestRecorder;
import com.updateclassiccrm.base.TestBase;

import com.updateclassiccrm.util.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class ContactPageTest extends TestBase {
    public ContactPageTest() throws Exception {
        super();
    }
    @Parameters({"browser"})
    @BeforeMethod
    public void setupAndLogin(Method method, String browser) throws Exception {
        initialize(browser);
        String pathname = "test-output/video";
        recorder = new ATUTestRecorder(pathname, this.getClass().getSimpleName() + "--" + method.getName(), false);
        recorder.start();
        //Perform login with valid data
        WebElement loginTextBox = driver.findElement(By.name("username"));
        WebElement passwordTextBox = driver.findElement(By.name("password"));
        WebElement loginButtonSubmit = driver.findElement(By.xpath("//input[@value='Login']"));
        //Sending given data to login form
        String userName = properties.getProperty("UserName");
        String passWord = properties.getProperty("Password");
        loginTextBox.sendKeys(userName);
        passwordTextBox.sendKeys(passWord);
        loginButtonSubmit.submit();
    }
    @AfterMethod
    public void teardown() throws Exception {
        recorder.stop();
        recorder = null;
        terminate();
    }
    @Test(priority = 9,enabled = false)
    public void addNewContact(Method method) throws Exception {
        driver.switchTo().frame("mainpanel");
        Actions action = new Actions(driver);
        WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts']"));
        WebElement addNewContact = driver.findElement(By.xpath("//a[@title='New Contact']"));
        action.moveToElement(contact).build().perform();
        action.moveToElement(addNewContact).click().build().perform();
        WebElement first_Name = driver.findElement(By.id("first_name"));
        WebElement last_Name = driver.findElement(By.id("surname"));
        WebElement saveButton = driver.findElement(By.xpath("//input[@value='Save']"));
        String firstNameText = "Bilal";
        String lastNameText = "Rahaoui";
        first_Name.sendKeys(firstNameText);
        last_Name.sendKeys(lastNameText);
        saveButton.submit();
        String expectedNameText = driver.findElement(By.xpath("//tr[2]//td[1]//table[1]//tbody[1]//tr[2]//td[2]")).getText();
        String actualNameText = firstNameText + " " + lastNameText;
        Assert.assertTrue(expectedNameText.contains(actualNameText), "Test failed because name is not match");
        TestUtils.takeScreenSHot(method.getName());

    }


}
