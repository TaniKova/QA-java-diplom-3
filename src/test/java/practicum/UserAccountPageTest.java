package practicum;


import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import practicum.pageobject.*;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static practicum.DriverManager.TIMEOUT;
import static practicum.MainData.testUserEmail;
import static practicum.MainData.testUserPassword;

public class UserAccountPageTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private AccountPage accountPage;


    @Before
    public void setUp() {
        driver = DriverManager.getNewWebDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
    }

    @Test
    @DisplayName("Check link to constructor from Account Page")
    public void goToConstructorByLinkTest() {
        driver.navigate().to(loginPage.getUrl());
        loginPage.login(testUserEmail, testUserPassword);
        homePage.header.clickAccountButton();
        loginPage.header.clickConstructorButton();
        assertEquals("Соберите бургер", homePage.getPageHeader());
    }

    @Test
    @DisplayName("Check link to constructor from Logo on Account Page")
    public void goToConstructorByLogoTest() {
        driver.navigate().to(loginPage.getUrl());
        loginPage.login(testUserEmail, testUserPassword);
        homePage.header.clickAccountButton();
        accountPage.header.clickLogo();
        assertEquals("Соберите бургер", homePage.getPageHeader());
    }

    @Test
    @DisplayName("Check logout from Account Page")
    public void logoutTest() {
        driver.navigate().to(loginPage.getUrl());
        loginPage.login(testUserEmail, testUserPassword);
        homePage.header.clickAccountButton();
        accountPage.clickLogOutButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        assertEquals("Вход", loginPage.getLoginText());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
