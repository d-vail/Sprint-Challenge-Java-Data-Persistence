package com.lambdaschool.todos.todos;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A database interface for the ToDo table
 */
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
