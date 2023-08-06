package practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import practicum.MainData;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private final String url = MainData.basicUrl + "/forgot-password";

    private final By forgotPasswordText = By.xpath(".//h2[text()='Восстановление пароля']");
    private final By emailField = By.xpath("//label[contains(text(),'Email')]/../input");
    private final By recoverButton = By.xpath("//button[contains(text(),'Восстановить')]");
    private final By loginLink = By.xpath(".//p/a[text() = 'Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open forgot-password page")
    public void openForgotPasswordPage (){
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    //вход через кнопку в форме восстановления пароля.
    @Step("Click login on ForgotPassword Page")
    public void clickLoginLink(){
        driver.findElement(loginLink).click();
    }

    @Step("Fill 'Email'")
    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Click Recover on ForgotPassword Page")
    public void clickRecoverButton() {
        driver.findElement(recoverButton).click();
    }

    public String getUrl() {
        return url;
    }
}
