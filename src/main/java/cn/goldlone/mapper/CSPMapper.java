package cn.goldlone.mapper;

import cn.goldlone.model.ScoreInfo;
import cn.goldlone.model.SingleScore;
import cn.goldlone.po.Application;
import cn.goldlone.po.Certification;
import cn.goldlone.po.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by CN on 2017/12/30.
 */
public interface CSPMapper {
    // 添加CSP认证
    public int addCert(Certification cert);
    // 获取认证名集合
    public List<Certification> getCertSet();
    // 根据认证名查询认证编号
    public int selectCertNoByName(String certName);
    // 获取未开始的认证集合
    public List<Certification> getCertSetNotStart();
    // 插入报名信息
    public int addApplication(Application application);
    // 修改照片名
    public int updatePhotoFile(@Param("memberNo") String memberNo, @Param("filename") String filename);
    // 查询某次认证的报名信息
    public List<Application> selectApplicationInfo(int certNo);
    // 查询某次CSP的最高分
    public SingleScore selectMaxScoreByCertNo(int certNo);
    // 查询某次CSP的平均分
    public SingleScore selectAverageScoreByCertNo(int certNo);
    // 查询某次CSP的最低分
    public SingleScore selectMinScoreByCertNo(int certNo);
    // 添加成绩
    public int addScore(Score score);
    // 查询所有成绩
    public List<ScoreInfo> selectAllScore();
    // 按认证编号查询查询所有人的成绩
    public List<ScoreInfo> selectScoreByNo(int no);
    // 按会员号查询查询所有认证的成绩
    public List<ScoreInfo> selectScoreByMemberNo(String memberNo);
    // 综合筛选成绩信息
    public List<ScoreInfo> queryScore(@Param("certNo") int certNo, @Param("low") int low, @Param("high") int high);
    // 查询加分了的人
    public List<ScoreInfo> selectAddScore();

}
