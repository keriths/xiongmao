package com.xm.service.dao.cim;

        import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationDetailDTO;
        import com.xm.service.apiimpl.pc.cim.activation.dto.ActivationStatusDTO;
        import org.springframework.stereotype.Repository;

        import java.util.List;

/**
 * Created by wangshuna on 2017/11/14.
 */
@Repository("activationDAO")
public interface ActivationDAO {

    List<ActivationDetailDTO> ActivationDetail(String factory, String status);

    List<ActivationStatusDTO> ActivationStatus(String status);
}
