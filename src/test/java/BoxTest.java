
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * Created by User on 18.07.2017.
 */
public class BoxTest {
    WebDriver driver;
    private TestRail trReport;

    void sleeppy(int sec){
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e){}
    }

    void LoginToBox(){
        driver.findElement(By.cssSelector("a.user-menu_log-in")).click();
        driver.findElement(By.cssSelector("input[name=\'login\']")).sendKeys("rvalek@intersog.de");
        driver.findElement(By.cssSelector("input[name=\'password\']")).sendKeys("welcome2hillel");
        driver.findElement(By.cssSelector("button[type=\'submit\']")).click();

    }

    void UploadFile(String path){
        File file = new File(path);
        driver.findElement(By.cssSelector("input[type=\'file\']")).sendKeys(file.getAbsolutePath());
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

    @BeforeTest
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe"); //tegs <properties> in pom.xml
        driver = new ChromeDriver();
        driver.get("https://www.box.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        LoginToBox();
    }

    @BeforeClass
    protected void prepareTestRailRun() throws Exception {
        trReport = new TestRail();
        trReport.startRun(1, "DropBox - " + new SimpleDateFormat("dd.MM.yy HH:mm").format(new Date()));
    }

    @Test(description ="3.Test for uploading file to the box.com")
    void UploadFile(){

        UploadFile("C:\\Users\\User\\IdeaProjects\\FirstWebD\\testingfileAnna.jpg");
        sleeppy(5);
        Assert.assertTrue(IfIsPresent("li[data-name=\'testingfileAnna.jpg\']"));

    }

    @AfterMethod
    void AfterM(ITestResult testResult){
        System.out.println(testResult.getMethod().getDescription());
        System.out.println(testResult.isSuccess());

        String testDescription = testResult.getMethod().getDescription();
        trReport.setResult(Integer.parseInt(testDescription.substring(0, testDescription.indexOf("."))), testResult.getStatus());
    }


    @AfterClass
    protected void closeTestRailRun() throws Exception {
        trReport.endRun();
    }

   @AfterTest
    void AfterT()
    { driver.close();
    }
}
