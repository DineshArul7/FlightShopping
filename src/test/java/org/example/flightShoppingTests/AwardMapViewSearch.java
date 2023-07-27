package org.example.flightShoppingTests;


import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import org.example.AuthToken.AuthToken;
import org.example.awardMapViewSearch.*;
import org.example.commonUtils.Base;
import org.example.commonUtils.Excel;
import org.example.commonUtils.ExtentReportUtils;
import org.example.dataProvider.DataProviderdata;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class AwardMapViewSearch extends Base {

    InPolygan inPolygan=new InPolygan();
    AwardMapSearchViewNonTopSearch awardMapSearchView=new AwardMapSearchViewNonTopSearch();
    AwardMapSearchViewTopSearch awardMapSearchViewTopSearch=new AwardMapSearchViewTopSearch();
    AwardMapSearchViewLiveSearch awardMapSearchViewLiveSearch=new AwardMapSearchViewLiveSearch();
    AwardMapSearchView_TopSearch_NoCache awardMapSearchViewTopSearchNoCache=new AwardMapSearchView_TopSearch_NoCache();
    AuthToken authToken = new AuthToken();
    ExtentReportUtils reportUtils=new ExtentReportUtils();
    Excel excel=new Excel();
    @BeforeClass
    public void beforeclass(){
        System.setProperty("sheetname","AwardMapViewSearch");
    }
    @Test(dataProvider = "data", dataProviderClass = DataProviderdata.class)
    public void MapViewSearchPrice_NonTopSearch(Object[] data) {

        logger.info("This is written Newly ####################################");
        logger.info("Deekay");
        Map<String, String> map = (Map<String, String>) data[0];
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //    System.out.println("Key    " + entry.getKey());
            //    System.out.println("Value  " + entry.getValue());
        }
        reportUtils.extentlogInitializer(map.get("S.NO") + map.get("Test Script Name") + map.get("Scenario"));
        authToken.getToken(channelName);
        Response inPoluganResponse=inPolygan.inPolygan(map);
        awardMapSearchView.awardmapSearchView(map,inPoluganResponse);
        map.put("Status",test.getStatus().toString());
        excel.writetoExcel(map,Integer.parseInt(map.get("S.NO")),System.getProperty("sheetname"));
    }
    @Test(dataProvider = "data", dataProviderClass = DataProviderdata.class)
    public void MapViewSearchPrice_TopSearch(Object[] data) {

        logger.info("This is written Newly ####################################");
        logger.info("Deekay");
        Map<String, String> map = (Map<String, String>) data[0];
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //    System.out.println("Key    " + entry.getKey());
            //    System.out.println("Value  " + entry.getValue());
        }
        reportUtils.extentlogInitializer(map.get("S.NO") + map.get("Test Script Name") + map.get("Scenario"));
        authToken.getToken(channelName);
        awardMapSearchViewTopSearch.awardmapSearchView(map);
        map.put("Status",test.getStatus().toString());
        excel.writetoExcel(map,Integer.parseInt(map.get("S.NO")),System.getProperty("sheetname"));
    }
    @Test(dataProvider = "data", dataProviderClass = DataProviderdata.class)
    public void MapViewSearchPrice_LiveSearch(Object[] data) {

        logger.info("This is written Newly ####################################");
        logger.info("Deekay");
        Map<String, String> map = (Map<String, String>) data[0];
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //    System.out.println("Key    " + entry.getKey());
            //    System.out.println("Value  " + entry.getValue());
        }
        reportUtils.extentlogInitializer(map.get("S.NO") + map.get("Test Script Name") + map.get("Scenario"));
        authToken.getToken(channelName);
        awardMapSearchViewLiveSearch.awardmapSearchView(map);
        map.put("Status",test.getStatus().toString());
        excel.writetoExcel(map,Integer.parseInt(map.get("S.NO")),System.getProperty("sheetname"));
    }
    @Test(dataProvider = "data", dataProviderClass = DataProviderdata.class)
    public void MapViewSearchPrice_TopSearch_NoCache(Object[] data) {

        logger.info("This is written Newly ####################################");
        logger.info("Deekay");
        Map<String, String> map = (Map<String, String>) data[0];
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //    System.out.println("Key    " + entry.getKey());
            //    System.out.println("Value  " + entry.getValue());
        }
        reportUtils.extentlogInitializer(map.get("S.NO") + map.get("Test Script Name") + map.get("Scenario"));
        authToken.getToken(channelName);
        awardMapSearchViewTopSearchNoCache.awardmapSearchView(map);
        map.put("Status",test.getStatus().toString());
        excel.writetoExcel(map,Integer.parseInt(map.get("S.NO")),System.getProperty("sheetname"));
    }
    @AfterClass
    public void afterclass(){
        /*
        List<com.aventstack.extentreports.model.Test> testList=spark.getReport().getTestList();
        int passed =0;
        int failed = 0;
        int total=testList.size();
        for(com.aventstack.extentreports.model.Test test:testList){
            if(test.getStatus().toString().equals("pass")){
                passed++;
            }
        }
        failed=total-passed;
        logger.info("#################################################################### Total ="+total+"  Passed ="+passed+" Failed = "+failed);
   */
    }


}
