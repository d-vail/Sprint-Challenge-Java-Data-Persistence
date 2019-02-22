package com.lambdaschool.todos.users.projections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * An interface based projection to a user's name and id
 */
@JsonPropertyOrder({"userId"})
public interface UserSummary {
  long getUserId();
  String getUserName();
}
