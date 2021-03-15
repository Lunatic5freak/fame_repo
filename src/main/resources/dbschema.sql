create database internship;

create table images(
    id int primary key auto_increment,
    filename varchar(20),
    filetype varchar(10),
    data longblob
    );

create table users(
    username varchar(30) primary key,
    fname varchar(20),
    lname varchar(20),
    contact varchar(11),
    password varchar(100),
    fileid int,
    enabled int default 1,
    foreign key(fileid) references images(id);
);

create table authorities(
    username varchar(30),
    authority varchar(15) default 'USER',
    id int primary key auto_increment,
    foreign key(username) references users(userid)
);
