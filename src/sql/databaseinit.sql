--- 초기 데이터베이스 환경 설정
DROP DATABASE SRdatabase;
CREATE DATABASE SRdatabase;
GRANT ALL PRIVILEGES ON SRdatabase.* to SRdatabase@localhost IDENTIFIED BY 'SRdatabase' WITH GRANT OPTION;
