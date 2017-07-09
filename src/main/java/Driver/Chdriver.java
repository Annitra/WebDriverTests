package Driver;

import org.apache.xpath.operations.String;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by User on 07.07.2017.
 */
public class Chdriver {

    public static void main(String[] args) {
      try {
          System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
          WebDriver driver = new ChromeDriver();
          driver.get("https://www.random.org/");
          Thread.sleep(2000);
          WebElement minInput = driver.findElement(By.id(""));
          minInput.sendKeys("100");
          minInput.getText();
      }
      catch ()

    }

}
