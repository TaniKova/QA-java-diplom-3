package practicum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import practicum.pageobject.*;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static practicum.DriverManager.TIMEOUT;

public class RegisterPageTest {
        private WebDriver driver;
        HomePage homePage;
        RegisterPage registerPage;
        LoginPage loginPage;
        AccountPage accountPage;
        ForgotPasswordPage forgotPasswordPage;
        User user;

        @Before
        public void setUp() {
            driver = DriverManager.getNewWebDriver();
            homePage = new HomePage(driver);
            registerPage = new RegisterPage(driver);
            loginPage = new LoginPage(driver);
            accountPage = new AccountPage(driver);
            forgotPasswordPage = new ForgotPasswordPage(driver);
            user = User.generateRandomUser();
        }

        //Проверяем успешную регистрацию
        @Test
        @DisplayName("Check register form works")
        public void registerFormTest() {
            driver.navigate().to(registerPage.getUrl());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
            registerPage.register(user);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
            //Проверяем, что с таким пользователем можно войти
            driver.navigate().to(loginPage.getUrl());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
            loginPage.login(user.getEmail(), user.getPassword());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
            homePage.header.clickAccountButton();
            assertTrue("Logged into profile",accountPage.checkProfileLink());
        }

        //Проверка ошибки для некорректного пароля. Минимальный пароль — шесть символов.
        @Test
        @DisplayName("Can not register with too short password")
        public void notRegisterWithToShortPasswordTest() {
            driver.navigate().to(registerPage.getUrl());
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
            user.setPassword(user.getPassword().substring(0, 5));
            registerPage.register(user);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
            assertEquals("Error 'incorrect password' is displaying", "Некорректный пароль", registerPage.getPasswordErrorMessage());
        }


        @After
        public void teardown() {
            driver.quit();
        }
    }
