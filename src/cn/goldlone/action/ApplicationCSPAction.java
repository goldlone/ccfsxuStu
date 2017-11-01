package cn.goldlone.action;

import cn.goldlone.dao.CSPDao;
import cn.goldlone.entity.ApplicationInfo;
import cn.goldlone.entity.Certification;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by CN on 2017/10/31.
 */
public class ApplicationCSPAction extends ActionSupport implements ModelDriven<ApplicationInfo>{

    private ApplicationInfo info = new ApplicationInfo();
    private CSPDao dao = new CSPDao();

    /**
     * 接收并存储报名信息
     * @return
     * @throws IOException
     */
    public String applicate() throws IOException {
        System.out.println(info.toString());
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
        boolean ret = dao.insertApplication(info);
        if(ret)
            res.put("ret", true);
        else
            res.put("ret", false);
        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 获取未开始CSP认证集合
     * @return
     * @throws IOException
     */
    public String getCertSetNotStart() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        JSONObject res = new JSONObject();
        res.put("ret", true);
        List<Certification> list = dao.getCertSetNotStart();
        res.put("len", list.size());
        res.put("data", new JSONArray(list));
        out.print(res.toString());
        out.flush();
        out.close();

        return null;
    }

    @Override
    public ApplicationInfo getModel() {
        return this.info;
    }
}
