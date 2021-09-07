create TABLE Users (
    id int auto_increment,
    name varchar(100) not null,
    email varchar(100) unique,
    password varchar(100),
    active tinyint,
    primary key(id)
)