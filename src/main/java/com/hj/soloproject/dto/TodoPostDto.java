package com.hj.soloproject.dto;

import lombok.Getter;

@Getter
public class TodoPostDto {
    private String title;
    private Integer order;
    private boolean completed;
}
