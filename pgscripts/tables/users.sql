drop sequence if exists public.users_id_seq;
create SEQUENCE public.users_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999;

drop table if exists public.users
create table users (
id serial primary key,
email varchar(200) not null,
username varchar(50),
password varchar(50) not null,
role varchar(20) not null,
);
