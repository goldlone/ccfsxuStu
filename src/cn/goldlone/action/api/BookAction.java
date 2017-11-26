package cn.goldlone.action.api;

import cn.goldlone.dao.BookDao;
import cn.goldlone.entity.BookType;
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
}
