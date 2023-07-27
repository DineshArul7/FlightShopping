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

public class AwardMapSearchViewTopSearch extends Base {

    Map<String, String> map = new LinkedHashMap<String, String>();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.now();
    String date = localDate.format(dateTimeFormatter);


    public Response awardmapSearchView(Map<String, String> map) {

        this.map = map;
        baseURI = environment.get("MapViewSearchURL");
        basePath = "/api/award/MapViewSearchPrice";
        Response mapViewSearchResponse = given().relaxedHTTPSValidation().auth().oauth2(accessToken).body(getMapViewSearchBody())
                .contentType(ContentType.JSON)
                .log().all()
                .when().post().then().statusCode(200)
                .log().all()
                .extract().response();
        test.info("Deekay here to test the logs");
        return mapViewSearchResponse;


    }

    public Map getMapViewSearchBody() {
        String LiveSearchDestFromExcel = map.get("LiveSearchDestination");
        List<Object> Destinations=new LinkedList<>();
        Destinations.add(LiveSearchDestFromExcel);
        AwardMapSearchViewNonTopSearch awardMapSearchViewNonTopSearch=new AwardMapSearchViewNonTopSearch();
        Map<String,Object> bodyMap=awardMapSearchViewNonTopSearch.getMapViewSearchBody(map);
        bodyMap.put("Destination",Destinations);
        return bodyMap;
    }


}
