import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.util.concurrent.TimeUnit;
/**
 * Created by User on 14.07.2017.
 */
public class AppStudent {
    WebDriver driver;
    /*String selector;
    public Boolean isPresent(String selector)
    {
        try {
            WebElement presentElement = driver.findElement(By.id(selector));
            return true;
        }
        catch (ElementNotFoundException e) {return false; }
    }
    public Boolean notPresent(String selector)
    {
        try {
            WebElement presentElement =driver.findElement(By.id(selector));
            return false;
        }
        catch (ElementNotFoundException e) {return true; }
    }
    public Boolean isLogIn(String selector)
    {
        try {
            WebElement presentElement = driver.findElement(By.className(selector));
            return true;
        }
        catch (ElementNotFoundException e) {return false; }
    }*/

    @BeforeTest
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe"); //tegs <properties> in pom.xml
        driver = new ChromeDriver();
        driver.get("http://soft.it-hillel.com.ua:3000/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(description ="Create new student")
    void TestCreateStudent(){
        WebElement buttomPlus=driver.findElement(By.className("mdi-content-add"));
        buttomPlus.click();

        WebElement name = driver.findElement(By.id("icon_prefix"));
        name.clear();
        name.sendKeys("SomeStudent");

        WebElement phone = driver.findElement(By.id("icon_telephone"));
        phone.clear();
        phone.sendKeys("8-xxx-xxx-xx-xx");

        WebElement downArrow = driver.findElement(By.xpath("//input[@value=\"Student\"]"));
        downArrow.click();



    }



    @AfterMethod
    void AfterM(ITestResult testResult){
        System.out.println(testResult.getMethod().getDescription());
        System.out.println(testResult.isSuccess());
    }

    @AfterTest
    void AfterT()
    { driver.close();
    }
    
}
