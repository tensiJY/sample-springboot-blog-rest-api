

drop table if exists comments    
drop table if exists post;
drop table if exists users_roles;


    
    create table post (
       id bigint not null auto_increment,
        content varchar(255) not null,
        description varchar(255) not null,
        title varchar(255) not null,
        primary key (id)
    ) engine=InnoDB;

    alter table if exists post 
       add constraint UK_2jm25hjrq6iv4w8y1dhi0d9p4 unique (title);