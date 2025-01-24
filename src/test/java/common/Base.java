package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Base {
    public WebDriver driver;
    public Properties prop;

    public Properties readProperties() {
        prop = new Properties();
        File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");

        try {
            FileInputStream inputStream = new FileInputStream(file);
            prop.load(inputStream);
        } catch (Exception e) {
            System.out.println("Failure to read config properties file");
            e.printStackTrace();
        }
        return prop;
    }


    public WebDriver setupBrowser() {
        prop = readProperties();
        String browserName= prop.getProperty("browserName").toUpperCase();
        switch (browserName) {
            case "CHROME":
                driver = new ChromeDriver();
                break;

            case "FIREFOX":
                driver = new FirefoxDriver();
                break;

            case "EDGE":
                driver = new EdgeDriver();
                break;

            default:
                System.out.println("Invalid browser selection");

        }
        return driver;
    }

    public  WebDriver preSetup(){
        prop = readProperties();
        driver.get(prop.getProperty("url"));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(prop.getProperty("implicitWaitTimeout"))));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
