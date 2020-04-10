create table tours (
    id integer primary key autoincrement not null,
    name varchar(128),
    tour_date date,
    price float,
    tour_type varchar(128),
    location varchar(128)
);

create table ratings (
    id integer primary key autoincrement not null,
    name varchar(128),
    rating_date date,
    stars integer,
    feedback varchar(512),
    tour_id integer, /*id a tour sem faer einkunn*/
    passenger_id integer, /*id a passanger sem gaf einkunn*/
    foreign key(tour_id) references tours(id),
    foreign key(passenger_id) references passenger(id)
);

create table passenger(
    id integer primary key autoincrement not null,
    name varchar(128),
    ssn varchar(10),
    age integer
);


insert into ratings values(null,"vofflur a borgarnesi", date('2001-04-04'), 5, "aedislega gaman hehe",1,3);
insert into ratings values(null,"vaendiskaup i keflavik", date('2019-12-24'), 4, "mjog flottar konur bara gaman sko",2,5);
insert into ratings values(null,"sundferd a selfossi", date('2020-01-01'), 5, "gott ad fara i sund",3,10);
insert into ratings values(null,"rutuferd a hlemm square", date('2019-01-07'), 3, "guide taladi ekki islensku",4,2);
insert into ratings values(null,"bjor a sjallanum i AK", date('1997-10-07'), 2, "ekki gaman",5,1);

insert into tours values(1,"vofflur a borgarnesi",date('2001-04-04'),10000.0,"menningarferð","borgarnes");
insert into tours values(2,"vaendiskaup i keflavik", date('2019-12-24'),60000.0,"ævintýraferð","keflavik");