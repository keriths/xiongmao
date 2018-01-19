package com.xm.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by fanshuai on 18/1/19.
 */
@Component
public class CIMDataSyncTask {
    /**
     * 投放达成率数据同步，数据按天同步，同步任务每小时跑一次，每次把前三天的数据检查一遍
     * 首先把工厂数据库前三天的数据查询出来
     * 根据主键查询数据是否存在，已经存在更新
     * 不存在新增加
     */
    @Scheduled(fixedRate = 1000*5)
    public void InputCompletionDataSync(){

    }
}
