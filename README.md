# Sprint Challenge: Java Data Persistence

Lambda School Sprint Challenge for Java Data Persistence with JPA, Hibernate and SQLite.

## Introduction

This is a basic todo database scheme with users and a todo list.

## Instructions

Create a REST api server to store and read data from the provided SQLite Database called todos.db. `data.sql` is the script 
that was used to populate the database.

The table layouts are as follows:

- ToDo
    - ToDoId primary key, not null int
    - Description string, not null
    - DateStarted datetime
    - Completed boolean (0 = false 1 = true)
    - UserId foreign key (one user to many todos) not null
- User
    - UserId primary key, not null int
    - UserName string, not null

### Expose the following endpoints

| Method    | Endpoint                  | Action                                                    |
| --------- | ------------------------- | --------------------------------------------------------- |
| GET       | /users                    | Returns all users                                         |
| GET       | /todos                    | Returns all todos                                         |
| GET       | /users/userid/{userid}    | Return the user based off of the user id                  |
| GET       | /users/username/{username}| Return the user based off of the user name                |
| GET       | /todos/todoid/{todoid}    | Return the todo based off of the todo id                  |
| GET       | /todos/users              | Return a listing of the todos with its assigned user name |
| GET       | /todos/active             | Return a listing of the todos not yet completed           |
| POST      | /users                    | Add a user                                                |
| POST      | /todos                    | Add a todo                                                |
| PUT       | /users/userid/{userid}    | Update a user based on the user id                        |
| PUT       | /todos/todoid/{todoid}    | Update a todo based on the todo id                        |
| DELETE    | /users/userid/{userid}    | Delete a user and associated todos based on the user id   |
| DELETE    | /todos/todoid/{todoid}    | Delete a todo based on the todo id                        |

Keep in mind:

- The end points should return null when no data is found
- Change endpoints so they return data that is deleted or a new copy of updated data
- End points should return the data they worked with or nothing if no data was found

### Add Swagger Documentation

Add custom responses to each of the follow error conditions:

- 200 - successfully retrieve list
- 401 - not authorized for this resource
- 403 - access to resource forbidden
- 404 - resource not found

Add custom Swagger Documentation to each of the follow endpoints. The rest of the end points may have the default 
documentation.

- GET /todos
- GET /todos/users
- GET /todos/active
- PUT /todos/todoid/{todoid}
- DELETE /todos/todoid/{todoid}

### Add flyway data migration

This project will just have a starting migration. The DDL for creating the tables can be found in the file `tables.DDL`

### Expose actuator endpoints

Expose at least the following the actuator endpoints to help with system management:

- /health
- /inf
- /metrics

## Stretch Goals

- Add custom responses to the rest of the endpoints
- Update each of these three actuator endpoints to report your own messages
