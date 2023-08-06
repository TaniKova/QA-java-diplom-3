package practicum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private final WebDriver driver;
    private final By constructorButton = By.xpath("//*[text()='Конструктор']");
    private final By feedButton = By.xpath("//p[contains(text(),'Лента Заказов')]");
    private final By logo = By.className("AppHeader_header__logo__2D0X2");
    private final By accountButton = By.xpath(".//p[text()='Личный Кабинет']");

    public Header(WebDriver driver) {
        this.driver = driver;
    }


    public void clickConstructorButton(){
        driver.findElement(constructorButton).click();
    }

    public void clickFeedButton(){
        driver.findElement(feedButton).click();
    }

    public void clickLogo(){
        driver.findElement(logo).click();
    }

    public void clickAccountButton(){
        driver.findElement(accountButton).click();
    }
}
