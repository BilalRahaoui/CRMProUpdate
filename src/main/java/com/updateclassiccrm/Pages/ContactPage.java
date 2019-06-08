package com.updateclassiccrm.Pages;

import com.updateclassiccrm.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage extends TestBase {
    public ContactPage() throws Exception {
        PageFactory.initElements(driver,this);

    }
    @FindBy(id = "first_name")
    WebElement first_Name;

    @FindBy(id = "surname")
    WebElement last_Name;

    @FindBy(xpath = "//input[@value='Save']")
    WebElement saveButton;

    @FindBy(xpath = "//tr[2]//td[1]//table[1]//tbody[1]//tr[2]//td[2]")
    WebElement nameText;

    public void sendDataToContact(String firstName,String lastName){
        first_Name.sendKeys(firstName);
        last_Name.sendKeys(lastName);
        saveButton.submit();
    }
    public String checkIfDataSent(){
        return nameText.getText();
    }



}
