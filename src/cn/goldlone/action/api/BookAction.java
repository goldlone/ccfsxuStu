package cn.goldlone.action.api;

import cn.goldlone.dao.BookDao;
import cn.goldlone.entity.BookType;
import cn.goldlone.entity.BorrowBook;
import cn.goldlone.entity.Certification;
import cn.goldlone.model.BookInfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CN on 2017/10/17.
 */
public class BookAction extends ActionSupport {
    private BookDao dao = new BookDao();
    private int typeNo;
    private String bookName;
    private String isbn;
    private String memberNo;
    private int no;

    /**
     * 获取图书类别
     * @return
     * @throws IOException
     */
    public String getBookType() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
        List<BookType> list = dao.getBookType();

        res.put("ret", true);
        res.put("len", list.size());
        res.put("data", new JSONArray(list));

        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 根据类别获取图书信息
     * @return
     * @throws IOException
     */
    public String selectBookByType() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
        ArrayList<BookInfo> list = dao.selectBookByType(typeNo);
        res.put("ret", true);
        res.put("len", list.size());
        res.put("data", new JSONArray(list));

        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 根据名字获取图书信息
     * @return
     * @throws IOException
     */
    public String selectBookByName() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
        ArrayList<BookInfo> list = dao.selectBookByName(bookName);
        res.put("ret", true);
        res.put("len", list.size());
        res.put("data", new JSONArray(list));

        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 根据ISBN编号获取图书信息
     * @return
     * @throws IOException
     */
    public String selectBookByISBN() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
        BookInfo info = dao.selectBookByISBN(isbn);
        res.put("ret", true);
        System.out.println(new JSONObject(info).toString());
        res.put("data", new JSONObject(info));

        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 借书
     * @return
     */
    public String borrowBook() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();

        BorrowBook book = new BorrowBook();
        book.setBookNo(isbn);
        book.setMemberNo(memberNo);

        boolean ret = dao.borrowBook(book);
        res.put("ret", ret);
        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 还书
     * @return
     */
    public String backBook() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();

        BorrowBook book = new BorrowBook();
        book.setNo(no);
        book.setBookNo(isbn);
        boolean ret = dao.backBook(book);
        res.put("ret", ret);
        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 查询库存
     * @return
     * @throws IOException
     */
    public String searchInventory() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
        int num = dao.selectInventory(isbn);
        if(num == Integer.MAX_VALUE) {
            res.put("ret", false);
            res.put("num", 0);
        } else {
            res.put("ret", true);
            res.put("num", num);
        }
        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 查询某本书的尚未归还的借阅记录
     * @return
     * @throws IOException
     */
    public String searchOrder() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
        res.put("ret", true);
        ArrayList<BorrowBook> list = dao.selectOrder(isbn);
        res.put("data", new JSONArray(list));
        res.put("len", list.size());
        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 添加图书
     * @return
     */
    public String addBookInfo() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
//        res.put("ret", true);
//        ArrayList<BorrowBook> list = dao.selectOrder(isbn);
//        res.put("data", new JSONArray(list));
//        res.put("len", list.size());
        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    public int getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(int typeNo) {
        this.typeNo = typeNo;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
