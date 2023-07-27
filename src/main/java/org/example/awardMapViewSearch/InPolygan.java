package org.example.awardMapViewSearch;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;
import org.example.commonUtils.Base;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.*;

public class InPolygan extends Base {

    Map<String, String> map = new LinkedHashMap<String, String>();


    public Response inPolygan(Map<String, String> map) {

        this.map = map;
        baseURI = environment.get("Environment_URL");
        basePath = "/8.0/ReferenceData/Airports/InPolygon";
        Response inpolyganResponse = given().relaxedHTTPSValidation().auth().oauth2(accessToken).body(getInPolyganBody())
                .contentType(ContentType.JSON)
                .when().post().then().statusCode(200)
                .extract().response();
        return inpolyganResponse;


    }

    public Map getInPolyganBody() {

        String[] lattitude=map.get("Latitude").split("\\|");
        String[] longitude=map.get("Longitude").split("\\|");



        String body="{\n" +
                "  \"LangCode\": \""+map.get("Language")+"\",\n" +
                "  \"OriginCode\": \""+map.get("Origin")+"\",\n" +
                "  \"Vertices\": [\n" +
                "    {\n" +
                "      \"Latitude\": "+lattitude[0]+",\n" +
                "      \"Longitude\": "+longitude[0]+"\n" +
                "    },\n" +
                "    {\n" +
                "      \"Latitude\":"+lattitude[1]+",\n" +
                "      \"Longitude\": "+longitude[1]+"\n" +
                "    },\n" +
                "    {\n" +
                "      \"Latitude\": "+lattitude[2]+",\n" +
                "      \"Longitude\": "+longitude[2]+"\n" +
                "    },\n" +
                "    {\n" +
                "      \"Latitude\": "+lattitude[3]+",\n" +
                "      \"Longitude\": "+longitude[3]+"\n" +
                "    },\n" +
                "    {\n" +
                "      \"Latitude\": "+lattitude[0]+",\n" +
                "      \"Longitude\": "+longitude[0]+"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        System.out.println(body);
        JsonPath jsonPath = new JsonPath(body);
        Map<String, Object> bodymap = jsonPath.getMap(".");
        return bodymap;
    }


}
