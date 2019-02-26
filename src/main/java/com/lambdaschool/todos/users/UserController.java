package com.lambdaschool.todos.users;

import com.lambdaschool.todos.exceptions.ResourceNotFoundException;
import com.lambdaschool.todos.users.projections.UserSummary;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User REST Controller
 */
@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/users", description = "CRUD operations for users", consumes = "application/json")
public class UserController {
  @Autowired
  private UserService userService;

  /**
   * Map GET /users to getUsers() in User Service.
   *
   * @return  A list of all users
   */
  @GetMapping()
  @ApiOperation(value = "Finds all users", response = UserSummary.class, responseContainer = "List")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved list of users"),
          @ApiResponse(code = 500, message = "Something has gone terribly wrong")
  })
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
  @ApiOperation(value = "Find a user by id", response = UserSummary.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved user"),
          @ApiResponse(code = 404, message = "Invalid user id"),
          @ApiResponse(code = 500, message = "Something has gone terribly wrong")
  })
  public UserSummary getUser(
          @ApiParam(value = "The user id", required = true) @PathVariable long userId
  ) throws ResourceNotFoundException {
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
  @ApiOperation(value = "Find a user by name", response = UserSummary.class, responseContainer = "List")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully retrieved user"),
          @ApiResponse(code = 404, message = "Invalid user name"),
          @ApiResponse(code = 500, message = "Something has gone terribly wrong")
  })
  public List<UserSummary> getUserByName(
          @ApiParam(value = "The user name", required = true) @PathVariable String userName
  ) throws ResourceNotFoundException {
    return userService.getUserByName(userName);
  }

  /**
   * Map POST /users to createUser() in User Service.
   *
   * @param user  A user JSON data object
   * @return      The saved user
   */
  @PostMapping()
  @ApiOperation(value = "Create a new user", response = User.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully created user"),
          @ApiResponse(code = 400, message = "Invalid request body"),
          @ApiResponse(code = 500, message = "Something has gone terribly wrong")
  })
  public User createUser(
          @ApiParam(value = "The user object that needs to be created", required = true) @RequestBody User user) {
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
  @ApiOperation(value = "Updates an existing user", response = User.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully updated user"),
          @ApiResponse(code = 400, message = "Invalid request body"),
          @ApiResponse(code = 404, message = "Invalid user id"),
          @ApiResponse(code = 500, message = "Something has gone terribly wrong")
  })
  public User updateUser(
          @ApiParam(value = "The user id", required = true) @PathVariable long userId,
          @ApiParam(value = "The user object that needs to be updated", required = true) @RequestBody User updatedUser
  ) throws ResourceNotFoundException {
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
  @ApiOperation(value = "Deletes a user", response = User.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Successfully deleted user"),
          @ApiResponse(code = 404, message = "Invalid user id"),
          @ApiResponse(code = 500, message = "Something has gone terribly wrong")
  })
  public User deleteUser(
          @ApiParam(value = "The user id", required = true) @PathVariable long userId
  ) throws ResourceNotFoundException {
    return userService.deleteUser(userId);
  }
}
