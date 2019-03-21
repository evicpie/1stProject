DROP TABLE MEMBER;
CREATE TABLE MEMBER (
    mId VARCHAR2(100) PRIMARY KEY,
    mPw VARCHAR2(100) NOT NULL,
    mName VARCHAR2(100) NOT NULL,
    mTel VARCHAR2(100),
    mBirth DATE,
    mEmail VARCHAR2(30),
    mGender VARCHAR2(5),
    mAddress VARCHAR(100),
    mDel VARCHAR(3) DEFAULT 'N');
    
    SELECT * FROM MEMBER;
-- mId confirm
    SELECT * FROM MEMBER WHERE MID='aaa';
    
-- join
    INSERT INTO MEMBER (MID, MPW, MNAME, MTEL, MBIRTH, MEMAIL, MGENDER, MADDRESS)
        VALUES ('aaa', '111', 'hong', NULL, TO_DATE('19900101','YYYYMMDD'), NULL, 'M', 'SEOUL');
    INSERT INTO MEMBER (MID, MPW, MNAME, MTEL, MBIRTH, MEMAIL, MGENDER, MADDRESS)
        VALUES ('bbb', '111', 'park', NULL, '1990-01-01', NULL, 'F', 'SEOUL');
    commit;
-- login
    SELECT * FROM MEMBER WHERE MID='aaa'; -- id wrong /pw wrong
    SELECT * FROM MEMBER WHERE MID='aaa' AND MPW='111';
    
-- mId·Î dto °¡Á®¿À±â
    SELECT * FROM MEMBER WHERE MID='aaa';     
    
-- modify info
    UPDATE MEMBER SET MPW='111',
                                        MNAME = 'HONG GIL',
                                        MTEL = '010-0000-0000',
                                        MBIRTH = NULL,
                                        MEMAIL = 'H@TJ.COM',
                                        MGENDER = 'F',
                                        MADDRESS = 'INCHON'
                            WHERE MID='aaa';
-- È¸¿ø Å»Åð
    UPDATE MEMBER SET mDel = 'Y' WHERE MID='bbb';
    

COMMIT;