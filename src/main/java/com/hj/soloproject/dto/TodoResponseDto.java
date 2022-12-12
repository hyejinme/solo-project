package com.hj.soloproject.dto;

import com.hj.soloproject.Todo;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {
    private Long id;
    private String title;
    private Integer order;
    private boolean completed;
    private String url;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.order = todo.getOrder();
        this.completed = todo.isCompleted();
        this.url = "http://localhost:8080/" + this.id;
    }
}

