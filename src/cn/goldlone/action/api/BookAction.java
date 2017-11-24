package cn.goldlone.action.api;

import cn.goldlone.dao.BookDao;
import cn.goldlone.entity.BookType;
import cn.goldlone.entity.Certification;
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
}
