package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }
    public void fillpassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void Clickonloginbtn(){
        driver.findElement(loginButton).click();
    }

}
