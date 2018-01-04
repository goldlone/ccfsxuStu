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

