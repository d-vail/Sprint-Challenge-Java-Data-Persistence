package com.lambdaschool.todos.todos;

import com.lambdaschool.todos.todos.projections.UserToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ToDo Service
 */
@Service
public class ToDoService {
  @Autowired
  private ToDoRepository toDoRepo;

  private static final int TODO_PENDING = 0;
  private static final int TODO_COMPLETE = 1;

  /**
   * Retrieve all todos.
   *
   * @return  A list of all todos
   */
  public List<ToDo> getToDos() {
    return toDoRepo.findAll();
  }

  /**
   * Retrieve a todo based on the todo id.
   *
   * @param toDoId  A todo id
   * @return        A todo
   */
  public ToDo getToDo(long toDoId) {
    return toDoRepo.findById(toDoId).orElse(null);
  }

  /**
   * Retrieve all todos with the assigned user name.
   *
   * @return  A list of all todos
   */
  public List<UserToDo> getToDosWithUser() {
    return toDoRepo.findUserToDosBy();
  }

  /**
   * Retrieve all pending todos.
   *
   * @return  A list of pending todos
   */
  public List<ToDo> getActiveToDos() {
    return toDoRepo.findByCompletedEquals(TODO_PENDING);
  }

  /**
   * Create a new todo
   *
   * @param toDo  A todo JSON data object
   * @return      The saved todo
   */
  public ToDo createToDo(ToDo toDo) {
    return toDoRepo.save(toDo);
  }

  /**
   * Update a todo based on the todo id.
   *
   * @param toDoId      The todo id
   * @param updatedToDo The todo JSON data object
   * @return            The updated todo
   */
  public ToDo updateToDo(long toDoId, ToDo updatedToDo) {
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
   * @return        The deleted todo
   */
  public ToDo deleteToDo(long toDoId) {
    Optional<ToDo> toDo = toDoRepo.findById(toDoId);

    if(toDo.isPresent()) {
      toDoRepo.deleteById(toDoId);
    }

    return toDo.orElse(null);
  }
}
