use team_spam_db;


-- users
insert into users values (NULL, 'Mike', 'Zyskind', true, 'Founder', 'hendrerit dolor magna eget est');
insert into users values (NULL, 'John', 'Doe', true, 'Founder', 'gravida neque convallis a cras');
insert into users values (NULL, 'Jim', 'Smith', true, 'Founder', 'quisque id diam vel quam');
insert into users values (NULL, 'Frank', 'Smith', true, 'Founder', 'sit amet consectetur adipiscing elit');
insert into users values (null, 'Oscar', 'Wilde', true, 'Co-Founder', 'metus dictum at tempor commodo');
insert into users values (null, 'Douglas', 'Adams', false, 'Member', 'posuere urna nec tincidunt praesent');
insert into users values (null, 'Bram', 'Stoker', false, 'Co-Founder', 'euismod quis viverra nibh cras');
insert into users values (null, 'Isaac', 'Asimov', false, 'Co-Founder', 'ipsum faucibus vitae aliquet nec');
insert into users values (null, 'Jules', 'Verne', true, 'Participant', 'vulputate eu scelerisque felis imperdiet');
insert into users values (null, 'Mary', 'Shelley', false, 'Member', null);
insert into users values (null, 'William', 'Shatner', false, 'Co-Founder', 'enim nec dui nunc mattis');
insert into users values (null, 'Homer', 'Randall', true, 'Co-Founder', null);
insert into users values (null, 'Russell', 'Hyde', true, 'Participant', null);
insert into users values (null, 'Lawrence', 'Bray', true, 'Member', null);
insert into users values (null, 'Troy', 'Willis', false, 'Founder', 'vivamus at augue eget arcu');
insert into users values (null, 'Gilbert', 'Patterson', false, 'Member', 'hendrerit gravida rutrum quisque non');
insert into users values (null, 'Bonnie', 'Decker', true, 'Co-Founder', null);
insert into users values (null, 'Diana', 'Bright', false, 'Founder', 'tincidunt arcu non sodales neque');
insert into users values (null, 'Gabriella', 'Shepard', true, 'Founder', null);
insert into users values (null, 'Emily', 'Armstrong', false, 'Co-Founder', null);
insert into users values (null, 'Caroline', 'Hodge', false, 'Founder', 'et sollicitudin ac orci phasellus');
insert into users values (null, 'Lori', 'Mason', true, 'Member', 'in aliquam sem fringilla ut');
insert into users values (null, 'Bridget', 'Perry', false, 'Co-Founder', 'ornare aenean euismod elementum nisi');
insert into users values (null, 'Chelsey', 'Shields', false, 'Participant', null);
insert into users values (null, 'Ellen', 'Cohen', true, 'Participant', null);
insert into users values (null, 'Dorothy', 'McKay', true, 'Member', 'tellus orci ac auctor augue');
insert into users values (null, 'Lisa', 'Rowland', true, 'Co-Founder', 'amet nisl purus in mollis');
insert into users values (null, 'Donnie', 'Green', true, 'Co-Founder', 'quis varius quam quisque id');
insert into users values (null, 'Leo', 'Sutton', false, 'Participant', null);
insert into users values (null, 'Tony', 'Donovan', true, 'Participant', 'vel fringilla est ullamcorper eget');
insert into users values (null, 'Diego', 'Burris', false, 'Co-Founder', null);


