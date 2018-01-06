package cn.goldlone.service;

import cn.goldlone.mapper.CSPMapper;
import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.LoginInfo;
import cn.goldlone.model.ScoreInfo;
import cn.goldlone.model.UserInfo;
import cn.goldlone.po.Application;
import cn.goldlone.po.Certification;
import cn.goldlone.po.Member;
import cn.goldlone.po.Score;
import cn.goldlone.utils.MybatisUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;

import java.sql.Timestamp;
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

        CSPMapper cm = sqlSession.getMapper(CSPMapper.class);

        // 添加CSP认证
//        Certification cert = new Certification("第一次CCF计算机软件能力认证",
//                Timestamp.valueOf("2014-03-15 13:30:00"),
//                Timestamp.valueOf("2014-03-15 17:30:00"),
//                180, 300);
//        Certification cert2 = new Certification("第二次CCF计算机软件能力认证",
//                Timestamp.valueOf("2014-09-15 13:30:00"),
//                Timestamp.valueOf("2014-09-15 17:30:00"),
//                180, 300);
//        Certification cert3 = new Certification("第三次CCF计算机软件能力认证",
//                Timestamp.valueOf("2014-12-15 13:30:00"),
//                Timestamp.valueOf("2014-12-15 17:30:00"),
//                180, 300);
//        cm.addCert(cert);
//        cm.addCert(cert2);
//        cm.addCert(cert3);
//        sqlSession.commit();
        // 获取认证名集合
//        List<Certification> list = cm.getCertSet();
//        for(Certification cert: list)
//            System.out.println(cert);
        // 根据认证名查询认证编号
//        System.out.println(cm.selectCertNoByName(""));
        // 获取未开始的认证集合
//        List<Certification> list = cm.getCertSetNotStart();
//        for(Certification cert: list)
//            System.out.println(cert);
        // 插入报名信息
//        Application app = new Application();
//        app.setCertNo(1);
//        app.setLanguage("Java");
//        app.setMemberNo("62151G");
//        app.setPurpose("其他");
//        app.setUsername("cheng123");
//        app.setPassword("qwerzxcv");
//        cm.addApplication(app);
//        sqlSession.commit();
        // 查询某次认证的报名信息
//        List<Application> list = cm.selectApplicationInfo(1);
//        for(Application app: list)
//            System.out.println(app);
        // 添加成绩
//        Score score = new Score(1, "62151G", 200, 100,
//                100, 0, 0, 0);
//        cm.addScore(score);
//        sqlSession.commit();
        // 查询所有成绩
//        List<ScoreInfo> list = cm.selectAllScore();
//        for(ScoreInfo info: list)
//            System.out.println(info);
        // 按认证编号查询查询所有人的成绩
//        List<ScoreInfo> list = cm.selectScoreByNo(1);
//        for(ScoreInfo info: list)
//            System.out.println(info);
        // 按会员号查询查询所有认证的成绩
//        List<ScoreInfo> list = cm.selectScoreByMemberNo("62151G");
//        for(ScoreInfo info: list)
//            System.out.println(info);
        // 综合筛选成绩信息(编号，最低，最高)
//        List<ScoreInfo> list = cm.queryScore(1, 0, 200);
//        for(ScoreInfo info: list)
//            System.out.println(info);
        // 查询加分了的人
//        List<ScoreInfo> list = cm.selectAddScore();
//        for(ScoreInfo info: list)
//            System.out.println(info);



    }


}
