INSERT INTO Users (UserId, UserName)
VALUES (1, "Bob"),
       (2, "Jane");

INSERT INTO ToDo (Description, DateStarted, Completed, UserId)
VALUES ("Finish java-orders-swagger", datetime("2019-01-13 04:04:04"), 0, 1),
       ("Walk the dogs", datetime("2019-01-17 04:04:04"), 1, 2),
       ("Feed the turtles", datetime("2019-03-01 04:04:04"), 0, 2),
       ("Complete the sprint challenge", datetime("2019-02-22 04:04:04"), 0, 1),
       ("provide feedback to my instructor", datetime("2019-02-13 04:04:04"), 1, 1);