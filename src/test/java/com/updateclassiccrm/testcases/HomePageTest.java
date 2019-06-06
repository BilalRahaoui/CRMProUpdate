package com.updateclassiccrm.testcases;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import com.updateclassiccrm.base.TestBase;
import com.updateclassiccrm.util.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class HomePageTest extends TestBase {


    public HomePageTest() throws Exception {
        super();
    }

    @BeforeMethod
    public void setupAndLogin(Method method) throws Exception {
        initialize();
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

    @Test(priority = 5)
    public void clickOnContact(Method method) throws Exception {
        driver.switchTo().frame("mainpanel");
        Actions action = new Actions(driver);
        WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts']"));
        action.moveToElement(contact).click().build().perform();
        boolean status = driver.findElement(By.xpath("//td[contains(text(),'Status')]")).isDisplayed();
        Assert.assertTrue(status, "Click on contact failed,status is not displayed");
        TestUtils.takeScreenSHot(method.getName());

    }

    @Test(priority = 6)
    public void addNewContact(Method method) throws Exception {
        driver.switchTo().frame("mainpanel");
        Actions action = new Actions(driver);
        WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts']"));
        WebElement addNewcontact = driver.findElement(By.xpath("//a[@title='New Contact']"));
        action.moveToElement(contact).build().perform();
        action.moveToElement(addNewcontact).click().build().perform();
        WebElement first_Name = driver.findElement(By.id("first_name"));
        WebElement last_Name = driver.findElement(By.id("surname"));
        WebElement saveButon = driver.findElement(By.xpath("//input[@value='Save']"));
        String firstNameText = "Bilal";
        String lastNameText = "Rahaoui";
        first_Name.sendKeys(firstNameText);
        last_Name.sendKeys(lastNameText);
        saveButon.submit();
        String expectedNameText = driver.findElement(By.xpath("//tr[2]//td[1]//table[1]//tbody[1]//tr[2]//td[2]")).getText();
        String actualNameText = firstNameText + " " + lastNameText;
        Assert.assertTrue(expectedNameText.contains(actualNameText), "Test failed because name is not match");
        TestUtils.takeScreenSHot(method.getName());

    }

    @Test(priority = 7)
    public void clickOnDeals(Method method) throws Exception {
        driver.switchTo().frame("mainpanel");
        WebElement deals = driver.findElement(By.xpath("//a[@title='Deals']"));
        deals.click();
        Boolean keyword = driver.findElement(By.xpath("//td[contains(text(),'Keyword')]")).isDisplayed();
        Assert.assertTrue(keyword, "Click on Deals failed,keyword is not displayed");
        TestUtils.takeScreenSHot(method.getName());

    }

    @Test(priority = 8)
    public void clickOnTasks(Method method) throws Exception {
        driver.switchTo().frame("mainpanel");
        WebElement tasks = driver.findElement(By.xpath("//a[@title='Tasks']"));
        tasks.click();
        Boolean keyword = driver.findElement(By.xpath("//td[contains(text(),'Keyword')]")).isDisplayed();
        Assert.assertTrue(keyword, "Click on Tasks failed,keyword is not displayed");
        TestUtils.takeScreenSHot(method.getName());

    }
}


