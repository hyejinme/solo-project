package com.hj.soloproject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo) {
       Todo findTodo = findVerifiedTodo(todo.getId());

       Optional.ofNullable(todo.getTitle()).ifPresent(title -> findTodo.setTitle(title));
       Optional.ofNullable(todo.getOrder()).ifPresent(todoOrder -> findTodo.setOrder(todoOrder));
       Optional.ofNullable(todo.isCompleted()).ifPresent(isCompleted -> findTodo.setCompleted(isCompleted));
       return todoRepository.save(findTodo);
    }

    public Todo findTodo(Long id) {
        return findVerifiedTodo(id);
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    public void deleteTodo(Long id) {
        Todo findTodo = findVerifiedTodo(id);

        todoRepository.delete(findTodo);
    }

    public void deleteAll() {
        todoRepository.deleteAll();
    }

    public Todo findVerifiedTodo(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo findTodo = optionalTodo.orElseThrow(() -> new RuntimeException("TODO NOT FOUND"));
        return findTodo;
    }
}
