package com.web.dto.response;

import com.web.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {

    private Long id;

    private String nameDepartment;

    public static DepartmentResponse converterDepaertmentToDepartmentResponse(Department department){
        return new DepartmentResponse(
                department.getId(),
                department.getNameDepartment()
        );
    }
}
