-- حذف sequence اگر وجود دارد
BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE person_seq';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -2289 THEN -- sequence does not exist
            RAISE;
        END IF;
END;
/

-- ساخت sequence جدید
CREATE SEQUENCE person_seq START WITH 1 INCREMENT BY 1;

-- نمایش مقدار فعلی
SELECT person_seq.nextval FROM dual;

-- ایجاد جدول اگر قبلاً ساخته نشده
BEGIN
    EXECUTE IMMEDIATE '
    CREATE TABLE PERSON (
                            id         NUMBER PRIMARY KEY,
                            name       NVARCHAR2(30),
                            family     NVARCHAR2(30),
                            username   NVARCHAR2(20) UNIQUE NOT NULL,
                            password   NVARCHAR2(20),
                            birth_date DATE,
                            phonenumber NVARCHAR2(15)
    )';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -955 THEN -- table already exists
            RAISE;
        END IF;
END;
/

COMMIT;
