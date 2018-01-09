package cn.goldlone.controller;

import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.Result;
import cn.goldlone.model.UserInfo;
import cn.goldlone.po.Member;
import cn.goldlone.utils.MybatisUtils;
import cn.goldlone.utils.ResultUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by CN on 2018/1/8.
 */
@RestController
public class MemberController {
    private SqlSession sqlSession = MybatisUtils.openSqlSession();
    private MemberMapper mm = sqlSession.getMapper(MemberMapper.class);

    /**
     * 获取年级集合
     * @return
     */
    @PostMapping("/member/getGradeSet")
    public Result getGradeSet() {
        Result result = null;
        List<Integer> list = mm.selectGradeSet();
        result = ResultUtils.success(list, "获取年级集合成功");
        return result;
    }

    /**
     * 综合查询会员信息
     * @param member
     * @return
     */
    @PostMapping("/member/queryMember")
    public Result queryMember(Member member) {
        Result result = null;
//        System.out.println(member);
        List<UserInfo> list = mm.queryMember(member);
//        for(UserInfo info: list)
//            System.out.println(info);
        result = ResultUtils.success(list, "查询会员信息成功");
        return result;
    }






    @GetMapping("/list")
    public Result get() {
        List<UserInfo> list = mm.selectAllMember();
        return ResultUtils.success(list, "请求成功");
    }


}
