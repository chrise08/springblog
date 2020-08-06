USE ganymede_spring_db;

insert into users (username, email, password) values
('bob123', 'bob@email.com', 'password');

INSERT INTO posts (title, body, author_id) VALUES
('Title 1', 'Something in the body to represent it.', 1),
('Title 2', 'Something in the body to represent it.', 1),
('Title 3', 'Something in the body to represent it.', 1),
('Title 4', 'Something in the body to represent it.', 1);