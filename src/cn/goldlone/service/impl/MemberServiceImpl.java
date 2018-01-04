package cn.goldlone.service.impl;

import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.UserInfo;
import cn.goldlone.po.Member;
import cn.goldlone.service.MemberService;
import cn.goldlone.utils.MybatisUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by CN on 2018/1/4.
 */
public class MemberServiceImpl implements MemberService {
    private SqlSession sqlSession;
    private MemberMapper memberMapper;

    public MemberServiceImpl() {
        this.sqlSession = MybatisUtils.openSqlSession();
        this.memberMapper = sqlSession.getMapper(MemberMapper.class);
    }




    @Override
    public boolean addMember(Member member) {
        try {
            memberMapper.addMember(member);
            sqlSession.commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }
        return false;
    }

    @Override
    public boolean addMember(List<Member> list) {
        try {
            for(Member member: list)
                memberMapper.addMember(member);
            sqlSession.commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
//            sqlSession.rollback();
        }
        return false;
    }

    @Override
    public boolean addMemberByUserInfo(List<UserInfo> list) {
        try {
            Member member = null;
            for(UserInfo info: list) {
                if(memberMapper.selectMemberByNo(info.getNo()) != null) {
                    System.out.println(info.getNo()+"---已存在，已更新失效日期");
                    memberMapper.updateEndTimeByNo(info.getNo(), info.getEndTime());
                } else {
                    member = new Member();
                    member.setNo(info.getNo());
                    member.setName(info.getName());
                    member.setStuNo(info.getStuNo());
                    member.setPhone(info.getPhone());
                    member.setEmail(info.getEmail());
                    member.setGender(info.getGender());
                    member.setDiscipline(info.getDiscipline());
                    member.setGrade(info.getGrade());
                    member.setClassNum(info.getClassNum());
                    member.setDegreeNo(memberMapper.selectDegreeNo(info.getDegree()));
                    member.setId(info.getId());
                    member.setStartTime(info.getStartTime());
                    member.setEndTime(info.getEndTime());
                    member.setMemberTypeNo(1);
                    member.setPassword(DigestUtils.sha256Hex(info.getNo()));
                    member.setPower(5);
                    memberMapper.addMember(member);
                }
            }
            sqlSession.commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
//            sqlSession.rollback();
        }
        return false;
    }
}
