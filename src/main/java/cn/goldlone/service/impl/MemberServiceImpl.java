package cn.goldlone.service.impl;

import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.LoginInfo;
import cn.goldlone.model.Result;
import cn.goldlone.model.UserInfo;
import cn.goldlone.po.Member;
import cn.goldlone.utils.ResultUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by CN on 2018/1/4.
 */
@Service
public class MemberServiceImpl {

    @Autowired
    private MemberMapper mm;

    /**
     * 获取登录信息
     * @param email
     * @return
     */
    public LoginInfo login(String email) {
        return mm.login(email);
    }

    /**
     * 获取年级集合
     * @return
     */
    public Result getGradeSet() {
        return ResultUtil.success(mm.selectGradeSet(), "获取年级集合成功");
    }

    /**
     * 综合查询会员信息
     * @param member
     * @return
     */
    public Result queryMember(Member member) {
        List<UserInfo> list = mm.queryMember(member);
        if(list.size()==0)
            return ResultUtil.error(ResultUtil.CODE_NOT_EXIST, "查询信息为空");
        return ResultUtil.success(list, "查询成功");
    }

    /**
     * 手动录入会员信息
     * @param member
     * @return
     */
    public Result addMember(Member member) {
        Result result = null;
        member.setPassword(DigestUtils.sha256Hex(member.getNo()));
        member.setPower(5);
        member.setAddScore(0);
        member.setMemberTypeNo(1);
        if(mm.addMember(member)>0) {
            result = ResultUtil.success("录入成功");
        } else {
            result =  ResultUtil.error(ResultUtil.CODE_FORMAT_ERROR, "录入失败：数据有问题，数据库插入失败");
        }
        return result;
    }


    /**
     * 更新会员信息
     * @param member
     * @return
     */
    public Result updateMember(Member member) {
        Result result = null;
        if(mm.updateMemberInfo(member)>0)
            result = ResultUtil.success(null, "修改成功");
        else
            result = ResultUtil.error(ResultUtil.CODE_NOT_EXIST, "会员信息不存在");
        return result;
    }


    /**
     * 批量录入
     * @param list
     * @return
     */
    public Result addMemberByUserInfo(List<UserInfo> list) {
        List<String> errors = new ArrayList<>(list.size());
        Member member = null;
        for(UserInfo info: list) {
            if(mm.selectMemberByNo(info.getNo()) != null) {
                System.out.println(info.getNo()+"-"+info.getName()+"--已存在，已更新失效日期");
                mm.updateEndTimeByNo(info.getNo(), info.getEndTime());
            } else {
                member = new Member();
                member.setNo(info.getNo());
                member.setName(info.getName());
                member.setStuNo(info.getStuNo());
                member.setPhone(info.getPhone());
                member.setEmail(info.getEmail());
                member.setGender(info.getGender());
                member.setDiscipline(info.getDiscipline());
                member.setGrade(info.getGrade());
                member.setClassNum(info.getClassNum());
                member.setDegreeNo(mm.selectDegreeNo(info.getDegree()));
                member.setId(info.getId());
                member.setStartTime(info.getStartTime());
                member.setEndTime(info.getEndTime());
                member.setMemberTypeNo(1);
                member.setPassword(DigestUtils.sha256Hex(info.getNo()));
                member.setPower(5);
                try {
                    mm.addMember(member);
                } catch (Exception e) {
                    e.printStackTrace();
                    errors.add(member.getNo()+" - "+member.getName());
                }
            }
        }
        return ResultUtil.success(errors, "上传成功");
    }

}
