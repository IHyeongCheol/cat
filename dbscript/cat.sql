
--테이블 삭제
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE NOTICE CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_NOTICE;
DROP TABLE FAMOUS;
DROP SEQUENCE SEQ_FAMOUS;
DROP TABLE BOARD CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_BOARD_NO;
DROP SEQUENCE SEQ_BOARD_READCOUNT;
DROP TABLE BCOMMENT CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_BCOMMENT;
DROP TABLE BRESTRAIN CASCADE CONSTRAINTS;
DROP TABLE BCRESTRAIN CASCADE CONSTRAINTS;
DROP TABLE CATPROFILE CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_CATPROFILE;

--회원 테이블(13개)
CREATE TABLE MEMBER(
  USER_ID VARCHAR2(50) CONSTRAINT PK_MEMBER_UID PRIMARY KEY,
  USER_PWD VARCHAR2(100) NOT NULL,
  USER_NAME VARCHAR2(50) NOT NULL,
  USER_BIRTH DATE  NOT NULL,
  PHONE VARCHAR2(50) NOT NULL,
  ADDRESS VARCHAR2(500) NOT NULL,
  KEYWORD VARCHAR2(100) NOT NULL,
  ADMIN_OK CHAR(1) NOT NULL,
  LOGIN_OK CHAR(1) NOT NULL,
  LOGIN_STACK NUMBER DEFAULT 0 NOT NULL,
  POSTCODE VARCHAR(50),
  ADDRESS1 VARCHAR2(500),
  ADDRESS2 VARCHAR2(500)
);
COMMENT ON COLUMN MEMBER.USER_ID IS '사용자 아이디';
COMMENT ON COLUMN MEMBER.USER_PWD IS '사용자 비밀번호';
COMMENT ON COLUMN MEMBER.USER_NAME IS '사용자 이름';
COMMENT ON COLUMN MEMBER.USER_BIRTH IS '사용자 생년월일';
COMMENT ON COLUMN MEMBER.PHONE IS '사용자 휴대폰 번호';
COMMENT ON COLUMN MEMBER.ADDRESS IS '사용자 전체주소';
COMMENT ON COLUMN MEMBER.KEYWORD IS '사용자 키워드';
COMMENT ON COLUMN MEMBER.ADMIN_OK IS '관리자 권한 부여';
COMMENT ON COLUMN MEMBER.LOGIN_OK IS '로그인 허용 여부';
COMMENT ON COLUMN MEMBER.LOGIN_STACK IS '로그인 제한 스택';
COMMENT ON COLUMN MEMBER.POSTCODE IS '사용자 우편번호';
COMMENT ON COLUMN MEMBER.ADDRESS1 IS '사용자 주소';
COMMENT ON COLUMN MEMBER.ADDRESS2 IS '사용자 상세주소';


--공지사항 테이블(6개)
CREATE TABLE NOTICE(
  NOTICE_NO NUMBER CONSTRAINT PK_NOTICENO PRIMARY KEY,
  USER_ID VARCHAR2(50) NOT NULL,
  NOTICE_TITLE VARCHAR2(50) NOT NULL,
  NOTICE_CONTENT VARCHAR2(2000) NOT NULL,
  NOTICE_DATE DATE DEFAULT SYSDATE NOT NULL,
  NOTICE_COUNT NUMBER NOT NULL,
  CONSTRAINT FK_NOTICE_USER_ID FOREIGN KEY (USER_ID) REFERENCES MEMBER (USER_ID) ON DELETE CASCADE    
);
COMMENT ON COLUMN NOTICE.NOTICE_NO IS '공지글 번호';
COMMENT ON COLUMN NOTICE.USER_ID IS '관리자 아이디';
COMMENT ON COLUMN NOTICE.NOTICE_TITLE IS '공지사항 제목';
COMMENT ON COLUMN NOTICE.NOTICE_CONTENT IS '공지사항 내용';
COMMENT ON COLUMN NOTICE.NOTICE_DATE IS '공지사항 등록 날짜';
COMMENT ON COLUMN NOTICE.NOTICE_COUNT IS '공지사항 조회수';

CREATE SEQUENCE SEQ_NOTICE_NO
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

CREATE SEQUENCE SEQ_NOTICE_READCOUNT
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

--인기 고양이 정보 테이블(6개)
CREATE TABLE FAMOUS(
  USER_ID VARCHAR2(50) NOT NULL,
  REGISTER_NUM NUMBER NOT NULL,
  CAT_ORIGINAL_IMG VARCHAR2(1000) NOT NULL,
  CAT_RENAME_IMG VARCHAR2(1000) NOT NULL,
  CAT_NAME VARCHAR2(200) NOT NULL,
  CAT_EXPLAIN VARCHAR2(2000) NOT NULL,
  CONSTRAINT FK_FAMOUS_USER_ID FOREIGN KEY (USER_ID) REFERENCES MEMBER (USER_ID) ON DELETE CASCADE
);
COMMENT ON COLUMN FAMOUS.USER_ID IS '관리자 아이디';
COMMENT ON COLUMN FAMOUS.REGISTER_NUM IS '등록 글 번호';
COMMENT ON COLUMN FAMOUS.CAT_ORIGINAL_IMG IS '고양이 이미지 등록 파일명';
COMMENT ON COLUMN FAMOUS.CAT_RENAME_IMG IS '고양이 이미지 변경 파일명';
COMMENT ON COLUMN FAMOUS.CAT_NAME IS '고양이 이름';
COMMENT ON COLUMN FAMOUS.CAT_EXPLAIN IS '고양이 설명';

