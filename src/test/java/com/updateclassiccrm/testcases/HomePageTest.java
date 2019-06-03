package com.updateclassiccrm.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest {
    private WebDriver driver;

    @BeforeMethod
    public void setupAndLogin() {
        //Setup relative path to browser to start testing using selected browser
        String chromeDriverPath = "src/main/resources/WebDrivers/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        //Initialize webdriver for selected browser
        driver = new ChromeDriver();
        //maximize window
        driver.manage().window().maximize();
        //navigate to application utl
        driver.get("https://www.crmpro.com/index.html");
        //Perform login with valid data
        WebElement loginTextBox = driver.findElement(By.name("username"));
        WebElement passwordTextBox = driver.findElement(By.name("password"));
        WebElement loginButtonSubmit = driver.findElement(By.xpath("//input[@value='Login']"));
        loginTextBox.sendKeys("rahaouitesting");
        passwordTextBox.sendKeys("Bmn123456");
        loginButtonSubmit.submit();
    }

    @AfterMethod
    public void teardown() {
        //delete cookies and close browser
        driver.manage().deleteAllCookies();
        driver.quit();
        driver = null;
    }

    @Test(priority = 5)
    public void clickOnContact() {
        driver.switchTo().frame("mainpanel");
        Actions action = new Actions(driver);
        WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts']"));
        action.moveToElement(contact).click().build().perform();
        boolean status = driver.findElement(By.xpath("//td[contains(text(),'Status')]")).isDisplayed();
        Assert.assertTrue(status, "Click on contact failed,status is not displayed");
    }

    @Test(priority = 6)
    public void addNewContact() {
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
        Assert.assertTrue(expectedNameText.contains(actualNameText),"Test failed because name is not match");
    }
    @Test(priority = 7)
    public void clickOnDeals(){
        driver.switchTo().frame("mainpanel");
        WebElement deals = driver.findElement(By.xpath("//a[@title='Deals']"));
        deals.click();
        Boolean keyword = driver.findElement(By.xpath("//td[contains(text(),'Keyword')]")).isDisplayed();
        Assert.assertTrue(keyword,"Click on Deals failed,keyword is not displayed");
    }
    @Test(priority = 8)
    public void clickOnTasks(){
        driver.switchTo().frame("mainpanel");
        WebElement tasks = driver.findElement(By.xpath("//a[@title='Tasks']"));
        tasks.click();
        Boolean keyword = driver.findElement(By.xpath("//td[contains(text(),'Keyword')]")).isDisplayed();
        Assert.assertTrue(keyword,"Click on Tasks failed,keyword is not displayed");

    }
}
