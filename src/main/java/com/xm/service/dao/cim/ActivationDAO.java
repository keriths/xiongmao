package com.xm.service.dao.cim;

        import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationDetailDTO;
        import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationEQPTypeListRetDTO;
        import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationStatusDTO;
        import org.apache.ibatis.annotations.Param;
        import org.springframework.stereotype.Repository;

        import java.util.Date;
        import java.util.List;

/**
 * Created by wangshuna on 2017/11/14.
 */
@Repository("activationDAO")
public interface ActivationDAO {

    /*List<ActivationDetailDTO> ActivationDetail(String factory, String status);

    List<ActivationStatusDTO> ActivationStatus(String status);*/

    List<ActivationEQPTypeListRetDTO.ActivationStatusNumDetail> queryActivationStatusNum(
            @Param("factory") String factory,
            @Param("eqpType")String eqpType,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate

    );

    List<ActivationEQPTypeListRetDTO.ActivationTypeDetail> queryActivationEQPType(
            @Param("factory")String factory,
            @Param("eqpTypeList") List<String> eqpTypeList,
            @Param("beginDate")Date beginDate,
            @Param("endDate")Date endDate
    );


}