-- events
insert into events values (NULL, 1, 'Daley Building', '09:05:2 am', '2/11/2020', 'Chess Tournament', 'tempus iaculis urna id volutpat', 'Pizza', 'Chess Club');
insert into events values (NULL, 1, 'Lewis Center', '08:45:28 pm', '3/05/2020', 'Movie Night', 'sit amet nisl purus in', 'Donuts', 'DeBlock');
insert into events values (NULL, 2, 'Munroe Hall', '08:37:31 am', '2/20/2021', 'Swing Dance Lessons', 'sit amet mattis vulputate enim', 'Pizza', 'Dance Club');
insert into events values (NULL, 3, 'Levan Center', '05:47:12 pm', '1/31/2019', 'Meditation 101', 'elit ullamcorper dignissim cras', 'Ice cream', "The Writer's Block");
insert into events values (NULL, 4, 'Byrne Hall', '04:54:10 pm', '11/24/2022', 'Canvas and Cocoa', 'eget arcu dictum varius duis', 'Cookies', 'DePals');
insert into events values (null, 1, 'Richardson Library', '09:4:26 am', '1/2/2010', 'Yoga Class', 'habitasse platea dictumst vestibulum rhoncus', 'Pizza', 'Yoga Club');
insert into events values (null, 5, 'Sanctuary Hall', '3:18:57 pm', '5/9/2011', 'Career Fair', 'amet commodo nulla facilisi', 'Donuts', 'Career Center');
insert into events values (null, 8, 'University Hall', '07:06:45 pm', '7/5/2011', 'Movie Night', 'et malesuada fames ac', 'Cookies', 'DeBlock');
insert into events values (null, 4, 'Sheffield Square', '02:58:07 pm', '8/8/2011', 'Study Session', 'sed elementum tempus egestas', 'Pizza', 'Student Services');
insert into events values (null, 6, 'Centennial Hall', '03:40:45 pm', '10/27/2011', 'Chess Tournament', 'accumsan tortor posuere ac', 'Ice cream', 'Chess Club');
insert into events values (null, 22, 'Corcoran Hall', '10:09:56 am', '10/6/2012', 'Career Services Workshop', 'lacus laoreet non curabitur', 'Cookies', 'Career Center');
insert into events values (null, 14, 'Wish Field', '12:42:41 pm', '12/8/2012', 'Career Fair', 'justo eget magna fermentum', 'Pizza', 'Career Center');
insert into events values (null, 27, 'Levan Center', '04:12:31 pm', '7/28/2013', 'Career Fair', 'phasellus vestibulum lorem sed', 'Donuts', 'Career Center');
insert into events values (null, 30, 'McCabe Hall', '08:39:43 am', '8/8/2013', 'Chess Tournament', 'eros donec ac odio', 'Pizza', 'Chess Club');
insert into events values (null, 17, 'Student Center', '08:57:45 am', '3/13/2015', 'Swing Dance Lessons', 'sed odio morbi quis', 'Cookies', 'Dance Club');
insert into events values (null, 19, 'Byrne Hall', '10:05:40 am', '10/31/2015', 'Yoga Class', 'accumsan lacus vel facilisis', 'Donuts', 'Yoga Club');
insert into events values (null, 22, 'Munroe Hall', '01:03:58 pm', '5/9/2016', 'Conquer Test Anxiety', 'id ornare arcu odio', 'Ice cream', "The Writer's Block");
insert into events values (null, 24, 'Sheffield Square', '08:38:54 am', '4/15/2017', 'Career Fair', 'dolor morbi non arcu', 'Cookies', 'Career Center');
insert into events values (null, 5, 'Student Center', '05:44:03 pm', '4/18/2017', 'Canvas and Cocoa', 'at ultrices mi tempus', 'Donuts', 'DePals');
insert into events values (null, 7, 'Wish Field', '11:07:22 am', '6/1/2017', 'Holiday Special', 'risus sed vulputate odio', 'Pizza', 'Student Services');
insert into events values (null, 2, 'Munroe Hall', '01:02:52 pm', '3/30/2018', 'Chess Tournament', 'magna fringilla urna porttitor', 'Ice cream', 'Chess Club');
insert into events values (null, 19, 'Sheffield Square', '11:28:16 am', '12/30/2018', 'Canvas and Cocoa', 'ac turpis egestas maecenas', 'Donuts', 'DePals');
insert into events values (null, 18, 'Richardson Library', '06:38:55 pm', '10/7/2020', 'Meditation 101', 'consectetur a erat nam', 'Ice cream', "The Writer's Block");
insert into events values (null, 18, 'Wish Field', '03:44:33 pm', '3/16/2021', 'Meditation 101', 'purus sit amet luctus', 'Pizza', "The Writer's Block");
insert into events values (null, 27, 'Student Center', '08:38:56 am', '12/21/2021', 'Conquer Test Anxiety', 'nunc faucibus a pellentesque', 'Cookies', "The Writer's Block");
insert into events values (null, 14, 'Munroe Hall', '06:09:28 pm', '12/4/2011', 'Chess Tournament', 'vel turpis nunc eget', 'Donuts', 'Chess Club');
insert into events values (null, 17, 'Byrne Hall', '03:10:39 pm', '10/30/2012', 'Study Session', 'integer enim neque volutpat', 'Ice cream', 'Student Services');
insert into events values (null, 30, 'Richardson Library', '01:59:01 pm', '4/18/2013', 'Meditation 101', 'senectus et netus et', 'Cookies', "The Writer's Block");
insert into events values (null, 1, 'Centennial Hall', '01:30:38 pm', '9/21/2013', 'Holiday Special', 'tristique senectus et netus', 'Pizza', 'Student Services');
insert into events values (null, 5, 'McCabe Hall', '10:15:06 am', '1/2/2014', 'Chess Tournament', 'orci dapibus ultrices in', 'Donuts', 'Chess Club');
insert into events values (null, 9, 'McCabe Hall', '01:18:08 pm', '1/8/2014', 'Canvas and Cocoa', 'blandit volutpat maecenas volutpat', 'Donuts', 'DePals');


-- attending
insert into attending values (1, 2);
insert into attending values (2, 6);
insert into attending values (3, 18);
insert into attending values (6, 23);
insert into attending values (7, 4);
insert into attending values (23, 12);
insert into attending values (3, 12);
insert into attending values (16, 2);
insert into attending values (3, 4);
insert into attending values (2, 5);
insert into attending values (7, 18);


-- clubs
insert into clubs values (null, 'Robotics Club');
insert into clubs values (null, 'Student Council');
insert into clubs values (null, 'Book Club');
insert into clubs values (null, 'Game Club');
insert into clubs values (null, 'Dance Club');
insert into clubs values (null, 'Basketball');
insert into clubs values (null, 'Tennis');

-- members
