package com.lambdaschool.todos.todos;

import com.lambdaschool.todos.todos.projections.UserToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * A database interface for the ToDo table
 */
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
  List<UserToDo> findUserToDosBy();
  List<ToDo> findByCompletedEquals(int completed);
}
