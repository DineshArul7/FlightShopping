package org.example.AuthToken;

import static io.restassured.RestAssured.*;
import  io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.commonUtils.Base;

public class AuthToken extends Base {
    public void getToken(String channelName){
        baseURI=environment.get("Auth_URL");
        basePath=environment.get("Auth_Basepath");
        String body="";

if((Environment.equalsIgnoreCase("QA")||Environment.equalsIgnoreCase("UAT"))&&channelName.equalsIgnoreCase("CSL")){
body="{ \n" +
        "  \t\"grant_type\": \"client_credentials\", \n" +
        " \t\"client_id\": \"CSLTester_UAL_9faf9528-e3b9-42cd-ae8f-13e205220c41\", \n" +
        "  \t\"client_secret\": \"V4sArMhjckDU5K9TEt2qUcvw\", \n" +
        "  \t\"scope\": \"openid tester\", \n" +
        "  \t\"endUserAgentIP\":\"127.0.0.1\", \n" +
        "     \"endUserAgentID\":\"52a444e6-e968-49d6-973c-6a4930d5e054\", \n" +
        "  \t\"userType\":\"guest\" \n" +
        "}";
} else if((Environment.equalsIgnoreCase("PREPROD")||Environment.equalsIgnoreCase("PROD"))&&channelName.equalsIgnoreCase("CSL")){
body="{\n" +
        "      \"grant_type\": \"client_credentials\",\n" +
        "     \"client_id\": \"CSLTester_UAL_9faf9528-e3b9-42cd-ae8f-13e205220c41\",\n" +
        "      \"client_secret\": \"4CA3ab7WHZ9fyZkZTjj8qXEj\",\n" +
        "      \"scope\": \"openid tester\",\n" +
        "      \"endUserAgentIP\":\"127.0.0.1\",\n" +
        "     \"endUserAgentID\":\"52a444e6-e968-49d6-973c-6a4930d5e054\",\n" +
        "      \"userType\":\"guest\"\n" +
        "}";
} else if((Environment.equalsIgnoreCase("QA")||Environment.equalsIgnoreCase("UAT"))&&channelName.equalsIgnoreCase("WEB")){
body="{ \n" +
        "  \t\"grant_type\": \"client_credentials\", \n" +
        " \t\"client_id\": \"CSLTester_UAL_9faf9528-e3b9-42cd-ae8f-13e205220c41\", \n" +
        "  \t\"client_secret\": \"V4sArMhjckDU5K9TEt2qUcvw\", \n" +
        "  \t\"scope\": \"openid tester\", \n" +
        "  \t\"endUserAgentIP\":\"127.0.0.1\", \n" +
        "     \"endUserAgentID\":\"52a444e6-e968-49d6-973c-6a4930d5e054\", \n" +
        "  \t\"userType\":\"guest\" \n" +
        "}";
} else if((Environment.equalsIgnoreCase("PREPROD")||Environment.equalsIgnoreCase("PROD"))&&channelName.equalsIgnoreCase("WEB")){
body="{\n" +
        "  \"grant_type\": \"client_credentials\",\n" +
        "  \"client_id\": \"united.comTest_UAL_42DEF344-629F-419D-A1C0-4B607F28D20A\",\n" +
        "  \"client_secret\": \"ub874vwFGkscAhabmUN839v5\",\n" +
        "  \"scope\": \"openid united.comtest\",\n" +
        "  \"endUserAgentIP\": \"127.0.0.1\",\n" +
        "  \"endUserAgentID\": \"52a444e6-e968-49d6-973c-6a4930d5e054\",\n" +
        "  \"userType\": \"guest\"\n" +
        "\n" +
        "}";
} else if((Environment.equalsIgnoreCase("QA")||Environment.equalsIgnoreCase("UAT"))&&channelName.equalsIgnoreCase("Mobile")){
body="{\n" +
        "  \"grant_type\": \"client_credentials\",\n" +
        "  \"client_id\": \"Mobile-AndroidPhoneTest_UAL_643E1E47-1242-4B6C-AB7E-64024E4BC84C\",\n" +
        "  \"client_secret\": \"Mqd2xDrtBsM4Qa3pUeK5Z9z4\",\n" +
        "  \"scope\": \"openid mobile-androidphonetest\",\n" +
        "  \"endUserAgentIP\": \"127.0.0.1\",\n" +
        "  \"endUserAgentID\": \"52a444e6-e968-49d6-973c-6a4930d5e054\",\n" +
        "  \"userType\": \"guest\"\n" +
        "}";
} else if((Environment.equalsIgnoreCase("PREPROD")||Environment.equalsIgnoreCase("PROD"))&&channelName.equalsIgnoreCase("Mobile")){
body="{\n" +
        "\"grant_type\": \"client_credentials\",\n" +
        "\"client_id\": \"Mobile-AndroidPhone_UAL_643E1E47-1242-4B6C-AB7E-64024E4BC84C\",\n" +
        "\"client_secret\": \"NV3XcWmNZ4qCdzGZa3hs7sWH\",\n" +
        "\"scope\": \"openid mobile-androidphone\",\n" +
        "\"endUserAgentIP\": \"127.0.0.1\",\n" +
        "\"endUserAgentID\": \"52a444e6-e968-49d6-973c-6a4930d5e054\",\n" +
        "\"userType\": \"guest\"\n" +
        "}";
} else if((Environment.equalsIgnoreCase("QA")||Environment.equalsIgnoreCase("UAT"))&&channelName.equalsIgnoreCase("Navigator")){
body="{\n" +
        "  \"client_id\": \"Navigator_UAL_1D61EB62-D1C8-4E0D-BBFA-37C03D0E76FB\",\n" +
        "  \"client_secret\": \"kfye9NHDvKE8MMYGw5jukUkFkSk2z2Yb\",\n" +
        " \"endUserAgentID\": \"52a444e6-e968-49d6-973c-6a4930d5e054\",\n" +
        "  \"endUserAgentIP\": \"10.233.71.134\",\n" +
        "  \"grant_type\": \"password\",\n" +
        "  \"scope\": \"openid navigator\",\n" +
        "  \"userType\": \"employee\",\n" +
        "\"username\":\"v756374\",\n" +
        "\"password\":\"Nirvan is son29\"\n" +
        "}";
} else if((Environment.equalsIgnoreCase("PREPROD")||Environment.equalsIgnoreCase("PROD"))&&channelName.equalsIgnoreCase("Navigator")){
body="{\n" +
        "    \"client_id\": \"Navigator_UAL_1D61EB62-D1C8-4E0D-BBFA-37C03D0E76FB\",\n" +
        "    \"client_secret\": \"2KraMyrfQJZ8r5tshks97hpy\",\n" +
        "    \"scope\": \"openid navigator\",\n" +
        "    \"grant_type\": \"password\",\n" +
        "    \"userType\": \"employee\",\n" +
        "\"username\":\"v756374\",\n" +
        "\"password\":\"Nirvan is son29\"\n" +
        "}";
} else if((Environment.equalsIgnoreCase("QA")||Environment.equalsIgnoreCase("UAT"))&&channelName.equalsIgnoreCase("Compass")){
body="{\n" +
        "  \"grant_type\": \"client_credentials\",\n" +
        "  \"client_id\": \"CompassTest_UAL_CEFCA815-9BA4-4D7C-9A5C-B457284482D0\",\n" +
        "  \"client_secret\": \"FqFDfLepu72XKHVMdXdJVA3M\",\n" +
        "  \"scope\": \"openid compasstest\",\n" +
        "  \"endUserAgentIP\": \"127.0.0.1\",\n" +
        "  \"endUserAgentID\": \"52a444e6-e968-49d6-973c-6a4930d5e054\",\n" +
        "  \"userType\": \"guest\"\n" +
        "}";
} else if(Environment.contains("QA")&&Environment.contains("AWS")){
body="{\"client_id\":\"FlightShoppingTest_UAL_37a53dd2-6d94-436f-8186-a21a4bffad13\",\"client_secret\":\"w63k4kcBLuM3xdCgJbyxPHt5\",\"endUserAgentID\":\"e3b3714b-4949-4190-9eab-3d76f8087a94\",\"endUserAgentIP\":\"10.233.117.39\",\"grant_type\":\"client_credentials\",\"scope\":\"flightshopping openid\",\"userType\":\"guest\"}";
} else if((Environment.contains("PREPROD")||Environment.contains("PROD"))&&Environment.contains("AWS")){
body="{ \n" +
        "  \"grant_type\": \"client_credentials\", \n" +
        " \"client_id\": \"CSLTester_UAL_9faf9528-e3b9-42cd-ae8f-13e205220c41\", \n" +
        "  \"client_secret\": \"4CA3ab7WHZ9fyZkZTjj8qXEj\", \n" +
        "  \"scope\": \"openid tester\", \n" +
        "  \"endUserAgentIP\":\"127.0.0.1\", \n" +
        "    \"endUserAgentID\":\"52a444e6-e968-49d6-973c-6a4930d5e054\", \n" +
        "  \"userType\":\"guest\" \n" +
        "}";
}
        Response response=given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .log().all().body(body).post().then().log().all().extract().response();
        JsonPath jsonPath=new JsonPath(response.asString());
        // accessToken="Bearer "+jsonPath.getString("access_token");
        accessToken=jsonPath.getString("access_token");
         xIDToken=jsonPath.getString("id_token");
    }
}
