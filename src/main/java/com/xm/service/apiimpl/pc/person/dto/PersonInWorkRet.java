package com.xm.service.apiimpl.pc.person.dto;

import com.xm.platform.annotations.ApiResultFieldDesc;
import com.xm.service.dto.BaseRetDTO;

import java.util.Map;

/**
 * Created by fanshuai on 18/9/19.
 */
public class PersonInWorkRet extends BaseRetDTO {
    @ApiResultFieldDesc(desc = "各区域的数据")
    private Map<String,PersonNumDTO> personNumDTOMap;

    public Map<String, PersonNumDTO> getPersonNumDTOMap() {
        return personNumDTOMap;
    }

    public void setPersonNumDTOMap(Map<String, PersonNumDTO> personNumDTOMap) {
        this.personNumDTOMap = personNumDTOMap;
    }
}
