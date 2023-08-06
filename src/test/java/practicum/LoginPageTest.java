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

public class LoginPageTest {
    private WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    AccountPage accountPage;
    ForgotPasswordPage forgotPasswordPage;

    @Before
    public void setUp() {
        driver = DriverManager.getNewWebDriver();
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Test
    @DisplayName("Check open login page by URL")
    public void registerUrlTest() {
        driver.navigate().to(loginPage.getUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        loginPage.waitForTitleVisibility();
        System.out.println(loginPage.getLoginText());
        assertEquals("Check login page open via url", "Вход", loginPage.getLoginText());
    }

    //вход по кнопке «Войти в аккаунт» на главной,
    @Test
    @DisplayName("Check login from home page")
    public void loginPageOpensFromHomePageTest() {
        driver.navigate().to(MainData.basicUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        homePage.clickLoginToAccountButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        assertEquals("Login page opens from home page", loginPage.getUrl(), driver.getCurrentUrl());
    }

    //вход через кнопку «Личный кабинет» в хэдере
    @Test
    @DisplayName("Check login from header")
    public void loginPageOpensFromHeaderTest() {
        driver.navigate().to(homePage.getUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        homePage.header.clickAccountButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        assertEquals("Login page opens from home page header", loginPage.getUrl(), driver.getCurrentUrl());
    }

    //вход через кнопку в форме регистрации,
    @Test
    @DisplayName("Check login from register page")
    public void loginPageOpensFromRegisterPageTest() {
        driver.navigate().to(registerPage.getUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        registerPage.clickLoginLink();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        assertEquals("Login page opens from register page", loginPage.getUrl(), driver.getCurrentUrl());
    }

    //вход через кнопку в форме восстановления пароля.
    @Test
    @DisplayName("Check login from forgot password page")
    public void loginPageOpensFromForgotPasswordPageTest() {
        driver.navigate().to(forgotPasswordPage.getUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        forgotPasswordPage.clickLoginLink();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        assertEquals("Login page opens from Forgot Password page", loginPage.getUrl(), driver.getCurrentUrl());
    }

    //ПРоверяем что форма логина вообще работает и заодно проверяется переход в личный кабинет
    @Test
    @DisplayName("Check login form works")
    public void loginWithLoginFormTest() {
        driver.navigate().to(loginPage.getUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        loginPage.login(MainData.testUserEmail, MainData.testUserPassword);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        homePage.header.clickAccountButton();
        assertTrue("Logged into profile",accountPage.checkProfileLink());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

