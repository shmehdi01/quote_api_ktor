create TABLE Users (
    id int auto_increment,
    name varchar(100) not null,
    email varchar(100) unique,
    password varchar(100),
    active tinyint,
    primary key(id)
);

create TABLE Quotes (
    id int auto_increment,
    quote varchar(100) not null,
    author varchar(100) ,
    authorId varchar(100) ,
    userId int,
    active tinyint,
    primary key(id)
);

create TABLE Categories (
    id int auto_increment,
    name varchar(100) not null,
    active tinyint,
    primary key(id)
);