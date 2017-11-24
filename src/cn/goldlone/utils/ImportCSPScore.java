package cn.goldlone.utils;

import cn.goldlone.dao.CSPDao;
import cn.goldlone.dao.MemberDao;
import cn.goldlone.entity.Score;
import cn.goldlone.model.UserInfo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;

/**
 * Created by CN on 2017/11/18.
 */
public class ImportCSPScore {
    public static void main(String[] args) {
//        createTemplate();
//        importInfo(new File("./web/excel/第十一次CCF计算机软件能力认证.xls"), 11);
    }

    /**
     * 导入CSP成绩信息
     * @param file
     */
    public static boolean importInfo(File file, int certNo) {
//        String fileName = file.getName();
//        String certNo = fileName.substring(0, fileName.lastIndexOf('.'));
        CSPDao cspDao = new CSPDao();
//        int certNo = cspDao.selectCertNoByName(certName);
        try {
            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(0);
            MemberDao memberDao = new MemberDao();
            for (int i = 1; i < sheet.getRows(); i++) {
                String email = sheet.getCell(10, i).getContents();
                String no = memberDao.selectNoByEmail(email);
                if(no == null) {
                    System.out.println("无会员号");
                    continue;
                }
                Score score = new Score();
                score.setCertNo(certNo);
                score.setMemberNo(no);
                // 成绩
                int totalScore = (int)Double.parseDouble(sheet.getCell(2, i).getContents());
                int first = (int)Double.parseDouble(sheet.getCell(3, i).getContents());
                int second = (int)Double.parseDouble(sheet.getCell(4, i).getContents());
                int third = (int)Double.parseDouble(sheet.getCell(5, i).getContents());
                int forth = (int)Double.parseDouble(sheet.getCell(6, i).getContents());
                int fifth = (int)Double.parseDouble(sheet.getCell(7, i).getContents());
                score.setAll(totalScore);
                score.setFirst(first);
                score.setSencond(second);
                score.setThird(third);
                score.setForth(forth);
                score.setFifth(fifth);

                String language = sheet.getCell(8, i).getContents();
                score.setLanguage(language);
                cspDao.addScore(score);
                String name = sheet.getCell(0, i).getContents();
                String phone = sheet.getCell(9, i).getContents();
                UserInfo info = memberDao.selectMemberByNo(no).get(0);
                if(info.getPhone() == null || "".equals(info.getPhone()))
                    memberDao.updatePhoneByNo(no, phone);
                System.out.println("第"+i+"个");
//                System.out.println(no+" "+name+totalScore+first+second+third+forth+fifth+language+phone+email);
            }
            workbook.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void createTemplate() {
        String[] title = {"会员号","姓名","学号", "手机", "邮箱", "专业", "年级",
                "班级", "学历", "身份证号", "生效日期", "失效日期"};
        //创建Excel文件
        File file = new File("./web/excel/score.xls");
        System.out.println(file.getAbsoluteFile());
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
