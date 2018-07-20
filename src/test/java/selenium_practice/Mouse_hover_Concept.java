package selenium_practice;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Mouse_hover_Concept {
    protected WebDriver driver;

    @Before
    public void setUp(){
        ChromeDriverManager.getInstance().setup();

        driver = new ChromeDriver();
        driver.navigate().to("https://www.costco.com");

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.manage().window().maximize();//maximize the window
        driver.manage().deleteAllCookies();//delete cookies
    }
    @Test
    public void mouseHover(){
        WebElement element = driver.findElement(By.xpath("//a[@id = 'warehouse-locations-dropdown']"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();

    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
