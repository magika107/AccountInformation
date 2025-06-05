-- Create new person
insert into PERSON
    (ID, NAME, FAMILY, USERNAME, PASSWORD, BIRTH_DATE, PHONENUMBER)
values (12, 'ali', 'alipour', 'ali', 'ali123', null, null);

SELECT table_name FROM user_tables WHERE table_name = 'PERSONS';
SELECT * FROM all_tables WHERE table_name = 'PERSONS';


-- Read person(s)
-- select ID,
--        NAME,
--        FAMILY,
--        USERNAME,
--        PASSWORD,
--        PHONENUMBER
-- from PERSON
-- where name = 'ali';

-- Update person(s)
-- update PERSON
-- set name     = 'alireza',
--     password = 'aliali112233'
-- where id = 2;

-- Delete person(s)
-- delete
-- from PERSON
-- where id = 2;

-- Commit changes
commit;
