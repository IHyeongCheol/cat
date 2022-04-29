insert into BOARD values (SEQ_BOARD_NO.NEXTVAL, 'dongju', '안녕', '내용', SYSDATE, SEQ_BOARD_READCOUNT.NEXTVAL, NULL, NULL);
insert into NOTICE values (SEQ_NOTICE_NO.NEXTVAL, 'dongju', '안녕', '내sdafsadfsadkfjbsakdlfbsajdkfbsajdlbdsajfklbsadkfbdsajkfdsafdsafdsadfsadfsadfsadfdsafsadfsadfsdafdsadfsadfdsafsadfsadfsdafdsafsadgsafdhbfdabdafndnfdndsgnfgsndfsadfdsafsadfsadfsdafdsafsadgsafdhbfdabdafndnfdndsgnfgsndfsadfdsafsadfsadfsdafdsafsadgsafdhbfdabdafndnfdndsgnfgsndfsadfdsafsadfsadfsdafdsafsadgsafdhbfdabdafndnfdndsgnfgsnfsadgsafdhbfdabdafndnfdndsgnfgsnfgsn용', SYSDATE, SEQ_NOTICE_NO.NEXTVAL);

alter table CATPROFILE add measure_date varchar(50) default '20200405' not null;

insert into CATPROFILE values ('dongju', '동냥이', '러시안블루', 1, '유아기', 3.4, 'Y', 'ASDFDA.JPG', 'CAT1.JPG', SEQ_CATPROFILE.NEXTVAL, 9.123125123);

SELECT * FROM NOTICE;
SELECT * FROM BOARD;

SELECT * FROM MEMBER;

SELECT * FROM CATPROFILE;

select * from catprofile
		where user_id = 'ehdwn' and cat_name = '빵냥이' 
order by repeat_number desc;

select * from catprofile
		where user_id = 'ehdwn' order by cat_name, repeat_number ;

alter table member add POSTCODE VARCHAR(50);
alter table member add ADDRESS1 VARCHAR(500);
alter table member add ADDRESS2 VARCHAR(500);

COMMIT;

SELECT *  
			FROM (SELECT USER_ID, CAT_NAME, BREED, AGE,   
			               STATURE, WEIGHT, NEUTERING,
			               CAT_ORIGINAL_IMG, CAT_RENAME_IMG,REPEAT_NUMBER,
			               MEASURE_VALUE,MEASURE_DATE
			       FROM (select * from CATPROFILE where user_id = 'ehdwn' order by CAT_NAME));
--			where rnum >= #{startRow} and rnum <= #{endRow};