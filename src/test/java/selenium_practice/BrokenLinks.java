package selenium_practice;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrokenLinks {
    protected WebDriver driver;

    @Before
    public void setUp(){
        ChromeDriverManager.getInstance().setup();

        driver = new ChromeDriver();
        driver.navigate().to("https://www.google.com");

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.manage().window().maximize();//maximize the window
        driver.manage().deleteAllCookies();//delete cookies
    }

    @Test
    public void brokenLink() {
        List<WebElement> link = driver.findElements(By.tagName("a"));
        System.out.println(link.size());
        for (int i = 0; i < link.size(); i++) {
            WebElement element = link.get(i);//get all element from list
            System.out.println(element);//print all elemnts

            //Next line is to get the link of href by using getAttribute()method
            String urlVariable = element.getAttribute("href");
            try {
                verify(urlVariable);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    //Create method as verify()

        public static void verify(String urlLink) throws IOException {
            try {
                //Create an object of URL Class and pass the parameter as urlLink
                URL link = new URL(urlLink);
                // Create method HttpURLConnection
                HttpURLConnection httpcon = (HttpURLConnection)link.openConnection();
                httpcon.setConnectTimeout(2000);
                httpcon.connect();
                if(httpcon.getResponseCode()== 200){
                    System.out.println(httpcon.getResponseMessage());
                }if(httpcon.getResponseCode()== 400){
                    System.out.println(httpcon.getResponseMessage());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

        }
    @After
    public void teatDown(){
        driver.close();
        driver.quit();
    }


}
