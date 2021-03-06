DROP TABLE BOARD;
DROP SEQUENCE BOARD_SEQ;

SELECT B.*, MNAME FROM BOARD B, MEMBER M WHERE B.MID=M.MID AND bNum=2;

CREATE SEQUENCE BOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE BOARD(
    bNum NUMBER(6) PRIMARY KEY,
    bTitle VARCHAR2(100) NOT NULL,
    bContent VARCHAR2(300),
    bDate DATE DEFAULT SYSDATE,
    bHIT NUMBER(6) DEFAULT 0,
    bGroup NUMBER(6) NOT NULL,
    bStep NUMBER(3) NOT NULL,
    bIndent NUMBER(3) NOT NULL,
    mId VARCHAR2(30) REFERENCES MEMBER(mId),
    bIp VARCHAR2(20) NOT NULL);
    
    SELECT * FROM 
				(SELECT ROWNUM RN, A.* FROM
				(SELECT * FROM BOARD ORDER BY BGROUP DESC, BSTEP) A)
				WHERE RN BETWEEN 1 AND 10;
    
-- 希耕汽戚斗 (据越)
INSERT INTO BOARD (bNum, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, mId, bIp)
    VALUES (BOARD_SEQ.NEXTVAL, 'けけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけけ', 'content', null, 0,
        BOARD_SEQ.CURRVAL, 0, 0, 'aaa', '192.168.10.151');
SELECT * FROM BOARD;

-- 希耕汽戚斗(是税 1腰越拭 企廃 湛腰属 岩痕越)
INSERT INTO BOARD (bNum, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, mId, bIp)
    VALUES (BOARD_SEQ.NEXTVAL, 'reply', 'content', null, 0,
        1, 1, 1, 'bbb', '192.168.10.151');
SELECT * FROM BOARD ORDER BY bGROUP DESC, bSTEP;
SELECT COUNT(*) cnt FROM BOARD;

-- 希耕汽戚斗 是税 1腰越拭 企廃 砧腰属 岩痕越
-- 岩痕越 蓄亜 穿 STPE a 呪楳
UPDATE BOARD SET bSTEP = bSTEP+1
    WHERE bGROUP = 1 AND bSTEP>0;
INSERT INTO BOARD (bNum, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, mId, bIp)
    VALUES (BOARD_SEQ.NEXTVAL, 'reply', 'content', null, 0,
        1, 1, 1, 'bbb', '192.168.10.151');
COMMIT;

--bHIT 馬蟹 臣軒奄
UPDATE BOARD SET bHIT = bHIT +1 WHERE bNum=1;
SELECT B.*, MNAME FROM BOARD B, MEMBER M WHERE B.MID=M.MID AND bNum=1;
--bNum生稽 越 DTO 左奄
SELECT * FROM BOARD WHERE bNum=1;

--越 呪舛馬奄 (BNUM, BTITLE, BCONTENT, BIP, BDATE)
UPDATE BOARD SET BTITLE = '薦鯉',
                           BCONTENT = '沙庚',
                           BIP = '192.168.151.0',
                           BDATE = SYSDATE
                           WHERE BNUM = 1;

-- 働舛越 肢薦(id壱形)
DELETE FROM BOARD WHERE BNUM=1 AND MID='11';

-- 越鯉系 startRow endRow
SELECT * FROM 
        (SELECT ROWNUM RN, A.* FROM 
            (SELECT B.*, MNAME FROM BOARD B, MEMBER M WHERE B.MID=M.MID
                ORDER BY BGROUP DESC, BSTEP) A) WHERE RN BETWEEN 1 AND 5;