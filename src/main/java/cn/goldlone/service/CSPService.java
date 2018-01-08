package cn.goldlone.service;

import cn.goldlone.model.ImportScore;
import cn.goldlone.po.Score;

import java.util.List;

/**
 * Created by CN on 2018/1/6.
 */
public interface CSPService {


    // 添加CSP认证成绩
    public boolean addScore(Score score);
    // 批量添加CSP认证成绩
    public boolean addScore(List<Score> list);
    // 使用导入成绩信息，批量添加CSP认证成绩
    public boolean ImportScoreInfo(List<ImportScore> list);

}
