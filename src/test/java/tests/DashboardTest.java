package tests;

import common.Base;
import common.Helpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.DashboardPage;

import java.time.Duration;
import java.util.List;

public class DashboardTest extends Base {
    public WebDriver driver ;
    Helpers helper;
    DashboardPage dashboardPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeTest
    public void setup(){
        driver = setupBrowser();
        driver = preSetup();
        helper = new Helpers(driver);
        dashboardPage = new DashboardPage(driver);
        helper.loginToHRM("Admin","admin123");
    }

    @Test(priority = 1)
    public void verifyDashboardHeader(){
        Assert.assertTrue(helper.verifyDashBoardHeader("Dashboard"),"Header is mismatch");
    }

    @Test (priority = 2)
    public void clickProfileDropDownMethod() throws InterruptedException {
        helper.clickProfileDropdown();
        helper.timeWait(2000);
    }

    @Test(priority = 3,dependsOnMethods = "clickProfileDropDownMethod")
    public void verifyDropdownItem1(){

        Assert.assertEquals(dashboardPage.aboutListMethod().getText(),"About","Item1 mismatch");
    }

    @Test(priority = 4,dependsOnMethods = "clickProfileDropDownMethod")
    public void verifyDropdownItem2(){
        Assert.assertEquals(dashboardPage.supportListMethod().getText(),"Support","Item2 mismatch");
    }

    @Test(priority = 5,dependsOnMethods = "clickProfileDropDownMethod")
    public void verifyDropdownItem3(){
        helper.clickProfileDropdown();
        Assert.assertEquals(dashboardPage.changePasswordListMethod().getText(),"Change Password","Item3 mismatch");
    }

    @Test(priority = 6,dependsOnMethods = "clickProfileDropDownMethod")
    public void verifyDropdownItem4(){
        helper.clickProfileDropdown();
        Assert.assertEquals(dashboardPage.logoutListMethod().getText(),"Logout","Item4 mismatch");
    }

    @Test(priority = 7,dependsOnMethods = "clickProfileDropDownMethod")
    public void verifyDropdownItem4Er(){
        helper.clickProfileDropdown();
        Assert.assertEquals(dashboardPage.logoutListMethod().getText(),"Logoutss","Item4 mismatch");
    }

    @AfterTest
    public  void tearDown(){
        driver.quit();
    }
}
