drop table movies;
drop table genres;
drop table associative;
drop table actors;
drop table directors;

CREATE TABLE movies(
  id_movie VARCHAR(10) NOT NULL,
  title VARCHAR(10),
  release_date DATE,
  duration integer(5),
  score integer(5)
  )
 ;
CREATE TABLE genres(
  id_gen VARCHAR(10) NOT NULL,
  name VARCHAR(10)
  )
 ;
 CREATE TABLE actors(
  id_movie VARCHAR(10) ,
  fname VARCHAR(30),
  lname VARCHAR(30)
  )
  ;
  CREATE TABLE associative(
  id_movie VARCHAR(10),
  id_gen VARCHAR(10)
  )
 ;
 CREATE TABLE directors(
  id_movie VARCHAR(10) ,
  name VARCHAR(30)
  )
 ;

INSERT INTO movies VALUES   ('111', 'Movie1', STR_TO_DATE("10 May 2017", "%d %M %Y"),130,142);
INSERT INTO movies VALUES   ('112', 'Movie2', STR_TO_DATE("15 March 2007", "%d %M %Y"),170,105);
INSERT INTO movies VALUES   ('113', 'Movie3', STR_TO_DATE("13 August 2019", "%d %M %Y"),140,132);
INSERT INTO movies VALUES   ('114', 'Movie4', STR_TO_DATE("16 June 2002", "%d %M %Y"),120,1233);
INSERT INTO movies VALUES   ('115', 'Movie5', STR_TO_DATE("30 December 2021", "%d %M %Y"),100,12);


INSERT INTO genres VALUES ('21', 'Action');
INSERT INTO genres VALUES ('22', 'Drama');

INSERT INTO associative VALUES ('111','21');
INSERT INTO associative VALUES ('112','22');
INSERT INTO associative VALUES ('113','22');
INSERT INTO associative VALUES ('114','21');
INSERT INTO associative VALUES ('115','22');

INSERT INTO actors VALUES ('111', 'Ion' , 'Mihai');
INSERT INTO actors VALUES ('111', 'Ioana' ,'Maria');

INSERT INTO directors VALUES ('111', 'Ionel Mihaila');
INSERT INTO directors VALUES ('114', 'Magda Belciug');


COMMIT;