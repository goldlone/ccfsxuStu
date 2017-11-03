package cn.goldlone.utils;

import cn.goldlone.dao.CSPDao;
import cn.goldlone.entity.ApplicationInfo;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.util.ArrayList;

import java.io.File;

/**
 * Created by CN on 2017/11/2.
 */
public class ExportApplicationExcel {
    public static void main(String[] args) {
//        new ExportApplicationExcel().exportInfo(0);
    }

    /**
     * 导出报名信息表
     * @param certNo
     */
    public void exportInfo(int certNo) {
        ArrayList<ApplicationInfo> list = new CSPDao().selectApplicationInfo(certNo);
        String[] title = {"认证编号", "会员号", "姓名",
                "身份证号", "手机", "邮箱", "语言", "身份",
                "目的", "考研学校", "用户名", "密码", "照片名", "收费"};
        //创建Excel文件
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        path = path.substring(0, path.indexOf("/WEB-INF"));
        File file = new File(path+"/excel/applicate.xls");
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
                label = new Label(0, i+1, list.get(i).getCertNo());
                sheet.addCell(label);
                label = new Label(1, i+1, list.get(i).getMemberNo());
                sheet.addCell(label);
                label = new Label(2, i+1, list.get(i).getName());
                sheet.addCell(label);
                label = new Label(3, i+1, list.get(i).getId());
                sheet.addCell(label);
                label = new Label(4, i+1, list.get(i).getPhone());
                sheet.addCell(label);
                label = new Label(5, i+1, list.get(i).getEmail());
                sheet.addCell(label);
                label = new Label(6, i+1, list.get(i).getLanguage());
                sheet.addCell(label);
                label = new Label(7, i+1, list.get(i).getDegree());
                sheet.addCell(label);
                label = new Label(8, i+1, list.get(i).getPurpose());
                sheet.addCell(label);
                label = new Label(9, i+1, list.get(i).getSchool());
                sheet.addCell(label);
                label = new Label(10, i+1, list.get(i).getUsername());
                sheet.addCell(label);
                label = new Label(11, i+1, list.get(i).getPassword());
                sheet.addCell(label);
                label = new Label(12, i+1, list.get(i).getPhotoName());
                sheet.addCell(label);
                label = new Label(13, i+1, "");
                sheet.addCell(label);
            }
            //写入数据
            workbook.write();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
