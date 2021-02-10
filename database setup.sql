drop database if exists team_spam_db;
create database if not exists team_spam_db;
use team_spam_db;

create table organizers
(
	organizerId int not null primary key auto_increment,
    firstName varchar(16) not null,
    lastName varchar(16) not null,
    membership varchar(32) not null,
    role varchar(16),
    summary varchar(64)
);

create table events
(
	eventId int not null primary key auto_increment,
    organizerId int not null,
    location varchar(32) not null,
    eventTime varchar(16) not null,
    eventDate varchar(16) not null,
    eventTitle varchar(32) not null,
    descripton varchar(64),
    foodType varchar(32),
    organization varchar(32),
    FOREIGN KEY (organizerId) references organizers(organizerId)
);