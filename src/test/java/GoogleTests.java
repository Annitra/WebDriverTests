import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 11.07.2017.
 */
public class GoogleTests {
    WebDriver driver;


    void sleeppy(int sec){
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e){}
    }
 boolean IfIsPresent(String input)
    {
        try {
            WebElement presentElement = driver.findElement(By.cssSelector(input));
            return true;
        }
        catch (org.openqa.selenium.NoSuchElementException e ) {
            return false; }
    }

    void Steps(String selectorInput,String input, String buttonInput)  {

        WebElement element=driver.findElement(By.cssSelector(selectorInput));
                   element.clear();
                   element.sendKeys(input);

        sleeppy(2);

        WebElement button = driver.findElement(By.cssSelector(buttonInput));
                   button.click();
        sleeppy(2);
   }
void FirstStep(String mail){
    Steps("input[type=\'email\']",mail,"div[role=\'button\']" );
}
void SecondStep(String password){
    Steps("input[name=\'password\']", password, "div[id=\'passwordNext\']" );
}
    @BeforeTest
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe"); //tegs <properties> in pom.xml
        driver = new ChromeDriver();
        driver.get("https://www.google.com.ua/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("gb_70")).click();
    }

      @Test(groups ="positive" ,description ="Log in with correct login",priority = 2)
    void TestLog(){
         FirstStep("anuta07.05@gmail.com");
         Assert.assertTrue(IfIsPresent("input[name=\'password\']"));
    }
      @Test(groups ="negative",description ="Log in with the uncorrect login",priority = 1)
    void TestUnLog(){
        FirstStep("anufdknflta07.05@gmail.com");
        Assert.assertFalse(IfIsPresent("input[name=\'password\']"));
    }

    @Test(groups ="positive", dependsOnMethods = {"TestLog"}, description = "Log in with correct password.",priority = 2)
    void TestPas(){
        SecondStep("parol");
        Assert.assertFalse(IfIsPresent("input[name=\'password\']"));

    }
    @Test(groups ="negative",dependsOnMethods = {"TestLog"}, description ="Log in with uncorrect password",priority = 1)
     void TestUnPas(){
        SecondStep("flinta12345672299");
        Assert.assertTrue(IfIsPresent("input[name=\'password\']"));

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
