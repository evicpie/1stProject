DROP TABLE ADMINID;

CREATE TABLE ADMINID (
    aId VARCHAR(30) PRIMARY KEY,
    aPw VARCHAR(30) NOT NULL,
    aName VARCHAR(30) NOT NULL);
    
-- ������ ���̵�
    INSERT INTO ADMINID VALUES ('ADMIN', '111', '������');
    
-- ������ ȸ�� ��� ��������
    SELECT COUNT(*) CNT FROM MEMBER;
    
-- startRow���� endRow���� ȸ����������
    SELECT * FROM MEMBER ORDER BY MID;
    SELECT ROWNUM RN, A.* FROM (SELECT * FROM MEMBER ORDER BY MID) A;
    SELECT * FROM 
        (SELECT ROWNUM RN, A.* FROM 
            (SELECT * FROM MEMBER ORDER BY MID) A) WHERE RN BETWEEN 1 AND 2;
            
-- ȸ�� Ż��
    DELETE FROM MEMBER WHERE MID='bbb';