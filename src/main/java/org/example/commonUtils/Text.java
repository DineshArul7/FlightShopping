package org.example.commonUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class Text {

    CommonUtils commonUtils = new CommonUtils();


    String pathout;
    FileInputStream fis = null;
    File file=null;
    BufferedReader bufferedReader=null;

    public Text() {
        this.pathout = commonUtils.getTextPath();
    }

    public List<String> readText() {
        List<String> list=new LinkedList<String>();
        try {
            file=new File(pathout);
             bufferedReader=new BufferedReader(new FileReader(file));
            String line="";
            while ((line=bufferedReader.readLine())!=null){
                list.add(line);
               String codeName= line.substring(0,3);
               }


            //creating workbook instance that refers to .xls file
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

System.out.println(list.size());

        return list;
    }

    public BufferedReader bufferedReader(){
        try {
            file=new File(pathout);
            bufferedReader=new BufferedReader(new FileReader(file));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bufferedReader;
    }


}
