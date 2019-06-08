package com.updateclassiccrm.Pages;

import com.updateclassiccrm.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    public LoginPage() throws Exception {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@class='img-responsive']")
    WebElement logo;

    @FindBy(name = "username")
    WebElement loginTextBox;

    @FindBy(name = "password")
    WebElement passwordTextBox;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginButtonSubmit;

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean logoIsDisplayed() {
        return logo.isDisplayed();
    }

    public HomePage performLogin(String UserName, String Password) throws Exception {
        loginTextBox.sendKeys(UserName);
        passwordTextBox.sendKeys(Password);
        loginButtonSubmit.submit();
        return new HomePage();
    }


}

