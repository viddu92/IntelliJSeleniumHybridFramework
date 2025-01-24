package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class MyListeners implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(MyListeners.class);
    ExtentReports extentReports;
    ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
       extentReports = MyExtentReporter.generateExtentReport();
        System.out.println("onStart:Execution of Project Tests started " +context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        extentTest=extentReports.createTest(testName);
        extentTest.log(Status.INFO,testName+" started successfully");
        //System.out.println("onTestStart:Test has started " +result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        extentTest = extentReports.createTest(testName);
        extentTest.log(Status.INFO,testName+" passed");
        //System.out.println("onTestSuccess: Test is PASS " +result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        WebDriver driver = null;
        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        String destScreenshot = Utilities.takeScreenshotOnFailure(driver,testName);
        extentTest.addScreenCaptureFromPath(destScreenshot);
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL,testName+" failed");
        // System.out.println("onTestFailure:Test has failed " +result.getName());
        log.error(String.valueOf(result.getThrowable())); //return the exception details of the failure
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        extentTest.log(Status.SKIP,testName+" skipped");
        //System.out.println("onTestSkipped:Test has skipped " +result.getName());
        log.error(String.valueOf(result.getThrowable())); //return the exception details of the skip
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("onFinish:Execution of Project Tests finished " +context.getName());
        extentReports.flush();
    }
}
