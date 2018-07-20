package selenium_practice;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Frame_Handle {
    protected WebDriver driver;

    @Before
    public void setUP(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();

        driver.navigate().to("https://freecrm.com");

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Test
    public void frame() throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Kartick");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("bangladesh");
        driver.findElement(By.xpath("//input[@class='btn btn-small' and @type = 'submit']")).click();
        Thread.sleep(3000);

      //switch to frame
        int total_number = driver.findElements(By.tagName("frame")).size();
        System.out.println("Total iFrame/Frame is ----->"+ total_number);

        driver.switchTo().frame("mainpanel");//by frame name as String
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(text(),'Contacts')]")).click();
        //click signOUt
        driver.findElement(By.xpath("//a[@class='topnavlink']/i[@class='fa fa-sign-out icon-2x']")).click();
        Thread.sleep(2000);

        //go back to dafault window
        driver.switchTo().defaultContent();
        //click pricing button in main window
        driver.findElement(By.xpath("//a[text()='Pricing']")).click();

       // driver.switchTo().frame(0);//switch to frame by using frame() and index

        // We can swich to frame by webelement also.and this is the best practice
        //WebElement my_frame = driver.findElement(By.xpath("Xpath"));
        //driver.switchTo().frame(my_frame);

    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }

}
