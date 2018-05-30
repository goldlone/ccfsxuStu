select M_password password, M_memberNo memberNo, M_power power
from Member
where M_email = '1099455338@qq.com';

select now()> M_endTime
FROM Member WHERE M_memberNo = '61234G';

# 查询会员信息
SELECT m1.M_memberNo,m1.M_name,m1.M_stuNo,m1.M_phone,
  m1.M_email,m1.M_gender,m1.M_discipline,m1.M_grade,
  m1.M_class,d.DT_name,m1.M_id,m1.M_startTime,m1.M_endTime,
  m2.MT_name,m1.M_power,p.PT_name,m1.M_addScore,
  now()>m1.M_endTime as expired
FROM Member m1, MemberType m2, PowerType p, DegreeType d
WHERE m1.M_typeNo=m2.MT_no AND
      m1.M_power=p.PT_no AND
      m1.M_degreeNo=d.DT_no;

SELECT m1.M_memberNo,m1.M_name,m1.M_stuNo,m1.M_phone,
  m1.M_email,m1.M_gender,m1.M_discipline,m1.M_grade,
  m1.M_class,d.DT_name,m1.M_id,m1.M_startTime,m1.M_endTime,
  m2.MT_name,m1.M_power,p.PT_name,m1.M_addScore,
  now()>m1.M_endTime as expired
FROM Member m1, MemberType m2, PowerType p, DegreeType d
WHERE m1.M_memberNo='61234G' AND
      m1.M_typeNo=m2.MT_no AND
      m1.M_power=p.PT_no AND
      m1.M_degreeNo=d.DT_no;

SELECT m1.M_memberNo,m1.M_name,m1.M_stuNo,m1.M_phone,
  m1.M_email,m1.M_gender,m1.M_discipline,m1.M_grade,
  m1.M_class,d.DT_name,m1.M_id,m1.M_startTime,m1.M_endTime,
  m2.MT_name,m1.M_power,p.PT_name,m1.M_addScore,
  now()>m1.M_endTime as expired
FROM Member m1, MemberType m2, PowerType p, DegreeType d
WHERE now()<m1.M_endTime AND
      m1.M_typeNo=m2.MT_no AND
      m1.M_power=p.PT_no AND
      m1.M_degreeNo=d.DT_no;

SELECT a.A_certNo,c.C_name,a.A_memberNo,m.M_id,m.M_phone,
      m.M_email,a.A_language,d.DT_name,a.A_purpose,
      a.A_purposeUniversity,a.A_username,a.A_password,m.M_photo
FROM Application a,Certification c,Member m,DegreeType d
WHERE a.A_certNo=1 AND
      a.A_certNo=c.C_no AND
      a.A_memberNo=m.M_memberNo AND
      m.M_degreeNo=d.DT_name;

SELECT c.C_name,m.M_name,s.S_memberNo,s.S_all,s.S_first,
        s.S_second,s.S_third,s.S_forth,s.S_fifth
FROM Score s,Certification c,Member m
WHERE s.S_memberNo = '' AND
      s.S_certNo=c.C_name AND
      s.S_memberNo=m.M_memberNo;

SELECT c.C_name,m.M_name,s.S_memberNo,s.S_all,s.S_first,
s.S_second,s.S_third,s.S_forth,s.S_fifth
FROM Score s,Certification c,Member m
WHERE s.S_certNo = #{certNo} AND
s.S_certNo=c.C_name AND
s.S_memberNo=m.M_memberNo;

SELECT c.C_name,m.M_name,s.S_memberNo,s.S_all,s.S_first,
        s.S_second,s.S_third,s.S_forth,s.S_fifth
FROM Member m,Certification c,Score s
WHERE m.M_addScore>0 AND
      m.M_addScore=c.C_no AND
      m.M_memberNo=s.S_memberNo;

SELECT now();

SELECT a.A_certNo,c.C_name,a.A_memberNo,m.M_id,m.M_phone,
  m.M_email,a.A_language,d.DT_name,a.A_purpose,
  a.A_purposeUniversity,a.A_username,a.A_password,m.M_photo
FROM Application a,Certification c,Member m,DegreeType d
WHERE a.A_certNo=1 AND
      a.A_certNo=c.C_no AND
      a.A_memberNo=m.M_memberNo AND
      m.M_degreeNo=d.DT_no;

SELECT *
FROM Application a,Certification c,Member m
WHERE A_certNo = 1 AND
      a.A_certNo=c.C_no AND
      a.A_memberNo=m.M_memberNo;

