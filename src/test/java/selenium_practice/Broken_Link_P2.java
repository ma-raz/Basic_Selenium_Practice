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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Broken_Link_P2 {
    protected WebDriver driver;

    @Before
    public void setUp(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://www.google.com");

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

    }

    @Test
    public void broken_link() throws IOException {

        //collecting all 'a' tag
        List <WebElement> linksList = driver.findElements(By.tagName("a"));
        //adding 'img' tag with 'a' tag
        linksList.addAll(driver.findElements(By.tagName("img")));

        //Result of total tag(a+img)
        System.out.println("Size of full links and images -->" +linksList.size());
        //From total list now we have to deduct all of inActive tag(a+img) with JavaScript tag also

        List <WebElement> activeLinks = new ArrayList<WebElement>();
        for(int i =0;i<linksList.size();i++){
            if(linksList.get(i).getAttribute("href")!= null && (!linksList.get(i).getAttribute("href").contains("javascript")))
            {
                activeLinks.add(linksList.get(i));
                System.out.println("Size of active links and images -->" + activeLinks.size());

                //Iterate active links(a+img) exclude javaScript
                for(int j = 0; j<activeLinks.size();j++){
                 //Here first we create object of URL class and getAttribute of href tag. Secondly we are casting our URL class. Sign is (HttpURLConnection).
                 // Then we create connection object of HttpURLConnection class.Finally we are open connection by use openConnection()method.
                   HttpURLConnection connection =
                     (HttpURLConnection)new URL(activeLinks.get(j).getAttribute("href")).openConnection();
                   connection.connect();
                   String  response = connection.getResponseMessage();
                   connection.disconnect();
                    System.out.println(activeLinks.get(j).getAttribute("href")+ "---->"+response);
                }
            }
        }
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();

    }
}
