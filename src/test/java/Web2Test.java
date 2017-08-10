import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;

/**
 * Created by User on 10.07.2017.
 */
public class Web2Test {
    Web2 myWeb;
    int gettingNumber[];

    @BeforeTest
    void setUp() {
        System.out.println("Get random numbers");
        myWeb = new Web2();

    }

    @Test(description = "Test for the generator of the random numbers validation")
    void TestWeb() {
            int gettingNumber[] = myWeb.randomNumber();
            for (int i = 0; i <= gettingNumber.length; i++) {
                System.out.println(gettingNumber[i]);
            }
            System.out.println("Checking: is gettingNumber in the interval [1,100]");
            // System.out.println(gettingNumber); //Integer
            // if (gettingNumber>=1 && gettingNumber<=100){System.out.println("Ura!");}  // Checking: is gettingNumber in the interval
            //gettingNumber=123;
            // Assert.assertTrue((gettingNumber >= 1 && gettingNumber <= 100), "Getting number " + gettingNumber + " is not in the interval [1,100]");

    }


   /* @AfterMethod
    void afterM(ITestResult testResult){
        System.out.println(testResult.isSuccess());
        System.out.println(testResult.getMethod().getDescription());
    }*/
}
