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

public class HomePageTest {
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

        driver.navigate().to(homePage.getUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT*2));
    }

    @Test
    @DisplayName("�������� �������� � ������ '�����'")
    public void selectBunsTabTest()
    {   if("�����".equals(homePage.getSelectedTabName())){
            homePage.clickSauceButton();}
        homePage.clickBunButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT*2));
        assertEquals("Check buns tab selected", "�����", homePage.getSelectedTabName());
    }

    @Test
    @DisplayName("�������� �������� � ������ '�����'")
    public void selectSaucesTabTest()
    {   if("�����".equals(homePage.getSelectedTabName())){
        homePage.clickBunButton();}
        homePage.clickSauceButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT*2));
        assertEquals("Check buns tab selected", "�����", homePage.getSelectedTabName());
    }

    @Test
    @DisplayName("�������� �������� � ������ '�������'")
    public void selectFillingTabTest()
    {   if("�������".equals(homePage.getSelectedTabName())){
        homePage.clickBunButton();}
        homePage.clickFillingButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT*2));
        assertEquals("Check buns tab selected", "�������", homePage.getSelectedTabName());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}