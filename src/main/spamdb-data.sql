use team_spam_db;


-- organizers
insert into organizers values (NULL, 'Mike', 'Zyskind', 'Organizer', 'Role1', 'hendrerit dolor magna eget est');
insert into organizers values (NULL, 'John', 'Doe', 'Organizer', 'Role1', 'gravida neque convallis a cras');
insert into organizers values (NULL, 'Jim', 'Smith', 'Organizer', 'Role1', 'quisque id diam vel quam');
insert into organizers values (NULL, 'Frank', 'Smith', 'Organizer', 'Role1', 'sit amet consectetur adipiscing elit');
insert into organizers values (null, 'Oscar', 'Wilde', 'Organizer', 'Role2', 'metus dictum at tempor commodo');
insert into organizers values (null, 'Douglas', 'Adams', 'Member', 'Role3', 'posuere urna nec tincidunt praesent');
insert into organizers values (null, 'Bram', 'Stoker', 'Member', 'Role2', 'euismod quis viverra nibh cras');
insert into organizers values (null, 'Isaac', 'Asimov', 'Member', 'Role2', 'ipsum faucibus vitae aliquet nec');
insert into organizers values (null, 'Jules', 'Verne', 'Organizer', 'Role4', 'vulputate eu scelerisque felis imperdiet');
insert into organizers values (null, 'Mary', 'Shelley', 'Member', 'Role3', null);
insert into organizers values (null, 'William', 'Shatner', 'Member', 'Role2', 'enim nec dui nunc mattis');
insert into organizers values (null, 'Homer', 'Randall', 'Organizer', 'Role2', null);
insert into organizers values (null, 'Russell', 'Hyde', 'Organizer', 'Role4', null);
insert into organizers values (null, 'Lawrence', 'Bray', 'Organizer', 'Role3', null);
insert into organizers values (null, 'Troy', 'Willis', 'Member', 'Role1', 'vivamus at augue eget arcu');
insert into organizers values (null, 'Gilbert', 'Patterson', 'Member', 'Role3', 'hendrerit gravida rutrum quisque non');
insert into organizers values (null, 'Bonnie', 'Decker', 'Organizer', 'Role2', null);
insert into organizers values (null, 'Diana', 'Bright', 'Member', 'Role1', 'tincidunt arcu non sodales neque');
insert into organizers values (null, 'Gabriella', 'Shepard', 'Organizer', 'Role1', null);
insert into organizers values (null, 'Emily', 'Armstrong', 'Member', 'Role2', null);
insert into organizers values (null, 'Caroline', 'Hodge', 'Member', 'Role1', 'et sollicitudin ac orci phasellus');
insert into organizers values (null, 'Lori', 'Mason', 'Organizer', 'Role3', 'in aliquam sem fringilla ut');
insert into organizers values (null, 'Bridget', 'Perry', 'Member', 'Role2', 'ornare aenean euismod elementum nisi');
insert into organizers values (null, 'Chelsey', 'Shields', 'Member', 'Role4', null);
insert into organizers values (null, 'Ellen', 'Cohen', 'Organizer', 'Role4', null);
insert into organizers values (null, 'Dorothy', 'McKay', 'Organizer', 'Role3', 'tellus orci ac auctor augue');
insert into organizers values (null, 'Lisa', 'Rowland', 'Organizer', 'Role2', 'amet nisl purus in mollis');
insert into organizers values (null, 'Donnie', 'Green', 'Organizer', 'Role2', 'quis varius quam quisque id');
insert into organizers values (null, 'Leo', 'Sutton', 'Member', 'Role4', null);
insert into organizers values (null, 'Tony', 'Donovan', 'Organizer', 'Role4', 'vel fringilla est ullamcorper eget');
insert into organizers values (null, 'Diego', 'Burris', 'Member', 'Role2', null);


