package org.example.flightShoppingTests;


import io.restassured.response.Response;
import org.example.AuthToken.AuthToken;
import org.example.FSE2E.*;
import org.example.commonUtils.Base;
import org.example.commonUtils.ExtentReportUtils;
import org.example.dataProvider.DataProviderdata;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class revenueShop extends Base {
    Shop shop = new Shop();
    ShopSelect shopSelect=new ShopSelect();
    ShopBookingDetailsV2 shopBookingDetailsV2=new ShopBookingDetailsV2();
    RegisterFlights registerFlights=new RegisterFlights();
    RegisterTravelers registerTravelers=new RegisterTravelers();
    LoadReservationAndDisplaycart loadReservationAndDisplaycart=new LoadReservationAndDisplaycart();
    AddPayment addPayment=new AddPayment();

    RegisterFormsOfPayment registerFormsOfPayment=new RegisterFormsOfPayment();
    AuthToken authToken = new AuthToken();
    ExtentReportUtils reportUtils=new ExtentReportUtils();
@BeforeClass
public void beforeclass(){
    System.setProperty("sheetname","Revenue");
}
    @Test(dataProvider = "data", dataProviderClass = DataProviderdata.class)
    public void FS_Flow_Revenue(Object[] data) {

        logger.info("This is written Newly ####################################");
        logger.info("Deekay");
        Map<String, String> map = (Map<String, String>) data[0];
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //    System.out.println("Key    " + entry.getKey());
            //    System.out.println("Value  " + entry.getValue());
        }
        reportUtils.extentlogInitializer(map.get("S.NO") + map.get("Test Script Name") + map.get("Scenario"));
        authToken.getToken(channelName);
        Response shopResponse=shop.shop(map);
        Response shopSelectResponse=shopSelect.shopSelect(map,shopResponse);
        Response shopBookingDetailsV2Response=shopBookingDetailsV2.shopBookingsDetailsV2(map,shopSelectResponse);
        Response registerFlightsResponse=registerFlights.registerFlihgts(map,shopBookingDetailsV2Response);
        Response registerTravelersResponse=registerTravelers.registerTravelers(map,registerFlightsResponse);
        Response loadedReservationAndDisplaycartResponse=loadReservationAndDisplaycart.loadReservationAndDisplaycart(map,registerTravelersResponse);
        Response addPaymentResponse=addPayment.addPayment(map);
        registerFormsOfPayment.shop(map,loadedReservationAndDisplaycartResponse,addPaymentResponse);
    }
}
