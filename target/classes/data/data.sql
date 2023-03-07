--  users
INSERT INTO users (email,name,password,username) VALUES ('park@google.co.kr','park','$2a$10$MzaSxBoPG9pDVqu./2A1VuR10k6fVwT7m9/g5oVZTxUyFl3fPCcDO','park');
INSERT INTO users (email,name,password,username) VALUES ('admin@admin.kr','admin','$2a$10$enWNKtC346SN.3TdqF01yOAEMDeVaEAO9wHnqkZ5vBA89EXBVQBOy','admin');

--  roles
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

--  users_roles
INSERT INTO users_roles (user_id,role_id) VALUES (1,2);
INSERT INTO users_roles (user_id,role_id) VALUES (2,1);

-- 
insert into categories (name, sescription) values ('탐방', '탐방입니다');

-- 
insert into posts (title, description, content, category_id) values ('제목입니다', '냉용입니다', '설명입니다', 1);