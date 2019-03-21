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
    
-- 더미데이터 (원글)
INSERT INTO BOARD (bNum, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, mId, bIp)
    VALUES (BOARD_SEQ.NEXTVAL, 'ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ', 'content', null, 0,
        BOARD_SEQ.CURRVAL, 0, 0, 'aaa', '192.168.10.151');
SELECT * FROM BOARD;

-- 더미데이터(위의 1번글에 대한 첫번째 답변글)
INSERT INTO BOARD (bNum, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, mId, bIp)
    VALUES (BOARD_SEQ.NEXTVAL, 'reply', 'content', null, 0,
        1, 1, 1, 'bbb', '192.168.10.151');
SELECT * FROM BOARD ORDER BY bGROUP DESC, bSTEP;
SELECT COUNT(*) cnt FROM BOARD;

-- 더미데이터 위의 1번글에 대한 두번째 답변글
-- 답변글 추가 전 STPE a 수행
UPDATE BOARD SET bSTEP = bSTEP+1
    WHERE bGROUP = 1 AND bSTEP>0;
INSERT INTO BOARD (bNum, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent, mId, bIp)
    VALUES (BOARD_SEQ.NEXTVAL, 'reply', 'content', null, 0,
        1, 1, 1, 'bbb', '192.168.10.151');
COMMIT;

--bHIT 하나 올리기
UPDATE BOARD SET bHIT = bHIT +1 WHERE bNum=1;
SELECT B.*, MNAME FROM BOARD B, MEMBER M WHERE B.MID=M.MID AND bNum=1;
--bNum으로 글 DTO 보기
SELECT * FROM BOARD WHERE bNum=1;

--글 수정하기 (BNUM, BTITLE, BCONTENT, BIP, BDATE)
UPDATE BOARD SET BTITLE = '제목',
                           BCONTENT = '본문',
                           BIP = '192.168.151.0',
                           BDATE = SYSDATE
                           WHERE BNUM = 1;

-- 특정글 삭제(id고려)
DELETE FROM BOARD WHERE BNUM=1 AND MID='11';

-- 글목록 startRow endRow
SELECT * FROM 
        (SELECT ROWNUM RN, A.* FROM 
            (SELECT B.*, MNAME FROM BOARD B, MEMBER M WHERE B.MID=M.MID
                ORDER BY BGROUP DESC, BSTEP) A) WHERE RN BETWEEN 1 AND 5;