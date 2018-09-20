package com.xm.service.dao.person;

import com.xm.service.apiimpl.pc.person.PersonService;
import com.xm.service.apiimpl.pc.person.dto.HistoryDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 18/6/17.
 */
@Repository("historyDAO")
public interface HistoryDAO {
    /**
     * 有进出卡机的区域内的人
     * @param inIds    区域进的卡机ID
     * @param outIds   区域出的卡机ID
     * @param beforDate 时间
     * @return
     */
    public List<HistoryDTO> queryLaeastInOutCards(@Param("inIds")List<Integer> inIds,@Param("outIds")List<Integer> outIds,@Param("beforDate")Date beforDate);

    /**
     * 指定区域的人
     * @param cardIds   指定区域的卡机ID
     * @param beforDate 时间
     * @return
     */
    public List<HistoryDTO> querySigleIdCards(@Param("cardIds")List<Integer> cardIds,@Param("beforDate")Date beforDate);

    /**
     * 查询公共区域的打卡人编号
     * @param factoryCardIds 工厂进口卡机id
     * @param cardIds        区域所有办公室的id
     * @param beforDate      日期
     * @return
     */
    public List<HistoryDTO> selectFactoryPublicAreaInfos(@Param("factoryCardIds")List<Integer> factoryCardIds, @Param("cardIds")List<Integer> cardIds,@Param("beforDate")Date beforDate);


//    public List<PersonService.HistoryData> queryLatestData(@Param("portList")List<Integer> portList,@Param("startTime") Date startTime,@Param("endTime") Date endTime) ;
}
