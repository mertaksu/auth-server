-- Truncate all tables
truncate users_roles;
truncate user_role_privileges;
truncate privilege;
truncate user_role;
truncate users;

select * from oauth_client_details; -- oauth client details table

select * from users; -- registered users table

select * from user_role; -- role table

select * from users_roles; -- user role map table

select * from privilege; -- privilege table

select * from user_role_privileges; -- role privilege map table
