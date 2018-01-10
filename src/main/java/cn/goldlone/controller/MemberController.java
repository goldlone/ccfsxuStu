package cn.goldlone.controller;

import ch.qos.logback.core.util.FileUtil;
import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.Result;
import cn.goldlone.model.UserInfo;
import cn.goldlone.po.Member;
import cn.goldlone.utils.ExcelUtils;
import cn.goldlone.utils.MybatisUtils;
import cn.goldlone.utils.ResultUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.util.List;
import java.io.File;
import java.util.Map;


/**
 * Created by CN on 2018/1/8.
 */
@RestController
public class MemberController {
    private SqlSession sqlSession = null;
    private MemberMapper mm = null;

    /**
     * 获取年级集合
     * @return
     */
    @PostMapping("/member/getGradeSet")
    public Result getGradeSet() {
        sqlSession = MybatisUtils.openSqlSession();
        mm = sqlSession.getMapper(MemberMapper.class);
        Result result = null;
        try {
            result = ResultUtils.success(mm.selectGradeSet(), "获取年级集合成功");
        } catch (Exception e) {
            return ResultUtils.error(1, "异常："+e.getMessage());
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 综合查询会员信息
     * @param member
     * @return
     */
    @PostMapping("/member/queryMember")
    public Result queryMember(Member member) {
        sqlSession = MybatisUtils.openSqlSession();
        mm = sqlSession.getMapper(MemberMapper.class);
        Result result = null;
        try {
            result = ResultUtils.success(mm.queryMember(member), "查询会员信息成功");
        } catch (Exception e) {
            return ResultUtils.error(1, "异常："+e.getMessage());
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 手动录入会员信息
     * @param member
     * @return
     */
    @PostMapping("/member/add")
    public Result addMember(Member member) {
        sqlSession = MybatisUtils.openSqlSession();
        mm = sqlSession.getMapper(MemberMapper.class);
        Result result = null;
        try {
            member.setPassword(DigestUtils.sha256Hex(member.getNo()));
            member.setPower(5);
            member.setAddScore(0);
            member.setMemberTypeNo(1);
            if(mm.addMember(member)>0) {
                sqlSession.commit();
                result = ResultUtils.success(null, "录入成功");
            } else {
                result =  ResultUtils.error(1, "录入失败：数据有问题，数据库插入失败");
            }
        } catch (Exception e) {
            result = ResultUtils.error(2, "异常："+e.getMessage());
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 接收会员信息文件
     * @param file
     * @return
     */
    @PostMapping("/member/addByFile")
    public Result addMemberByFile(@RequestParam("file")MultipartFile file) {
        sqlSession = MybatisUtils.openSqlSession();
        mm = sqlSession.getMapper(MemberMapper.class);
        Result result = null;
        try {
            result = ExcelUtils.importMemberInfo(file);
        } catch (Exception e) {
            result =  ResultUtils.error(1, "异常："+e.getMessage());
        } finally {
            sqlSession.close();
        }
        return result;
    }

    /**
     * 更新会员信息
     * @param member
     * @return
     */
    @PostMapping("/member/update")
    public Result updateMember(Member member) {
        Result result = null;
        try {
            mm.updateMemberInfo(member);
            sqlSession.commit();
            result = ResultUtils.success(null, "修改成功");
        }catch (Exception e) {
            result = ResultUtils.error(1, "更新失败："+e.getMessage());
        } finally {
            sqlSession.close();
        }
        return result;
    }






}
