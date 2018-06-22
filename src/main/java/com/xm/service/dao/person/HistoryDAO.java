package com.xm.service.dao.person;

import com.xm.service.apiimpl.pc.person.PersonService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 18/6/17.
 */
@Repository("historyDAO")
public interface HistoryDAO {
    public List<PersonService.HistoryData> queryLatestData(@Param("portList")List<Integer> portList,@Param("startTime") Date startTime,@Param("endTime") Date endTime) ;
}
