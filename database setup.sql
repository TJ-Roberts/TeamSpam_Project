drop database if exists team_spam_db;
create database if not exists team_spam_db;
use team_spam_db;

create table users
(
	userId int not null primary key auto_increment,
    firstName varchar(16) not null,
    lastName varchar(16) not null,
    isOrganizer boolean not null,
    role varchar(16),
    summary varchar(64)
);

create table events
(
	eventId int not null primary key auto_increment,
    userId int not null,
    location varchar(32) not null,
    eventTime varchar(16) not null,
    eventDate varchar(16) not null,
    eventTitle varchar(32) not null,
    description varchar(64),
    foodType varchar(32),
    organization varchar(32),
    FOREIGN KEY (userId) references users(userId)
);

create table attending
(
	eventId int not null,
    userId int not null,
    primary key (eventId, userId),
    foreign key (eventId) references events(eventId),
    foreign key (userId) references users(userId)
);

create table clubs
(
	clubId int not null primary key auto_increment,
    clubName varchar(32) not null
);

create table members
(
	userId int not null,
    clubId int not null,
    primary key (userId, clubId),
    foreign key (clubId) references clubs(clubId),
    foreign key (userId) references users(userId)
);