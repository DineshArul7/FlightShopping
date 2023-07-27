package org.example.commonUtils;

import org.apache.logging.log4j.LogManager;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CommonUtils {


    LocalDateTime localDate = LocalDateTime.now();

    public String getProperty(String value){
        String result="";
        try {
            String path= System.getProperty("user.dir")+"\\src\\test\\resources\\properties.properties";
            FileReader reader=new FileReader(path);
            Properties properties=new Properties();
            properties.load(reader);
             result=properties.getProperty(value);
            reader.close();

        } catch (Exception e) {
System.out.println("Catched");
        }
return result;
    }

    public String getExcelPath(String Name){
        return System.getProperty("user.dir") + "\\" +getProperty(Name)+ ".xls";
    }

    public String getTextPath(){
        return System.getProperty("user.dir") + "\\" +getProperty("Text")+ ".TXT";
    }

    public String getCurrentDate(String format){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        String date = localDate.format(dateTimeFormatter);
        return date;
    }

    public String addDays(int days,String localDatestr){
        localDate=LocalDateTime.parse(localDatestr);
        String date=localDate.plusDays(days).toString();
        return date;
    }

    public String subDays(int days,String localDatestr){
        localDate=LocalDateTime.parse(localDatestr);
        String date=localDate.minusDays(days).toString();
        return date;
    }

    public Logger getlogger(){

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter

            fh = new FileHandler(System.getProperty("user.dir") + "\\" + "Logs.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
           // logger.info("My first log");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logger;
    }

    public org.apache.logging.log4j.Logger getlog4jlogger(String className){
        org.apache.logging.log4j.Logger logger= LogManager.getLogger(className);
        return logger;
    }
}
