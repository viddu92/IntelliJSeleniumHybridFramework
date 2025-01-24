package tests;

import common.Base;
import common.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.Utilities;

import java.time.Duration;

public class LoginTest extends Base {
   public WebDriver driver;
    Helpers helper;

    @BeforeMethod
    public void setup(){
        driver = setupBrowser();
        driver = preSetup();
        helper = new Helpers(driver);
    }

    @Test(priority = 1,dataProvider = "testdataSetup1")
    public void loginwithValidCreds(String username,String password){
        helper.loginToHRM(username,password);
    }


    @Test(priority = 2,dataProvider = "testdataSetup2")
    public void loginwithInvalidCreds(String username,String password){
        helper.loginToHRM(username,password);
        helper.getWarningText();
        Assert.assertEquals(helper.getWarningText(),"Invalid credentials","Mismatch warning message");
    }

    @DataProvider
    public Object[][] testdataSetup1(){
        Object[][] testData1 = Utilities.readTestData("Login1");
        return testData1;
    }

    @DataProvider
    public Object[][] testdataSetup2(){
        Object[][] testData2 = Utilities.readTestData("Login2");
        return testData2;
    }

    @AfterMethod
    public  void tearDown(){
        driver.quit();
    }
}
