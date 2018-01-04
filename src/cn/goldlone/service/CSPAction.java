//package cn.goldlone.service;
//
//import cn.goldlone.dao.CSPDao;
//import cn.goldlone.entity.Certification;
//import cn.goldlone.model.ScoreInfo;
//import cn.goldlone.utils.ImportCSPScore;
//import com.opensymphony.xwork2.ActionSupport;
//import com.opensymphony.xwork2.ModelDriven;
//import org.apache.struts2.ServletActionContext;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * CSP相关操作
// * Created by CN on 2017/10/17.
// */
//public class CSPAction  extends ActionSupport implements ModelDriven<Certification>{
//
//    // 会员号
//    private String no;
//    // 认证编号
//    private int certNo;
//    // 成绩区间
//    private int lowScore;
//    private int highScore;
//    // 成绩单excel
//    private File scoreFile;
//
//    private Certification cert = new Certification();
//    private CSPDao dao = new CSPDao();
//    /**
//     * 获取认证名集合
//     * @return
//     */
//    public String getCertSet() throws IOException {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpServletResponse response = ServletActionContext.getResponse();
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        JSONObject res = new JSONObject();
//        ArrayList<Certification> list = dao.getCertSet();
//
//        res.put("ret", true);
//        res.put("len", list.size());
//        res.put("data", new JSONArray(list));
//
//        out.print(res.toString());
//        out.flush();
//        out.close();
//        return null;
//    }
//
//    /**
//     * 根据会员号查询成绩
//     * @return
//     * @throws IOException
//     */
//    public String getScoreByNo() throws IOException{
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpServletResponse response = ServletActionContext.getResponse();
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        JSONObject res = new JSONObject();
//
//        System.out.println("会员号"+this.getNo());
//        if(no!=null) {
//            List<ScoreInfo> list = dao.selectScoreByMemberNo(no);
//            res.put("ret", true);
//            res.put("len", list.size());
//            res.put("data", new JSONArray(list));
//        } else {
//            res.put("ret", false);
//        }
//
//        out.print(res.toString());
//        out.flush();
//        out.close();
//        return null;
//    }
//
//    /**
//     * 查询成绩
//     * @return
//     * @throws IOException
//     */
//    public String queryScore() throws IOException {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpServletResponse response = ServletActionContext.getResponse();
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        JSONObject res = new JSONObject();
//
//        System.out.println(certNo+" "+lowScore+" "+highScore+" ");
//        List<ScoreInfo> list = dao.queryScore(certNo, lowScore, highScore);
//
//        res.put("ret", true);
//        res.put("len", list.size());
//        res.put("data", new JSONArray(list));
//
//        out.print(res.toString());
//        out.flush();
//        out.close();
//        return null;
//    }
//
//    /**
//     * 添加CSP认证考试
//     * @return
//     * @throws IOException
//     */
//    public String addCertification() throws IOException {
//        HttpServletResponse response = ServletActionContext.getResponse();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//        PrintWriter out = response.getWriter();
//        JSONObject res = new JSONObject();
//        boolean ret = dao.insertCert(cert);
//        res.put("ret", ret);
//        out.print(res.toString());
//        out.flush();
//        out.close();
//        return null;
//    }
//
//    /**
//     * 接收CSP成绩excel文件
//     * @return
//     */
//    public String receiveCSPScoreFile() throws IOException {
//        HttpServletResponse response = ServletActionContext.getResponse();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//        PrintWriter out = response.getWriter();
//        JSONObject res = new JSONObject();
//
//        if(certNo == 0)
//            res.put("ret", false);
//        else {
//            boolean ret = ImportCSPScore.importInfo(scoreFile, certNo);
//            res.put("ret", ret);
//        }
//        out.print(res.toString());
//        out.flush();
//        out.close();
//        return null;
//    }
//
//
//    public String getNo() {
//        return no;
//    }
//
//    public void setNo(String no) {
//        this.no = no;
//    }
//
//    public int getCertNo() {
//        return certNo;
//    }
//
//    public void setCertNo(int certNo) {
//        this.certNo = certNo;
//    }
//
//    public int getLowScore() {
//        return lowScore;
//    }
//
//    public void setLowScore(int lowScore) {
//        this.lowScore = lowScore;
//    }
//
//    public int getHighScore() {
//        return highScore;
//    }
//
//    public void setHighScore(int highScore) {
//        this.highScore = highScore;
//    }
//
//    public File getScoreFile() {
//        return scoreFile;
//    }
//
//    public void setScoreFile(File scoreFile) {
//        this.scoreFile = scoreFile;
//    }
//
//    @Override
//    public Certification getModel() {
//        return this.cert;
//    }
//}
