package com.web.dto.response;

import com.web.entity.Department;
import com.web.entity.Specialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecializeResponse {

    private Long id;

    private String codeSpecialize;

    private String nameSpecialize;

    private Long departmentId;

    public static SpecializeResponse converterSpecializeToSpecializeResponse(Specialize specialize){
        return new SpecializeResponse(
                specialize.getId(),
                specialize.getCodeSpecialize(),
                specialize.getNameSpecialize(),
                specialize.getDepartment().getId()
        );
    }
}
