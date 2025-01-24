package common;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.LoginPage;



import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import static java.lang.Thread.sleep;

public class Helpers {
    public WebDriver driver;
    LoginPage login;
    DashboardPage dash;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    JavascriptExecutor js = (JavascriptExecutor)driver;

    public  Helpers(WebDriver driver){
        this.driver=driver;
        login = new LoginPage(this.driver);
        dash = new DashboardPage(this.driver);
    }


    public void sendKeysInputField(WebElement element, String input) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(input);
    }

    public void clickButton(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void hardClick(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        js.executeScript("arguments[0].click();",element);
    }

    public void scrollIntoView (WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView();",element);
    }

    public String getElementText(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public boolean verifyDashBoardHeader(String headerValue){
        return getElementText(dash.dashboardHeaderMethod()).equalsIgnoreCase(headerValue);
    }

    public void timeWait(long milliSeconds) throws InterruptedException {
        try {
            Thread.sleep(milliSeconds);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void clickProfileDropdown(){
        clickButton(dash.profileNameMethod());
    }

    public void loginToHRM(String userName, String password){
        sendKeysInputField(login.usernameInputField(),userName);
        sendKeysInputField(login.passwordInputField(),password);
        clickButton(login.loginButton());
    }

    public String getWarningText(){
        return getElementText(login.warningMessage());
    }
}
