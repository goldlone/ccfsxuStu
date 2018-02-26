package cn.goldlone.service.impl;

import cn.goldlone.mapper.CSPMapper;
import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.ImportScore;
import cn.goldlone.model.Result;
import cn.goldlone.model.ScoreInfo;
import cn.goldlone.model.SingleScore;
import cn.goldlone.po.Certification;
import cn.goldlone.po.Score;
import cn.goldlone.service.CSPService;
import cn.goldlone.utils.MybatisUtils;
import cn.goldlone.utils.ResultUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CN on 2018/1/6.
 */
public class CSPServiceImpl implements CSPService {

    private SqlSession sqlSession;
    private CSPMapper cm;

    public CSPServiceImpl() {
        this.sqlSession = MybatisUtils.openSqlSession();
        this.cm = sqlSession.getMapper(CSPMapper.class);
    }

    /**
     * 添加CSP认证成绩
     * @param score
     * @return
     */
    @Override
    public boolean addScore(Score score) {
        try {
            cm.addScore(score);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return false;
    }

    /**
     * 批量添加CSP认证成绩
     * @param list
     * @return
     */
    @Override
    public boolean addScore(List<Score> list) {
        try{
            for(Score score: list)
                cm.addScore(score);
            sqlSession.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return false;
    }

    /**
     * 使用导入成绩信息，批量添加CSP认证成绩
     * @param list
     * @return
     */
    @Override
    public boolean ImportScoreInfo(List<ImportScore> list) {
        List<Score> sList = new ArrayList<>();
        try{
            int certNo = 0;
            String memberNo = null;
            MemberMapper mm = sqlSession.getMapper(MemberMapper.class);
            for(ImportScore score: list) {
                try {
                    certNo = cm.selectCertNoByName(score.getCertName());
                    memberNo = mm.selectNoByEmail(score.getEmail());
                    sList.add(new Score(score, certNo, memberNo));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return this.addScore(sList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return false;
    }



    public Result getScoreByCertNo(int certNo) {
        Result result = null;
        // 查询成绩
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

}
