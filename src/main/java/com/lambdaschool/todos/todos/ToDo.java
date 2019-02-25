package com.lambdaschool.todos.todos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lambdaschool.todos.users.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
  @Column(name = "ToDoId")
  private long toDoId;

  @NotNull(message = "description cannot be blank")
  @Column(name = "Description")
  private String description;

  @NotNull(message = "dateStarted cannot be blank")
  @Column(name = "DateStarted")
  private String dateStarted;

  @NotNull(message = "completed cannot be blank")
  @Min(value = 0, message = "completed must be a 0 or 1")
  @Max(value = 1, message = "completed must be a 0 or 1")
  @Column(name = "Completed")
  private int completed;

  @NotNull(message = "userId cannot be blank")
  @Column(name = "UserId")
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
