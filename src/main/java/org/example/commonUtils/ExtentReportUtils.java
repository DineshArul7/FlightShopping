package org.example.commonUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;

public class ExtentReportUtils extends Base{



    public void startReport(){
        extent =new ExtentReports();
        spark=new ExtentSparkReporter(System.getProperty("user.dir")+"/target/extentReport/index.html");
        extent.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("FlightShopping");
        spark.config().setReportName("AutomationByRestAssured");
    }

    public void extentlogInitializer(String testName){
        test=extent.createTest(testName);
        logger= LogManager.getLogger(testName);
    }
}
