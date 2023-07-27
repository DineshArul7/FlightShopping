package org.example.listeners;


import com.aventstack.extentreports.reporter.ExtentReporter;
import org.example.commonUtils.Base;
import org.example.commonUtils.CommonUtils;
import org.example.commonUtils.Excel;
import org.example.commonUtils.ExtentReportUtils;
import org.example.utils.emailGeneration.EmailGeneration;
import org.example.utils.saveToDB.SaveToDB;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements IExecutionListener,ITestListener, ExtentReporter {
Base base=new Base();
Excel excel=new Excel();
CommonUtils commonUtils=new CommonUtils();
ExtentReportUtils reportUtils=new ExtentReportUtils();
SaveToDB saveToDB=new SaveToDB();
EmailGeneration emailGeneration=new EmailGeneration();


    @Override
    public void onExecutionStart() {
        base.startTime=commonUtils.getCurrentDate("yyyy-MM-dd'T'hh:mm:ss");
        base.initializeLogger(getClass().getName());
        base.logger.info("onExecutionStart");
        System.out.println("println"+"onExecutionStart");
        base.Environment=System.getProperty("env");
        if(base.Environment==null||base.Environment.equalsIgnoreCase("")){
            base.Environment=commonUtils.getProperty("Environment");
        }
        base.channelName=commonUtils.getProperty("channelName");
       base.environment= excel.readExcel("Environment","Environment_Name", base.Environment).get(0);
       base.logger.info("Enviroment name -------"+base.Environment);
       reportUtils.startReport();

    }

    @Override
    public void onExecutionFinish() {

        base.log("Deeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeekay");
        base.endTime=commonUtils.getCurrentDate("yyyy-MM-dd'T'hh:mm:ss");
        //LocalDate endtime=LocalDate.parse(base.endTime)-LocalDate.parse(base.startTime);
        base.ExecutionTime= "";
        base.logger.info("onExecutionFinish");
        System.out.println("println"+"onExecutionFinish");
        base.log(base.extent.getStats().toString());
        base.log("#################################################################### Total ="+base.total+"  Passed ="+base.passed+" Failed = "+base.failed);
        saveToDB.savetoDB();
        //emailGeneration.sample();
        base.extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
       base.logger.info("onTestStart Start");
        System.out.println("println"+"onTestStart");
        base.total++;

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        base.logger.info("onTestSuccess");
        System.out.println("println"+"onTestSuccess");
        base.passed++;
        base.test.pass("");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        base.logger.info("onTestFailure");
        System.out.println("println"+"onTestFailure");
        base.failed++;
        base.test.fail("");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        base.logger.info("onTestSkipped");
        System.out.println("println"+"onTestSkipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        base.logger.info("onTestFailedButWithinSuccessPercentage");
        System.out.println("println"+"onTestFailedButWithinSuccessPercentage");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        base.logger.info("onTestFailedWithTimeout");
        System.out.println("println"+"onTestFailedWithTimeout");
    }

    @Override
    public void onStart(ITestContext context) {
        base.logger.info("onStart");
        System.out.println("println"+"onStart");
    }

    @Override
    public void onFinish(ITestContext context) {
        base.logger.info("onFinish");
        System.out.println("println"+"onFinish");
    }
}
