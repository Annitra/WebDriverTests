
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * Created by User on 14.07.2017.
 */
public class AppStudent {
    WebDriver driver;

    void sleeppy(int sec) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    void AddingSomeone(String nameSelector, String nameInput,String phoneSelector,String phoneInput){
        WebElement buttomPlus=driver.findElement(By.className("mdi-content-add"));
        buttomPlus.click();

        WebElement name = driver.findElement(By.cssSelector(nameSelector));
        name.clear();
        name.sendKeys(nameInput);

        WebElement phone = driver.findElement(By.cssSelector(phoneSelector));
        phone.clear();
        phone.sendKeys(phoneInput);
    }
    void Saving(String selector){
        WebElement saveButton = driver.findElement(By.cssSelector(selector));
        saveButton.click();

        sleeppy(2);
        WebElement save2Button = driver.findElement(By.cssSelector(selector));
        save2Button.click();
    }

    void foundNewElement(String tagName){
        List<WebElement> elements = driver.findElements(By.tagName(tagName));
        WebElement element = elements.get(elements.size()-1);
        element.click();
    }
    boolean IfAddingElementPresent(String input){

        try {
            WebElement presentElement = driver.findElement(By.cssSelector(input));
            return true;
        }
        catch (org.openqa.selenium.NoSuchElementException e ) {
            return false; }
       }
    @BeforeTest
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe"); //tegs <properties> in pom.xml
        driver = new ChromeDriver();
        driver.get("http://soft.it-hillel.com.ua:3000/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(description ="Create new student")
    void TestCreateStudent(){
        AddingSomeone("input[id=\'icon_prefix\']","SomeStudent","input[type=\'tel\']","8-xxx-xxx-xx-xx");

        Saving("a.save-btn");

        foundNewElement("li");
        Assert.assertTrue(IfAddingElementPresent("label.active"));

    }
/*
    @Test(dependsOnMethods = {"TestCreateStudent"},description = "Delete new student")
    void TestDeleteStudent(){
        foundNewElement("li");
        WebElement checkDelete=driver.findElement(By.cssSelector("i.remove"));
                    checkDelete.click();
    }
*/
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
