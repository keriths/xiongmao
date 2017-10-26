package com.xm.service.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanshuai on 17/10/26.
 */
@XmlRootElement(name = "data")
public class MapDTO implements XMLDTO{
//    public Map<String,Object> value = new HashMap<String,Object>();
    public List<ValueDTO> values = new ArrayList<ValueDTO>();
    public void put(String key,Object value,String desc){
        ValueDTO valueDTO = new ValueDTO();
        valueDTO.key=key;
        valueDTO.value=value;
        valueDTO.desc=desc;
        values.add(valueDTO);
    }
    public void put(String key,Object value){
        put(key,value,null);
    }
}
