package org.example.FSE2E;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.commonUtils.Base;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.*;

public class AddPayment extends Base{

    Map<String, String> map = new LinkedHashMap<String, String>();


    public Response addPayment(Map<String, String> map) {

        this.map = map;
        baseURI = environment.get("Environment_URL");
        basePath = "/8.0/Payment/DataVault/AddPayment";
        Response addpaymentResponse = given().relaxedHTTPSValidation().auth().oauth2(accessToken).body(getAddPaymentBody())
                .contentType(ContentType.JSON)
                .when().post().then().statusCode(200)
                .extract().response();
        return addpaymentResponse;
    }

    public Map getAddPaymentBody() {
        String body="\n" +
                "{\n" +
                "  \"CallingService\": {\n" +
                "    \"Requestor\": {\n" +
                "      \"AgentAAA\": \"WEB\",\n" +
                "      \"ApplicationSource\": \"FLIGHTSHOP\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"Items\": [\n" +
                "    {\n" +
                "      \"__type\": \"CreditCard:#United.Service.Presentation.PaymentModel\",\n" +
                "      \"AccountNumber\": \"4000400012345678\",\n" +
                "      \"BillingAddress\": {\n" +
                "        \"AddressLines\": [\n" +
                "          \"1600 smith st\"\n" +
                "        ],\n" +
                "        \"City\": \"Houston\",\n" +
                "        \"Country\": {},\n" +
                "        \"PostalCode\": \"77002\",\n" +
                "        \"StateProvince\": {\n" +
                "          \"ShortName\": \"TX\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Currency\": {\n" +
                "        \"Code\": \"USD\"\n" +
                "      },\n" +
                "      \"GroupID\": \""+ UUID.randomUUID() +"\",\n" +
                "      \"OperationID\": \""+UUID.randomUUID()+"\",\n" +
                "      \"Payor\": {\n" +
                "        \"GivenName\": \"Test Testing\"\n" +
                "      },\n" +
                "      \"Pin\": \"123\",\n" +
                "      \"WalletCategory\": 0,\n" +
                "      \"Code\": \"VI\",\n" +
                "      \"CreditCardTypeCode\": 7,\n" +
                "      \"Description\": 7,\n" +
                "      \"ExpirationDate\": \"01/39\",\n" +
                "      \"InputCapability\": 0,\n" +
                "      \"Name\": \"qatest\",\n" +
                "      \"PinEntryCapability\": 0,\n" +
                "      \"SecurityCode\": \"123\",\n" +
                "      \"TerminalAttended\": 0,\n" +
                "      \"TerminalCardholderActivated\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"PointOfSaleCountryCode\": \"US\",   \n" +
                "  \"Types\":[]\n" +
                "}";
        JsonPath jsonPath = new JsonPath(body);
        Map<String, Object> bodymap = jsonPath.getMap("$");
        return bodymap;
    }

}
