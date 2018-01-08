package cn.goldlone.service.impl;

import cn.goldlone.mapper.CSPMapper;
import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.ImportScore;
import cn.goldlone.po.Score;
import cn.goldlone.service.CSPService;
import cn.goldlone.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CN on 2018/1/6.
 */
public class CSPServiceImpl implements CSPService {

    private SqlSession sqlSession;
    private CSPMapper cspMapper;

    public CSPServiceImpl() {
        this.sqlSession = MybatisUtils.openSqlSession();
        this.cspMapper = sqlSession.getMapper(CSPMapper.class);
    }

    /**
     * 添加CSP认证成绩
     * @param score
     * @return
     */
    @Override
    public boolean addScore(Score score) {
        try {
            cspMapper.addScore(score);
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
                cspMapper.addScore(score);
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
                    certNo = cspMapper.selectCertNoByName(score.getCertName());
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
}
