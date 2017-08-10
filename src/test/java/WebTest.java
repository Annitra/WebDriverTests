import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by User on 09.07.2017.
 */

public class WebTest {
   Web myWeb;
   int gettingNumber ;
    @BeforeTest
    void setUp(){
        System.out.println("Get random number");
        myWeb = new Web();

    }

    @Test(description = "Test for the generator of the random numbers validation")
    void TestWeb() {
        String t = myWeb.randomNumber();
        // System.out.println(t);// String line
        int gettingNumber = Integer.parseInt(t);

        System.out.println("Checking: is gettingNumber "+gettingNumber+" in the interval [1,100]");
        // System.out.println(gettingNumber); //Integer
        // if (gettingNumber>=1 && gettingNumber<=100){System.out.println("Ura!");}  // Checking: is gettingNumber in the interval
        //gettingNumber=123;
        Assert.assertTrue((gettingNumber >= 1 && gettingNumber <= 100), "Getting number " + gettingNumber + " is not in the interval [1,100]");
    }


    @AfterMethod
    void afterM(ITestResult testResult){
        System.out.println(testResult.isSuccess());
        System.out.println(testResult.getMethod().getDescription());
    }
}
