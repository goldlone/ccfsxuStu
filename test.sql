# 查询所有会员信息
select M_memberNo, M_name, M_stuNo, M_phone, M_email, M_discipline, M_grade, M_class, D_degreeName, M_id, M_startTime, M_endTime, M_typeName, M_passwd, M_power
from Member as a left join MemberType as b on a.M_typeNo=b.M_typeNo left join DegreeInfo as c on a.M_degreeNo=c.D_degreeNo
where now() between M_startTime and M_endTime;

# 用户登录
select M_phone, M_passwd
from Member
where M_phone = '18435187057';

# 查询成绩
select M_name, S_memberNo, S_all, S_first, S_second, S_third, S_forth, S_fifth, C_name
from Score as a left join Member as b on a.S_memberNo=b.M_memberNo left join Certification as c on a.S_certNo=c.C_no
where S_certNo=11;

# 查询是否已经加分
select M_memberNo, M_name, M_stuNo, M_addScore
from Member
where M_addScore>0;

# 查询某个人的所有成绩
select M_name, S_memberNo, S_all, S_first, S_second, S_third, S_forth, S_fifth, C_name
from Score, Member, Certification
where S_memberNo=M_memberNo and S_certNo=C_no and S_memberNo='65535G';

# 获取年级集合
select distinct M_grade
from Member;

# 查询会员信息
select M_memberNo, M_name, M_stuNo, M_phone, M_email, M_discipline, M_grade, M_class, D_degreeName, M_id, M_startTime, M_endTime, M_typeName, M_passwd, M_power
from Member as a left join MemberType as b on a.M_typeNo=b.M_typeNo left join DegreeInfo as c on a.M_degreeNo=c.D_degreeNo
where now() between M_startTime and M_endTime and M_grade=2015 and 1;

# 获取认证集合
SELECT  C_no, C_name
from Certification;

# 查询成绩
select M_name, S_memberNo, S_all, S_first, S_second, S_third, S_forth, S_fifth, C_name
from Score, Member, Certification
where S_memberNo=M_memberNo
      and S_certNo=C_no
#       and S_certNo=8
      and S_all BETWEEN 100 and 250;
SELECT M_name, S_memberNo, S_all, S_first, S_second, S_third, S_forth, S_fifth, C_name FROM Score, Member, Certification WHERE S_memberNo=M_memberNo       AND S_certNo=C_no       AND S_all BETWEEN 0 AND 300;

SELECT B_typeNo, B_typeName FROM BookType;
INSERT INTO BookType VALUES(1, 'Java');
INSERT INTO BookType VALUES(2, 'Python');
INSERT INTO BookType VALUES(3, '机器学习');
INSERT INTO BookType VALUES(4, '操作系统');
INSERT INTO BookType VALUES(5, 'MySQL数据库');

SELECT *
FROM Certification
WHERE now() < C_startTime;

# 创建报名信息表
DROP TABLE IF EXISTS Application;
create table Application(
  A_certNo int,
  A_memberNo varchar(8),
  A_name VARCHAR(20),
  A_language varchar(10),
  A_fee int,
  A_id VARCHAR(20),
  A_degree VARCHAR(20),
  A_purpose VARCHAR(20),
  A_school VARCHAR(50),
  A_username VARCHAR(50),
  A_password VARCHAR(50),
  A_photo varchar(255),
  A_phone VARCHAR(11),
  A_email VARCHAR(30),
  primary key(A_certNo, A_memberNo),
  foreign key(A_CertNo) references Certification(C_no),
  foreign key(A_memberNo) references Member(M_memberNo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SELECT * from Application;
