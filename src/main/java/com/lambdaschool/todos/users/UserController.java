package com.lambdaschool.todos.users;

import com.lambdaschool.todos.users.projections.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * User REST Controller
 */
@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
  @Autowired
  private UserRepository userRepo;

  /**
   * Find all users.
   *
   * @return  A list of all users
   */
  @GetMapping()
  public List<UserSummary> findAllUsers() {
    return userRepo.findAllUsersBy();
  }

  /**
   * Find a user based on the user id.
   *
   * @param userId  A user id
   * @return        A user or null if user is not found
   */
  @GetMapping("/userid/{userId}")
  public UserSummary findUserById(@PathVariable long userId) {
    return userRepo.findByUserId(userId);
  }

  /**
   * Find a user based on the user name.
   *
   * @param userName  A user name
   * @return          A list of matching users
   */
  @GetMapping("/username/{userName}")
  public List<UserSummary> findUserByName(@PathVariable String userName) {
    return userRepo.findByUserName(userName);
  }

  /**
   * Create a new user.
   *
   * @param user  A user JSON data object
   * @return      The saved user
   */
  @PostMapping()
  public User createUser(@RequestBody User user) {
    return userRepo.save(user);
  }

  /**
   * Update a user based on the user id.
   *
   * @param userId      The user id
   * @param updatedUser A user JSON data object
   * @return            The updated user or null if user was not found
   */
  @PutMapping("/userid/{userId}")
  public User updateUser(@PathVariable long userId, @RequestBody User updatedUser) {
    Optional<User> user = userRepo.findById(userId);

    if(user.isPresent()) {
      updatedUser.setUserId(userId);
      userRepo.save(updatedUser);
      return updatedUser;
    } else {
      return null;
    }
  }
}
