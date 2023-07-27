package org.example.FSE2E;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.commonUtils.Base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class LoadReservationAndDisplaycart extends Base{

    Map<String, String> map = new LinkedHashMap<String, String>();

    String cartID = "";
    Response registerTravelersResponse = null;
    JsonPath jsonPath = null;

    public Response loadReservationAndDisplaycart(Map<String, String> map, Response response) {

        this.map = map;
        registerTravelersResponse = response;
        baseURI = environment.get("Environment_URL");
        basePath = "/8.2/shoppingcart/cart/LoadReservationAndDisplayCart";
        Response loadReservationAndDisplaycartResponse = given().relaxedHTTPSValidation().auth().oauth2(accessToken).body(getLoadReservationAndDisplaycartBody())
                .contentType(ContentType.JSON)
                .when().post().then().statusCode(200)
                .extract().response();
        return loadReservationAndDisplaycartResponse;
    }

    public Map getLoadReservationAndDisplaycartBody() {

        Map<String,Object> bodymap=new LinkedHashMap<>();
        bodymap.put("CartId",getCartID());
        return bodymap;
    }

    public String getCartID() {
        jsonPath = new JsonPath(registerTravelersResponse.asString());
        cartID = jsonPath.getString("CartId");
        return cartID;
    }

}
