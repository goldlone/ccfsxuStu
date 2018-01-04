package cn.goldlone.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.goldlone.model.UserInfo;
import cn.goldlone.po.Member;
import cn.goldlone.service.MemberService;
import cn.goldlone.service.impl.MemberServiceImpl;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created by CN on 2018/1/4.
 */
public class ExcelUtils {

    public static void main(String[] args) {
//        new ExcelUtils().createTemplate();
//        new ExcelUtils().importMemberInfo(new File("./web/excel/templateMember.xls"));
    }

    /**
     * 导入会员信息
     * @param file
     * @return
     */
    public boolean importMemberInfo(File file) {
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(0);
            MemberService memberService = new MemberServiceImpl();
            List<UserInfo> list = new ArrayList<UserInfo>();
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

                UserInfo member = new UserInfo();
                member.setName(name);
                member.setNo(no);
                member.setPhone(phone);
                member.setEmail(email);
                member.setStartTime(startTime);
                member.setEndTime(endTime);
                member.setStuNo(stuNo);
                member.setDiscipline(discipline);
                member.setGender(gender);
                member.setGrade(grade);
                member.setClassNum(classNum);
                member.setDegree(degree);
                member.setId(id);
                list.add(member);
            }
            System.out.println(list.size());
            System.out.println(memberService.addMemberByUserInfo(list));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 创建会员信息导入模板
     */
    public void createTemplate() {
        String[] title = {"姓名（必）", "会员号（必）", "任职单位", "手机", "邮箱（必）", "生效日期（必）",
                "失效日期（必）", "学号", "年级", "班级", "专业", "性别", "学历", "身份证号"};
        //创建Excel文件
//        File file = new File("./web/excel/templateMember.xls");
        File file = new File("./web/excel/templateMember.xls");
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
