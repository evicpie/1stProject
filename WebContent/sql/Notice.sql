DROP TABLE NOTICE;
DROP SEQUENCE NOTICE_SEQ;

CREATE SEQUENCE NOTICE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE NOTICE(
    nNum NUMBER(6) PRIMARY KEY,
    nTitle VARCHAR2(100) NOT NULL,
    nContent VARCHAR2(300),
    nDate DATE DEFAULT SYSDATE,
    nHIT NUMBER(6) DEFAULT 0,
    aId VARCHAR2(30) REFERENCES ADMINID(aId));
    
-- ���̵����� (����)
INSERT INTO NOTICE (nNum, nTitle, nContent, nDate, nHit, aId)
    VALUES (NOTICE_SEQ.NEXTVAL, '�Ϻη� �� ��� ���� ���� WWWWWWWWQWWQQWWWWW', 'content', SYSDATE, 0, 'ADMIN');
SELECT * FROM NOTICE;
commit;
--bHIT �ϳ� �ø���
UPDATE NOTICE SET nHIT = nHIT +1 WHERE nNum=2;

--bNum���� �� DTO ����
SELECT * FROM NOTICE WHERE nNum=2;

--�� �����ϱ� (NNUM, NTITLE, NCONTENT, NDATE)
UPDATE NOTICE SET NTITLE = '����',
                           NCONTENT = '����',
                           NDATE = SYSDATE
                           WHERE nNUM = 2;

-- Ư���� ����(id���)
DELETE FROM NOTICE WHERE NNUM=3 AND AID='ADMIN';

--�� ���� ��������
SELECT * FROM NOTICE WHERE NNUM=1;

-- �۰��� 
SELECT COUNT(*) FROM NOTICE;

-- �۸�� startRow endRow
SELECT * FROM 
        (SELECT ROWNUM RN, A.* FROM 
            (SELECT * FROM NOTICE
                ORDER BY NNUM DESC) A) WHERE RN BETWEEN 1 AND 4;