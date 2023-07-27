package org.example.commonUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class Base
{
    Excel excel=new Excel();
   static WebDriver driver;
   public static Logger logger;
   public String Error="";
    public static String Environment="";
    public static String channelName="";

    public static String accessToken="";
    public static String xIDToken="";
    public static Map<String, String> environment = new LinkedHashMap<String,String>();

    public static ExtentReports extent;
    public static ExtentSparkReporter spark;
    public static ExtentTest test;
    public static int passed=0;
    public static int failed=0;
    public static int total=0;

    public static String startTime="";
    public static String endTime="";

    public static String ExecutionTime="";





    public void initializeDriver(String browser) throws MalformedURLException {

        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            desiredCapabilities.setBrowserName("firefox");
        } else  {
            WebDriverManager.chromedriver().setup();
            desiredCapabilities.setBrowserName("chrome");
        }

           //

            desiredCapabilities.setPlatform(Platform.WINDOWS);
            desiredCapabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
            driver=new ChromeDriver();
           //driver=new RemoteWebDriver(new URL("http://192.168.1.5:4444"),desiredCapabilities);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.get("https://www.booking.com/");


    }

    public void tearDownDriver(){
        driver.close();
    }



    public void initializeLogger(String testName){
        logger= LogManager.getLogger(testName);
        logger.info("Deekay initialize");

    }

    public void log(String message){
        test.info(message);
        logger.info(message);
    }

    @BeforeMethod
    public void beforeMethod(ITestResult result){
        logger.info("Before Method");
    }

    @AfterMethod
    public void afterMethod(){
        logger.info("After Method");
    }



}
