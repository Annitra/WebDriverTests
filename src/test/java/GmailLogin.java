import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 11.07.2017.
 */
public class GmailLogin {
    WebDriver driver;
    String selector;
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
    }

    @BeforeTest
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe"); //tegs <properties> in pom.xml
         driver = new ChromeDriver();
        driver.get("https://www.google.com.ua/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement uvijtu = driver.findElement(By.id("gb_70"));
        uvijtu.click();
    }

      @Test(description ="Log in with uncorrect login",priority = 1)
    void TestUnLog(){
        WebElement login=driver.findElement(By.xpath("//input[@type=\"email\"]"));
        login.clear();
        login.sendKeys("anuta07.05gmail.com");

        WebElement buttom = driver.findElement(By.id("identifierNext"));
        buttom.click();

        Assert.assertFalse(notPresent("headingSubtext"));
    }

      @Test(description ="Log in with the correct login",priority = 2)
    void TestLog(){

        WebElement login=driver.findElement(By.xpath("//input[@type=\"email\"]"));
        login.clear();
        login.sendKeys("anuta07.05@gmail.com");

        WebElement buttom = driver.findElement(By.id("identifierNext"));
        buttom.click();

        Assert.assertTrue(isPresent("profileIdentifier"));
    }


    @Test(dependsOnMethods = {"TestLog"}, description = "Log in with correct password. Previus: Need to put in correct password for account!",priority = 2)
    void TestPas(){
        WebElement pasfield = driver.findElement(By.name("password"));
        pasfield.clear();
        pasfield.sendKeys("your password");  // Need to put in correct password for account

        WebElement buttom = driver.findElement(By.id("passwordNext"));
        buttom.click();

        Assert.assertTrue(isPresent("hplogo"));

    }

     @Test(dependsOnMethods = {"TestLog"}, description ="Log in with uncorrect password",priority = 1)
    void TestUnPasLog(){
        WebElement pasfield = driver.findElement(By.name("password"));
        pasfield.clear();
        pasfield.sendKeys("qwerty"); //add entering from

         WebElement buttom = driver.findElement(By.id("passwordNext"));
         buttom.click();

        Assert.assertFalse(notPresent("profileIdentifier"));
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
