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