CREATE SEQUENCE SEQ_FAMOUS
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

-- 게시판 테이블(8개) - 게시글 번호, 조회수 시퀀스 2개 만들어 놓음
CREATE TABLE BOARD(
	BOARD_NO NUMBER,
    USER_ID VARCHAR2(50) NOT NULL,
    BOARD_TITLE	VARCHAR2(50) NOT NULL,
    BOARD_CONTENT VARCHAR2(2000) NOT NULL,
    BOARD_DATE	DATE DEFAULT SYSDATE NOT NULL,
    BOARD_READCOUNT NUMBER NOT NULL,
	BOARD_ORIGINAL_IMG VARCHAR2(100),
    BOARD_RENAME_IMG VARCHAR2(100),
    CONSTRAINT PK_BOARD PRIMARY KEY (BOARD_NO),
    CONSTRAINT FK_BOARD_USER_ID FOREIGN KEY (USER_ID) REFERENCES MEMBER (USER_ID) ON DELETE CASCADE
);
COMMENT ON COLUMN BOARD.BOARD_NO IS '게시글 번호';
COMMENT ON COLUMN BOARD.USER_ID IS '관리자 아이디';
COMMENT ON COLUMN BOARD.BOARD_TITLE IS '게시글 제목';
COMMENT ON COLUMN BOARD.BOARD_CONTENT IS '게시글 내용';
COMMENT ON COLUMN BOARD.BOARD_DATE IS '게시글 등록 날짜';
COMMENT ON COLUMN BOARD.BOARD_READCOUNT IS '게시글 조회수';
COMMENT ON COLUMN BOARD.BOARD_ORIGINAL_IMG IS '게시글 등록 이미지 파일명';
COMMENT ON COLUMN BOARD.BOARD_RENAME_IMG IS '게시글 변경 이미지 파일명';

CREATE SEQUENCE SEQ_BOARD_NO
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

CREATE SEQUENCE SEQ_BOARD_READCOUNT
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

-- 게시판 댓글 테이블(5개)
CREATE TABLE BCOMMENT(
	BCOMMENT_NO NUMBER,
    BOARD_NO NUMBER NOT NULL,
    USER_ID VARCHAR2(50) NOT NULL,
    BCOMMENT_CONTENT VARCHAR2(1000) NOT NULL,
    BCOMMENT_DATE DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT PK_BCOMMENT_NO PRIMARY KEY (BCOMMENT_NO),
    CONSTRAINT FK_BCOMMENT_BOARD_NO FOREIGN KEY (BOARD_NO) REFERENCES BOARD (BOARD_NO) ON DELETE CASCADE,
    CONSTRAINT FK_BCOMMENT_USER_ID FOREIGN KEY (USER_ID) REFERENCES MEMBER (USER_ID) ON DELETE CASCADE
);
COMMENT ON COLUMN BCOMMENT.BCOMMENT_NO IS '게시글 댓글 번호';
COMMENT ON COLUMN BCOMMENT.BOARD_NO IS '게시글 번호';
COMMENT ON COLUMN BCOMMENT.USER_ID IS '사용자 아이디';
COMMENT ON COLUMN BCOMMENT.BCOMMENT_CONTENT IS '댓글 내용';
COMMENT ON COLUMN BCOMMENT.BCOMMENT_DATE IS '댓글 등록 날짜';

CREATE SEQUENCE SEQ_BCOMMENT
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

-- 게시판 제제 테이블(2개)
CREATE TABLE BRESTRAIN(
    USER_ID VARCHAR2(50) NOT NULL,
    BOARD_NO NUMBER NOT NULL,
    CONSTRAINT FK_BRESTRAIN_USER_ID FOREIGN KEY (USER_ID) REFERENCES MEMBER (USER_ID) ON DELETE CASCADE,
    CONSTRAINT FK_BRESTRAIN_BOARD_NO FOREIGN KEY (BOARD_NO) REFERENCES BOARD (BOARD_NO) ON DELETE CASCADE
);
COMMENT ON COLUMN BRESTRAIN.USER_ID IS '사용자 아이디';
COMMENT ON COLUMN BRESTRAIN.BOARD_NO IS '게시글 번호';

