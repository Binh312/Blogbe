package com.web.dto.response;

import com.web.entity.Document;
import com.web.entity.Subject;
import com.web.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentResponse {

    private Long id;

    private String name;

    private LocalDate createdDate;

    private LocalDateTime createdTime;

    private String image;

    private Integer numView;

    private String description;

    private String linkFile;

    private Boolean actived;

    private String nameSubject;

    private User user;

    public static DocumentResponse converterDocumentToDocumentResponse(Document document){
        return new DocumentResponse(
                document.getId(),
                document.getName(),
                document.getCreatedDate(),
                document.getCreatedTime(),
                document.getImage(),
                document.getNumView(),
                document.getDescription(),
                document.getLinkFile(),
                document.getActived(),
                document.getNameSubject(),
                document.getUser()
        );
    }

}
