package com.lambdaschool.todos.users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lambdaschool.todos.todos.ToDo;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * A user
 */
@Data
@Entity
@Table(name = "User")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "UserId")
  private long userId;

  @NotNull(message = "userName cannot be blank")
  @Column(name = "UserName")
  private String userName;

  @JsonManagedReference
  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
  private List<ToDo> toDos = new ArrayList<>();

  /**
   * Default Constructor for JPA
   */
  public User() { }
}
