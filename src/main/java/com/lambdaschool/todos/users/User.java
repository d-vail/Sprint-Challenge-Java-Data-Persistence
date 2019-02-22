package com.lambdaschool.todos.users;

import lombok.Data;

import javax.persistence.*;

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

  /**
   * Default Constructor for JPA
   */
  public User() { }
}
