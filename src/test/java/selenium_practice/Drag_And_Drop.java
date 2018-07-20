package selenium_practice;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Drag_And_Drop {
    protected WebDriver driver;

    @Before
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.navigate().to("http://jqueryui.com/droppable/");

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Test
    public void dragconcept() throws InterruptedException {
        driver.switchTo().frame(0);
        Actions action = new Actions(driver);
         Thread.sleep(2000);
        action.clickAndHold(driver.findElement(By.xpath("//*[@id='draggable']")))
        .moveToElement(driver.findElement(By.xpath("//*[@id ='droppable']")))
         .release()
         .build()
         .perform();

    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();

    }
    protected void highlight(WebElement element) {
        for (int i = 0; i < 2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 2px solid red;");
            delayFor(200);
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "");
            delayFor(200);
        }
    }
    public void delayFor(int timeInMili){
        try {
            Thread.sleep(timeInMili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
