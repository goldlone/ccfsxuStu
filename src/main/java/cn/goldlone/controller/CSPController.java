package cn.goldlone.controller;

import cn.goldlone.model.Result;
import cn.goldlone.po.Certification;
import cn.goldlone.service.impl.CSPServiceImpl;
import cn.goldlone.utils.ExcelUtils;
import cn.goldlone.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by CN on 2018/1/8.
 */
@RestController
public class CSPController extends BaseController {

    @Autowired
    private CSPServiceImpl cs;


    /**
     * 获取CSP认证集合
     * @return
     */
    @PostMapping("/csp/certSet")
    public Result getCertSet() {
        return cs.getCertSet();
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
        return cs.queryScore(certNo, lowScore, highScore);
    }


    /**
     * 获取某个会员的成绩
     * @param memberNo
     * @return
     */
    @PostMapping("/csp/ScoreByMember")
    public Result getScoreByMemberNo(String memberNo) {
        return cs.getScoreByMemberNo(memberNo);
    }


    /**
     * 查询自身的成绩，用于分析
     * @param request
     * @return
     */
    @PostMapping("/csp/memberScore")
    public Result getScoreByMemberNo1(HttpServletRequest request) {
//        String memberNo = (String) request.getSession().getAttribute("memberNo");
        String memberNo = "62151G";

        return cs.getScoreByMemberNo1(memberNo);
    }


    /**
     * 获取某次CSP认证的成绩单
     * @param certNo
     * @return
     */
    @PostMapping("/csp/certScore")
    public Result getScoreByCertNo(int certNo) {
        return cs.getScoreByCertNo(certNo);
    }


    /**
     * 获取历次CSP的三围属性
     * @return
     */
    @PostMapping("/csp/certThreeScore")
    public Result getCertThreeScore() {
        return cs.getCertThreeScore();
    }


    /**
     * 文件录入CSP成绩
     * @param file
     * @return
     */
    @PostMapping("/csp/addScoreFile")
    public Result uploadCSPScore(@RequestParam("file") MultipartFile file) {
        return cs.ImportScoreInfo(ExcelUtils.importScore(file));
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
            bis = new BufferedInputStream(new FileInputStream(ExcelUtils.exportApplicationInfo(certNo)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            result = ResultUtil.error(1, "异常："+e.getMessage());
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
        return cs.addCertification(cert);
    }



}
