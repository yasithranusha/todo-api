package com.yasith.todo.service;

import com.yasith.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto addToDo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodo(Long id, TodoDto todoDto);

    void deleteTodo(Long id);

    TodoDto completeTodo(Long id);
    TodoDto inCompleteTodo(Long id);
}
