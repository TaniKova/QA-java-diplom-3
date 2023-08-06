package practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static practicum.DriverManager.TIMEOUT;

public class AccountPage {

        private final WebDriver driver;
        public final Header header;
        public final By profile = By.xpath("//*[text()='Профиль']");
        public final By accountOrderHistory = By.xpath("//*[text()='История заказов']");
        private final By exit = By.xpath("//button[text()='Выход']");

        public AccountPage(WebDriver driver) {
            this.driver = driver;
            this.header = new Header(driver);
        }

        // выход по кнопке «Выйти» в личном кабинете.
        @Step("Exit by exit button in account page")
        public void clickLogOutButton() {
            new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                    .until(ExpectedConditions.elementToBeClickable(exit));
            driver.findElement(exit).click();
        }

        @Step("Check profile link visibility")
        public boolean checkProfileLink() {
            return driver.findElement(profile).isDisplayed();
        }


    }
