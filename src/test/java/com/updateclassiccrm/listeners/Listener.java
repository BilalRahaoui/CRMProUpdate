package com.updateclassiccrm.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    public void onTestStart(ITestResult result) {
        System.out.println("Start test");
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test success");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed");
    }

    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {

    }
}
