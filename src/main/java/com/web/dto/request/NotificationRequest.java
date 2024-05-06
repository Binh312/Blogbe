package com.web.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class NotificationRequest {

    private Long id;

    private String title;

    private String image;

    private String content;

    private List<FileDto> linkFiles = new ArrayList<>();
}
