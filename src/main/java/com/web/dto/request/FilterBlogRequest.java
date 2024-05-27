package com.web.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.web.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterBlogRequest {
    private String title;

    private String description;

    private String content;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdDate;

    private Boolean actived;

    private String userName;

    private String cateName;
}
