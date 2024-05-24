package com.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectRequest {

    private Long id;

    private String codeSubject;

    private String nameSubject;

    private Long specializeId;
}
