package com.lambdaschool.todos.todos.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * An interface based projection to display to do with its assigned user
 */
@JsonPropertyOrder({"toDoId", "description", "dateStarted", "completed"})
public interface UserToDo {
  long getToDoId();
  String getDescription();
  String getDateStarted();
  int GetCompleted();
  AssignedUser getUser();

  interface AssignedUser {
    String getUserName();
  }
}
