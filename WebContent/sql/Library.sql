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
INSERT INTO LIBRARY VALUES (LIBRARY_SEQ.NEXTVAL, '노빈손 에버랜드에 가다', 
    '박경수', '2002', '뜨인돌', '보존서고1', 'nobinson_ever.jpg', '가다!', 'N', 0, 817,'N');
    
SELECT * FROM LIBRARY;
-- 도서목록   
SELECT * FROM 
        (SELECT ROWNUM RN, A.* FROM 
            (SELECT * FROM LIBRARY ORDER BY LMANY, LTITLE DESC) A) 
            WHERE RN BETWEEN 1 AND 2;
SELECT * FROM 
        (SELECT ROWNUM RN, A.* FROM 
            (SELECT L.*, lbDate, lbREdate FROM LIBRARY L, LIBRARYBORROW B WHERE L.lCallnum=B.lCallnum
                ORDER BY LMANY, LTITLE DESC) A) WHERE RN BETWEEN 1 AND 2;
-- 새로 들어온 책 4권
SELECT * FROM 
        (SELECT ROWNUM RN, A.* FROM 
            (SELECT * FROM LIBRARY ORDER BY LCALLNUM+0 DESC) A) WHERE RN BETWEEN 1 AND 4;
            
            commit;
-- 책검색
SELECT * FROM LIBRARY WHERE LTITLE LIKE '%'||?||'%' OR lKeyword LIKE '%'||?||'%';

-- 도서등록ADMIN
INSERT INTO LIBRARY (lCallnum, lTitle, lWriter, lYear, lPublicsher, lLocation, lImage, lKeyword, bNumber)
    VALUES (LIBRARY_SEQ.NEXTVAL, '노빈손, 피라미드의 비밀을 풀어라', '강영숙', '2000', '뜨인돌', '보존서고1', 'nothing.jpg', '노빈손', 980);
INSERT INTO LIBRARY (lCallnum, lTitle, lWriter, lYear, lPublicsher, lLocation, lImage, lKeyword, bNumber)
    VALUES (LIBRARY_SEQ.NEXTVAL, '노빈손, 티라노의 알을 찾아라', '강산들', '2009', '뜨인돌', '일반자료실1', 'nobinson_tirano.jpg', '노빈손,티라노,아동,백악기', 400);
INSERT INTO LIBRARY (lCallnum, lTitle, lWriter, lYear, lPublicsher, lLocation, lImage, lKeyword, bNumber)
    VALUES (LIBRARY_SEQ.NEXTVAL, '노빈손의 남극 어드벤처', '박경수', '2008', '뜨인돌', '일반자료실1', 'nobinson_polar.jpg', '노빈손,남극,소설,자연과학', 400);
-- 책 상세보기
SELECT * FROM LIBRARY WHERE LCALLNUM=4;
-- 도서수정ADMIN
UPDATE LIBRARY SET LTITLE = '노빈손, 피라미드의 비밀을 풀어라',
                             LWRITER = '강영숙',
                             LYEAR = 2000,
                             LPUBLICSHER = '뜨인돌',
                             LLOCATION = '보존서고1',
                             LIMAGE = 'nothing.jpg',
                             LKEYWORD = '노빈손, 이집트',
                             BNUMBER = 980
                WHERE LCALLNUM = 1;
    commit;                         
-- 도서삭제ADMIN
DELETE FROM LIBRARY WHERE LCALLNUM=3;
UPDATE LIBRARY SET lDel = 'Y' WHERE lCallnum=10;

-- 책 대출 처리(대출횟수 1 증가)
UPDATE LIBRARY SET LMANY = LMANY+1, LBORROW = 'N' WHERE LCALLNUM=1;
INSERT INTO LIBRARYBORROW (LBNUM, LCALLNUM, MID)
    VALUES (LIBRARYBORROW_SEQ.NEXTVAL, '1', 'aaa');
-- 책 반납 처리
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