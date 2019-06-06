package com.updateclassiccrm.util;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebListener implements WebDriverEventListener {

    public void beforeNavigateTo(String URL, WebDriver driver) {
        System.out.println("Before navigating to : " + URL);
    }

    public void afterNavigateTo(String URL, WebDriver driver) {
        System.out.println("Navigated to: " + URL);
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] arg2) {
        System.out.println("Value of the:" + element.toString()
                + " before any changes made");
    }

    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] arg2) {
        System.out.println("Element value changed to: " + element.toString());
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println("Trying to click on: " + element.toString());
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println("Clicked on: " + element.toString());
    }

    public void beforeNavigateBack(WebDriver driver) {
        System.out.println("Navigating back to previous page");
    }

    public void afterNavigateBack(WebDriver driver) {
        System.out.println("Navigated back to previous page");
    }

    public void beforeNavigateForward(WebDriver driver) {
        System.out.println("Navigating forward to next page");
    }

    public void afterNavigateForward(WebDriver driver) {
        System.out.println("Navigated forward to next page");
    }

    public void onException(Throwable error, WebDriver driver) {
        System.out.println("Exception occurred: " + error);
    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Trying to find Element By : " + by.toString());
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Found Element By : " + by.toString());
    }

    public void afterAlertAccept(WebDriver arg0) {
        // TODO Auto-generated method stub

    }

    public void afterAlertDismiss(WebDriver arg0) {
        // TODO Auto-generated method stub

    }

    public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
        // TODO Auto-generated method stub

    }

    public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
        // TODO Auto-generated method stub

    }

    public void afterNavigateRefresh(WebDriver arg0) {
        // TODO Auto-generated method stub

    }

    public void afterScript(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

    public void afterSwitchToWindow(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

    public void beforeAlertAccept(WebDriver arg0) {
        // TODO Auto-generated method stub

    }

    public void beforeAlertDismiss(WebDriver arg0) {
        // TODO Auto-generated method stub

    }

    public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
        // TODO Auto-generated method stub

    }

    public void beforeGetText(WebElement arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

    public void beforeNavigateRefresh(WebDriver arg0) {
        // TODO Auto-generated method stub

    }

    public void beforeScript(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

    public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
        // TODO Auto-generated method stub

    }

}