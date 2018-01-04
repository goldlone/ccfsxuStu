package cn.goldlone.service;

import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.LoginInfo;
import cn.goldlone.model.UserInfo;
import cn.goldlone.po.Member;
import cn.goldlone.utils.MybatisUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by CN on 2017/12/30.
 */
public class Test {
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtils.openSqlSession();

//        MemberMapper mm = sqlSession.getMapper(MemberMapper.class);
        // 登录
//        LoginInfo info = mm.login("857353825@qq.com");
//        System.out.println(info);
        // 获取年级集合
//        List<Integer> list = mm.selectGradeSet();
//        for(Integer i: list)
//            System.out.println(i);
        // 添加会员信息
//        Member member = new Member("68888G", "张三", "201502401011", "18435187011",
//                "2013525296@qq.com", "男", "计科", 2015, 2, 1, "1420151515151", "2015-09-01",
//                "2020-12-31", 1, "./68888.jpg", DigestUtils.sha256Hex("abcd1234"),
//                1, 8);
//        System.out.println(mm.addMember(member));
//        sqlSession.commit();
        // 获取全部会员信息
//        List<UserInfo> list = mm.selectAllMember();
//        if(list.size()>0)
//            for(UserInfo u: list)
//                System.out.println(u.toString());
        // 根据会员号查询会员信息
//        UserInfo info = mm.selectMemberByNo("68888G");
//        System.out.println(info);
        // 根据邮箱查询会员信息
//        UserInfo info = mm.selectMemberByEmail("2013525296@qq.com");
//        System.out.println(info);
        // 查询过期与未过期的会员信息
//        List<UserInfo> list = mm.selectExpiredMember();
//        if(list.size()>0)
//            for(UserInfo u: list)
//                System.out.println(u.toString());
//        System.out.println("......");
//        List<UserInfo> list1 = mm.selectNotExpiredMember();
//        if(list1.size()>0)
//            for(UserInfo u: list1)
//                System.out.println(u.toString());
        // 查询权限
//        System.out.println(mm.selectPower("61234G"));
        // 根据会员姓名查询会员信息
//        List<UserInfo> list = mm.selectMemberByName("张");
//        if(list.size()>0)
//            for(UserInfo u: list)
//                System.out.println(u.toString());
        // 综合信息查询会员信息
//        Member member = new Member();
//        member.setGrade(2015);
//        member.setEndTime("0");
//        member.setNo("61234G");
//        member.setName("程");
//        List<UserInfo> list = mm.queryMember(member);
//        if(list.size()>0)
//            for(UserInfo u: list)
//                System.out.println(u.toString());
        // 查询学历编号
//        System.out.println(mm.selectDegreeNo("本科"));
        // 修改会员信息
//        Member member = new Member();
//        member.setNo("61234G");
//        member.setPower(3);
//        member.setName("王五");
//        System.out.println(mm.updateMemberInfo(member));
//        sqlSession.commit();



    }


}
