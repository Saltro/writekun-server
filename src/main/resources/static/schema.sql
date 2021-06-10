# 表初始化
create table if not exists `note` (
    id int primary key auto_increment,
    title varchar(30),
    tag varchar(30),
    text varchar(1000),
    create_date timestamp,
    change_date timestamp,
    `status` int,
    user_id int,
    username varchar(50)
) character set = utf8;

create table if not exists `user` (
      `user_id` int primary key auto_increment,
      `username` varchar(150),
      `password` varchar(128),
      `email` varchar(150),
      `role` varchar(20),
      is_active int(1)
) character set = utf8;

create table if not exists `role` (
    role_name varchar(20),
    authority varchar(150)
) character set = utf8;

insert into role(role_name, authority) values('USER', 'note');