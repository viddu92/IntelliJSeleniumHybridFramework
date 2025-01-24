package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

   public WebDriver driver;

   public LoginPage(WebDriver driver){
       this.driver=driver;
   }

    By username = By.name("username");
    By password = By.name("password");
    By login = By.xpath("//button[normalize-space()='Login']");
    By warning = By.cssSelector((".oxd-alert-content-text"));

    public WebElement usernameInputField(){
        return driver.findElement(username);
    }

    public WebElement passwordInputField(){
        return driver.findElement(password);
    }

    public WebElement loginButton(){
        return driver.findElement(login);
    }

    public WebElement warningMessage(){
        return driver.findElement(warning);
    }
}
