# 表初始化
create table if not exists `note` (
    id int primary key auto_increment,
    title varchar(30),
    tag varchar(30),
    text varchar(1000),
    `date` date,
    `status` int,
    user_id int,
    username varchar(50)
) character set = utf8;