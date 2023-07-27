package org.example.FSE2E;

import static io.restassured.RestAssured.*;
import  io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.commonUtils.Base;
import org.example.commonUtils.Excel;
import org.testng.annotations.Test;

import java.net.StandardSocketOptions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Shop extends Base {
//    public class Shop  {
Excel excel=new Excel();
    Map<String, String> map = new LinkedHashMap<String,String>();

    Response response=null;


    public Response shop(Map<String, String> map) {

        this.map=map;
        logger.info(map.get("AwardTravel"));
        logger.info(map.get("MP_Login"));
        baseURI = environment.get("Environment_URL");
        basePath = "/8.0/flight/flightshopping/api/shop";
        logger.info(environment.get("Environment_URL"));
        //map.containsKey("");
        response=given().relaxedHTTPSValidation().auth().oauth2(accessToken).body(shopBody().prettify())
                .contentType(ContentType.JSON)
            //    .log().all()
                .when().post().then().statusCode(200)
          //      .log().all()
                .extract().response();
        return response;
    }


    public JsonPath  shopBodyy(){
        String ChannelType="";
        String LangCode="";
        String CountryCode="";

        String Body="{\n" +
                "  \"AccessCode\": \"1A7370E9-A532-4376-BD39-41795F01321C\",\n" +
                "  \"CabinPreferenceMain\": \"economy\",\n" +
                "  \"CalendarLengthOfStay\": -1,\n" +
                "  \"CalendarFilters\": {\n" +
                "    \"SortType\": 0,\n" +
                "    \"PricingSortProductType\": null,\n" +
                "    \"PaginationOptions\": null,\n" +
                "    \"Filters\": {\n" +
                "      \"PriceScheduleOptions\": {\n" +
                "        \"Stops\": 1,\n" +
                "        \"PriceFilter\": null,\n" +
                "        \"ArrivalFilter\": null,\n" +
                "        \"DepartureFilter\": null,\n" +
                "        \"TripDuration\": null,\n" +
                "        \"LayoverDuration\": null\n" +
                "      },\n" +
                "      \"AirportOptions\": null,\n" +
                "      \"ExperienceOptions\": null\n" +
                "    }\n" +
                "  },\n" +
                "  \"ChannelType\": \"WEB\",\n" +
                "  \"CountryCode\": \"US\",\n" +
                "  \"DecodesRequested\": true,\n" +
                "  \"DisablePricingBySlice\": true,\n" +
                "  \"InclCancelledFlights\": true,\n" +
                "  \"InclOAMain\": true,\n" +
                "  \"InclStarMain\": true,\n" +
                "  \"InclUACodeshares\": true,\n" +
                "  \"InclUAMain\": true,\n" +
                "  \"InclUARegionals\": true,\n" +
                "  \"InitialShop\": true,\n" +
                "  \"LangCode\": \"en-US\",\n" +
                "  \"PageIndex\": 1,\n" +
                "  \"PageSize\": 1000,\n" +
                "  \"RecentSearchKey\": \"ORDDEN9/2/2023\",\n" +
                "  \"SearchTypeSelection\": 1,\n" +
                "  \"SimpleSearch\": true,\n" +
                "  \"SortType\": \"bestmatches\",\n" +
                "  \"StopsInclusive\": true,\n" +
                "  \"TrueAvailability\": true,\n" +
                "  \"UpgradeComplimentaryRequested\": true,\n" +
                "  \"FareType\": \"Refundable\",\n" +
                "  \"Trips\": "+buildtripNode() +",\n" +
                "  \"PaxInfoList\": "+buildpaxinfolist() +
                "}";
        JsonPath json=new JsonPath(Body);
        System.out.println(json.prettify());

        return json;
    }

    public String buildtripNode(){
        int tripindex=1;
        String Origin="ORD";
        String Destination="DEN";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date=LocalDate.now();

        String DepartDate=date.plusDays(90).format(dateTimeFormatter);

        String trip="{\n" +
                "      \"Destination\": \""+Destination+"\",\n" +
                "      \"Origin\": \""+Origin+"\",\n" +
                "      \"DepartDate\": \""+DepartDate+"\",\n" +
                "      \"Index\": "+tripindex+",\n" +
                "      \"NonStopMarket\": true,\n" +
                "      \"TripIndex\": "+tripindex+",\n" +
                "      \"Flights\": [],\n" +
                "      \"ChangeType\": 0,\n" +
                "    }";
        String tripNode="";
        if(tripNode.equalsIgnoreCase("")){

            tripNode="["+trip;
        }else tripNode=tripNode+","+trip;
        tripNode=tripNode+"]";
        //JsonPath jsonPath=new JsonPath(tripNode);
       // System.out.println(jsonPath.prettify());
        return tripNode;
    }

    public String buildpaxinfolist(){
        int PaxType=1;
        String paxinfolist="{\n" +
                "      \"PaxType\": "+PaxType+",\n" +
                "      \"DateOfBirth\": \"06/24/1993\"\n" +
                "    }";
        String paxNode="";
        if(paxNode.equalsIgnoreCase("")){

            paxNode="["+paxinfolist;
        }else paxNode=paxNode+","+paxinfolist;
        paxNode=paxNode+"]";
        //JsonPath jsonPath=new JsonPath(tripNode);
        // System.out.println(jsonPath.prettify());
        return paxNode;
    }

    public JsonPath shopBody(){
        String body="{\n" +
                "  \"Characteristics\": [\n" +
                "    {\n" +
                "      \"Code\": \"SOFT_LOGGED_IN\",\n" +
                "      \"Value\": \"false\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"Code\": \"UsePassedCartId\",\n" +
                "      \"Value\": \"false\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"ChannelType\": \"WEB\",\n" +
                "  \"CountryCode\": \"US\",\n" +
                "  \"InitialShop\": true,\n" +
                "  \"LangCode\": \"en-US\",\n" +
                "  \"PageIndex\": 1,\n" +
                "  \"PageSize\": 1000,\n" +
                "  \"SortType\": \"bestmatches\",\n" +
                "  \"StopsInclusive\": true,\n" +
                "  \"TrueAvailability\": true,\n" +
                "   \"PaxInfoList\": "+buildpaxinfolist()+",\n"+
                "  \"Trips\": "+buildtripNode()+",\n"+
                "  \"FareType\": \"Refundable\"\n" +
                "}";
        JsonPath jsonPath=new JsonPath(body);
        return jsonPath;
    }

    public void sampletest(){
shopBody();
}
}
