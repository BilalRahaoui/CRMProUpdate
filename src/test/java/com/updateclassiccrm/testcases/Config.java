package com.updateclassiccrm.testcases;


import com.relevantcodes.extentreports.ExtentReports;
import com.updateclassiccrm.base.TestBase;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class Config extends TestBase {
    public Config() throws Exception {
        super();
    }
    @BeforeSuite
    public void start(){
        String reportFilePath = "test-output/Report/index.html";
        extent = new ExtentReports(reportFilePath,true);

        extent.addSystemInfo("OS", "windows");
        extent.addSystemInfo("Tester", "Bilal RAHAOUI");
        extent.addSystemInfo("Test Name", "Classic CRM Update");
        extent.addSystemInfo("language", "JAVA");
        extent.addSystemInfo("Framework Design", "Page Object");
    }
    @AfterSuite
    public void end(){
        extent.flush();
    }
}
