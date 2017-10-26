package com.xm.web.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xm.service.dto.XMLDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/22.
 */
public class XMLHttpMessageConverter extends AbstractHttpMessageConverter {
    @Override
    protected boolean supports(Class clazz) {
        if (XMLDTO.class.isAssignableFrom(clazz)){
            return true;
        }
        return false;
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        return null;
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = outputMessage.getHeaders();
        OutputStream out = outputMessage.getBody();
        String xml = convertToXml(obj, "UTF-8");
        headers.setContentLength(xml.length());
        out.write(xml.getBytes());
    }

    public static String convertToXml(Object obj, String encoding) {
        /*
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        */
        String jsonStr = com.alibaba.fastjson.JSON.toJSONString(obj);
//        JSONObject jobj = JSONObject.fromObject(jsonStr);
//        String xml =  JsonBean2XmlString(jobj);
//        return xml;

        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><data>"+json2xml(JSON.parseObject(jsonStr))+"</data>";
    }
    public static String json2xml(JSONObject json){
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : json.entrySet()){
            String key = entry.getKey();
            Object val = entry.getValue();
            if (val instanceof JSONObject){
                sb.append("<").append(key).append(">");
                sb.append(json2xml((JSONObject)val));
                sb.append("</").append(key).append(">");
            }else if (val instanceof JSONArray){
                sb.append(jsonArray2Xml(key,(JSONArray)val));
            }else {
                sb.append("<").append(key).append(">");
                sb.append(val.toString());
                sb.append("</").append(key).append(">");
            }

        }
        return sb.toString();
    }
    public static String jsonArray2Xml(String key,JSONArray jsonArray){
        StringBuffer sb = new StringBuffer();
        for (Object obj : jsonArray){
            sb.append("<").append(key).append(">");
            if (obj instanceof JSONArray){
                sb.append(jsonArray2Xml(key, (JSONArray) obj));
            }else if (obj instanceof JSONObject){
                sb.append(json2xml((JSONObject)obj));
            }else {
                sb.append(obj.toString());
            }
            sb.append("</").append(key).append(">");
        }
        return sb.toString();
    }

//    public static String JsonBean2XmlString(JSONObject json){
//        StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
//        for(Object key : json.keySet()){
//            sb.append("<").append(key).append(">");
//            Object value = json.get(key);
//            //判断value是否含有JSONArray
//            JSONObject jsonV = ((JSONObject)value);
//            for(Object key2 : jsonV.keySet()){
//                sb.append("<").append(key2).append(">");
//                Object value2 = jsonV.get(key2);
//                sb.append(value2);
//                sb.append("</").append(key2).append(">");
//            }
//            sb.append("</").append(key).append(">");
//        }
//        return sb.toString();
//    }
}
