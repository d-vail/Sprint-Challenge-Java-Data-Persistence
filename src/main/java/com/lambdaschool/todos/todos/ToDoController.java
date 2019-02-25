package com.lambdaschool.todos.todos;

import com.lambdaschool.todos.exceptions.ResourceNotFoundException;
import com.lambdaschool.todos.todos.projections.UserToDo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ToDo REST Controller
 */
@RestController
@RequestMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/todos", description = "CRUD operations for to do's", consumes = "application/json")
public class ToDoController {
  @Autowired
  private ToDoService toDoService;

  /**
   * Map GET /todos to getToDos() in ToDo Service.
   *
   * @return  A list of all todos
   */
  @GetMapping()
  @ApiOperation(value = "Finds all to do's", response = ToDo.class, responseContainer = "List")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved list of to do's")
  })
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
  @ApiOperation(value = "Find a to do by id", response = ToDo.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved to do"),
          @ApiResponse(code = 404, message = "Invalid to do id")
  })
  public ToDo getToDo(
          @ApiParam(value = "The to do id", required = true) @PathVariable long toDoId
  ) throws ResourceNotFoundException {
    return toDoService.getToDo(toDoId);
  }

  /**
   * Map GET /todos/users to getToDosWithUser() in ToDo Service.
   *
   * @return  A list of all todos
   */
  @GetMapping("/users")
  @ApiOperation(value = "Finds all to do's with the assigned user", response = UserToDo.class,
          responseContainer = "List")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved list of to do's")
  })
  public List<UserToDo> findAllUserToDos() {
    return toDoService.getToDosWithUser();
  }

  /**
   * Map GET /todos/active to getActiveToDos() in ToDo Service.
   *
   * @return  A list of pending todos
   */
  @GetMapping("/active")
  @ApiOperation(value = "Finds all pending to do's", response = ToDo.class, responseContainer =
          "List")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved list of active to do's")
  })
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
  @ApiOperation(value = "Create a new to do", response = ToDo.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully created to do")
  })
  public ToDo createToDo(
          @ApiParam(value = "The to do object that needs to be created", required = true) @RequestBody ToDo toDo) {
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
  @ApiOperation(value = "Update an existing to do", response = ToDo.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully updated to do"),
          @ApiResponse(code = 404, message = "Invalid to do id")
  })
  public ToDo updateToDo(
          @ApiParam(value = "The to do id", required = true) @PathVariable long toDoId,
          @ApiParam(value = "The to do object to be updated") @RequestBody ToDo updatedToDo
  ) throws ResourceNotFoundException {
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
  @ApiOperation(value = "Deletes a to do", response = ToDo.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully deleted to do"),
          @ApiResponse(code = 404, message = "Invalid to do id")
  })
  public ToDo deleteTodo(
          @ApiParam(value = "The to do id", required = true) @PathVariable long toDoId
  ) throws ResourceNotFoundException {
    return toDoService.deleteToDo(toDoId);
  }
}