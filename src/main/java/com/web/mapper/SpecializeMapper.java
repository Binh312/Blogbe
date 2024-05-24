package com.web.mapper;

import com.web.dto.request.SpecializeRequest;
import com.web.entity.Specialize;
import org.springframework.stereotype.Service;

@Service
public class SpecializeMapper {

    public Specialize converterRequestToSpecialize(SpecializeRequest specializeRequest){
        Specialize specialize = new Specialize();
        specialize.setId(specializeRequest.getId());
        specialize.setCodeSpecialize(specializeRequest.getCodeSpecialize());
        specialize.setNameSpecialize(specializeRequest.getNameSpecialize());
        specialize.setIdDepartment(specializeRequest.getDepartmentId());
        return specialize;
    }
}
