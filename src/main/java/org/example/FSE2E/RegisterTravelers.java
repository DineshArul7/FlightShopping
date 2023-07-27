package org.example.FSE2E;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.commonUtils.Base;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.*;

public class RegisterTravelers  extends Base{

    Map<String, String> map = new LinkedHashMap<String, String>();

    String cartID = "";
    Response registerFlightsResponse = null;
    JsonPath jsonPath = null;

    public Response registerTravelers(Map<String, String> map, Response response) {

        this.map = map;
        registerFlightsResponse = response;
        baseURI = environment.get("Environment_URL");
        basePath = "/8.2/shoppingcart/cart/RegisterTravelers";

        logger.info("#######################################error with the above line");
        Response registertravelerresponse = given().relaxedHTTPSValidation().auth().oauth2(accessToken).body(getregistertravelersBody())
                .contentType(ContentType.JSON)
                .when().post().then().statusCode(200)
                .extract().response();
        return registertravelerresponse;
    }

    public Map getregistertravelersBody() {
        String body = "{\n" +
                "  \"Phones\": [],\n" +
                "  \"Travelers\": null,\n" +
                "  \"loyaltyPerson\": null,\n" +
                "  \"SpecialServiceRequest\": null,\n" +
                "  \"IsReserved\": false,\n" +
                "  \"IsSessionFirst\": false,\n" +
                "  \"IsUMNROptIn\": false,\n" +
                "  \"Channel\": \"WEB\",\n" +
                "  \"WorkFlowType\": 1\n" +
                "}";
        JsonPath jsonPath = new JsonPath(body);
        Map<String,Object> bodymap=jsonPath.getMap("$");
        bodymap.put("PetTravelers",getPetTravelers());
        bodymap.put("CartId",getCartID());
        bodymap.put("FlightTravelers",getFlightTravelers());
        bodymap.put("Characteristics",getCharacteristics());
        return bodymap;
    }

    public String getCartID() {
        jsonPath = new JsonPath(registerFlightsResponse.asString());
        cartID = jsonPath.getString("CartId");
        return cartID;
    }


    public List getCharacteristics(){
        List list=new LinkedList();
        LinkedHashMap charmap=new LinkedHashMap();
        charmap.put("Code","OMNICHANNELCART");
        charmap.put("Value",true);
        list.add(charmap);
        return list;
    }
    public List getFlightTravelers(){
        LocalDate date=LocalDate.now();
        date=date.minusYears(22);
        String DOB=date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        List list=new LinkedList();
        LinkedHashMap flighttravelerMap=new LinkedHashMap();

        flighttravelerMap.put("OxygenFlowRate",0);
        flighttravelerMap.put("SpecialServiceRequests",new LinkedList());
        flighttravelerMap.put("PtcList",new LinkedList());



        LinkedHashMap personsMap=new LinkedHashMap();
        personsMap.put("Surname","Arul");
        personsMap.put("GivenName","Dinesh");
        personsMap.put("MiddleName","");
        personsMap.put("Suffix","MD");
        personsMap.put("DateOfBirth",DOB);
        personsMap.put("Sex","M");
        LinkedHashMap documentsMap=new LinkedHashMap();
        documentsMap.putAll(personsMap);
        documentsMap.put("Type",0);
        List documnentList=new LinkedList();
        documnentList.add(documentsMap);
        personsMap.put("Documents",documnentList);
        LinkedHashMap countryOfResidence=new LinkedHashMap();
        personsMap.put("CountryOfResidence",countryOfResidence);
        personsMap.put("Type","ADT");

        LinkedHashMap travelerMap=new LinkedHashMap();
        travelerMap.put("Person",personsMap);

        flighttravelerMap.put("Traveler",travelerMap);
        list.add(flighttravelerMap);

return list;
    }

public List getPetTravelers(){
        List list=new LinkedList();
        LinkedHashMap pettraveler=new LinkedHashMap();
        return list;
}
}
