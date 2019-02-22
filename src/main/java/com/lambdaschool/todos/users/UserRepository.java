package com.lambdaschool.todos.users;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A database interface for the USer table
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
