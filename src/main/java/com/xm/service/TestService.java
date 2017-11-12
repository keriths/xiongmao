package com.xm.service;

import com.alibaba.fastjson.JSON;
import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiParamDoc;
import com.xm.service.annotations.ApiServiceDoc;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/10/22.
 */
@Service("testService")
//@ApiServiceDoc(name = "测试服务")
public class TestService {
    @ApiMethodDoc(apiCode = "testService.testName",name = "测试方法")
    public String getTestName(){
        return "test";
    }

    @ApiMethodDoc(apiCode = "testService.testName1",name = "测试方法")
    public String getTestName(@ApiParamDoc(desc = "请输入aaa(*)")String aaa,String bbb,int c){
        return "test";
    }

    @ApiMethodDoc(apiCode = "testService.testName2",name = "测试方法")
    public String getTestName(@ApiParamDoc(desc = "请输入aaa(*)")String aaa,String bbb,List<String> c){
        return "list";
    }
    @ApiMethodDoc(apiCode = "testService.map",name = "测试方法map参数")
    public String mapTest(@ApiParamDoc(desc = "map参数(*)")Map<String,String> map){
        return JSON.toJSONString(map);
    }

    @ApiMethodDoc(apiCode = "testVoid",name = "testVoid")
    public void testVoid(){
        return;
    }

    @ApiMethodDoc(apiCode = "testint",name = "testint")
    public int testint(){
        return 1;
    }

    @ApiMethodDoc(apiCode = "testInteger",name = "testInteger")
    public Integer testInteger(){
        return 1;
    }

    @ApiMethodDoc(apiCode = "testBigDecimal",name = "testBigDecimal")
    public BigDecimal testBigDecimal(){
        return new BigDecimal("1");
    }
}
