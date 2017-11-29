-- 会员类型表
drop table if exists MemberType;
CREATE TABLE MemberType(
	M_typeNo tinyint NOT NULL,
	M_typeName varchar(10),
	PRIMARY KEY(M_typeNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO MemberType VALUES(0, '学生会员');
INSERT INTO MemberType VALUES(1, '会员');

-- 学历信息表
drop table if exists DegreeInfo;
CREATE TABLE DegreeInfo(
	D_degreeNo tinyint NOT NULL,
	D_degreeName varchar(10),
	PRIMARY KEY(D_degreeNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO DegreeInfo VALUES(0, '本科');
INSERT INTO DegreeInfo VALUES(1, '硕士');
INSERT INTO DegreeInfo VALUES(2, '博士');

-- 会员信息表
drop table if exists Member;
CREATE TABLE Member(
	M_memberNo varchar(8) NOT NULL,
	M_name varchar(20) not null,
	M_stuNo varchar(20) not null,
	M_phone varchar(11) not null,
	M_email varchar(50) NOT NULL UNIQUE,
	M_discipline varchar(20),
	M_grade int not null,
	M_class int,
	M_degreeNo tinyint,
	M_id varchar(20),
	M_startTime Date,
	M_endTime Date,
	M_typeNo tinyint,
	M_passwd varchar(20),
	M_power tinyint,
	primary key(M_memberNo),
	foreign key(M_degreeNo) references DegreeInfo(D_degreeNo),
	foreign key(M_typeNo) references MemberType(M_typeNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO Member VALUES('65535G', '程宁', '201502401086', '18435187057', '857353825@qq.com', '计算机科学与技术',
	'2015', '2', 0, '142***************', '2016-09-01', '2020-12-31', 0, '123123', 0);

-- CSP（CCSP）认证信息表
drop table if exists Certification;
create table Certification(
	C_no int not null AUTO_INCREMENT,
	C_name varchar(30) not null UNIQUE,
	C_startTime datetime,
	C_endTime datetime,
	C_memberFee int,
	C_notMemberFee int,
	primary key(C_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into Certification values(11, '第十一次CCF计算机软件能力认证', '2017-09-17 13:30:00', '2017-09-17 17:30:00', 180, 300);

-- 会员CSP（CCSP）成绩信息表
drop table if exists Score;
create table Score(
	S_certNo int NOT NULL ,
	S_memberNo varchar(8) NOT NULL ,
	S_all int,
	S_first int,
	S_second int,
	S_third int,
	S_forth int,
	S_fifth int,
	S_language VARCHAR(20),
	primary key(S_certNo, S_memberNo),
	foreign key(S_certNo) references Certification(C_no),
	foreign key(S_memberNo) references Member(M_memberNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- insert into Score(S_certNo, S_memberNo, S_all, S_first, S_second, S_third, S_forth, S_fifth) values(11, '65535G', 300, 100, 100, 100, 0, 0);

-- 会员CSP报名信息表
drop table if exists Application;
create table Application(
	A_no int not null AUTO_INCREMENT,
	A_certNo int,
	A_language varchar(10),
	A_isMember tinyint,
	A_memberNo varchar(8),
	A_fee int,
	A_photo varchar(255),
	primary key(A_no),
	foreign key(A_CertNo) references Certification(C_no),
	foreign key(A_memberNo) references Member(M_memberNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- insert into Application(A_CertNo, A_language, A_isMember, A_fee, A_photo) values(11, 'C++', 0, 50, './da.jpg');


-- 图书类别表
drop table if exists BookType;
create table BookType(
	B_typeNo int AUTO_INCREMENT,
	B_typeName varchar(20) UNIQUE,
	primary key(B_typeNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- insert into BookType values(1, 'Java');

-- 图书信息表
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
	foreign key(B_typeNo) references BookType(B_typeNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- insert into BookInfo values('1234567891231', 'Java从入门到精通（第3版）', 1, '某个少年', '清华大学出版社', 69.9, 1);

-- 借阅登记表
drop table if exists BorrowBook;
create table BorrowBook(
	B_borrowNo int AUTO_INCREMENT,
	B_bookNo varchar(13),
	B_memberNo varchar(8),
	B_borrowTime datetime,
	B_backTime datetime,
	primary key(B_borrowNo),
	foreign key(B_bookNo) references BookInfo(B_bookNo),
	foreign key(B_memberNo) references Member(M_memberNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- insert into BorrowBook values(1, '1234567891231', '65535G', '2017-09-28 16:09:00', '2017-10-28 16:09:00');







