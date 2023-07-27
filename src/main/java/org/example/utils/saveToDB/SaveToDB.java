package org.example.utils.saveToDB;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.poi.ss.formula.functions.Days;
import org.example.commonUtils.Base;
import org.example.commonUtils.CommonUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.*;

public class SaveToDB extends Base {

    CommonUtils commonUtils=new CommonUtils();


    public Response savetoDB() {

        baseURI = "http://10.164.67.113:8700";
        basePath = "/AutomationExecutionData/AutomationExecutions.asmx";
        Response mapViewSearchResponse = given().relaxedHTTPSValidation().auth().oauth2(accessToken).body(getMapViewSearchBody())
                .contentType("application/soap+xml;charset=UTF-8;action=\"http://tempuri.org/SaveAutomationRuns\"\n")

                .log().all()
                .when().post().then().statusCode(200)
                .log().all()
                .extract().response();
        test.info("Deekay here to test the logs");
        return mapViewSearchResponse;

    }

    public String getMapViewSearchBody() {
        Calendar cal=new GregorianCalendar();
        int week=cal.get(Calendar.WEEK_OF_MONTH);
        String month=new SimpleDateFormat("MMM").format(cal.getTime());
        String year=new SimpleDateFormat("YYYY").format(cal.getTime());
        String relName=commonUtils.getProperty("ReleaseName");
        String releaseName=year+" "+month+" "+week+" "+" "+relName;
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("mm");
        int executionTime=Integer.parseInt(LocalDateTime.parse(endTime).format(dateTimeFormatter))-Integer.parseInt(LocalDateTime.parse(startTime).format(dateTimeFormatter));



        String body="<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soap:Header/>\n" +
                "   <soap:Body>\n" +
                "      <tem:SaveAutomationRuns>\n" +
                "         <tem:ProjectID>"+commonUtils.getProperty("ProjectID")+"</tem:ProjectID><tem:ExecutionStartDateTime>"+startTime+"</tem:ExecutionStartDateTime>\n" +
                "         <tem:ExecutionEndDateTime>"+endTime+"</tem:ExecutionEndDateTime>\n" +
                "         <tem:ExecutionTime>"+executionTime+"</tem:ExecutionTime>\n" +
                "         <tem:TotalCasesExecuted>"+passed+"</tem:TotalCasesExecuted>\n" +
                "         <tem:TotalCasesPassed>"+total+"</tem:TotalCasesPassed>\n" +
                "         <tem:TotalCasesFailed>"+failed+"</tem:TotalCasesFailed><tem:ReleaseName>"+releaseName+"</tem:ReleaseName><tem:Environment>"+System.getProperty("env")+"</tem:Environment><tem:VMUsed>"+commonUtils.getProperty("VMUsed")+"</tem:VMUsed><tem:TestingType>"+commonUtils.getProperty("TestingType")+"</tem:TestingType><tem:TestingPurpose>"+commonUtils.getProperty("TestingPurpose")+"</tem:TestingPurpose><tem:ReportedBy>"+commonUtils.getProperty("ReportedBy")+"</tem:ReportedBy>\n" +
                "      </tem:SaveAutomationRuns>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>";
        return body;
    }


}
