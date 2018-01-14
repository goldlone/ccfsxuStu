package cn.goldlone.controller;

import cn.goldlone.mapper.CSPMapper;
import cn.goldlone.model.Result;
import cn.goldlone.model.ScoreInfo;
import cn.goldlone.model.SingleScore;
import cn.goldlone.po.Certification;
import cn.goldlone.utils.ExcelUtils;
import cn.goldlone.utils.MybatisUtils;
import cn.goldlone.utils.ResultUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CN on 2018/1/8.
 */
@RestController
public class CSPController {
    private SqlSession sqlSession = null;
    private CSPMapper cm = null;


    /**
     * 获取CSP认证集合
     * @return
     */
    @PostMapping("/csp/certSet")
    public Result getCertSet() {
        sqlSession = MybatisUtils.openSqlSession();
        cm = sqlSession.getMapper(CSPMapper.class);

        Result result = null;
        try {
            result = ResultUtils.success(cm.getCertSet(), "获取CSP认证集合成功");
//            List<Certification> list = cm.getCertSet();
//            for(Certification cert: list)
//                System.out.println(cert);
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 查询成绩
     * @param certNo
     * @param lowScore
     * @param highScore
     * @return
     */
    @PostMapping("/csp/queryScore")
    public Result queryScore(Integer certNo, Integer lowScore, Integer highScore) {
        sqlSession = MybatisUtils.openSqlSession();
        cm = sqlSession.getMapper(CSPMapper.class);
        Result result = null;
        try {
            result = ResultUtils.success(cm.queryScore(certNo, lowScore, highScore), "查询CSP成绩成功");
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 获取某个会员的成绩
     * @param memberNo
     * @return
     */
    @PostMapping("/csp/ScoreByMember")
    public Result getScoreByMemberNo(String memberNo) {
        sqlSession = MybatisUtils.openSqlSession();
        cm = sqlSession.getMapper(CSPMapper.class);
        Result result = null;
        try {
            result = ResultUtils.success(cm.selectScoreByMemberNo(memberNo), "查询CSP成绩成功");
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 获取某个会员的成绩信息
     * @param request
     * @return
     */
    @PostMapping("/csp/memberScore")
    public Result getScoreByMemberNo(HttpServletRequest request) {
        sqlSession = MybatisUtils.openSqlSession();
        cm = sqlSession.getMapper(CSPMapper.class);
        Result result = null;
        String memberNo = (String) request.getSession().getAttribute("memberNo");
//        if(memberNo==null || "".equals(memberNo)) {
//            // 重新登录
//            return null;
//        }
        memberNo = "62151G";
        try{
            List<ScoreInfo> list = cm.selectScoreByMemberNo(memberNo);
            for (ScoreInfo info : list) {
                System.out.println(info);
                SingleScore ss = cm.selectMaxScoreByCertNo(info.getCertNo());
                if (ss == null)
                    info.setMax(new SingleScore());
                else
                    info.setMax(ss);
                ss = cm.selectAverageScoreByCertNo(info.getCertNo());
                if (ss == null)
                    info.setAverage(new SingleScore());
                else
                    info.setAverage(ss);
                ss = cm.selectMinScoreByCertNo(info.getCertNo());
                if (ss == null)
                    info.setMin(new SingleScore());
                else
                    info.setMin(ss);
            }
            result = ResultUtils.success(list, "获取成绩成功");
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 获取某次CSP认证的成绩单
     * @param request
     * @param certNo
     * @return
     */
    @PostMapping("/csp/certScore")
    public Result getScoreByCertNo(HttpServletRequest request, int certNo) {
        Result result = null;
        // 检测是否登录

        // 检测权限

        // 查询成绩
        sqlSession = MybatisUtils.openSqlSession();
        cm = sqlSession.getMapper(CSPMapper.class);
        try{
            System.out.println("QAQ:"+certNo);
            List<ScoreInfo> list = cm.selectScoreByNo(certNo);
            SingleScore max = cm.selectMaxScoreByCertNo(certNo);
            SingleScore average = cm.selectAverageScoreByCertNo(certNo);
            SingleScore min = cm.selectMinScoreByCertNo(certNo);
            if(max==null)
                max = new SingleScore();
            if(average==null)
                average = new SingleScore();
            if(min==null)
                min = new SingleScore();
            for (ScoreInfo info : list) {
                info.setMax(max);
                info.setAverage(average);
                info.setMin(min);
            }
            result = ResultUtils.success(list, "获取成绩成功");
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
            e.printStackTrace();
        } finally {
           sqlSession.close();
        }
        return result;
    }

    /**
     * 获取历次CSP的三围属性
     * @return
     */
    @PostMapping("/csp/certThreeScore")
    public Result getCertThreeScore() {
        Result result = null;
        sqlSession = MybatisUtils.openSqlSession();
        cm = sqlSession.getMapper(CSPMapper.class);
        try{
            List<ScoreInfo> list = new ArrayList<>();
            ScoreInfo info = null;
            List<Certification> certList = cm.getCertSet();
            SingleScore max = null;
            SingleScore average = null;
            SingleScore min = null;
            for(Certification cert: certList) {
                info = new ScoreInfo();
                info.setCertNo(cert.getNo());
                info.setCertName(cert.getName());
                max = cm.selectMaxScoreByCertNo(cert.getNo());
                average = cm.selectAverageScoreByCertNo(cert.getNo());
                min = cm.selectMinScoreByCertNo(cert.getNo());
                if(max==null)
                    max = new SingleScore();
                if(average==null)
                    average = new SingleScore();
                if(min==null)
                    min = new SingleScore();
                info.setMax(max);
                info.setAverage(average);
                info.setMin(min);
                list.add(info);
            }
            result = ResultUtils.success(list, "查询成功");
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

//    public Result

    /**
     * 文件录入CSP成绩
     * @param file
     * @return
     */
    @PostMapping("/csp/addScoreFile")
    public Result uploadCSPScore(@RequestParam("file") MultipartFile file) {
        Result result = ExcelUtils.importScore(file);
        return result;
    }

    /**
     * 下载CSP报名信息
     * @param res
     * @param certNo
     * @return
     */
    @GetMapping("/csp/downLoadApplication")
    public Result downLoadApplication(HttpServletResponse res, int certNo) {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + "applicate.xls");
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        Result result = null;
        try {
            os = res.getOutputStream();
            File file = ExcelUtils.exportApplicationInfo(certNo);
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 添加CSP认证
     * @param cert
     * @return
     */
    @PostMapping("/csp/addCertification")
    public Result addCertification(Certification cert) {
        sqlSession = MybatisUtils.openSqlSession();
        cm = sqlSession.getMapper(CSPMapper.class);
        Result result = null;
        try {
            result = ResultUtils.success(cm.addCert(cert), "查询CSP成绩成功");
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }



}
