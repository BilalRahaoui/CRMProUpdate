package com.updateclassiccrm.util;

import com.updateclassiccrm.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class TestUtils extends TestBase {
    public TestUtils() throws Exception {
        super();
    }

    public static void takeScreenSHot(String screenshotName) throws Exception {
        //Take screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String pathname = "test-output/screenshot" + "/" + screenshotName + ".png";
        FileUtils.copyFile(srcFile, new File(pathname));
    }

}
