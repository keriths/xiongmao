package com.xm.service;

import com.xm.service.annotations.ApiMethodDoc;
import com.xm.service.annotations.ApiParamDoc;
import com.xm.service.annotations.ApiServiceDoc;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fanshuai on 17/10/22.
 */
@Service("testService")
@ApiServiceDoc(name = "测试服务")
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
}
