-- posdb 데이터 베이스 생성
CREATE DATABASE posdb;
-- posdb 데이터 베이스 작업데이터베이스로 변환 
USE posdb; 
-- salesdata 테이블 생성(포스기에서 찍힌 품목정보들이 이쪽으로 넘어오고, 포스기의 판매관리 탭의 정보들은 이쪽에서 받아옴)
CREATE TABLE salesdata (numberdb INT NOT NULL AUTO_INCREMENT PRIMARY KEY , menudb CHAR(20) NOT NULL, countdb INT NOT NULL, pricedb INT NOT NULL, sumdb INT, time TIMESTAMP DEFAULT NOW());


USE posdb;
-- memberdb 테이블 생성(포스기의 회원관리 탭의 정보들은 이쪽에서 받아옴)
CREATE TABLE memberdb (numberdb INT NOT NULL AUTO_INCREMENT PRIMARY KEY, membername CHAR(20) NOT NULL, id CHAR(20), pw CHAR(20), birth DATE, gender CHAR(2), phone CHAR(20), email CHAR(20));
-- 회원정보들 memberdb에 삽입
INSERT INTO memberdb VALUES (NULL, "신준호", "admin", "xxx", NULL, "남", "010-1111-2222", "admin@playdata.com");
INSERT INTO memberdb VALUES (NULL, "조철호", "CCH12", "1234", NULL, "남", "010-1234-5678", "CCH12@playdata.com");
INSERT INTO memberdb VALUES (NULL, "최종준", "CJJ56", "5678", NULL, "남", "010-3333-5555", "CJJ56@playdata.com");
INSERT INTO memberdb VALUES (NULL, "김정훈", "KJH78", "9876", NULL, "남", "010-6666-7777", "KJH78@playdata.com");