SELECT c.C_name,m.M_name,s.S_memberNo,s.S_all,s.S_first,
  s.S_second,s.S_third,s.S_forth,s.S_fifth
FROM Score s,Certification c,Member m
WHERE s.S_certNo=c.C_no AND
      s.S_memberNo=m.M_memberNo;

SELECT b1.B_no,b1.B_name,b2.BT_name,b1.B_author,b1.B_publicer,
        b1.B_publiceDate,b1.B_price,b1.B_inventory
FROM BookInfo b1,BookType b2
WHERE b1.B_no='' AND b1.B_typeNo=b2.BT_no;


SELECT b1.B_no,b1.B_name,b2.BT_name,b1.B_author,b1.B_publicer,
        b1.B_publiceDate,b1.B_price,b1.B_inventory
FROM BookInfo b1,BookType b2
WHERE b1.B_typeNo=0 AND b1.B_typeNo=b2.BT_no;

INSERT
INTO BorrowBook(BB_bookNo,BB_memberNo,BB_borrowTime)
VALUES('', '', '');


SELECT b.BB_no,b.BB_bookNo,b.BB_memberNo,m.M_name,b.BB_borrowTime
FROM BorrowBook b,Member m
WHERE BB_backTime IS NULL AND
      b.BB_memberNo=m.M_memberNo;


SELECT * FROM Score  WHERE 1 ORDER BY S_all DESC ;


SELECT c.C_name,m.M_name,s.S_memberNo,s.S_all,s.S_first,
  s.S_second,s.S_third,s.S_forth,s.S_fifth
FROM Score s,Certification c,Member m
WHERE s.S_memberNo = '62151G' AND
      s.S_certNo=c.C_no AND
      s.S_memberNo=m.M_memberNo;

SELECT MAX(S_all) FROM Score WHERE S_certNo=12;

SELECT AVG(S_all), AVG(S_first), AVG(S_second), AVG(S_third), AVG(S_forth), AVG(S_fifth)
FROM Score
WHERE S_certNo=11;

SELECT MAX(S_all) S_all,
       MAX(S_first) S_first,
       MAX(S_second) S_second,
       MAX(S_third) S_third,
       MAX(S_forth) S_forth,
       MAX(S_fifth) S_fifth
FROM Score
WHERE S_certNo=12;


CREATE UNIQUE INDEX index_uq_member_type_name ON MemberType(MT_name);
CREATE UNIQUE INDEX index_uq_degree_type_name ON DegreeType(DT_name);
CREATE UNIQUE INDEX index_uq_power_type_name ON PowerType(PT_name);
CREATE INDEX index_member_name ON Member(M_name);
CREATE INDEX index_uq_member_email ON Member(M_email);
CREATE INDEX index_member_grade ON Member(M_grade);
CREATE INDEX index_member_endTime ON Member(M_endTime);

CREATE UNIQUE INDEX index_uq_cert_name ON Certification(C_name);
CREATE UNIQUE INDEX index_uq_cert_startTime ON Certification(C_startTime);
CREATE UNIQUE INDEX index_score_certNo ON Score(S_certNo);
CREATE UNIQUE INDEX index_score_memberNo ON Score(S_memberNo);

CREATE UNIQUE INDEX index_uq_book_type_name ON BookType(BT_name);
CREATE INDEX index_book_type ON BookInfo(B_typeNo);
CREATE INDEX index_book_name ON BookInfo(B_name);
CREATE INDEX index_borrow_book_isbn ON BorrowBook(BB_no);


CREATE VIEW LoginView
AS
SELECT M_password, M_memberNo, M_power
FROM Member;

CREATE VIEW MemberInfoView
AS
SELECT m1.M_memberNo,m1.M_name,m1.M_stuNo,m1.M_phone,
  m1.M_email,m1.M_gender,m1.M_discipline,m1.M_grade,
  m1.M_class,d.DT_name,m1.M_id,m1.M_startTime,m1.M_endTime,
  m2.MT_name,m1.M_power,p.PT_name,m1.M_addScore,
  now()>m1.M_endTime as expired
FROM Member m1, MemberType m2, PowerType p, DegreeType d
WHERE m1.M_typeNo=m2.MT_no AND
      m1.M_power=p.PT_no AND
      m1.M_degreeNo=d.DT_no;

CREATE VIEW ApplicationView
AS
SELECT a.A_certNo,c.C_name,m.M_name,a.A_memberNo,m.M_gender,
  m.M_id,m.M_phone,m.M_email,a.A_language,d.DT_name,m.M_grade,
  a.A_purpose,a.A_purposeUniversity,a.A_username,a.A_password,
  m.M_photo
