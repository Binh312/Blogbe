package com.web.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class BlogRequest {

    private Long id;

    private String title;

    private String description;

    private String image;

    private String content;

    private List<Long> listCategoryId = new ArrayList<>();
}
