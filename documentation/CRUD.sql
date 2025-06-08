-- حذف توالی در صورت وجود
BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE person_seq';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN -- sequence does not exist
            RAISE;
        END IF;
END;
/

CREATE SEQUENCE person_seq START WITH 1 INCREMENT BY 1;

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE PERSON CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN -- table does not exist
            RAISE;
        END IF;
END;
/

-- ایجاد جدول PERSON
CREATE TABLE PERSON (
                        ID number unique not null,
                        NAME NVARCHAR2(30),
                        FAMILY NVARCHAR2(30),
                        USERNAME NVARCHAR2(20) UNIQUE NOT NULL,
                        PASSWORD NVARCHAR2(20),
                        BIRTH_DATE DATE,
                        PHONENUMBER NVARCHAR2(15)
);
COMMIT;
