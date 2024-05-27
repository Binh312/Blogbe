package com.web.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterDocumentRequest {
    private String name;

    private String description;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdDate;

    private Boolean actived;

    private String userName;

    private String nameSubject;
}
