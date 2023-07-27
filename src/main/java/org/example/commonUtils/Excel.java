package org.example.commonUtils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.example.commonUtils.CommonUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class Excel {
    CommonUtils commonUtils = new CommonUtils();


    String pathout;
    FileInputStream fis = null;
    HSSFWorkbook wb = null;

    public Excel() {
        this.pathout = commonUtils.getExcelPath("Excel");
    }

    public void writetoExcel(Map<String, String> map, int rowcount,String sheetName) {

        try {
            fis = new FileInputStream(new File(pathout));
            //creating workbook instance that refers to .xls file
            wb = new HSSFWorkbook(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheet(sheetName);

        Row row = sheet.createRow(rowcount);
        ;
        Cell cell;
        Set<String> set = map.keySet();
        int count = 0;
        for (String key : set) {
            cell = row.createCell(count);
            cell.setCellValue(map.get(key));
            count++;
        }

        try {
            fis.close();
            FileOutputStream fos = new FileOutputStream(pathout);
            wb.write(fos);
            fos.close();
            wb.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public List<Map<String, String>> readExcel(String SheetName,String key,String methodName) {

        try {
            fis = new FileInputStream(new File(pathout));
            //creating workbook instance that refers to .xls file
            wb = new HSSFWorkbook(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheet(SheetName);

        DataFormatter formatter = new DataFormatter();
        Iterator rowiterator = sheet.iterator();
        Row row;
        Cell cell;
        Iterator celliterator;
        String value = "";
        List<String> firstrow = new ArrayList<String>();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        // List<String> restRow=new ArrayList<String>();
        int count = 0;

        while (rowiterator.hasNext()) {
            row = (Row) rowiterator.next();
            celliterator = row.cellIterator();
            List<String> restRow = new ArrayList<String>();
            Map<String, String> map = new LinkedHashMap<String, String>();
            while (celliterator.hasNext()) {
                cell = (Cell) celliterator.next();
                value = formatter.formatCellValue(cell);
                if (count == 0) {
                    firstrow.add(value);
                } else {
                    restRow.add(value);
                }
            }
            if (count > 0) {
                for (int i = 0; i < firstrow.size(); i++) {
                    map.put(firstrow.get(i), restRow.get(i));
                }if (map.get("Execute").equalsIgnoreCase("yes") && map.get(key).equalsIgnoreCase(methodName)) {
                    list.add(map);
                }
            }
            count++;
        }
        try {
            wb.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public List<Map<String, String>> readExcelPlain(String SheetName) {

        try {
            fis = new FileInputStream(new File(pathout));
            //creating workbook instance that refers to .xls file
            wb = new HSSFWorkbook(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheet(SheetName);

        DataFormatter formatter = new DataFormatter();
        Iterator rowiterator = sheet.iterator();
        Row row;
        Cell cell;
        Iterator celliterator;
        String value = "";
        List<String> firstrow = new ArrayList<String>();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        // List<String> restRow=new ArrayList<String>();
        int count = 0;

        while (rowiterator.hasNext()) {
            row = (Row) rowiterator.next();
            celliterator = row.cellIterator();
            List<String> restRow = new ArrayList<String>();
            Map<String, String> map = new LinkedHashMap<String, String>();
            while (celliterator.hasNext()) {
                cell = (Cell) celliterator.next();
                value = formatter.formatCellValue(cell);
                if (count == 0) {
                    firstrow.add(value);
                } else {
                    restRow.add(value);
                }
            }
            if (count > 0) {
                for (int i = 0; i < firstrow.size(); i++) {
                    map.put(firstrow.get(i), restRow.get(i));
                }

                list.add(map);

            }
            count++;
        }
        try {
            wb.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public Map<String,Map<String,String>> readsheets() {

        try {
            fis = new FileInputStream(new File(pathout));
            //creating workbook instance that refers to .xls file
            wb = new HSSFWorkbook(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //creating a Sheet object to retrieve the object
        Map<String,Map<String,String>> map=new LinkedHashMap<>();
        Map<String,String> sheetmap=new LinkedHashMap<>();
        int NoOfSheets=wb.getNumberOfSheets();
        HSSFSheet sheet =null;
        DataFormatter formatter = new DataFormatter();
        for(int i=0;i<NoOfSheets;i++){
            sheet=wb.getSheetAt(i);
            Iterator rowiterator = sheet.iterator();
            Row row;
            Cell cell;
            Iterator celliterator;
            String value = "";
            int count=0;
            while (rowiterator.hasNext()) {
                row = (Row) rowiterator.next();
                if(count==0){
                    count++;
                    continue;
                }

                celliterator = row.cellIterator();
                List<String> restRow = new ArrayList<String>();
                // Map<String, String> map = new LinkedHashMap<String, String>();
                while (celliterator.hasNext()) {
                    cell = (Cell) celliterator.next();
                    value = formatter.formatCellValue(cell);

                    restRow.add(value);
                }
                sheetmap.put(restRow.get(0),restRow.get(1)+"|"+restRow.get(2));
            }
            map.put(wb.getSheetName(i),sheetmap );
        }



        try {
            wb.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    public void writeErrorstoExcel(String Error,String sheetName) {
        String pathError=commonUtils.getExcelPath("ErrorExcel");
        try {
            fis = new FileInputStream(new File(pathError));
            //creating workbook instance that refers to .xls file
            wb = new HSSFWorkbook(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheet(sheetName);
        int lastrow=sheet.getLastRowNum();
        Row row = sheet.createRow(lastrow+1);
        ;
        Cell cell;

        int count = 0;

        cell = row.createCell(0);
        cell.setCellValue(Error);

        try {
            fis.close();
            FileOutputStream fos = new FileOutputStream(pathError);
            wb.write(fos);
            fos.close();
            wb.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