FROM Application a,Certification c,Member m,DegreeType d
WHERE a.A_certNo=c.C_no AND
      a.A_memberNo=m.M_memberNo AND
      m.M_degreeNo=d.DT_no;

CREATE VIEW ScoreInfoView
AS
SELECT s.S_certNo,c.C_name,m.M_name,s.S_memberNo,s.S_all,s.S_first,
  s.S_second,s.S_third,s.S_forth,s.S_fifth
FROM Score s,Certification c,Member m
WHERE s.S_certNo=c.C_no AND
      s.S_memberNo=m.M_memberNo;

CREATE VIEW BookInfoView
AS
SELECT b1.B_no,b1.B_name,b2.BT_name,b1.B_author,b1.B_publicer,
  b1.B_publiceDate,b1.B_price,b1.B_inventory
FROM BookInfo b1,BookType b2
WHERE b1.B_typeNo=b2.BT_no;


SELECT s.S_certNo,c.C_name,m.M_name,s.S_memberNo,s.S_all,s.S_first,
  s.S_second,s.S_third,s.S_forth,s.S_fifth
FROM Score s,Certification c,Member m
WHERE s.S_certNo = ? AND
      s.S_certNo=c.C_no AND
      s.S_memberNo=m.M_memberNo
ORDER BY S_all DESC;


SELECT S_certNo,
        MAX(S_all) S_all,
       MAX(S_first) S_first,
       MAX(S_second) S_second,
       MAX(S_third) S_third,
       MAX(S_forth) S_forth,
       MAX(S_fifth) S_fifth
FROM Score
GROUP BY S_certNo;


CREATE TRIGGER borrow_book
AFTER INSERT ON BorrowBook
FOR EACH ROW
BEGIN
  UPDATE BookInfo SET B_inventory=B_inventory-1 WHERE B_no=BorrowBook.BB_bookNo;
END;

CREATE TRIGGER back_book
AFTER UPDATE ON BorrowBook
FOR EACH ROW
BEGIN
  UPDATE BookInfo SET B_inventory=B_inventory+1 WHERE B_no=BorrowBook.BB_bookNo;
END;

CREATE TRIGGER add_score
AFTER INSERT ON Score
FOR EACH ROW
BEGIN
  UPDATE Member SET M_addScore=Score.S_certNo WHERE Score.S_all>200 AND M_addScore=0;
END;



























INSERT INTO `BookInfo` VALUES ('1111', '编译原理', '7', '郑超', '山西大学出版社', '2018-01-08', '999.99', '6');
INSERT INTO `BookInfo` VALUES ('2222', 'QQ', '10', '确定', '去去去', '2018-01-06', '21.9', '20');
INSERT INTO `BookInfo` VALUES ('3333', 'Linux私房菜', '3', '鸟哥', '人民邮电出版社', '2012-10-01', '97', '1');
INSERT INTO `BookInfo` VALUES ('9787040207705', '编译程序设计原理', '7', '金成植，金英', '高等教育出版社', '2000-07-01', '40', '1');
INSERT INTO `BookInfo` VALUES ('9787115373991', 'Flask Web开发，基于Python的Web应用开发实战', '2', 'Miguel Grinberg', '人民邮电出版社', '2015-01-01', '59', '1');



-- ----------------------------
-- Records of BorrowBook
-- ----------------------------
INSERT INTO `BorrowBook` VALUES ('1', '1111', '62151G', '2018-01-11 01:55:48', '2018-01-11 02:34:56');
INSERT INTO `BorrowBook` VALUES ('3', '1111', '62151G', '2018-01-11 01:59:18', null);
INSERT INTO `BorrowBook` VALUES ('4', '1111', '62151G', '2018-01-11 02:40:19', null);



