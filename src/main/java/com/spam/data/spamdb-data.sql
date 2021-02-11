use team_spam_db;
select * from organizers;
select * from events;

-- organizers
insert into organizers values (NULL, 'Mike', 'Zyskind', 'Organizer', 'Role1', 'Summary');
insert into organizers values (NULL, 'John', 'Doe', 'Organizer', 'Role1', 'Summary');
insert into organizers values (NULL, 'Jim', 'Smith', 'Organizer', 'Role1', 'Summary');
insert into organizers values (NULL, 'Frank', 'Smith', 'Organizer', 'Role1', 'Summary');

-- events
insert into events values (NULL, 1, 'Daley Building', '8:40PM', '2/11/2020', 'Title1', 'description', 'Pizza', 'organization');
insert into events values (NULL, 1, 'Lewis Center', '9:00AM', '3/05/2020', 'Title2', 'description', 'Donuts', 'organization');
insert into events values (NULL, 2, 'Munroe Hall', '12:30PM', '2/20/2021', 'Title3', 'description', 'Pizza', 'organization');
insert into events values (NULL, 3, 'Levan Center', '4:00PM', '1/31/2019', 'Title4', 'description', 'Ice cream', 'organization');
insert into events values (NULL, 4, 'Byrne Hall', '10:00AM', '11/24/2022', 'Title5', 'description', 'Cookies', 'organization');