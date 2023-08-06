package practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practicum.MainData;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    public final Header header;
    private final String url = MainData.basicUrl + "/login";

    private final By loginText = By.xpath("//h2[contains(text(),'Вход')]");

    private final By emailField =By.xpath("//label[contains(text(),'Email')]/../input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//*[text()='Войти']");
    private final By registerLink = By.xpath("//*[text()='Зарегистрироваться']");

    private final By recoverPasswordLink = By.xpath("//*[text()='Восстановить пароль']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.header = new Header(driver);
    }

    @Step("Wait for header visibility")
    public void waitForTitleVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(loginText));
    }

    @Step("Fill in Email")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Fill in Password")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Click button LogIn")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Click register Button")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Click recover password link")
    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordLink).click();
    }

    public String getLoginText() {
        return driver.findElement(loginText).getText();
    }

    @Step("LogIn to user account")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }


    public String getUrl() {
        return url;
    }
}
