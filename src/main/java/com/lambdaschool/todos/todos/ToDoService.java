package com.lambdaschool.todos.todos;

import com.lambdaschool.todos.exceptions.ResourceNotFoundException;
import com.lambdaschool.todos.todos.projections.UserToDo;
import com.lambdaschool.todos.users.User;
import com.lambdaschool.todos.users.UserRepository;
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

  @Autowired
  private UserRepository userRepo;

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
   * @throws ResourceNotFoundException if todo id does not exist
   */
  public ToDo getToDo(long toDoId) throws ResourceNotFoundException {
    Optional<ToDo> toDo = toDoRepo.findById(toDoId);
    if(toDo.isEmpty()) {
      throw new ResourceNotFoundException(ToDo.class, "toDoId", Long.toString(toDoId));
    }
    return toDo.orElse(null);
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
   * @throws ResourceNotFoundException if todo id does not exist
   */
  public ToDo updateToDo(long toDoId, ToDo updatedToDo) throws ResourceNotFoundException {
    Long userId = updatedToDo.getUserId();
    Optional<ToDo> toDo = toDoRepo.findById(toDoId);
    Optional<User> user = userRepo.findById(userId);

    if(toDo.isEmpty()) {
      throw new ResourceNotFoundException(ToDo.class, "toDoId", Long.toString(toDoId));
    }

    if(user.isEmpty()) {
      throw new ResourceNotFoundException(User.class, "userId", Long.toString(userId));
    }

    if(updatedToDo.getDateStarted() == null) {
      updatedToDo.setDateStarted(toDo.get().getDateStarted());
    }

    updatedToDo.setToDoId(toDoId);
    toDoRepo.save(updatedToDo);
    return updatedToDo;
  }

  /**
   * Delete a todo based on the todo id.
   *
   * @param toDoId  The todo id
   * @return        The deleted todo
   * @throws ResourceNotFoundException if todo id does not exist
   */
  public ToDo deleteToDo(long toDoId) throws ResourceNotFoundException {
    Optional<ToDo> toDo = toDoRepo.findById(toDoId);

    if(toDo.isEmpty()) {
      throw new ResourceNotFoundException(ToDo.class, "toDoId", Long.toString(toDoId));
    }

    toDoRepo.deleteById(toDoId);
    return toDo.orElse(null);
  }
}
