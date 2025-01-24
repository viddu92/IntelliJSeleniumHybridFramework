package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashboardPage {
    public WebDriver driver;

    public DashboardPage(WebDriver driver){
        this.driver=driver;
    }

    By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    By profileName = By.cssSelector("p.oxd-userdropdown-name");
    By profileDropDown = By.cssSelector("ul.oxd-dropdown-menu li");
    By aboutList = By.cssSelector("ul.oxd-dropdown-menu li:nth-child(1)");
    By supportList = By.cssSelector("ul.oxd-dropdown-menu li:nth-child(2)");
    By changePasswordList = By.cssSelector("ul.oxd-dropdown-menu li:nth-child(3)");
    By logoutList = By.cssSelector("ul.oxd-dropdown-menu li:nth-child(4)");

    public WebElement dashboardHeaderMethod(){
        return driver.findElement(dashboardHeader);
    }

    public WebElement profileNameMethod(){
        return driver.findElement(profileName);
    }

    public List<WebElement> profileListMethod(){
        return driver.findElements(profileDropDown);
    }

    public WebElement aboutListMethod(){
        return driver.findElement(aboutList);
    }

    public WebElement supportListMethod(){
        return driver.findElement(supportList);
    }

    public WebElement changePasswordListMethod(){
        return driver.findElement(changePasswordList);
    }

    public WebElement logoutListMethod(){
        return driver.findElement(logoutList);
    }
}
