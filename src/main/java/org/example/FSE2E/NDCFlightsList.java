package org.example.FSE2E;

import org.example.commonUtils.Base;

import java.util.LinkedHashMap;
import java.util.Map;

public class NDCFlightsList extends Base {
    Map<String, String> map = new LinkedHashMap<String,String>();

    public void ndcFlightsList(Map<String, String> map) {
        this.map = map;
        
    }

}
