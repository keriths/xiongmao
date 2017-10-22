package com.xm.web.test;

import com.xm.service.TestService;
import com.xm.web.vo.DefaultXMLRetVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/22.
 */
@Controller
public class TestAction {
    @Resource(name = "testService")
    private TestService testService;
    @RequestMapping(value = "/test")
    @ResponseBody
    public Object testAction(){
        Map map = new HashMap();
        map.put("Integer",new Integer(1));
        map.put("Bigdecimal",new BigDecimal("1.34"));
        map.put("Date",new Date());
        return map;
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public DefaultXMLRetVO testXmlAction(){
        DefaultXMLRetVO vo =  new DefaultXMLRetVO();
        vo.setCode(200);
        vo.setErrorMsg("成功"+testService.getTestName());
        return vo;
    }
}