INSERT INTO `Score` VALUES ('8', '62151G', '230', '100', '90', '40', '0', '0');
INSERT INTO `Score` VALUES ('8', '62496G', '0', '0', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('8', '62595G', '190', '100', '90', '0', '0', '0');
INSERT INTO `Score` VALUES ('9', '62151G', '170', '100', '70', '0', '0', '0');
INSERT INTO `Score` VALUES ('9', '62496G', '0', '0', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('9', '62595G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('10', '62151G', '130', '100', '30', '0', '0', '0');
INSERT INTO `Score` VALUES ('10', '62496G', '0', '0', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('10', '62595G', '250', '100', '100', '50', '0', '0');
INSERT INTO `Score` VALUES ('11', '62151G', '210', '100', '100', '10', '0', '0');
INSERT INTO `Score` VALUES ('11', '62202G', '140', '100', '40', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '62214G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '62373G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '62419G', '80', '70', '10', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '62626G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '62637G', '160', '100', '60', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '62664G', '0', '0', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '63184G', '160', '100', '30', '30', '0', '0');
INSERT INTO `Score` VALUES ('11', '63317G', '110', '100', '10', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '64345G', '140', '100', '40', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '64352G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '64676G', '5', '0', '0', '0', '5', '0');
INSERT INTO `Score` VALUES ('11', '64822G', '220', '100', '0', '20', '100', '0');
INSERT INTO `Score` VALUES ('11', '66031G', '70', '70', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '66595G', '130', '100', '0', '0', '0', '30');
INSERT INTO `Score` VALUES ('11', '68083G', '140', '100', '30', '10', '0', '0');
INSERT INTO `Score` VALUES ('11', '73084G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '73092G', '160', '100', '30', '30', '0', '0');
INSERT INTO `Score` VALUES ('11', '73298G', '140', '100', '40', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '73592G', '110', '100', '10', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '73711G', '120', '100', '20', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '73971G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '78094G', '0', '0', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '78172G', '90', '90', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '78263G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82379G', '225', '100', '90', '0', '5', '30');
INSERT INTO `Score` VALUES ('11', '82382G', '120', '100', '20', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82383G', '160', '100', '30', '0', '0', '30');
INSERT INTO `Score` VALUES ('11', '82384G', '110', '100', '10', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82654G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82655G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82656G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82657G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82658G', '110', '100', '10', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82659G', '90', '90', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82660G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82661G', '90', '90', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82663G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82664G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82666G', '130', '100', '30', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82667G', '0', '0', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('11', '82668G', '110', '100', '10', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '52886G', '130', '100', '30', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62151G', '270', '100', '100', '0', '70', '0');
INSERT INTO `Score` VALUES ('12', '62202G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62212G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62213G', '210', '100', '100', '0', '10', '0');
INSERT INTO `Score` VALUES ('12', '62214G', '160', '100', '30', '0', '30', '0');
INSERT INTO `Score` VALUES ('12', '62350G', '190', '100', '90', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62372G', '190', '100', '90', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62373G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62378G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62379G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62381G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62382G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62419G', '190', '100', '90', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62577G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62595G', '210', '100', '90', '0', '20', '0');
INSERT INTO `Score` VALUES ('12', '62626G', '110', '100', '10', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62664G', '190', '100', '90', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62715G', '0', '0', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '62847G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '63184G', '240', '100', '100', '0', '40', '0');
INSERT INTO `Score` VALUES ('12', '63304G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '63305G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '63317G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '64345G', '120', '20', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '64822G', '185', '100', '10', '5', '70', '0');
INSERT INTO `Score` VALUES ('12', '64827G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '64912G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '65354G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '65356G', '90', '0', '90', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '65730G', '220', '100', '90', '0', '30', '0');
INSERT INTO `Score` VALUES ('12', '66031G', '120', '100', '20', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '66595G', '150', '100', '30', '0', '20', '0');
INSERT INTO `Score` VALUES ('12', '68082G', '150', '100', '30', '0', '20', '0');
INSERT INTO `Score` VALUES ('12', '68083G', '225', '100', '100', '5', '20', '0');
INSERT INTO `Score` VALUES ('12', '68084G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '73084G', '190', '100', '90', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '73092G', '225', '100', '100', '5', '20', '0');
INSERT INTO `Score` VALUES ('12', '73298G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '73308G', '220', '100', '100', '0', '20', '0');
INSERT INTO `Score` VALUES ('12', '73971G', '190', '100', '90', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '78172G', '190', '100', '90', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '78263G', '200', '100', '90', '0', '10', '0');
INSERT INTO `Score` VALUES ('12', '79835G', '0', '0', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '81761G', '190', '100', '90', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82363G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82379G', '110', '100', '10', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82382G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82383G', '210', '100', '100', '0', '10', '0');
INSERT INTO `Score` VALUES ('12', '82384G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82386G', '100', '100', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82395G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82403G', '130', '100', '30', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82407G', '0', '0', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82438G', '230', '100', '100', '0', '30', '0');
INSERT INTO `Score` VALUES ('12', '82448G', '230', '100', '100', '0', '30', '0');
INSERT INTO `Score` VALUES ('12', '82480G', '0', '0', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82493G', '20', '20', '0', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82496G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82661G', '130', '100', '30', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82666G', '190', '100', '90', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82668G', '200', '100', '100', '0', '0', '0');
INSERT INTO `Score` VALUES ('12', '82674G', '30', '30', '0', '0', '0', '0');








