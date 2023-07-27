package org.example.FSE2E;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.commonUtils.Base;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class RegisterFlights extends Base {

    Map<String, String> map = new LinkedHashMap<String, String>();

    String cartID = "";
    Response shopbookingResponse = null;
    JsonPath jsonPath = null;

    public Response registerFlihgts(Map<String, String> map, Response response) {

        this.map = map;
        shopbookingResponse = response;
        baseURI = environment.get("Environment_URL");
        basePath = "/8.2/shoppingcart/cart/RegisterFlights";

        Response registerFlihgtsResponse = given().relaxedHTTPSValidation().auth().oauth2(accessToken).body(getshopSelectBody())
                .contentType(ContentType.JSON)
         //       .log().all()
                .when().post().then().statusCode(200)
                .extract().response();
        return registerFlihgtsResponse;

    }

    public Map getshopSelectBody() {
        String body = "{\n" +
                "  \"CartId\": \"" + getCartID() + "\",\n" +
                "  \"ChannelType\": \"WEB\",\n" +
                "  \"CountryCode\": \"US\",\n" +
                "  \"LangCode\": \"en-US\",\n" +
                "  \"FareType\": \"Refundable\"\n" +
                "}";

        JsonPath jsonPath = new JsonPath(body);
        Map<String, Object> bodymap = jsonPath.getMap("$");
        bodymap.put("Reservation", getReservation());
        bodymap.put(("CartInfo"), getDisplayCart());
        return bodymap;
    }

    public String getCartID() {
        jsonPath = new JsonPath(shopbookingResponse.asString());
        cartID = jsonPath.getString("CartId");
        return cartID;
    }

    public LinkedHashMap getReservation() {
        jsonPath = new JsonPath(shopbookingResponse.asString());
        LinkedHashMap reservation = jsonPath.getJsonObject("Reservation");
        return reservation;
    }

    public LinkedHashMap getDisplayCart() {
        jsonPath = new JsonPath(shopbookingResponse.asString());
        LinkedHashMap displayCart = jsonPath.getJsonObject("DisplayCart");

        return displayCart;
    }


}