-- events
insert into events values (NULL, 1, 'Daley Building', '9:5:2 am', '2/11/2020', 'Title1', 'tempus iaculis urna id volutpat', 'Pizza', 'organization');
insert into events values (NULL, 1, 'Lewis Center', '8:45:28 pm', '3/05/2020', 'Title2', 'sit amet nisl purus in', 'Donuts', 'organization');
insert into events values (NULL, 2, 'Munroe Hall', '8:37:31 am', '2/20/2021', 'Title3', 'sit amet mattis vulputate enim', 'Pizza', 'organization');
insert into events values (NULL, 3, 'Levan Center', '5:47:12 pm', '1/31/2019', 'Title4', null, 'Ice cream', 'organization');
insert into events values (NULL, 4, 'Byrne Hall', '4:54:10 pm', '11/24/2022', 'Title5', 'eget arcu dictum varius duis', 'Cookies', 'organization');
insert into events values (null, 1, 'Richardson Library', '9:4:26 am', '1/2/2010', 'Title6', 'habitasse platea dictumst vestibulum rhoncus', 'Pizza', 'organization');
insert into events values (null, 5, 'Sanctuary Hall', '3:18:57 pm', '5/9/2011', 'Title7', 'amet commodo nulla facilisi', 'Donuts', 'organization');
insert into events values (null, 8, 'University Hall', '7:6:45 pm', '7/5/2011', 'Title8', 'et malesuada fames ac', 'Cookies', 'organization');
insert into events values (null, 4, 'Sheffield Square', '2:58:7 pm', '8/8/2011', 'Title3', 'sed elementum tempus egestas', 'Pizza', 'organization');
insert into events values (null, 6, 'Centennial Hall', '3:40:45 pm', '10/27/2011', 'Title1', null, 'Ice cream', 'organization');
insert into events values (null, 22, 'Corcoran Hall', '10:9:56 am', '10/6/2012', 'Title8', 'lacus laoreet non curabitur', 'Cookies', 'organization');
insert into events values (null, 14, 'Wish Field', '0:42:41 pm', '12/8/2012', 'Title7', null, 'Pizza', 'organization');
insert into events values (null, 27, 'Levan Center', '4:12:31 pm', '7/28/2013', 'Title7', 'phasellus vestibulum lorem sed', 'Donuts', 'organization');
insert into events values (null, 30, 'McCabe Hall', '8:39:43 am', '8/8/2013', 'Title1', 'eros donec ac odio', 'Pizza', 'organization');
insert into events values (null, 17, 'Student Center', '8:57:45 am', '3/13/2015', 'Title3', 'sed odio morbi quis', 'Cookies', 'organization');
insert into events values (null, 19, 'Byrne Hall', '10:5:40 am', '10/31/2015', 'Title3', 'accumsan lacus vel facilisis', 'Donuts', 'organization');
insert into events values (null, 22, 'Munroe Hall', '1:3:58 pm', '5/9/2016', 'Title2', 'id ornare arcu odio', 'Ice cream', 'organization');
insert into events values (null, 24, 'Sheffield Square', '8:38:54 am', '4/15/2017', 'Title7', 'dolor morbi non arcu', 'Cookies', 'organization');
insert into events values (null, 5, 'Student Center', '5:44:3 pm', '4/18/2017', 'Title5', 'at ultrices mi tempus', 'Donuts', 'organization');
insert into events values (null, 7, 'Wish Field', '11:7:22 am', '6/1/2017', 'Title2', null, 'Pizza', 'organization');
insert into events values (null, 2, 'Munroe Hall', '1:2:52 pm', '3/30/2018', 'Title1', 'magna fringilla urna porttitor', 'Ice cream', 'organization');
insert into events values (null, 19, 'Sheffield Square', '11:28:16 am', '12/30/2018', 'Title5', 'ac turpis egestas maecenas', 'Donuts', 'organization');
insert into events values (null, 18, 'Richardson Library', '6:38:55 pm', '10/7/2020', 'Title4', 'consectetur a erat nam', 'Ice cream', 'organization');
insert into events values (null, 18, 'Wish Field', '3:44:33 pm', '3/16/2021', 'Title4', 'purus sit amet luctus', 'Pizza', 'organization');
insert into events values (null, 27, 'Student Center', '8:38:56 am', '12/21/2021', 'Title2', null, 'Cookies', 'organization');
insert into events values (null, 14, 'Munroe Hall', '6:9:28 pm', '12/4/2011', 'Title1', 'vel turpis nunc eget', 'Donuts', 'organization');
insert into events values (null, 17, 'Byrne Hall', '3:10:39 pm', '10/30/2012', 'Title3', 'integer enim neque volutpat', 'Ice cream', 'organization');
insert into events values (null, 30, 'Richardson Library', '1:59:1 pm', '4/18/2013', 'Title4', 'senectus et netus et', 'Cookies', 'organization');
insert into events values (null, 1, 'Centennial Hall', '1:30:38 pm', '9/21/2013', 'Title2', 'tristique senectus et netus', 'Pizza', 'organization');
insert into events values (null, 5, 'McCabe Hall', '10:15:6 am', '1/2/2014', 'Title1', 'orci dapibus ultrices in', 'Donuts', 'organization');
insert into events values (null, 9, 'McCabe Hall', '1:18:8 pm', '1/8/2014', 'Title5', 'blandit volutpat maecenas volutpat', 'Donuts', 'organization');