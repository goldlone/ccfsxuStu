package cn.goldlone.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.goldlone.mapper.BookMapper;
import cn.goldlone.mapper.CSPMapper;
import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.Result;
import cn.goldlone.po.*;
import cn.goldlone.service.MemberService;
import cn.goldlone.service.impl.MemberServiceImpl;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by CN on 2018/1/4.
 */
public class ExcelUtils {

    public static void main(String[] args) {
//        System.out.println(new File(".").getAbsolutePath());
//        ExcelUtils.createMemberTemplate();
//        ExcelUtils.importMemberInfo(new File("./templateMember.xls"));
//        createScoreTemplate();
//        Result res1 = ExcelUtils.importScore(new File("./csp11.xls"));
//        System.out.println("执行完成--------------");
//        for(String name: (List<String>)res1.getData())
//            System.out.println(name);
//        Result res2 = ExcelUtils.importScore(new File("./csp12.xls"));
//        System.out.println("执行完成--------------");
//        for(String name: (List<String>)res2.getData())
//            System.out.println(name);
//        exportApplicationInfo(1);
//        createBookTemplate();
//        importBook(new File("./templateBook.xls"));
    }

    /**
     * 导入会员信息
     * @param file
     * @return
     */
    public static Result importMemberInfo(MultipartFile file) {
        List<String> list = new ArrayList<>();
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        MemberMapper mm = sqlSession.getMapper(MemberMapper.class);
        try {
            Workbook workbook = Workbook.getWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet(0);
            MemberService memberService = new MemberServiceImpl();
            Member member = null;
            for (int i = 1; i < sheet.getRows(); i++) {
                String name = sheet.getCell(0, i).getContents();
                String no = sheet.getCell(1, i).getContents();
                String phone = sheet.getCell(3, i).getContents();
                String email = sheet.getCell(4, i).getContents();
                String startTime = sheet.getCell(5, i).getContents();
                String endTime = sheet.getCell(6, i).getContents();
                String stuNo = sheet.getCell(7, i).getContents();
                int grade = 0;
                if(!"".equals(sheet.getCell(8, i).getContents()))
                    grade = Integer.parseInt(sheet.getCell(8, i).getContents());
                int classNum = 0;
                if(!"".equals(sheet.getCell(9, i).getContents()))
                    classNum = Integer.parseInt(sheet.getCell(9, i).getContents());
                String discipline = sheet.getCell(10, i).getContents();
                String gender = sheet.getCell(11, i).getContents();
                String degree = sheet.getCell(12, i).getContents();
                String id = sheet.getCell(13, i).getContents();

                if(no!=null && !"".equals(no)) {
                    if(mm.selectMemberByNo(no) != null) {
                        System.out.println(no+"-"+name+"--已存在，已更新失效日期");
                        mm.updateEndTimeByNo(no, endTime);
                    } else {
                        member = new Member();
                        member.setNo(no);
                        member.setName(name);
                        member.setStuNo(stuNo);
                        member.setPhone(phone);
                        member.setEmail(email);
                        member.setGender(gender);
                        member.setDiscipline(discipline);
                        member.setGrade(grade);
                        member.setClassNum(classNum);
                        member.setDegreeNo(mm.selectDegreeNo(degree));
                        member.setId(id);
                        member.setStartTime(startTime);
                        member.setEndTime(endTime);
                        member.setMemberTypeNo(1);
                        member.setPassword(DigestUtils.sha256Hex(no));
                        member.setPower(5);
                        try {
                            mm.addMember(member);
                            sqlSession.commit();
                        } catch (Exception e) {
                            System.out.println("出现异常："+e.getMessage());
                            list.add(name);
                        }
                    }
                }
            }
            return ResultUtils.success(list, "录入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return ResultUtils.error(1, "录入失败，可能是Excel文件问题");
    }

    /**
     * 导入CSP认证成绩信息
     * @param file
     * @return
     */
    public static Result importScore(MultipartFile file) {
        List<String> list = new ArrayList<>();
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        MemberMapper mm = sqlSession.getMapper(MemberMapper.class);
        CSPMapper cm = sqlSession.getMapper(CSPMapper.class);
        Score score = null;
        try {
            Workbook workbook = Workbook.getWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet(0);
            for (int i = 1; i < sheet.getRows(); i++) {
                String certName = sheet.getCell(0, i).getContents();
                String name = sheet.getCell(1, i).getContents();
                int all = 0;
                int first = 0;
                int second = 0;
                int third = 0;
                int forth = 0;
                int fifth = 0;
                try {
                    all = (int)Double.parseDouble(sheet.getCell(2, i).getContents());
                    first = (int)Double.parseDouble(sheet.getCell(3, i).getContents());
                    second = (int)Double.parseDouble(sheet.getCell(4, i).getContents());
                    third = (int)Double.parseDouble(sheet.getCell(5, i).getContents());
                    forth = (int)Double.parseDouble(sheet.getCell(6, i).getContents());
                    fifth = (int)Double.parseDouble(sheet.getCell(7, i).getContents());
                } catch (Exception e) {
                    System.out.println("成绩非数值："+e.getMessage());
                }
                String email = sheet.getCell(8, i).getContents();

                score = new Score();
                score.setCertNo(cm.selectCertNoByName(certName));
                score.setMemberNo(mm.selectNoByEmail(email));
                score.setAll(all);
                score.setFirst(first);
                score.setSecond(second);
                score.setThird(third);
                score.setForth(forth);
                score.setFifth(fifth);

                System.out.println(score);
                try{
                    cm.addScore(score);
                    sqlSession.commit();
                } catch (Exception e) {
                    System.out.println("出现异常："+e.getMessage());
                    list.add(name);
                }
            }
            return ResultUtils.success(list, "录入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return ResultUtils.error(1, "录入失败，可能是Excel文件问题");
    }

    /**
     * 导出报名信息表
     * @param certNo
     */
    public static File exportApplicationInfo(int certNo) {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        CSPMapper cm = sqlSession.getMapper(CSPMapper.class);
        List<Application> list = cm.selectApplicationInfo(certNo);
        String[] title = {"认证名称", "会员号", "姓名", "性别",
                "身份证号", "手机", "邮箱", "编程语言", "学历", "年级",
                "目的", "考研学校", "用户名", "密码", "照片名", "收费"};
        //创建Excel文件
//        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        path = path.substring(0, path.indexOf("/WEB-INF"));
        File file = new File("./applicate.xls");
        try {
            file.createNewFile();
            //创建工作簿
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            //创建sheet
            WritableSheet sheet = workbook.createSheet("报名信息", 0);
            Label label = null;
            //第一行设置列名
            for (int i = 0; i < title.length; i++) {
                label = new Label(i,0,title[i]);
                sheet.addCell(label);
            }
            //追加数据
            for(int i=0; i<list.size(); i++) {
                label = new Label(0, i+1, list.get(i).getName());
                sheet.addCell(label);
                label = new Label(1, i+1, list.get(i).getMemberNo());
                sheet.addCell(label);
                label = new Label(2, i+1, list.get(i).getMemberName());
                sheet.addCell(label);
                label = new Label(3, i+1, list.get(i).getGender());
                sheet.addCell(label);
                label = new Label(4, i+1, list.get(i).getId());
                sheet.addCell(label);
                label = new Label(5, i+1, list.get(i).getPhone());
                sheet.addCell(label);
                label = new Label(6, i+1, list.get(i).getEmail());
                sheet.addCell(label);
                label = new Label(7, i+1, list.get(i).getLanguage());
                sheet.addCell(label);
                label = new Label(8, i+1, list.get(i).getDegree());
                sheet.addCell(label);
                label = new Label(9, i+1, ""+list.get(i).getGrade());
                sheet.addCell(label);
                label = new Label(10, i+1, list.get(i).getPurpose());
                sheet.addCell(label);
                label = new Label(11, i+1, list.get(i).getSchool());
                sheet.addCell(label);
                label = new Label(12, i+1, list.get(i).getUsername());
                sheet.addCell(label);
                label = new Label(13, i+1, list.get(i).getPassword());
                sheet.addCell(label);
                label = new Label(14, i+1, list.get(i).getPhoto());
                sheet.addCell(label);
                label = new Label(15, i+1, "");
                sheet.addCell(label);
            }
            //写入数据
            workbook.write();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return file;
    }

    /**
     * 导入图书信息
     * @param file
     * @return
     */
    public static Result importBook(MultipartFile file) {
        List<String> list = new ArrayList<>();
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        BookMapper bm = sqlSession.getMapper(BookMapper.class);
        Book book = null;
        try {
            Workbook workbook = Workbook.getWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet(0);
            for (int i = 1; i < sheet.getRows(); i++) {
                // 图书编号
                String no = sheet.getCell(0, i).getContents();
                // 图书名
                String name = sheet.getCell(1, i).getContents();
                //
                String typeName = sheet.getCell(2, i).getContents();
                String author = sheet.getCell(3, i).getContents();
                String publicer = sheet.getCell(4, i).getContents();
                String publicDate = sheet.getCell(5, i).getContents();
                double price = 0.0;
                int inventory = 0;
                try {
                    price = Double.parseDouble(sheet.getCell(6, i).getContents());
                    inventory = Integer.parseInt(sheet.getCell(7, i).getContents());
                } catch (Exception e) {
                    System.out.println("非数值："+e.getMessage());
                }
                System.out.println(no+","+name);
                Integer typeNo = bm.selectTypeNoByName(typeName);
                if(typeNo==null) {
                    BookType bt = new BookType(typeName);
                    bm.addBookType(bt);
                    typeNo = bt.getNo();
                }
                book = new Book();
                book.setTypeNo(typeNo);
                book.setNo(no);
                book.setName(name);
                book.setAuthor(author);
                book.setPublicer(publicer);
                book.setPublicDate(publicDate);
                book.setPrice(price);
                book.setInventory(inventory);
                System.out.println(book);
                try{
                    bm.addBook(book);
                    sqlSession.commit();
                } catch (Exception e) {
                    System.out.println("出现异常："+e.getMessage());
                    list.add(book.getName());
                }
            }
            return ResultUtils.success(list, "录入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return ResultUtils.error(1, "录入失败，可能是Excel文件问题");
    }

    /**
     * 创建会员信息导入模板
     */
    public static void createMemberTemplate() {
        String[] title = {"姓名（必）", "会员号（必）", "任职单位", "手机", "邮箱（必）", "生效日期（必）",
                "失效日期（必）", "学号", "年级", "班级", "专业", "性别", "学历", "身份证号"};
        //创建Excel文件
//        File file = new File("./web/excel/templateMember.xls");
        File file = new File("./templateMember.xls");
        createTemplate(file, title);
    }

    /**
     * 创建CSP认证成绩导入模板
     */
    public static void createScoreTemplate() {
        String[] title = {"认证名称", "姓名", "总成绩", "第一题", "第二题",
                "第三题", "第四题", "第五题", "邮箱"};
//        File file = new File("./web/excel/templateScore.xls");
        File file = new File("./templateScore.xls");
        createTemplate(file, title);
    }

    /**
     * 创建图书信息导入模板
     */
    public static void createBookTemplate() {
        String[] title = {"ISBN编码", "图书名", "类别", "作者",
                "出版社", "出版时间", "价格", "库存" };
        File file = new File("./templateBook.xls");
        createTemplate(file, title);
    }

    /**
     * 创建模板
     * @param file
     * @param title
     */
    public static void createTemplate(File file, String[] title) {
        System.out.println(file.getAbsolutePath());
        try {
            file.createNewFile();
            //创建工作簿
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            //创建sheet
            WritableSheet sheet = workbook.createSheet("导入模板", 0);
            Label label = null;
            //第一行设置列名
            for (int i = 0; i < title.length; i++) {
                label = new Label(i, 0, title[i]);
                sheet.addCell(label);
            }
            //写入数据
            workbook.write();
            workbook.close();
            System.out.println("创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
