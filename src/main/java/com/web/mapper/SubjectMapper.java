package com.web.mapper;

import com.web.dto.request.SubjectRequest;
import com.web.entity.Subject;
import org.springframework.stereotype.Service;

@Service
public class SubjectMapper {

    public Subject converterRequestToSubject(SubjectRequest subjectRequest){
        Subject subject = new Subject();
        subject.setId(subjectRequest.getId());
        subject.setCodeSubject(subjectRequest.getCodeSubject());
        subject.setNameSubject(subjectRequest.getNameSubject());
        subject.setConnecIdSpecialize(subjectRequest.getSpecializeId());
        return subject;
    }
}
