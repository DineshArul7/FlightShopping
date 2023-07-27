package org.example.dataProvider;



import org.example.commonUtils.Excel;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class DataProviderdata {

    Excel excel=new Excel();

   @DataProvider
    public Object[][] data(Method name){
       String sheetName=System.getProperty("sheetname");
       List<Map<String,String>> list=null;
            list=excel.readExcel(sheetName,"Test Script Name", name.getName());

       Object[][] data=new Object[list.size()][1];
       int count=0;
       for(Map<String,String> map:list){
           data[count][0]=map;
           count++;
       }
return data;
   }
}
