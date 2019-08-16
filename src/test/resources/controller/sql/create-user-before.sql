delete from user_role;
delete from usr;

insert into usr(id, name, email, password, email_verified, active, provider, provider_id) values
(1, 'user', 'local@local.com', '$2a$10$548paWmKs2cwwRZmtwqvSO7trumu5V/AOYS6fS2gniJWjllYzMMfq', false, true, 'local', ''),
(2, 'user', 'google@gmail.com', '', false, true, 'google', '1148398703401029107');

insert into user_role(user_id, roles) values
(1, 'USER'), (2, 'USER');