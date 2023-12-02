package com.yasith.todo.controller;

import com.yasith.todo.dto.TodoDto;
import com.yasith.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;

    //ADD todo RestAPI

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo = todoService.addToDo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //GET todo RESTAPI
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto,HttpStatus.OK);
    }
    //GET all todos RESTAPI
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todos=todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    //UPDATE todo RESTAPI
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long todoId,@RequestBody TodoDto todoDto){
        TodoDto updatedTodo = todoService.updateTodo(todoId, todoDto);
        return ResponseEntity.ok(updatedTodo);
    }

    //DELETE todo RESTAPI
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted successfully!");
    }

    //COMPLETE todo RESTAPI
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){
        TodoDto completedTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(completedTodo);
    }

    //IN-COMPLETE todo RESTAPI
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId){
        TodoDto inCompletedTodo = todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(inCompletedTodo);
    }
}
