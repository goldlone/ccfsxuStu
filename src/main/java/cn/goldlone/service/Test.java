package cn.goldlone.service;

import cn.goldlone.mapper.BookMapper;
import cn.goldlone.mapper.CSPMapper;
import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.*;
import cn.goldlone.po.*;
import cn.goldlone.utils.MybatisUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.File;

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
//        LoginInfo info = mm.login("qqq@qq.com");
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



//        CSPMapper cm = sqlSession.getMapper(CSPMapper.class);
        // 添加CSP认证
//        Certification cert1 = new Certification("第一次CCF计算机软件能力认证",
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
//        Certification cert4 = new Certification("第四次CCF计算机软件能力认证",
//                Timestamp.valueOf("2015-03-15 13:30:00"),
//                Timestamp.valueOf("2015-03-15 17:30:00"),
//                180, 300);
//        Certification cert5 = new Certification("第五次CCF计算机软件能力认证",
//                Timestamp.valueOf("2015-03-15 13:30:00"),
//                Timestamp.valueOf("2015-03-15 17:30:00"),
//                180, 300);
//        Certification cert6 = new Certification("第六次CCF计算机软件能力认证",
//                Timestamp.valueOf("2015-03-15 13:30:00"),
//                Timestamp.valueOf("2015-03-15 17:30:00"),
//                180, 300);
//        Certification cert7 = new Certification("第七次CCF计算机软件能力认证",
//                Timestamp.valueOf("2016-03-15 13:30:00"),
//                Timestamp.valueOf("2016-03-15 17:30:00"),
//                180, 300);
//        Certification cert8 = new Certification("第八次CCF计算机软件能力认证",
//                Timestamp.valueOf("2016-03-15 13:30:00"),
//                Timestamp.valueOf("2016-03-15 17:30:00"),
//                180, 300);
//        Certification cert9 = new Certification("第九次CCF计算机软件能力认证",
//                Timestamp.valueOf("2016-03-15 13:30:00"),
//                Timestamp.valueOf("2016-03-15 17:30:00"),
//                180, 300);
//        Certification cert10 = new Certification("第十次CCF计算机软件能力认证",
//                Timestamp.valueOf("2017-03-15 13:30:00"),
//                Timestamp.valueOf("2017-03-15 17:30:00"),
//                180, 300);
//        Certification cert11 = new Certification("第十一次CCF计算机软件能力认证",
//                Timestamp.valueOf("2017-09-15 13:30:00"),
//                Timestamp.valueOf("2017-09-15 17:30:00"),
//                180, 300);
//        Certification cert12 = new Certification("第十二次CCF计算机软件能力认证",
//                Timestamp.valueOf("2017-12-21 13:30:00"),
//                Timestamp.valueOf("2017-12-21 17:30:00"),
//                180, 300);
//        cm.addCert(cert1);
//        cm.addCert(cert2);
//        cm.addCert(cert3);
//        cm.addCert(cert4);
//        cm.addCert(cert5);
//        cm.addCert(cert6);
//        cm.addCert(cert7);
//        cm.addCert(cert8);
//        cm.addCert(cert9);
//        cm.addCert(cert10);
//        cm.addCert(cert11);
//        cm.addCert(cert12);
//        sqlSession.commit();
        // 获取认证名集合
//        List<Certification> list = cm.getCertSet();
//        for(Certification cert: list)
//            System.out.println(cert);
        // 根据认证名查询认证编号
//        System.out.println(cm.selectCertNoByName("第十二次CCF计算机软件能力认证"));
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


        BookMapper bm = sqlSession.getMapper(BookMapper.class);
        // 添加图书类别
//        BookType bt1 = new BookType("Java");
//        BookType bt2 = new BookType("Python");
//        BookType bt3 = new BookType("操作系统");
//        BookType bt4 = new BookType("机器学习");
//        BookType bt5 = new BookType("数据库");
//        BookType bt6 = new BookType("前端技术");
//        bm.addBookType(bt1);
//        bm.addBookType(bt2);
//        bm.addBookType(bt3);
//        bm.addBookType(bt4);
//        bm.addBookType(bt5);
//        bm.addBookType(bt6);
//        sqlSession.commit();
//        System.out.println(bt1.getNo());
        // 获取图书类别
//        List<BookType> list = bm.getBookType();
//        for(BookType bt: list)
//            System.out.println(bt.toString());
        // 获取类别名
//        System.out.println(bm.selectTypeName(1));
        // 查询图书类别编号
//        Integer num = null;
//        if((num=bm.selectTypeNoByName("qaqqa")) == null)
//            System.out.println("无该类别");
//        else
//            System.out.println(num);
        // 添加图书信息
//        Book book = new Book("9787115373991",
//                "Flask Web开发，基于Python的Web应用开发实战",
//                2, "Miguel Grinberg", "人民邮电出版社",
//                "2015-01-00", 59.00, 1);
//        bm.addBook(book);
//        sqlSession.commit();
        // 使用BookInfo添加图书信息
//        BookInfo book1 = new BookInfo("9787040207705",
//                "编译程序设计原理",
//                "编译原理", "金成植，金英", "高等教育出版社",
//                "2000-07-00", 40.00, 1);
//        Integer num = null;
//        BookType bt = new BookType(book1.getType());
//        Book b = null;
//        if((num=bm.selectTypeNoByName(book1.getType())) == null) {
//            bm.addBookType(bt);
//            b = new Book(book1, bt.getNo());
//        } else {
//            b = new Book(book1, num);
//        }
//        bm.addBook(b);
//        sqlSession.commit();
        //
//        List<BookInfo> list = bm.selectAllBook();
//        for(BookInfo info2: list)
//            System.out.println(info2);
        // 根据ISBN编号查询数目信息
//        BookInfo info1 = bm.selectBookByISBN("9787040207705");
//        System.out.println(info1);
        // 根据类别编号查询数目信息
//        List<BookInfo> list1 = bm.selectBookByType(0);
//        for(BookInfo info2: list1)
//            System.out.println(info2);
        // 根据部分书名查询数目信息
//        List<BookInfo> list2 = bm.selectBookByName("原理");
//        for(BookInfo info3: list2)
//            System.out.println(info3);
        // 借书
//        Integer num = null;
//        BorrowBook book = new BorrowBook("9787040207705",
//                "62151G", new Timestamp(System.currentTimeMillis()));
//        num = bm.selectInventory(book.getBookNo());
//        if(num!=null && num>0) {
//            bm.borrowBook(book);
//            bm.updateInventoryByBorrow(book.getBookNo());
//            sqlSession.commit();
//        } else {
//            System.out.println("该书籍不存在或库存不足");
//        }
        // 还书
//        List<BorrowInfo> list = bm.selectNotBackBook("9787040207705");
//        if(list!=null && list.size()>0) {
//            // 假设此处选选中第一个
//            BorrowBook book = new BorrowBook(list.get(0));
//            System.out.println(list.get(0));
//            book.setBackTime(new Timestamp(System.currentTimeMillis()));
//            bm.backBook(book);
//            bm.updateInventoryByBorrow(book.getBookNo());
//            sqlSession.commit();
//            System.out.println("还书成功");
//        } else {
//            System.out.println("该书籍无外借记录");
//        }



    }


}
