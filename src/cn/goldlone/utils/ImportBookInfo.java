package cn.goldlone.utils;

import cn.goldlone.dao.BookDao;
import cn.goldlone.model.BookInfo;
import cn.goldlone.model.ReturnInfo;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.IOException;

/**
 * 文件导入图书信息
 * Created by CN on 2017/11/26.
 */
public class ImportBookInfo {
    public static void main(String[] args) {
//        new ImportBookInfo().createTemplate();
        new ImportBookInfo().importBook(new File("./web/excel/templateBook.xls"));
    }


    /**
     * 导入图书信息
     * @param file
     * @return
     */
    public boolean importBook(File file) {
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(0);
            BookDao dao = new BookDao();
            for(int i=1; i<sheet.getRows(); i++) {
                String isbn = sheet.getCell(0, i).getContents();
                String bookName = sheet.getCell(1, i).getContents();;
                String type = sheet.getCell(2, i).getContents();;
                String author = sheet.getCell(3, i).getContents();;
                String publicer = sheet.getCell(4, i).getContents();;
                String publicDate = sheet.getCell(5, i).getContents();;
                Double price = Double.parseDouble(sheet.getCell(6, i).getContents());;
                int inventory = Integer.parseInt(sheet.getCell(7, i).getContents());;
                BookInfo book = new BookInfo();
                book.setNo(isbn);
                book.setName(bookName);
                book.setType(type);
                book.setAuthor(author);
                book.setPublicer(publicer);
                book.setPublicDate(publicDate);
                book.setPrice(price);
                book.setInventory(inventory);
                ReturnInfo info = dao.addBook(book);
                if(info.isSuccess()) {
                    System.out.println(i+"添加成功:"+book);
                } else {
                    System.out.println(i+"添加失败:"+isbn+" "+bookName+info.getInfo());
                }
            }
            System.out.println("导入完毕");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建导入模板
     */
    public void createTemplate() {
        String[] title = {"ISBN编号", "书名", "类别", "作者", "出版社", "出版时间", "单价", "库存"};
        //创建Excel文件
        File file = new File("./web/excel/templateBook.xls");
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
