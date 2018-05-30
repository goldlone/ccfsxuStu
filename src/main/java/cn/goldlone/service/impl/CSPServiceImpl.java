package cn.goldlone.service.impl;


import cn.goldlone.mapper.CSPMapper;
import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.ImportScore;
import cn.goldlone.model.Result;
import cn.goldlone.model.ScoreInfo;
import cn.goldlone.model.SingleScore;
import cn.goldlone.po.Certification;
import cn.goldlone.po.Score;
import cn.goldlone.utils.ExcelUtils;
import cn.goldlone.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CN on 2018/1/6.
 */
@Service
public class CSPServiceImpl {

    @Autowired
    private CSPMapper cm;
    @Autowired
    private MemberMapper mm;


    /**
     * 获取CSP认证集合
     * @return
     */
    public Result getCertSet() {
        return ResultUtil.success(cm.getCertSet(), "获取CSP认证集合成功");
    }

    /**
     * 查询成绩
     * @param certNo
     * @param lowScore
     * @param highScore
     * @return
     */
    public Result queryScore(Integer certNo, Integer lowScore, Integer highScore) {
        return ResultUtil.success(cm.queryScore(certNo, lowScore, highScore), "查询CSP成绩成功");
    }

    /**
     * 获取某个会员的成绩
     * @param memberNo
     * @return
     */
    public Result getScoreByMemberNo(String memberNo) {
        return ResultUtil.success(cm.selectScoreByMemberNo(memberNo), "查询CSP成绩成功");
    }

    /**
     * 查询自身的成绩，用于分析
     * @param memberNo
     * @return
     */
    public Result getScoreByMemberNo1(String memberNo) {
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
       return ResultUtil.success(list, "获取成绩成功");
    }

    /**
     * 获取某次CSP认证的成绩单
     * @param certNo
     * @return
     */
    public Result getScoreByCertNo(int certNo) {
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
        return ResultUtil.success(list, "获取成绩成功");
    }

    /**
     * 获取历次CSP的三围属性
     * @return
     */
    public Result getCertThreeScore() {
        List<ScoreInfo> list = new ArrayList<>();
        List<Certification> certList = cm.getCertSet();
        ScoreInfo info = null;
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
        return ResultUtil.success(list, "查询成功");
    }


    /**
     * 添加CSP认证
     * @param cert
     * @return
     */
    public Result addCertification(Certification cert) {
        System.out.println(cert);
        return ResultUtil.success(cm.addCert(cert), "查询CSP成绩成功");
    }



    /**
     * 使用导入成绩信息，批量添加CSP认证成绩
     * @param list
     * @return
     */
    public Result ImportScoreInfo(List<ImportScore> list) {
        List<String> errors = new ArrayList<>();
        int certNo = 0;
        String memberNo = null;
        Score s = null;
        for(ImportScore score: list) {
            try {
                certNo = cm.selectCertNoByName(score.getCertName());
                memberNo = mm.selectNoByEmail(score.getEmail());
                cm.addScore(new Score(score, certNo, memberNo));
            } catch (Exception e) {
                e.printStackTrace();
                errors.add(score.getName()+" - "+score.getEmail());
            }
        }
        return ResultUtil.success(errors, "录入成功");
    }



//    public Result getScoreByCertNo(int certNo) {
//        Result result = null;
//        // 查询成绩
//        try{
//            System.out.println("QAQ:"+certNo);
//            List<ScoreInfo> list = cm.selectScoreByNo(certNo);
//            SingleScore max = cm.selectMaxScoreByCertNo(certNo);
//            SingleScore average = cm.selectAverageScoreByCertNo(certNo);
//            SingleScore min = cm.selectMinScoreByCertNo(certNo);
//            if(max==null)
//                max = new SingleScore();
//            if(average==null)
//                average = new SingleScore();
//            if(min==null)
//                min = new SingleScore();
//            for (ScoreInfo info : list) {
//                info.setMax(max);
//                info.setAverage(average);
//                info.setMin(min);
//            }
//            result = ResultUtil.success(list, "获取成绩成功");
//        } catch (Exception e) {
//            result = ResultUtil.error(1, "异常："+e.getMessage());
//            e.printStackTrace();
//        } finally {
//            sqlSession.close();
//        }
//        return result;
//
//
//    }
//
//
//    public Result getCertThreeScore() {
//        Result result = null;
//        sqlSession = MybatisUtils.openSqlSession();
//        cm = sqlSession.getMapper(CSPMapper.class);
//        try{
//            List<ScoreInfo> list = new ArrayList<>();
//            ScoreInfo info = null;
//            List<Certification> certList = cm.getCertSet();
//            SingleScore max = null;
//            SingleScore average = null;
//            SingleScore min = null;
//            for(Certification cert: certList) {
//                info = new ScoreInfo();
//                info.setCertNo(cert.getNo());
//                info.setCertName(cert.getName());
//                max = cm.selectMaxScoreByCertNo(cert.getNo());
//                average = cm.selectAverageScoreByCertNo(cert.getNo());
//                min = cm.selectMinScoreByCertNo(cert.getNo());
//                if(max==null)
//                    max = new SingleScore();
//                if(average==null)
//                    average = new SingleScore();
//                if(min==null)
//                    min = new SingleScore();
//                info.setMax(max);
//                info.setAverage(average);
//                info.setMin(min);
//                list.add(info);
//            }
//            result = ResultUtil.success(list, "查询成功");
//        } catch (Exception e) {
//            result = ResultUtil.error(1, "异常："+e.getMessage());
//            e.printStackTrace();
//        } finally {
//            sqlSession.close();
//        }
//        return result;
//    }

}
