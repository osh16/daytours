create table tours (
    
);

create table users (

);

create table ratings (
    name varchar(128),
    rating_date date,
    stars integer,
    feedback varchar(512)
);


/* dummy gogn fyrir junit profun */
insert into ratings values("vofflur a borgarnesi", date('2001-04-04'), 5, "aedislega gaman hehe");
insert into ratings values("vaendiskaup i keflavik", date('2019-12-24'), 4, "mjog flottar
konur bara gaman sko");
insert into ratings values("sundferd a selfossi", date('2020-01-01'), 5, "gott ad fara i sund");
insert into ratings values("rutuferd a hlemm square", date('2019-01-07'), 3, "guide taladi ekki
islensku");
insert into ratings values("bjor a sjallanum i AK", date('1997-10-07'), 2, "ekki gaman");
