package com.web.dto.response;

import com.web.entity.Specialize;
import com.web.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponse {

    private Long id;

    private String codeSubject;

    private String nameSubject;

    private Long specializeId;

    public static SubjectResponse converterSubjectToSubjectResponse(Subject subject){
        return new SubjectResponse(
                subject.getId(),
                subject.getCodeSubject(),
                subject.getNameSubject(),
                subject.getSpecialize().getId()
        );
    }
}
