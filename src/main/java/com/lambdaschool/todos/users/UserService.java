package com.lambdaschool.todos.users;

import com.lambdaschool.todos.exceptions.ResourceNotFoundException;
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
   * @throws ResourceNotFoundException if user id does not exist
   */
  public UserSummary getUser(long userId) throws ResourceNotFoundException {
    UserSummary user = userRepo.findByUserId(userId);
    if(user == null) {
      throw new ResourceNotFoundException(User.class, "userId", Long.toString(userId));
    }
    return user;
  }

  /**
   * Retrieve a user based on the user name.
   *
   * @param userName  A user name
   * @return          A list of matching users
   * @throws ResourceNotFoundException if user name does not exist
   */
  public List<UserSummary> getUserByName(String userName) throws ResourceNotFoundException {
    List<UserSummary> users = userRepo.findByUserName(userName);
    if(users.isEmpty()) {
      throw new ResourceNotFoundException(User.class, "userName", userName);
    }
    return users;
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
   * @throws ResourceNotFoundException if user id does not exist
   */
  public User updateUser(long userId, User updatedUser) throws ResourceNotFoundException {
    Optional<User> user = userRepo.findById(userId);

    if(user.isEmpty()) {
      throw new ResourceNotFoundException(User.class, "userId", Long.toString(userId));
    }

    updatedUser.setUserId(userId);
    userRepo.save(updatedUser);
    return updatedUser;
  }

  /**
   * Delete a user and the user's associated todos based on the user id.
   *
   * @param userId  The user id
   * @return        The deleted user and associated todos
   * @throws ResourceNotFoundException if user id does not exist
   */
  public User deleteUser(long userId) throws ResourceNotFoundException {
    Optional<User> user = userRepo.findById(userId);

    if(user.isEmpty()) {
      throw new ResourceNotFoundException(User.class, "userId", Long.toString(userId));
    }

    userRepo.deleteById(userId);
    return user.orElse(null);
  }
}
