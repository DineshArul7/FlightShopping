package org.example.awardMapViewSearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.commonUtils.Base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.*;

public class AwardMapSearchViewNonTopSearch extends Base {

    Map<String, String> map = new LinkedHashMap<String, String>();
    Response InpolyganResponse=null;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.now();
    String date = localDate.format(dateTimeFormatter);


    public Response awardmapSearchView(Map<String, String> map,Response InpolyganResponse) {

        this.map = map;
        this.InpolyganResponse=InpolyganResponse;
        baseURI = environment.get("MapViewSearchURL");
        basePath = "/api/award/MapViewSearchPrice";
        Response mapViewSearchResponse = given().relaxedHTTPSValidation().auth().oauth2(accessToken).body(getMapViewSearchBody(map))
                .contentType(ContentType.JSON)
                .log().all()
                .when().post().then().statusCode(200)
                .log().all()
                .extract().response();
        test.info("Deekay here to test the logs");
        return mapViewSearchResponse;


    }

    public Map getMapViewSearchBody(Map<String,String> map) {

        String pax = map.get("Pax");
        String deptAirport = map.get("Origin");
        String Language = map.get("Language");
        String Latitude1 = map.get("Latitude");
        String Longitude1 = map.get("Longitude");
        String DepartureDate1 = map.get("DepartureDate");
        String ReturnDate1 = map.get("ReturnDate");
        ReturnDate1=localDate.plusDays(44).format(dateTimeFormatter);
        String countryCode = "US";
        DepartureDate1=localDate.plusDays(19).format(dateTimeFormatter);
        String MaxMiles = map.get("MaxMiles");
        int miles = 0;
        if (!MaxMiles.equalsIgnoreCase("")) {
            miles = Integer.parseInt(MaxMiles);
        }

        boolean flexibledates = Boolean.parseBoolean(map.get("FlexibleDates"));
        boolean specificdates = Boolean.parseBoolean(map.get("SpecificDates"));
        String MaxStopCount = map.get("MaxStopCount");
        String MaxPriceAmount = map.get("MaxPriceAmount");
        String MaxPriceCurrency = map.get("MaxPriceCurrency");
        int tripType = Integer.parseInt(map.get("TripType"));
        String destinations = map.get("Destinations");
        List<Object> Destinations=new LinkedList<>();
        try{
            JsonPath inpolyganjson = new JsonPath(InpolyganResponse.asString());
            List<Map> DestinationsAirports = inpolyganjson.getList("DestinationAirports");

            for(Map map1:DestinationsAirports){
                Destinations.add(map1.get("IATACode"));
            }
        }catch (Exception e){
            test.info("No Polygan response found");
            logger.warn("No Polygan response found");
        }


        int paxcount = Integer.parseInt(map.get("Count"));

        String guid = UUID.randomUUID().toString();

        LinkedHashMap paxObject = new LinkedHashMap();
        paxObject.put("Count", paxcount);
        paxObject.put("Ptc", pax);
        paxObject.put("residencyCountry", countryCode);
        Object MaxStopCount1 = 0;
        Object MaxPriceAmount1 = 0;
        Object MaxPriceCurrency1 = 0;

        if (MaxStopCount.equals("NA")) {
            MaxStopCount1 = null;
        } else {
            MaxStopCount1 = MaxStopCount;
        }
        if (MaxPriceAmount.equals("NA")) {
            MaxPriceAmount1 = null;
        } else {
            MaxPriceAmount1 = MaxPriceAmount;
        }
        if (MaxPriceCurrency.equals("NA")) {
            MaxPriceCurrency1 = null;
        } else {
            MaxPriceCurrency1 = MaxPriceCurrency;
        }
        LinkedHashMap mainObj = new LinkedHashMap();
        if (specificdates) {
            mainObj.put("DepartureDate", DepartureDate1);
            mainObj.put("DaysAfter", null);

            if (tripType == 0) {
                mainObj.put("ReturnDate", null);
            } else {
                mainObj.put("ReturnDate", ReturnDate1);
            }
        }else {
            int DaysAfter = 30;
            mainObj.put("DepartureDate", null);
            mainObj.put("DaysAfter", DaysAfter);
            mainObj.put("ReturnDate", null);

        }
            mainObj.put("Destination", Destinations);
            mainObj.put("MaxLengthOfStay", null);
            mainObj.put("MaxPriceAmount", MaxPriceAmount1);
            mainObj.put("MaxPriceCurrency", MaxPriceCurrency1);
            mainObj.put("MaxStopCount", MaxStopCount1);
            mainObj.put("MinLengthOfStay", null);
            mainObj.put("Origin", deptAirport);
            mainObj.put("PermittedCarrier", null);
            mainObj.put("trackingId", guid);
            mainObj.put("clientCurrentDate", date);
            mainObj.put("Passenger", paxObject);
            mainObj.put("AwardTravel", true);
            mainObj.put("TripType", tripType);
            if (MaxMiles.equals("")) {
            } else {
            mainObj.put("MaxMiles", miles);
            }
        ObjectMapper objectMapper=new ObjectMapper();
        String body= null;
        try {
            body = objectMapper.writeValueAsString(mainObj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(body);
        JsonPath jsonPath = new JsonPath(body);
        Map<String, Object> bodymap = jsonPath.getMap(".");
        return bodymap;
    }


}
