package com.xm.web;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by luokaiming on 2018/1/15 0015.
 */
@Component
public class TimerTask {
    /**
     * 启动时执行一次，之后每隔2秒执行一次
     */
    @Scheduled(fixedRate = 1000*5)
    public void test(){
        String time = DateFormat.getDateTimeInstance().format(new Date());
        System.out.println("定时任务测试"+time);
    }

}
