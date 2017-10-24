package com.xm.test;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.util.ReflectionUtil;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 17/10/24.
 */
public class JsonTest {
   public static void main(String[] args){
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
