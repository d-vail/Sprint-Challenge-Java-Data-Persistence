package com.lambdaschool.todos.todos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lambdaschool.todos.users.User;
import lombok.Data;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A todo item
 */
@Data
@Entity
@Table(name = "ToDo")
public class ToDo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ToDoId", nullable = false)
  private long toDoId;

  @Column(name = "Description", nullable = false)
  private String description;

  @Column(name = "DateStarted", nullable = false)
  private String dateStarted;

  @Column(name = "Completed", nullable = false)
  private int completed;

  @Column(name = "UserId", nullable = false)
  private long userId;

  @JsonBackReference
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "UserId", insertable = false, updatable = false)
  private User user;

  /**
   * Default Constructor for JPA
   */
  public ToDo() { }

  /**
   * Set the current timestamp before todo is inserted into database
   */
  @PrePersist
  public void setDefaultTimestamp() {
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    this.dateStarted = dateFormat.format(date);
  }
}
