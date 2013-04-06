CREATE TABLE appointments
(
username varchar(255) NOT NULL,
name varchar(255) NOT NULL,
apptdate varchar(255) NOT NULL,
starttime varchar(255) NOT NULL,
endtime varchar(255) NOT NULL,
phone varchar(255) NOT NULL,
notes varchar(255),
primary key(username, apptdate, starttime, endtime),
FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE users
(
username varchar(255) NOT NULL,
primary key(username)
);