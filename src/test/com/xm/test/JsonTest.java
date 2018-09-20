package com.xm.test;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.util.ReflectionUtil;
import com.xm.platform.util.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by fanshuai on 17/10/24.
 */
public class JsonTest {
   public static void main(String[] args){
        System.out.println(StringUtils.twoPoint("0.12345"));
       System.out.println(StringUtils.twoPoint("12340.12345"));
       System.out.println(StringUtils.twoPoint("12340.12"));
       System.out.println(StringUtils.twoPoint("12340.1"));
       System.out.println(StringUtils.twoPoint("12340"));
       System.out.println(StringUtils.twoPoint("12340."));
       Map<String,String> map = new HashMap<>();
       map.put("Array洁净室in","1,2,3");
       map.put("Array洁净室out","1,2,3");
       map.put("CF洁净室in","4,5,6");
       map.put("CF洁净室out","4,5,6");
       Map<String,String> map4a = new HashMap<>();
       map4a.put("4A自动化办公室","7,8,9");
       map4a.put("技术办公室","7,8,9");
       Map<String,Map> allMap = new HashMap<>();
       allMap.put("洁净室",map);
       allMap.put("4A",map4a);
        System.out.println(JSON.toJSONString(allMap));
       System.out.println();
       System.out.println();
       System.out.println();
       try {
           Date obj = new Date();
           String jsonStr = JSON.toJSONString(obj);
           System.out.println(jsonStr);
           Object o = JSON.parseObject(jsonStr,obj.getClass());
           System.out.println(o);
       }catch (Exception e){
           e.printStackTrace();
       }
       try {
           String str = "str";
           String jsonStr = JSON.toJSONString(str);
           System.out.println(jsonStr);
           Object o = JSON.parseObject(jsonStr,String.class);
           System.out.println(o);
       }catch (Exception e){
           e.printStackTrace();
       }


       try {
           Class dtoClass = DTO.class;
           Method[] methods = dtoClass.getMethods();
           for (Method method:methods){
               if (!"setDtoList".equals(method.getName())){
                   continue;
               }
               Type[] paramterTypes = method.getGenericParameterTypes();
               for (Type type:paramterTypes){
                   List<DTO> dtoList = new ArrayList<DTO>();
                   dtoList.add(new DTO(2,new Date()));
                   String jsonStr = JSON.toJSONString(dtoList);
                   Object o1 = JSON.parseObject(jsonStr,dtoList.getClass());
                   Object o2 = JSON.parseObject(jsonStr,type);
                   type.toString();
               }
           }
       }catch (Exception e){
            e.printStackTrace();
       }

       try {
           String jsonStr = JSON.toJSONString(1);
           System.out.println(jsonStr);
           Object o = JSON.parseObject(jsonStr,Integer.class);
           System.out.println(o);
       }catch (Exception e){
            e.printStackTrace();
       }

       try {
           DTO d = new DTO(1,new Date());
           List<DTO> dtoList = new ArrayList<DTO>();
           dtoList.add(new DTO(2,new Date()));
           d.setDtoList(dtoList);
           String jsonStr = JSON.toJSONString(d);
           System.out.println(jsonStr);
           Object o = JSON.parseObject(jsonStr,DTO.class);
           System.out.println(o);
       }catch (Exception e){
           e.printStackTrace();
       }
   }


}
