DROP TABLE ADMINID;

CREATE TABLE ADMINID (
    aId VARCHAR(30) PRIMARY KEY,
    aPw VARCHAR(30) NOT NULL,
    aName VARCHAR(30) NOT NULL);
    
-- 관리자 아이디
    INSERT INTO ADMINID VALUES ('ADMIN', '111', '관리자');
    
-- 가입한 회원 명수 가져오기
    SELECT COUNT(*) CNT FROM MEMBER;
    
-- startRow부터 endRow까지 회원가져오기
    SELECT * FROM MEMBER ORDER BY MID;
    SELECT ROWNUM RN, A.* FROM (SELECT * FROM MEMBER ORDER BY MID) A;
    SELECT * FROM 
        (SELECT ROWNUM RN, A.* FROM 
            (SELECT * FROM MEMBER ORDER BY MID) A) WHERE RN BETWEEN 1 AND 2;
            
-- 회원 탈퇴
    DELETE FROM MEMBER WHERE MID='bbb';