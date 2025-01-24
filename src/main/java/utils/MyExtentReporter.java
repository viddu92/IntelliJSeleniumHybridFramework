package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class MyExtentReporter {
    static String workingDir = System.getProperty("user.dir");

    public static ExtentReports generateExtentReport(){

        ExtentReports extent = new ExtentReports();
        File extentReportFile = new File(workingDir+"\\TestReports\\Automation Test Report"+"_"+System.currentTimeMillis()+".html");
        ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);

        //spark reporter config
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("OrangeHRM Automation Test Report");
        spark.config().setDocumentTitle("OrangeHRM Test Report");
        spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        //attaching spark report type to extent
        extent.attachReporter(spark);

        //adding additional usefull info to extent
        Properties propConfig = new Properties();
        try {
            FileInputStream fisConfig = new FileInputStream(new File(workingDir+"\\src\\test\\resources\\config.properties"));
            propConfig.load(fisConfig);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        extent.setSystemInfo("URL", propConfig.getProperty("url"));
        extent.setSystemInfo("Brower Name",propConfig.getProperty("browserName"));
        //below fetched with this data o/p" System.getProperties().list(System.out)
        extent.setSystemInfo("Operating System",System.getProperty("os.name"));
        extent.setSystemInfo("User name",System.getProperty("user.name"));
        extent.setSystemInfo("Java version", System.getProperty("java.version"));

        return extent;
    }
}
