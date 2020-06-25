package Tests;

import Utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class TestBase {

    public WebDriver driver;
    public SoftAssert softAssert;

    @Parameters("driverName")
    @BeforeTest(alwaysRun = true)
    public void setup(String driverName){

        driver = Driver.getDriver(driverName);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

}
