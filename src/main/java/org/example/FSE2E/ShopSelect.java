package org.example.FSE2E;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.commonUtils.Base;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class ShopSelect extends Base {
    Map<String, String> map = new LinkedHashMap<String,String>();

    String cartID="";
    String BBXCellId="" ;
    String BBXSolutionSetId="";
    Response shopResponse=null;
    JsonPath jsonPath=null;

    public Response shopSelect(Map<String, String> map, Response response) {

        this.map=map;
        shopResponse=response;
        baseURI = environment.get("Environment_URL");
        basePath = "/8.0/flight/flightshopping/api/Shopselect";
        Response shopSelectResponse=given().relaxedHTTPSValidation().auth().oauth2(accessToken).body(getshopSelectBody().prettify())
                .contentType(ContentType.JSON)
          //      .log().all()
                .when().post().then().statusCode(200)
            //    .log().all()
                .extract().response();
        return shopSelectResponse;
    }

    public JsonPath getshopSelectBody(){
        String body="{\n" +
                "  \"BBXSolutionSetId\": \""+getBBXSolutionSetId()+"\",\n" +
                "  \"BBXCellId\": \""+getBBXCellId()+"\",\n" +
                "  \"CartId\": \""+getCartID()+"\",\n" +
                "  \"ChannelType\": \"WEB\",\n" +
                "  \"CountryCode\": \"US\",\n" +
                "  \"LangCode\": \"en-US\",\n" +
                "  \"FareType\": \"Refundable\"\n" +
                "}";
        JsonPath jsonPath=new JsonPath(body);
        return jsonPath;
    }

    public String getCartID(){
        jsonPath=new JsonPath(shopResponse.asString());
        cartID=jsonPath.getString("CartId");
        return cartID;
    }
    public String getBBXCellId(){
        jsonPath=new JsonPath(shopResponse.asString());
        BBXCellId=jsonPath.getString("Trips[0].Flights[0].Products[0].ProductId");
        return BBXCellId;
    }
    public String getBBXSolutionSetId(){
        jsonPath=new JsonPath(shopResponse.asString());
        BBXSolutionSetId=jsonPath.getString("Trips[0].BBXSolutionSetId");
        return BBXSolutionSetId;
    }
}
