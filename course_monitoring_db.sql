create database course_monitoring_db;

use course_monitoring_db;

create table admin
(
id int PRIMARY KEY AUTO_INCREMENT,
username varchar(255) NOT NULL UNIQUE,
password varchar(255) NOT NULL
);

create table faculty
(
facultyId int PRIMARY KEY AUTO_INCREMENT,
facultyName varchar(255),
facultyAddress varchar(255),
mobile varchar(10),
email varchar(255),
username varchar(255) NOT NULL UNIQUE,
password varchar(255) NOT NULL
);

create table course
(
courseId int PRIMARY KEY AUTO_INCREMENT,
courseName varchar(255) UNIQUE,
fee int,
courseDescription text
);

create table batch
(
batchId int PRIMARY KEY AUTO_INCREMENT,
batchName varchar(255) UNIQUE NOT NULL,
courseId int NOT NULL,
facultyId int NOT NULL,
numberOfStudents int,
batchStartDate date,
duration varchar(255),
foreign key(courseId) references course(courseId) ON DELETE CASCADE,
foreign key(facultyId) references faculty(facultyId) ON DELETE CASCADE
);

create table coursPlan
(
planId int PRIMARY KEY AUTO_INCREMENT,
batchId int NOT NULL,
dayNumber int,
topic varchar(255),
status varchar(255),
foreign key(batchId) references batch(batchId) ON DELETE CASCADE
);
