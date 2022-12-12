package com.hj.soloproject;

import com.hj.soloproject.dto.TodoPatchDto;
import com.hj.soloproject.dto.TodoPostDto;
import com.hj.soloproject.dto.TodoResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/")
@Validated
@CrossOrigin
@Slf4j
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper) {
        this.todoService = todoService;
        this.mapper = mapper;
    }

    /**
     * Todo 등록(Post)
     */
    @PostMapping
    public ResponseEntity postTodo(@RequestBody TodoPostDto todoPostDto) {
        log.info("========CREATE========");
        Todo todo = mapper.todoPostDtoToTodo(todoPostDto);
        //ObjectUtils.isEmpty => null, 빈 문자열 체크
        // order와 completed가 비어있을 시, 각각 세팅
        if(ObjectUtils.isEmpty(todoPostDto.getOrder())) {
            todo.setOrder(1);
        }
        if(ObjectUtils.isEmpty(todoPostDto.isCompleted())) {
            todo.setCompleted(false);
        }

        Todo response = todoService.createTodo(todo);
        //return new ResponseEntity<>(mapper.todoToTodoResponseDto(response), HttpStatus.CREATED);
        return new ResponseEntity<>(new TodoResponseDto(response), HttpStatus.CREATED);

    }

    /**
     * Todo 수정(Patch)
     */
    @PatchMapping("/{id}")
    public ResponseEntity patchTodo(@PathVariable("id") @Positive Long id,
                                    @RequestBody TodoPatchDto todoPatchDto){
        log.info("========UPDATE========");
        todoPatchDto.setId(id);
        Todo response = todoService.updateTodo(mapper.todoPatchDtoToTodo(todoPatchDto));

        return new ResponseEntity<>(new TodoResponseDto(response), HttpStatus.OK);
    }

    /**
     * 특정 Todo 조회(Get)
     */
    @GetMapping("/{id}")
    public ResponseEntity getTodo(@PathVariable("id") @Positive Long id) {
        log.info("========READ========");
        Todo response = todoService.findTodo(id);
        return new ResponseEntity<>(new TodoResponseDto(response), HttpStatus.OK);
    }

    /**
     * 모든 Todo 조회(Get)
     */
    @GetMapping
    public ResponseEntity getTodos() {
        log.info("========READ ALL========");
        List<Todo> todos = todoService.findTodos();

        List<TodoResponseDto> response =
                todos.stream()
                        .map(TodoResponseDto::new)
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 특정 Todo 삭제(Delete)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable("id") @Positive Long id) {
        log.info("========DELETE========");
        todoService.deleteTodo(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * 전체 Todo 삭제(Delete)
     */
    @DeleteMapping
    public ResponseEntity deleteTodo() {
        log.info("========DELETE ALL========");
        todoService.deleteAll();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
