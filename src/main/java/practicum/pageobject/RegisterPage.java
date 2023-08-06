package practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import practicum.MainData;
import practicum.User;

public class RegisterPage {
    private final WebDriver driver;
    private final Header header;


    private final String url = MainData.basicUrl + "/register";
    private final By h1 = By.xpath(".//h1[text()='Регистрация']");
    private final By nameField =By.xpath("//label[contains(text(),'Имя')]/../input");
    private final By emailField =By.xpath("//label[contains(text(),'Email')]/../input");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By registerButton = By.xpath("//*[text()='Зарегистрироваться']");

    private final By loginLink = By.xpath(".//p/a[text() = 'Войти']");

    private final By errorText = By.xpath("//*[text()='Некорректный пароль']");
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.header = new Header(driver);
    }

    @Step("Click login link")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
    @Step("Fill in Name")
    public void setName(String email) {
        driver.findElement(nameField).sendKeys(email);
    }

    @Step("Fill in Email")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Fill in Password")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }


    @Step("click registerButton")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public String getUrl() {
        return url;
    }

    public String getPasswordErrorMessage() {
        return driver.findElement(errorText).getText();
    }

    public void register(User user) {
        setName(user.getName());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        clickRegisterButton();
    }
}
