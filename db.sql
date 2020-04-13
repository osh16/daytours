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
    age integer,
    tour_id integer,
    foreign key(tour_id) references tours(id)
);


/*insert into ratings values(null,"vofflur a borgarnesi", date('2001-04-04'), 5, "aedislega gaman hehe");
insert into ratings values(null,"vaendiskaup i keflavik", date('2019-12-24'), 4, "mjog flottar konur bara gaman sko");
insert into ratings values(null,"sundferd a selfossi", date('2020-01-01'), 5, "gott ad fara i sund");
insert into ratings values(null,"rutuferd a hlemm square", date('2019-01-07'), 3, "guide taladi ekki islensku");
insert into ratings values(null,"bjor a sjallanum i AK", date('1997-10-07'), 2, "ekki gaman");
>>>>>>> steini_branch
*/
