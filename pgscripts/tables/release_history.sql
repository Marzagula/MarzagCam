create table RELEASE_HISTORY (
id serial primary key,
release_name varchar(50) unique not null,
release_file bytea,
timestamp TIMESTAMP
);

CREATE OR REPLACE FUNCTION UPDATE_TIMESTAMP()
  RETURNS trigger AS
$$
BEGIN
         UPDATE RELEASE_HISTORY SET TIMESTAMP = current_date;
 
    RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

create or replace trigger release_insert after insert on RELEASE_HISTORY
FOR EACH ROW
EXECUTE PROCEDURE UPDATE_TIMESTAMP();