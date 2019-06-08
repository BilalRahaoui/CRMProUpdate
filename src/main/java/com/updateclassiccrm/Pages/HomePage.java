package com.updateclassiccrm.Pages;

import com.updateclassiccrm.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {

    public HomePage() throws Exception {
        PageFactory.initElements(driver, this);
    }

    Actions action;

    @FindBy(className = "logo_text")
    WebElement logoCrmInHomePage;

    @FindBy(xpath = "//a[@title='Contacts']")
    WebElement contact;

    @FindBy(xpath = "//a[@title='New Contact']")
    WebElement addNewContact;

    @FindBy(xpath = "//td[contains(text(),'Status')]")
    WebElement status;

    @FindBy(xpath = "//a[@title='Deals']")
    WebElement deals;

    @FindBy(xpath = "//a[@title='Tasks']")
    WebElement tasks;

    @FindBy(xpath = "//td[contains(text(),'Keyword')]")
    WebElement keyword;

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void switchToFrame(String frameName) {
        driver.switchTo().frame(frameName);
    }

    public void clickOnContact() {
        action = new Actions(driver);
        action.moveToElement(contact).build().perform();
        contact.click();
    }

    public ContactPage moveToContactAndClickNewContact() throws Exception {
        action = new Actions(driver);
        action.moveToElement(contact).build().perform();
        addNewContact.click();
        return new ContactPage();
    }

    public boolean isStatueDisplayed() {
        return status.isDisplayed();
    }

    public void clickOnDeals() {
        deals.click();
    }

    public boolean isKeywordDisplayed() {
        return keyword.isDisplayed();
    }

    public void clickOnTasks() {
        tasks.click();
    }


}
