package selenium_practice;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PopUP {
    protected WebDriver driver;

    @Before
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.navigate().to("https://mail.rediff.com/cgi-bin/login.cgi");

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Test
    public void popUpHandle(){
        WebElement element = driver.findElement(By.xpath("//input[@name='proceed']"));
        highlight(element);
        element.click();
        Alert alert = driver.switchTo().alert();
        delayFor(2000);
        System.out.println(alert.getText());

        String text = alert.getText();
        if(text.equals("Please enter a valid user name")){
            System.out.println("Correct Alert message");
        }else{
            System.out.println("In-correct alert message");
        }
        alert.accept();

    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(4000);
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
