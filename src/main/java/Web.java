import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by User on 07.07.2017.
 */
public class Web {
    String output;

   public String randomNumber() /*throws InterruptedException */ {
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe"); //tegs <properties> in pom.xml
            WebDriver driver = new ChromeDriver();
            //driver.get("https://www.random.org/");
            //driver.navigate().to("random.org");  // analog driver.get

            Thread.sleep(2000);

            driver.get("https://www.random.org/integers/");
            WebElement minInput = driver.findElement(By.name("num"));
            minInput.clear();
            minInput.sendKeys("1"); // wants to get only 1 random number
            WebElement columns = driver.findElement(By.xpath("//input[@name=\"col\"]"));
            columns.clear();
            columns.sendKeys("1"); // 1 columns because need only 1 number
            minInput.submit();
            //WebElement buttomGenerate = driver.findElement(By

           // buttomGenerate.click();
            Thread.sleep(2000);
            WebElement random = driver.findElement(By.tagName("pre"));
            output =random.getText();
            //output =random.getText().replace("  ","").replace(" ",""); //poluchenie znachenie tega
            driver.close();
            }
        catch(InterruptedException e)
        {e.printStackTrace();}
        return output ;
    }
}
