package com.xm.service.dao.cim;

        import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationDate;
        import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationStatusDate;
        import org.apache.ibatis.annotations.Param;
        import org.springframework.stereotype.Repository;

        import java.util.Date;
        import java.util.List;

/**
 * Created by wangshuna on 2017/11/14.
 */
@Repository("activationDAO")
public interface ActivationDAO {

    List<ActivationStatusDate.StatusNumberList> queryActivationStatusNum(
            @Param("factory") String factory,
            @Param("eqpId")String eqpId,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate

    );

    List<ActivationDate.StatusDateList> queryActivationEQPId(
            @Param("factory")String factory,
            @Param("eqpIdList") List<String> eqpIdList,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate
    );


}
