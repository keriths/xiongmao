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

    List<ActivationStatusDate.StatusNumberList> queryActivationStatusNum(
            @Param("factoryList") List<String> factoryList,
            @Param("groupName") String groupName,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate

    );

    List<ActivationDate.StatusDateList> queryActivationEQPId(
            @Param("factoryList") List<String> factoryList,
            @Param("groupName") String groupName,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate
    );

    Map<String,Object> loadByPrimaryKey(Map<String, Object> mapData);

    void addData(Map<String, Object> mapData);

    void updateData(Map<String, Object> mapData);
}
