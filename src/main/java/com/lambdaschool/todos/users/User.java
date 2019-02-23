package com.lambdaschool.todos.users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lambdaschool.todos.todos.ToDo;
import lombok.Data;

import javax.persistence.*;
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
  @Column(name = "UserId", nullable = false)
  private long userId;

  @Column(name = "UserName", nullable = false)
  private String userName;

  @JsonManagedReference
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<ToDo> toDos = new ArrayList<>();

  /**
   * Default Constructor for JPA
   */
  public User() { }
}
