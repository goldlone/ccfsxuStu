package cn.goldlone.controller;

import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.UserInfo;
import cn.goldlone.service.MemberService;
import cn.goldlone.service.impl.MemberServiceImpl;
import cn.goldlone.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.io.File;

/**
 * Created by CN on 2018/1/8.
 */
@RestController
public class MemberController {
    private SqlSession sqlSession = MybatisUtils.openSqlSession();
    private MemberMapper mm = sqlSession.getMapper(MemberMapper.class);



    @GetMapping("/list")
    public List<UserInfo> get() {
        sqlSession = MybatisUtils.openSqlSession();
        mm = sqlSession.getMapper(MemberMapper.class);
        List<UserInfo> list = mm.selectAllMember();
        sqlSession.close();
        return list;
    }


}
