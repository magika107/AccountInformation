create table persons
(
    id          number primary key,
    name        nvarchar2(30),
    family      nvarchar2(30),
    username    nvarchar2(20) unique not null,
    password    nvarchar2(20),
    birth_date  date,
    phone_number nvarchar2(15)
);


create sequence person_seq start with 1 increment by 1;


insert into persons
    (ID, NAME, FAMILY, USERNAME, PASSWORD, BIRTH_DATE, phone_number)
values (2, 'ali', 'alipour', 'ali', 'ali123', null, null);



insert into persons
    (ID, NAME, FAMILY, USERNAME, PASSWORD, BIRTH_DATE, phone_number)
values (3, 'hamed', 'karimi', 'karimii', 'karim34', null, 0222222);


commit;

