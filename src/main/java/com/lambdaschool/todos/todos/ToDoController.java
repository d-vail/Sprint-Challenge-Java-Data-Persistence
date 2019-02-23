package com.lambdaschool.todos.todos;

import com.lambdaschool.todos.todos.projections.UserToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ToDo REST Controller
 */
@RestController
@RequestMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ToDoController {
  @Autowired
  private ToDoRepository toDoRepo;

  private static final int TODO_PENDING = 0;
  private static final int TODO_COMPLETE = 1;

  /**
   * Find all todo items.
   *
   * @return  A list of all todo items
   */
  @GetMapping()
  public List<ToDo> findAllToDos() {
    return toDoRepo.findAll();
  }

  /**
   * Find a todo item by id
   * @param toDoId  A todo id
   * @return        A todo item or null if todo is not found
   */
  @GetMapping("/todoid/{toDoId}")
  public ToDo findToDoById(@PathVariable long toDoId) {
    return toDoRepo.findById(toDoId).orElse(null);
  }

  /**
   * Find all todos with the assigned user name.
   *
   * @return  A list of all todos with the assigned user name
   */
  @GetMapping("/users")
  public List<UserToDo> findAllUserToDos() {
    return toDoRepo.findUserToDosBy();
  }

  /**
   * Find all todos not yet completed.
   *
   * @return  A list of pending todos
   */
  @GetMapping("/active")
  public List<ToDo> findPendingToDos() {
    return toDoRepo.findByCompletedEquals(TODO_PENDING);
  }

  /**
   * Create a new todo
   * @param toDo  A todo JSON data object
   * @return      The saved todo
   */
  @PostMapping()
  public ToDo createToDo(@RequestBody ToDo toDo) {
    return toDoRepo.save(toDo);
  }

  /**
   * Update a todo based on the todo id.
   *
   * @param toDoId      The todo id
   * @param updatedToDo The todo JSON data object
   * @return            The updated todo or null if todo was not found
   */
  @PutMapping("/todoid/{toDoId}")
  public ToDo updateToDo(@PathVariable long toDoId, @RequestBody ToDo updatedToDo) {
    Optional<ToDo> toDo = toDoRepo.findById(toDoId);

    if(toDo.isPresent()) {
      if(updatedToDo.getDateStarted() == null) {
        updatedToDo.setDateStarted(toDo.get().getDateStarted());
      }

      updatedToDo.setToDoId(toDoId);
      toDoRepo.save(updatedToDo);
      return updatedToDo;
    } else {
      return null;
    }
  }

  /**
   * Delete a todo based on the todo id.
   *
   * @param toDoId  The todo id
   * @return        The deleted todo or null if the todo is not found
   */
  @DeleteMapping("/todoid/{toDoId}")
  public ToDo deleteTodo(@PathVariable long toDoId) {
    Optional<ToDo> toDo = toDoRepo.findById(toDoId);

    if(toDo.isPresent()) {
      toDoRepo.deleteById(toDoId);
    }

    return toDo.orElse(null);
  }
}