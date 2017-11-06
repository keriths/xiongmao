package com.xm.service.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/26.
 */
@XmlRootElement(name = "data")
public class MapDTO {
//    public Map<String,Object> value = new HashMap<String,Object>();
//    public List<ValueDTO> values = new ArrayList<ValueDTO>();
    private Map<String,Object> data = new HashMap<String, Object>();
    public void put(String key,Object value,String desc){
        ValueDTO valueDTO = new ValueDTO();
//        valueDTO.key=key;
        valueDTO.value=value;
        valueDTO.desc=desc;
        data.put(key, valueDTO);
    }
//    public void put(String key,Object value){
//        put(key,value,null);
//    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
