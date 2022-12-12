package com.hj.soloproject.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoPatchDto {
    private Long id;
    private String title;
    private Integer order;
    private boolean completed;
}
