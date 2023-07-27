package org.example.FSE2E;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.commonUtils.Base;
import org.example.commonUtils.Excel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class RegisterFormsOfPayment extends Base {
//    public class Shop  {
Excel excel=new Excel();
    Map<String, String> map = new LinkedHashMap<String,String>();

    Response loadReservationresponse=null;
    Response addPaymentresponse=null;
    JsonPath jsonPath = null;

    public Response shop(Map<String, String> map,Response loadReservationresponse,Response addPaymentresponse) {

        this.map=map;
        this.loadReservationresponse=loadReservationresponse;
        this.addPaymentresponse=addPaymentresponse;

        baseURI = environment.get("Environment_URL");
        basePath = "/8.2/shoppingcart/cart/registerformsofpayment";

        Response RegisterFormsOfPaymentResponse=given().relaxedHTTPSValidation().auth().oauth2(accessToken).body(getRefisterFormsOfPaymentBody())
                .contentType(ContentType.JSON)
                .when().post().then().statusCode(200)
                .extract().response();
        return RegisterFormsOfPaymentResponse;
    }

    public String getCartID() {
        jsonPath = new JsonPath(addPaymentresponse.asString());
       String  cartID = jsonPath.getString("CartId");
        return cartID;
    }
    public String getRefisterFormsOfPaymentBody(){
        String accountNumMasked;
        String accountNumToken;
        String operationID;
        String validation;
        String persistantToken;
        JsonPath addPaymentJson=new JsonPath(addPaymentresponse.asString());
        JsonPath loadreservationanddisplaycartjson=new JsonPath(loadReservationresponse.asString());
        double tripAmount=0;
        List<LinkedHashMap<String,Object>> displayPrices=loadreservationanddisplaycartjson.getList("DisplayCart.DisplayPrices");
        for(LinkedHashMap<String,Object> linkedHashMap:displayPrices){
            if(linkedHashMap.get("Type").toString().equalsIgnoreCase("Total")){
                tripAmount=Double.parseDouble(""+linkedHashMap.get("Amount"));
            }
        }
        accountNumMasked=addPaymentJson.getString("Items[0].AccountNumberMasked");
        accountNumToken=addPaymentJson.getString("Items[0].AccountNumberToken");
        operationID=addPaymentJson.getString("Items[0].OperationID");
        validation=addPaymentJson.getString("Responses[0].Message[0].Text");
        persistantToken=addPaymentJson.getString("Items[0].PersistentToken");
        String body="{\n" +
                "  \"AddPNRRemarks\": {\n" +
                "    \"PNRRemarks\": []\n" +
                "  },\n" +
                "  \"RemarksToBeAdded\": true,\n" +
                "  \"AddSSRToPNR\": {\n" +
                "    \"SSRRequests\": {}\n" +
                "  },\n" +
                "  \"SSRToBeAdded\": false,\n" +
                "  \"Phones\": [\n" +
                "    {\n" +
                "      \"Description\": \"H\",\n" +
                "      \"CountryAccessCode\": \"US\",\n" +
                "      \"AreaCityCode\": \"\",\n" +
                "      \"PhoneNumber\": \"8728721104\",\n" +
                "      \"Attention\": \"\",\n" +
                "      \"PhoneLocation\": {\n" +
                "        \"Description\": \"WEB\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"Emails\": [\n" +
                "    {\n" +
                "      \"Address\": \"dinesh.arul@united.com\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"SeatsToRemove\": null,\n" +
                "  \"FormsOfPayment\": [\n" +
                "    {\n" +
                "      \"Payment\": {\n" +
                "        \"CreditCard\": {\n" +
                "          \"AccountNumberMasked\": \""+accountNumMasked+"\",\n" +
                "          \"AccountNumberToken\": \""+accountNumToken+"\",\n" +
                "          \"Amount\":"+tripAmount+",\n" +
                "          \"BillingAddress\": {\n" +
                "            \"AddressLines\": [\n" +
                "              \"1600 smith st\"\n" +
                "            ],\n" +
                "            \"City\": \"houston\",\n" +
                "            \"Country\": {\n" +
                "              \"CountryCode\": \"US\"\n" +
                "            },\n" +
                "            \"PostalCode\": \"77027\",\n" +
                "            \"StateProvince\": {\n" +
                "              \"ShortName\": \"TX\",\n" +
                "              \"StateProvinceCode\": \"TX\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"Currency\": {\n" +
                "            \"Code\": \"USD\"\n" +
                "          },\n" +
                "          \"GroupID\": \""+operationID+"\",\n" +
                "          \"OperationID\": \""+operationID+"\",\n" +
                "          \"PersistentToken\": \""+persistantToken+"\",\n" +
                "          \"Payor\": {\n" +
                "            \"GivenName\": \"qatest\"\n" +
                "          },\n" +
                "          \"Code\": \"VI\",\n" +
                "          \"CreditCardTypeCode\": 7,\n" +
                "          \"Description\": 7,\n" +
                "          \"ExpirationDate\": \"01/39\",\n" +
                "          \"Name\": \"qatest\",\n" +
                "          \"SecurityCode\": \"123\",\n" +
                "          \"Pin\": \"123\",\n" +
                "          \"PinEntryCapability\": 0,\n" +
                "          \"TerminalAttended\": 0,\n" +
                "          \"TerminalCardholderActivated\": 0,\n" +
                "          \"WalletCategory\": 0\n" +
                "        }\n" +
                "      },\n" +
                "      \"PaymentTarget\": \"RES\",\n" +
                "      \"Amount\": "+tripAmount+"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"RemoveFareLock\": false,\n" +
                "  \"AdditionalData\": \"\",\n" +
                "  \"FareLockAutoTicket\": false,\n" +
                "  \"PostPurchase\": false,\n" +
                "  \"ReserveOnly\": false,\n" +
                "  \"IsPreferredBankCardHolder\": false,\n" +
                "  \"DontInvokeCheckout\": false,\n" +
                "  \"CartId\": \""+getCartID()+"\",\n" +
                "  \"Channel\": \"WEB\",\n" +
                "  \"CountryCode\": \"US\"\n" +
                "}";
        return body;
    }
}
