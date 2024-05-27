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
public class FilterUserRequest {
    private String username;

    private String fullName;

    private Boolean actived;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdDate;

    private String role;
}
