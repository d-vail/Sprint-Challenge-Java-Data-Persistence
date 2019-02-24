package com.lambdaschool.todos.users;

import com.lambdaschool.todos.users.projections.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * User Service
 */
@Service
public class UserService {
  @Autowired
  private UserRepository userRepo;

  /**
   * Retrieve all users.
   *
   * @return  A list of all users
   */
  public List<UserSummary> getUsers() {
    return userRepo.findAllUsersBy();
  }

  /**
   * Retrieve a user based on the user id.
   *
   * @param userId  A user id
   * @return        A user
   */
  public UserSummary getUser(long userId) {
    return userRepo.findByUserId(userId);
  }

  /**
   * Retrieve a user based on the user name.
   *
   * @param userName  A user name
   * @return          A list of matching users
   */
  public List<UserSummary> getUserByName(String userName) {
    return userRepo.findByUserName(userName);
  }

  /**
   * Create a new user.
   *
   * @param user  A user JSON data object
   * @return      The saved user
   */
  public User createUser(User user) {
    return userRepo.save(user);
  }

  /**
   * Update a user based on the user id.
   *
   * @param userId      The user id
   * @param updatedUser A user JSON data object
   * @return            The updated user
   */
  public User updateUser(long userId, User updatedUser) {
    Optional<User> user = userRepo.findById(userId);

    if(user.isPresent()) {
      updatedUser.setUserId(userId);
      userRepo.save(updatedUser);
      return updatedUser;
    } else {
      return null;
    }
  }

  /**
   * Delete a user and the user's associated todos based on the user id.
   *
   * @param userId  The user id
   * @return        The deleted user and associated todos
   */
  public User deleteUser(long userId) {
    Optional<User> user = userRepo.findById(userId);

    if(user.isPresent()) {
      userRepo.deleteById(userId);
    }

    return user.orElse(null);
  }
}
