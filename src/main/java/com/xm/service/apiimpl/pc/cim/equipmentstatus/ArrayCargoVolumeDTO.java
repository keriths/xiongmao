package com.xm.service.apiimpl.pc.cim.equipmentstatus;

import com.xm.service.dto.XMLDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by fanshuai on 17/10/26.
 */
@XmlRootElement(name = "data")
public class ArrayCargoVolumeDTO implements XMLDTO{
    public List<ArrayCargoVolumeItemValue> valueList;
    public static class ArrayCargoVolumeItemValue{
        public ArrayCargoVolumeItemValue(){}
        public ArrayCargoVolumeItemValue(int time,int num){
            this.time=time;
            this.num = num;
        }
        public int time;
        public int num;
    }
}
