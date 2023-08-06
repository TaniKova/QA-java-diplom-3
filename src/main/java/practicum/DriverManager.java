package practicum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static String browserName = "CHROME";
    public final static int TIMEOUT = 15;

    public static WebDriver getNewWebDriver(){
        WebDriver webDriver;
        if(browserName.equals("CHROME")){
            WebDriverManager.chromedriver().setup(); //путь к драйверу указан в PATH
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            webDriver = new ChromeDriver(options);
            return webDriver;
        }
        else {
            System.setProperty("webdriver.chrome.driver","Укажите Ваш путь к драйверу");
            ChromeDriverService service = new ChromeDriverService.Builder().build();
            webDriver = new ChromeDriver(service);
            return webDriver;
        }
    }
}
