package com.web.dto.request;

import com.web.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecializeRequest {

    private Long id;

    private String codeSpecialize;

    private String nameSpecialize;

    private Long departmentId;
}
