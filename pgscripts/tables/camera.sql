create table CAMERA (
id serial primary key,
owner_id varchar(50) not null,
camera_id varchar(50) not null,
application_path varchar(1000) not null,
application_version varchar(100) not null
);

