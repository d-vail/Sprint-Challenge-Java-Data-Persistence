package com.lambdaschool.todos.users;

import com.lambdaschool.todos.exceptions.ResourceNotFoundException;
import com.lambdaschool.todos.users.projections.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User REST Controller
 */
@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
  @Autowired
  private UserService userService;

  /**
   * Map GET /users to getUsers() in User Service.
   *
   * @return  A list of all users
   */
  @GetMapping()
  public List<UserSummary> getUsers() {
    return userService.getUsers();
  }

  /**
   * Map GET /users/userid/{userId} to getUser() in User Service.
   *
   * @return  A user
   * @throws ResourceNotFoundException if user id does not exist
   */
  @GetMapping("/userid/{userId}")
  public UserSummary getUser(@PathVariable long userId) throws ResourceNotFoundException {
    return userService.getUser(userId);
  }

  /**
   * Map GET /users/username/{userName} to getUserByName() in User Service.
   *
   * @param userName  A user name
   * @return          A list of matching users
   * @throws ResourceNotFoundException if user name does not exist
   */
  @GetMapping("/username/{userName}")
  public List<UserSummary> getUserByName(@PathVariable String userName) throws ResourceNotFoundException {
    return userService.getUserByName(userName);
  }

  /**
   * Map POST /users to createUser() in User Service.
   *
   * @param user  A user JSON data object
   * @return      The saved user
   */
  @PostMapping()
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  /**
   * Map PUT /users/userid/{userId} to updateUser() in User Service.
   *
   * @param userId      The user id
   * @param updatedUser A user JSON data object
   * @return            The updated user
   * @throws ResourceNotFoundException if user id does not exist
   */
  @PutMapping("/userid/{userId}")
  public User updateUser(@PathVariable long userId, @RequestBody User updatedUser) throws ResourceNotFoundException {
    return userService.updateUser(userId, updatedUser);
  }

  /**
   * Map DELETE /users/userid/{userId} to deleteUser() in User Service.
   *
   * @param userId  The user id
   * @return        The deleted user and associated todos
   * @throws ResourceNotFoundException if user id does not exist
   */
  @DeleteMapping("/userid/{userId}")
  public User deleteUser(@PathVariable long userId) throws ResourceNotFoundException {
    return userService.deleteUser(userId);
  }
}
