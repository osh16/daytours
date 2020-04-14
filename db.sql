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
    title varchar(128),/*ekki endilega nafn a tour*/
    rating_date date,
    stars integer,
    feedback varchar(512),
    tour_id integer, /*id a tour sem faer einkunn*/
    passenger_id integer, /*id a passanger sem gaf einkunn*/
    foreign key(tour_id) references tours(id),
    foreign key(passenger_id) references passenger(id)
);

create table passenger (
    id integer primary key autoincrement not null,
    name varchar(128),
    ssn varchar(10),
    age integer,
    specialNeeds String,
    tour_id integer,
    foreign key(tour_id) references tours(id)
);

create table booking (
    id integer primary key autoincrement not null,
    trip varchar(128),
    payment varchar(128),
    customer_name varchar(128)
);

/* tours */
insert into tours values(null,"vofflur a borgarnesi", date('2010-10-10'), 99999, "aevintyraferd","borgarnes");
insert into tours values(null,"vaendiskaup i keflavik", date('2019-12-24'), 7499, "aevintyraferd","keflavik");
insert into tours values(null,"chicago town orbygljuferd i iceland", date("2020-04-03"), 32395,"matarferd","kopavogur");
insert into tours values(null,"fundur um malefni transfolks", date("2020-04-03"), 0,"politisk herferd","reykjavik");

/* passengers */
insert into passenger values(null,"steini skrufjarn", "1010952399", 5, "ofnæmi fyrir aumingjum",1);
insert into passenger values(null,"nonni newcastle", "1009742399", 45, null, 4);
insert into passenger values(null,"skari skiptilykill", "0710932399", 12, "hnetuofnæmi", 1);
insert into passenger values(null,"hinni hallamaelir", "1004932489", 34, "kuldaskræfa", 2);
insert into passenger values(null,"maggi manchester", "0101014300", 10, null, 1);
insert into passenger values(null,"astradur stefansson", "0704932499", 25, "ekkert transfólk", 3);

/* ratings */
insert into ratings values(null,"besta ferd lifs mins", date('NOW'), 5, "aedislega gaman hehe", 2, 1);
insert into ratings values(null,"ok mun fara aftur", date('NOW'), 4, "mjog flottar konur bara gaman sko", 2, 2);
insert into ratings values(null,"hehe", date('NOW'), 5, "virkilega erfitt og ataknlegt", 3, 4);
insert into ratings values(null,"konan ekki anaegd", date('NOW'), 1, "....", 4, 1);
