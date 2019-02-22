package com.lambdaschool.todos.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ToDo REST Controller
 */
@RestController
@RequestMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ToDoController {
  @Autowired
  private ToDoRepository toDoRepo;
}
