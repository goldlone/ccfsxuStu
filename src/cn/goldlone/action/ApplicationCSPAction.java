package cn.goldlone.action;

import cn.goldlone.dao.CSPDao;
import cn.goldlone.entity.ApplicationInfo;
import cn.goldlone.entity.Certification;
import cn.goldlone.utils.ExportApplicationExcel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * Created by CN on 2017/10/31.
 */
public class ApplicationCSPAction extends ActionSupport implements ModelDriven<ApplicationInfo>{

    private ApplicationInfo info = new ApplicationInfo();
    private CSPDao dao = new CSPDao();
    private File photo;

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

    /**
     * 上传照片
     * @return
     * @throws IOException
     */
    public String uploadPhoto() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        System.out.println(info);
        JSONObject res = new JSONObject();
        if(info.getMemberNo()==null || "".equals(info.getMemberNo())) {
            res.put("ret", false);
        }
        else {
            String path = ServletActionContext.getServletContext().getRealPath("/photos");
            String filename = info.getMemberNo()+".jpg";
            OutputStream os = new FileOutputStream(new File(path, filename));
            InputStream is = new FileInputStream(photo);
            byte[] buf = new byte[1024];
            int length = 0 ;
            while(-1 != (length = is.read(buf))) {
                os.write(buf, 0, length) ;
            }
            is.close();
            os.close();
            boolean ret = dao.updateFile(info.getMemberNo(), filename);
            res.put("ret", ret);
        }
        System.out.println(res.toString());
        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 导出报名信息表
     * @return
     * @throws IOException
     */
    public String getApplication() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();
        try {
            new ExportApplicationExcel().exportInfo(Integer.parseInt(info.getCertNo()));
            res.put("ret", true);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("ret", false);
        }

        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    @Override
    public ApplicationInfo getModel() {
        return this.info;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }
}
