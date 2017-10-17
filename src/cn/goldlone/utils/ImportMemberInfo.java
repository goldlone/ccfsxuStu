package cn.goldlone.utils;

import java.io.File;

import cn.goldlone.dao.MemberDao;
import cn.goldlone.entity.Member;
import cn.goldlone.model.RegistInfo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 从EXCEL导入会员信息
 * @author CN
 */
public class ImportMemberInfo {
	
	public static void main(String[] args) {
//		createTemplate();

		importInfo();
	}
	
	/**
	 * 导入会员数据
	 */
	public static void importInfo() {
		try {
			Workbook workbook = Workbook.getWorkbook(new File("./res/member.xls"));
			Sheet sheet = workbook.getSheet(0);
			MemberDao dao = new MemberDao();
			Member user = null;
			for (int i = 1; i < sheet.getRows(); i++) {
				user = new Member();
				user.setNo(sheet.getCell(0, i).getContents());
				user.setName(sheet.getCell(1, i).getContents());
				user.setStuNo(sheet.getCell(2, i).getContents());
				user.setPhone(sheet.getCell(3, i).getContents());
				user.setEmail(sheet.getCell(4, i).getContents());
				user.setDiscipline(sheet.getCell(5, i).getContents());
				// 年级
				String grade = sheet.getCell(6, i).getContents();
				if("".equals(grade))
					user.setGrade(0);
				else
					user.setGrade(Integer.parseInt(grade));
				// 班级
				String classNum = sheet.getCell(7, i).getContents();
				if("".equals(classNum))
					user.setClassNum(0);
				else
					user.setClassNum(Integer.parseInt(classNum));
				// 学历编号
				String degreeNo = sheet.getCell(8, i).getContents();
				if("".equals(degreeNo))
					user.setDegreeNo(0);
				else
					user.setDegreeNo(Integer.parseInt(degreeNo));
				
				user.setId(sheet.getCell(9, i).getContents());
				user.setStartTime(sheet.getCell(10, i).getContents());
				user.setEndTime(sheet.getCell(11, i).getContents());
				// 会员类型编号
				user.setMemberTypeNo(0);
				// 默认密码为会员号
				user.setPasswd(user.getNo());
				// 身份权限
				user.setPower(0);
				RegistInfo info = dao.addMember(user);
				System.out.println(info.getInfo());
				System.out.println(user.toString());
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createTemplate() {
		String[] title = {"会员号","姓名","学号", "手机", "邮箱", "专业", "年级", 
				"班级", "学历", "身份证号", "生效日期", "失效日期"};
		//创建Excel文件
		File file = new File("./res/member.xls");
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
			//追加数据
//			for (int i = 1; i < 10; i++) {
//				label = new Label(0,i,"a" + 1);
//				sheet.addCell(label);
//				label = new Label(1,i,"user" + i);
//				sheet.addCell(label);
//				label = new Label(2,i,"男");
//				sheet.addCell(label);
//			}
			//写入数据
			workbook.write();
			workbook.close();
			System.out.println("创建成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
