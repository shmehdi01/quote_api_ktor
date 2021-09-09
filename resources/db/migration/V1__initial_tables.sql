create TABLE Users (
    id int auto_increment,
    name varchar(100) not null,
    email varchar(100) unique,
    password varchar(100),
    active tinyint,
    primary key(id)
)

create TABLE Quotes (
    id int auto_increment,
    quote varchar(100) not null,
    author varchar(100) unique,
    userId int,
    active tinyint,
    primary key(id)
)