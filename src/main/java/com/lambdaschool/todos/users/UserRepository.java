package com.lambdaschool.todos.users;

import com.lambdaschool.todos.users.projections.UserSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * A database interface for the USer table
 */
public interface UserRepository extends JpaRepository<User, Long> {
  List<UserSummary> findAllUsersBy();
  List<UserSummary> findByUserName(String userName);
  UserSummary findByUserId(long userId);
}
