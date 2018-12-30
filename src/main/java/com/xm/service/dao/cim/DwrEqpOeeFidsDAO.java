package com.xm.service.dao.cim;

        import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationDate;
        import com.xm.service.apiimpl.pc.cim.oee.dto.ActivationStatusDate;
        import org.apache.ibatis.annotations.Param;
        import org.springframework.stereotype.Repository;

        import java.util.Date;
        import java.util.List;
        import java.util.Map;

/**
 * Created by wangshuna on 2017/11/14.
 */
@Repository("activationDAO")
public interface DwrEqpOeeFidsDAO {
//    int queryActivationStatusNum_dqpidNum(
//            @Param("factoryList") List<String> factoryList,
//            @Param("groupName") String groupName,
//            @Param("beginDateStr")String beginDate,
//            @Param("endDateStr")String endDate
//
//    );
//    List<ActivationStatusDate.StatusNumberList> queryActivationStatusNum(
//            @Param("factoryList") List<String> factoryList,
//            @Param("groupName") String groupName,
//            @Param("beginDateStr")String beginDate,
//            @Param("endDateStr")String endDate
//    );

    List<ActivationStatusDate.StatusNumberList> queryActivationStatusNumByDay(
            @Param("factoryList") List<String> factoryList,
            @Param("eqpIdList") List<String> eqpIdList,
            @Param("bigEqpType")String bigEqpType,
            @Param("beginDateStr")String beginDate,
            @Param("endDateStr")String endDate
    );

//    List<ActivationDate.StatusDateList> queryActivationEQPId(
//            @Param("factoryList") List<String> factoryList,
//            @Param("groupName") String groupName,
//            @Param("beginDateStr")String beginDate,
//            @Param("endDateStr")String endDate
//    );

    List<ActivationDate.StatusDateList> queryActivationByEQPIdListAndFactory(
            @Param("factoryList") List<String> factoryList,
            @Param("eqpIdList") List<String> eqpIdList,
            @Param("beginDateStr")String beginDate,
            @Param("endDateStr")String endDate
    );

//    int queryActivationEQPId_eqpidNum(
//            @Param("factoryList") List<String> factoryList,
//            @Param("groupName") String groupName,
//            @Param("beginDateStr")String beginDate,
//            @Param("endDateStr")String endDate
//    );
    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);

    String queryEqpIdStr(@Param("factory")String factory, @Param("groupName")String groupName);
    List<String> queryBigEqpTypes(@Param("factory")String factory);
}
