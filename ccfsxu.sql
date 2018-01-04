# 会员类型表
drop table if exists MemberType;
CREATE TABLE MemberType(
  MT_no tinyint NOT NULL AUTO_INCREMENT,
  MT_name varchar(10) NOT NULL ,
  PRIMARY KEY(MT_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE MemberType ADD CONSTRAINT uq_member_type_name UNIQUE(MT_name);
INSERT INTO MemberType(MT_name) VALUES('学生会员');
INSERT INTO MemberType(MT_name) VALUES('会员');

# 学历信息表
drop table if exists DegreeType;
CREATE TABLE DegreeType(
  DT_no tinyint NOT NULL AUTO_INCREMENT,
  DT_name varchar(10) NOT NULL,
  PRIMARY KEY(DT_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE DegreeType ADD CONSTRAINT uq_degree_type_name UNIQUE(DT_name);
INSERT INTO DegreeType(DT_name) VALUES('专科');
INSERT INTO DegreeType(DT_name) VALUES('本科');
INSERT INTO DegreeType(DT_name) VALUES('硕士');
INSERT INTO DegreeType(DT_name) VALUES('博士');

# 权限信息表
DROP TABLE IF EXISTS PowerType;
CREATE TABLE PowerType(
  PT_no tinyint NOT NULL AUTO_INCREMENT,
  PT_name varchar(10) NOT NULL,
  PRIMARY KEY(PT_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE PowerType ADD CONSTRAINT uq_power_type_name UNIQUE(PT_name);
INSERT INTO PowerType (PT_name) VALUES ('超级管理员');
INSERT INTO PowerType (PT_name) VALUES ('会员信息管理员');
INSERT INTO PowerType (PT_name) VALUES ('CSP认证管理员');
INSERT INTO PowerType (PT_name) VALUES ('图书管理员');
INSERT INTO PowerType (PT_name) VALUES ('学生会员');

# 会员信息表
drop table if exists Member;
CREATE TABLE Member(
  M_memberNo varchar(10) NOT NULL,
  M_name varchar(20) NOT NULL ,
  M_stuNo varchar(20) DEFAULT '',
  M_phone varchar(11) DEFAULT '',
  M_email varchar(50) NOT NULL,
  M_gender VARCHAR(2) DEFAULT '',
  M_discipline varchar(20) DEFAULT '',
  M_grade int DEFAULT 0,
  M_class int DEFAULT 0,
  M_degreeNo tinyint DEFAULT 2,
  M_id varchar(20) DEFAULT '',
  M_startTime Date NOT NULL,
  M_endTime Date NOT NULL,
  M_typeNo tinyint NOT NULL DEFAULT 1,
  M_photo varchar(255),
  M_password varchar(64) NOT NULL,
  M_power tinyint NOT NULL DEFAULT 5,
  M_addScore int DEFAULT 0,
  PRIMARY KEY(M_memberNo),
  FOREIGN KEY(M_degreeNo) REFERENCES DegreeType(DT_no),
  FOREIGN KEY(M_typeNo) REFERENCES MemberType(MT_no),
  FOREIGN KEY(M_power) REFERENCES PowerType(PT_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE Member ADD CONSTRAINT uq_email UNIQUE(M_email);

# INSERT INTO Member VALUES('65535G', '程宁', '201502401086', '18435187057', '857353825@qq.com', '计算机科学与技术',
#                                     2015, 2, 2, '142***************', '2016-09-01', '2020-12-31', 1, '/sdad.pg', 'abcd1234', 1);

# CSP（CCSP）认证信息表
drop table if exists Certification;
create table Certification(
  C_no int NOT NULL AUTO_INCREMENT,
  C_name varchar(30) NOT NULL,
  C_startTime datetime NOT NULL,
  C_endTime datetime NOT NULL,
  C_memberFee int DEFAULT 180,
  C_notMemberFee int DEFAULT 300,
  PRIMARY KEY(C_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE Certification ADD CONSTRAINT uq_certification_name UNIQUE(C_name);
# insert into Certification values(11, '第十一次CCF计算机软件能力认证', '2017-09-17 13:30:00', '2017-09-17 17:30:00', 180, 300);

# 会员CSP（CCSP）成绩信息表
drop table if exists Score;
create table Score(
  S_certNo int NOT NULL ,
  S_memberNo varchar(10) NOT NULL ,
  S_all int DEFAULT 0,
  S_first int DEFAULT 0,
  S_second int DEFAULT 0,
  S_third int DEFAULT 0,
  S_forth int DEFAULT 0,
  S_fifth int DEFAULT 0,
  primary key(S_certNo, S_memberNo),
  foreign key(S_certNo) references Certification(C_no),
  foreign key(S_memberNo) references Member(M_memberNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
# insert into Score(S_certNo, S_memberNo, S_all, S_first, S_second, S_third, S_forth, S_fifth) values(11, '65535G', 300, 100, 100, 100, 0, 0);

# 会员CSP报名信息表
drop table if exists Application;
create table Application(
  A_certNo int NOT NULL,
  A_memberNo varchar(10) NOT NULL,
  A_language varchar(10),
  A_fee int DEFAULT 0,
  primary key(A_certNo, A_memberNo),
  foreign key(A_CertNo) references Certification(C_no),
  foreign key(A_memberNo) references Member(M_memberNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
# insert into Application(A_CertNo, A_language, A_isMember, A_fee, A_photo) values(11, 'C++', 0, 50, './da.jpg');


# 图书类别表
drop table if exists BookType;
create table BookType(
  BT_no int AUTO_INCREMENT,
  BT_name varchar(20) UNIQUE,
  primary key(BT_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE BookType ADD CONSTRAINT uq_book_type_name UNIQUE(BT_name);
# insert into BookType values(1, 'Java');

# 图书信息表
drop table if exists BookInfo;
create table BookInfo(
  B_bookNo varchar(13),
  B_name varchar(30),
  B_typeNo int,
  B_author varchar(50),
  B_publicer varchar(30),
  B_publiceDate DATE,
  B_price double,
  B_inventory int,
  primary key(B_bookNo),
  foreign key(B_typeNo) references BookType(BT_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
# insert into BookInfo values('1234567891231', 'Java从入门到精通（第3版）', 1, '某个少年', '清华大学出版社', 69.9, 1);

# 借阅登记表
drop table if exists BorrowBook;
create table BorrowBook(
  BB_no int AUTO_INCREMENT,
  BB_bookNo varchar(13),
  BB_memberNo varchar(10),
  BB_borrowTime datetime,
  BB_backTime datetime,
  primary key(BB_no),
  foreign key(BB_bookNo) references BookInfo(B_bookNo),
  foreign key(BB_memberNo) references Member(M_memberNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
# insert into BorrowBook values(1, '1234567891231', '65535G', '2017-09-28 16:09:00', '2017-10-28 16:09:00');







