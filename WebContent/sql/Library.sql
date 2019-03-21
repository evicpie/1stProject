DROP TABLE Library;
DROP SEQUENCE LIBRARY_SEQ;
CREATE SEQUENCE LIBRARY_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE Library (
    lCallnum VARCHAR2(100) PRIMARY KEY,
    lTitle VARCHAR2(100) NOT NULL,
    lWriter VARCHAR2(100),
    lYear NUMBER(4),
    lPublicsher VARCHAR(100),
    lLocation VARCHAR(100) NOT NULL,
    lImage VARCHAR(100) DEFAULT 'nothing.jpg',
    lKeyword VARCHAR(100),
    lBorrow VARCHAR(100) DEFAULT 'Y',
    lMany NUMBER(6) DEFAULT 0,
    bNumber NUMBER(3) REFERENCES BCode(bNumber),
    lDel VARCHAR(3) DEFAULT 'N');
INSERT INTO LIBRARY VALUES (LIBRARY_SEQ.NEXTVAL, '���� �������忡 ����', 
    '�ڰ��', '2002', '���ε�', '��������1', 'nobinson_ever.jpg', '����!', 'N', 0, 817,'N');
    
SELECT * FROM LIBRARY;
-- �������   
SELECT * FROM 
        (SELECT ROWNUM RN, A.* FROM 
            (SELECT * FROM LIBRARY ORDER BY LMANY, LTITLE DESC) A) 
            WHERE RN BETWEEN 1 AND 2;
SELECT * FROM 
        (SELECT ROWNUM RN, A.* FROM 
            (SELECT L.*, lbDate, lbREdate FROM LIBRARY L, LIBRARYBORROW B WHERE L.lCallnum=B.lCallnum
                ORDER BY LMANY, LTITLE DESC) A) WHERE RN BETWEEN 1 AND 2;
-- ���� ���� å 4��
SELECT * FROM 
        (SELECT ROWNUM RN, A.* FROM 
            (SELECT * FROM LIBRARY ORDER BY LCALLNUM+0 DESC) A) WHERE RN BETWEEN 1 AND 4;
            
            commit;
-- å�˻�
SELECT * FROM LIBRARY WHERE LTITLE LIKE '%'||?||'%' OR lKeyword LIKE '%'||?||'%';

-- �������ADMIN
INSERT INTO LIBRARY (lCallnum, lTitle, lWriter, lYear, lPublicsher, lLocation, lImage, lKeyword, bNumber)
    VALUES (LIBRARY_SEQ.NEXTVAL, '����, �Ƕ�̵��� ����� Ǯ���', '������', '2000', '���ε�', '��������1', 'nothing.jpg', '����', 980);
INSERT INTO LIBRARY (lCallnum, lTitle, lWriter, lYear, lPublicsher, lLocation, lImage, lKeyword, bNumber)
    VALUES (LIBRARY_SEQ.NEXTVAL, '����, Ƽ����� ���� ã�ƶ�', '�����', '2009', '���ε�', '�Ϲ��ڷ��1', 'nobinson_tirano.jpg', '����,Ƽ���,�Ƶ�,��Ǳ�', 400);
INSERT INTO LIBRARY (lCallnum, lTitle, lWriter, lYear, lPublicsher, lLocation, lImage, lKeyword, bNumber)
    VALUES (LIBRARY_SEQ.NEXTVAL, '������ ���� ��庥ó', '�ڰ��', '2008', '���ε�', '�Ϲ��ڷ��1', 'nobinson_polar.jpg', '����,����,�Ҽ�,�ڿ�����', 400);
-- å �󼼺���
SELECT * FROM LIBRARY WHERE LCALLNUM=4;
-- ��������ADMIN
UPDATE LIBRARY SET LTITLE = '����, �Ƕ�̵��� ����� Ǯ���',
                             LWRITER = '������',
                             LYEAR = 2000,
                             LPUBLICSHER = '���ε�',
                             LLOCATION = '��������1',
                             LIMAGE = 'nothing.jpg',
                             LKEYWORD = '����, ����Ʈ',
                             BNUMBER = 980
                WHERE LCALLNUM = 1;
    commit;                         
-- ��������ADMIN
DELETE FROM LIBRARY WHERE LCALLNUM=3;
UPDATE LIBRARY SET lDel = 'Y' WHERE lCallnum=10;

-- å ���� ó��(����Ƚ�� 1 ����)
UPDATE LIBRARY SET LMANY = LMANY+1, LBORROW = 'N' WHERE LCALLNUM=1;
INSERT INTO LIBRARYBORROW (LBNUM, LCALLNUM, MID)
    VALUES (LIBRARYBORROW_SEQ.NEXTVAL, '1', 'aaa');
-- å �ݳ� ó��
UPDATE LIBRARY SET LBORROW = 'Y' WHERE LCALLNUM=5;
SELECT * FROM LIBRARYBORROW WHERE lCallnum=5 AND LBREALRE IS NULL;
UPDATE LIBRARYBORROW SET lbREALre = SYSDATE WHERE lbnum=6;


UPDATE LIBRARY SET LTITLE = ?,
				                     LWRITER = ?, 
				                      LYEAR = ?,
                                     LPUBLICSHER = ?,
				                      LLOCATION = ?, 
				                       LIMAGE = ?,
				                       LKEYWORD = ?,
				                       BNUMBER = ?
				               WHERE lCallnum = ?