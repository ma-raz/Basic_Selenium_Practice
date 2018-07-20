package selenium_practice;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Basic_Selenium {
    protected WebDriver driver;

@Before
    public void setUp(){
    ChromeDriverManager.getInstance().setup();

    driver = new ChromeDriver();
    driver.navigate().to("https://www.costco.com");
    //String getString = driver.getCurrentUrl();
    //System.out.println("Result for current URL is :"  + getString);

    //To navigate to an URL and It will not wait till the whole page gets loaded

    //driver.get("https://www.costco.com");
    //To open an URL and it will wait till the whole page gets loaded

   // driver.navigate().refresh();
    //To refresh the URL




    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    driver.manage().window().maximize();//maximize the window
    driver.manage().deleteAllCookies();//delete cookies
}

@Test
    public void inputText() throws InterruptedException {
    WebElement element = driver.findElement(By.id("search-field"));
    element.sendKeys("Book");

    //getAttribute() method will return the value of attribute
    WebElement text = driver.findElement(By.id("search-field"));
    text.getAttribute("id");
    System.out.println(text);
    Thread.sleep(4000); //Static wait

   //How to Use Enter Key
    driver.findElement(By.xpath("//input[@id='search-field']")).sendKeys(Keys.ENTER);

}
    @Test
    public void clearText() throws InterruptedException {
        WebElement element = driver.findElement(By.id("search-field"));
        element.sendKeys("Book");
        element.getText();//Using getText() for getting locator value
        System.out.println(element);
        Thread.sleep(4000);


        //Use clear() to clear the text from search box
        driver.findElement(By.id("search-field")).clear();
    }

    @Test
    public  void elementDisplayed(){
     boolean elementPresent = driver.findElement(By.xpath("//a[@id = 'warehouse-locations-dropdown']")).isDisplayed();
        System.out.println(elementPresent);

        boolean elementPresent1 = driver.findElement(By.xpath("//select[@id='search-dropdown-select']")).isSelected();
        System.out.println(elementPresent1);

        boolean elementPresent2 = driver.findElement(By.xpath("//a[@id = 'warehouse-locations-dropdown']")).isEnabled();
        System.out.println(elementPresent2);

    }
@Test
    public void mouseHover(){
        WebElement element = driver.findElement(By.xpath("//a[@id = 'warehouse-locations-dropdown']"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();

    }
@Test
    public void checkBox() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//a[@id = 'warehouse-locations-dropdown']"));
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        Thread.sleep(2000);
        WebElement element1 = driver.findElement(By.xpath("//div[@class = 'style-check']/label[@title ='Gas Station' and @for = 'hasGas-desktop']"));
        element1.click();
    }

    @After
    public void tearDown(){
    // driver.close(); //To close current WebDriver instance
    // driver.quit();  // To close all the opened WebDriver instances
        //Add one more line
}

}