-- 게시판 댓글 제제 테이블(3개)
CREATE TABLE BCRESTRAIN(
    USER_ID VARCHAR2(50) NOT NULL,
    BOARD_NO NUMBER NOT NULL,
    BCOMMENT_NO NUMBER NOT NULL,
    CONSTRAINT FK_BCRESTRAIN_USER_ID FOREIGN KEY (USER_ID) REFERENCES MEMBER (USER_ID) ON DELETE CASCADE,
    CONSTRAINT FK_BCRESTRAIN_BOARD_NO FOREIGN KEY (BOARD_NO) REFERENCES BOARD (BOARD_NO) ON DELETE CASCADE,
    CONSTRAINT FK_BCRESTRAIN_BCOMMENT_NO FOREIGN KEY (BCOMMENT_NO) REFERENCES BCOMMENT (BCOMMENT_NO) ON DELETE CASCADE
);
COMMENT ON COLUMN BCRESTRAIN.USER_ID IS '사용자 아이디';
COMMENT ON COLUMN BCRESTRAIN.BOARD_NO IS '게시글 번호';
COMMENT ON COLUMN BCRESTRAIN.BCOMMENT_NO IS '댓글 번호';


-- 고양이 정보 테이블(11개)
CREATE TABLE CATPROFILE(
    USER_ID VARCHAR2(50) NOT NULL,
    CAT_NAME VARCHAR2(50) NOT NULL,
    BREED VARCHAR2(50) NOT NULL,
    AGE NUMBER NOT NULL,
    STATURE VARCHAR2(50) NOT NULL,
    WEIGHT NUMBER NOT NULL,
    NEUTERING CHAR(1) NOT NULL,
    CAT_ORIGINAL_IMG VARCHAR2(100) NOT NULL,
    CAT_RENAME_IMG VARCHAR2(100) NOT NULL,
    REPEAT_NUMBER NUMBER NOT NULL,
    MEASURE_VALUE NUMBER NOT NULL,
    MEASURE_DATE VARCHAR(50) DEFAULT SYSDATE NOT NULL,
    CONSTRAINT FK_CATPROFILE_USER_ID FOREIGN KEY (USER_ID) REFERENCES MEMBER (USER_ID) ON DELETE CASCADE
);
COMMENT ON COLUMN CATPROFILE.USER_ID IS '사용자 아이디';
COMMENT ON COLUMN CATPROFILE.CAT_NAME IS '고양이 이름';
COMMENT ON COLUMN CATPROFILE.BREED IS '고양이 품종';
COMMENT ON COLUMN CATPROFILE.AGE IS '고양이 나이';
COMMENT ON COLUMN CATPROFILE.STATURE IS '고양이 성장도';
COMMENT ON COLUMN CATPROFILE.WEIGHT IS '고양이 무게';
COMMENT ON COLUMN CATPROFILE.NEUTERING IS '고양이 중성화 유무';
COMMENT ON COLUMN CATPROFILE.CAT_ORIGINAL_IMG IS '고양이 기존 이미지 파일명';
COMMENT ON COLUMN CATPROFILE.CAT_RENAME_IMG IS '고양이 변경 이미지 파일명';
COMMENT ON COLUMN CATPROFILE.REPEAT_NUMBER IS '측정 횟수';
COMMENT ON COLUMN CATPROFILE.MEASURE_VALUE IS '비만도';

CREATE SEQUENCE SEQ_CATPROFILE
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

--로그인 계정 생성
-------------------------------------------------------------------------------------
INSERT INTO MEMBER VALUES ('dongju', 'dongju', '정동주', '91/01/04' , '01055459619', '일산', '러시안블루', 'Y', 'Y', 0, '10234', '서울 마포구 백범로 23', '304호');
INSERT INTO MEMBER VALUES ('ehdwn', 'ehdwn', '정동주', '91/01/04' , '01055459619', '일산', '러시안블루', 'N', 'Y', 0, '10234', '서울 마포구 백범로 23', '304호');
-------------------------------------------------------------------------------------
--테이블 공지 생성
insert into BOARD values (SEQ_BOARD_NO.NEXTVAL, 'dongju', '안녕', '내용', SYSDATE, SEQ_BOARD_READCOUNT.NEXTVAL, NULL, NULL);
insert into NOTICE values (SEQ_NOTICE_NO.NEXTVAL, 'dongju', '안녕', '내sdafsadfsadkfjbsakdlfbsajdkfbsajdlbdsajfklbsadkfbdsajkfdsafdsafdsadfsadfsadfsadfdsafsadfsadfsdafdsadfsadfdsafsadfsadfsdafdsafsadgsafdhbfdabdafndnfdndsgnfgsndfsadfdsafsadfsadfsdafdsafsadgsafdhbfdabdafndnfdndsgnfgsndfsadfdsafsadfsadfsdafdsafsadgsafdhbfdabdafndnfdndsgnfgsndfsadfdsafsadfsadfsdafdsafsadgsafdhbfdabdafndnfdndsgnfgsnfsadgsafdhbfdabdafndnfdndsgnfgsnfgsn용', SYSDATE, SEQ_NOTICE_NO.NEXTVAL);

COMMIT;