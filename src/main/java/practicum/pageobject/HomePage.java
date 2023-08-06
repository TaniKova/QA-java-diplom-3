package practicum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import practicum.MainData;

public class HomePage {
    private final WebDriver driver;
    public final Header header;

    public String getUrl() {
        return url;
    }

    private final String url = MainData.basicUrl;

    private final By pageHeader = By.xpath(".//h1[text()='Соберите бургер']");

    private final By bunsTab = By.xpath(".//div/span[text() = 'Булки']");
    private final By saucesTab =By.xpath(".//div/span[text() = 'Соусы']");
    private final By fillingsTab = By.xpath(".//div/span[text() = 'Начинки']");

    private final By loginToAccountButton = By.xpath("//button[text()='Войти в аккаунт']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.header = new Header(driver);
    }

    @Step("Open tab Buns")
    public void clickBunButton() {
        driver.findElement(bunsTab).click();
    }

    @Step("Open tab Sauce")
    public void clickSauceButton() {
        driver.findElement(saucesTab).click();
    }

    @Step("Open tab Fillings")
    public void clickFillingButton() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Get page H1")
    public String getPageHeader() {
        return driver.findElement(pageHeader).getText();
    }

    @Step("Get selected tab name")
    public String getSelectedTabName() {
        System.out.println(driver.findElement(By.className("tab_tab_type_current__2BEPc")).getText());
        return driver.findElement(By.className("tab_tab_type_current__2BEPc")).getText();
    }

    @Step("Click loginToAccountButton")
    public void clickLoginToAccountButton() {
        driver.findElement(loginToAccountButton).click();
    }
}

