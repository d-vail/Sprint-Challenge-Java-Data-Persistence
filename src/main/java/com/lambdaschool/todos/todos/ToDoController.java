package com.lambdaschool.todos.todos;

import com.lambdaschool.todos.exceptions.ResourceNotFoundException;
import com.lambdaschool.todos.todos.projections.UserToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ToDo REST Controller
 */
@RestController
@RequestMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ToDoController {
  @Autowired
  private ToDoService toDoService;

  /**
   * Map GET /todos to getToDos() in ToDo Service.
   *
   * @return  A list of all todos
   */
  @GetMapping()
  public List<ToDo> getToDos() {
    return toDoService.getToDos();
  }

  /**
   * Map GET /todos/todoid/{toDoId} to getToDo() in ToDo Service.
   *
   * @param toDoId  A todo id
   * @return        A todo
   * @throws ResourceNotFoundException if todo id does not exist
   */
  @GetMapping("/todoid/{toDoId}")
  public ToDo getToDo(@PathVariable long toDoId) throws ResourceNotFoundException {
    return toDoService.getToDo(toDoId);
  }

  /**
   * Map GET /todos/users to getToDosWithUser() in ToDo Service.
   *
   * @return  A list of all todos
   */
  @GetMapping("/users")
  public List<UserToDo> findAllUserToDos() {
    return toDoService.getToDosWithUser();
  }

  /**
   * Map GET /todos/active to getActiveToDos() in ToDo Service.
   *
   * @return  A list of pending todos
   */
  @GetMapping("/active")
  public List<ToDo> findPendingToDos() {
    return toDoService.getActiveToDos();
  }

  /**
   * Map POST /todos to createToDo() in ToDo Service.
   *
   * @param toDo  A todo JSON data object
   * @return      The saved todo
   */
  @PostMapping()
  public ToDo createToDo(@RequestBody ToDo toDo) {
    return toDoService.createToDo(toDo);
  }

  /**
   * Map PUT /todos/{toDoId} to updateToDo() in ToDo Service.
   *
   * @param toDoId      The todo id
   * @param updatedToDo The todo JSON data object
   * @return            The updated todo
   * @throws ResourceNotFoundException if todo id does not exist
   */
  @PutMapping("/todoid/{toDoId}")
  public ToDo updateToDo(@PathVariable long toDoId, @RequestBody ToDo updatedToDo) throws ResourceNotFoundException {
    return toDoService.updateToDo(toDoId, updatedToDo);
  }

  /**
   * Map DELETE /todos/{toDoId} to deleteTodo() in ToDo Service.
   *
   * @param toDoId  The todo id
   * @return        The deleted todo
   * @throws ResourceNotFoundException if todo id does not exist
   */
  @DeleteMapping("/todoid/{toDoId}")
  public ToDo deleteTodo(@PathVariable long toDoId) throws ResourceNotFoundException {
    return toDoService.deleteToDo(toDoId);
  }
}