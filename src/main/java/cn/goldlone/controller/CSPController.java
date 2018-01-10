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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by CN on 2018/1/8.
 */
@RestController
public class CSPController {
    private SqlSession sqlSession = MybatisUtils.openSqlSession();
    private CSPMapper cm = sqlSession.getMapper(CSPMapper.class);


    /**
     * 获取CSP认证集合
     * @return
     */
    @PostMapping("/csp/certSet")
    public Result getCertSet() {
        Result result = null;
        try {
            result = ResultUtils.success(cm.getCertSet(), "获取CSP认证集合成功");
//            List<Certification> list = cm.getCertSet();
//            for(Certification cert: list)
//                System.out.println(cert);
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
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
        Result result = null;
        try {
            result = ResultUtils.success(cm.queryScore(certNo, lowScore, highScore), "查询CSP成绩成功");
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
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
        Result result = null;
        try {
            result = ResultUtils.success(cm.selectScoreByMemberNo(memberNo), "查询CSP成绩成功");
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
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
        Result result = null;
        String memberNo = (String) request.getSession().getAttribute("memberNo");
//        if(memberNo==null || "".equals(memberNo)) {
//            // 重新登录
//            return null;
//        }
        memberNo = "62151G";
        try{
            List<ScoreInfo> list = cm.selectScoreByMemberNo(memberNo);
            ScoreInfo info = null;
            for(int i=0; i<list.size(); i++) {
                info = list.get(i);
                info.setMax(cm.selectMaxScoreByCertNo(info.getCertNo()));
                info.setAverage(cm.selectAverageScoreByCertNo(info.getCertNo()));
                info.setMin(cm.selectMinScoreByCertNo(info.getCertNo()));
            }
            result = ResultUtils.success(list, "获取成绩成功");
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
            e.printStackTrace();
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
        try{
            List<ScoreInfo> list = cm.selectScoreByNo(certNo);
            ScoreInfo info = null;
            SingleScore max = cm.selectMaxScoreByCertNo(certNo);
            SingleScore average = cm.selectAverageScoreByCertNo(certNo);
            SingleScore min = cm.selectMinScoreByCertNo(certNo);
            for(int i=0; i<list.size(); i++) {
                info = list.get(i);
                info.setMax(max);
                info.setAverage(average);
                info.setMin(min);
            }
            result = ResultUtils.success(list, "获取成绩成功");
        } catch (Exception e) {
            result = ResultUtils.error(1, "异常："+e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

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




}
