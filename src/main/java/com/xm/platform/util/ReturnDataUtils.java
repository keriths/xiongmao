package com.xm.platform.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/20 0020.
 */
public class ReturnDataUtils {

    public static BigDecimal demoData(String dataType,String demoData){
        BigDecimal data=new BigDecimal("0");
        Random random = new Random();
        if ("int".equals(dataType)){
            //判断样本数据是可选列表还是区间
            if (demoData.contains(",")){//是列表如：0,1,2,3
                /*String str[]=demoData.split(",");
                int i=RandomUtils.randomInt(0,str.length);
                data=new BigDecimal(str[i]);*/

                data=new BigDecimal(1);//设备状态类型数据默认状态为正常 1
            }else {//区间
                //判断是正数区间还是负数区间
                if (demoData.contains("(")){//负数区间如：(-2100)-(-2000)
                    String str=demoData.replace("(-","");
                    String s=str.replace(")","");
                    String strdata[]=s.split("-");
                    int mix=Integer.parseInt(strdata[1]);
                    int max=Integer.parseInt(strdata[0]);
                    data=new BigDecimal(RandomUtils.randomInt(mix,max));
                    data = data.multiply(new BigDecimal(-1));

                }else{//正数区间:200-300
                    String str[]=demoData.split("-");
                    int mix=Integer.parseInt(str[0]);
                    int max=Integer.parseInt(str[1]);
                    data=new BigDecimal(RandomUtils.randomInt(mix,max));
                }

            }

        }else if("float".equals(dataType)){
            String str[]=demoData.split("-");
            float min=Float.parseFloat(str[0]);
            float max=Float.parseFloat(str[1]);
            float c=min + ((max - min) * random.nextFloat());
            data=new BigDecimal(c).setScale(2,BigDecimal.ROUND_HALF_DOWN);
        }else if("string".equals(dataType)){
            //String str[]={"MAN","WAT","RUN","TRB","MNT"};
            int i=RandomUtils.randomInt(0,5);
            data=new BigDecimal(i);
        }
        //System.out.println(data);
        return data;
    }

    public static BigDecimal targetData(String factory,String productId,String periodDate){

        return new BigDecimal("99.8");
    }

    public static void main(String[] args){
        BigDecimal d=ReturnDataUtils.demoData("string","MAN,WAT,RUN,TRB,MNT ");

    }
}
