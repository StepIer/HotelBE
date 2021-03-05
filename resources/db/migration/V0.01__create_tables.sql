

create TABLE T1 (
    id serial primary key,
    login varchar(100)
);

create TABLE Users(
id serial primary key,
name varchar(255) not null unique,
surname varchar(255) not null,
token varchar(255),
password_hash varchar(255) not null
);

create TABLE Rooms (
id serial primary key,
number int not null,
occupied bool not null,
user_id int,
cost int not null,
FOREIGN KEY (user_id) REFERENCES Users (id)
);

