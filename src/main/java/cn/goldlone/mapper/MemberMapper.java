package cn.goldlone.mapper;

import cn.goldlone.model.LoginInfo;
import cn.goldlone.model.UserInfo;
import cn.goldlone.po.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by CN on 2017/12/30.
 */
@Mapper
public interface MemberMapper {
    // 会员登录
    public LoginInfo login(String email);
    // 获取年级集合
    public List<Integer> selectGradeSet();
    // 查询权限
    public Integer selectPower(String memberNo);
    // 查询学历编号
    public Integer selectDegreeNo(String degree);
    // 添加会员信息
    public int addMember(Member user);
    // 查询所有会员信息
    public List<UserInfo> selectAllMember();
    // 根据会员号查询会员信息
    public UserInfo selectMemberByNo(String memberNo);
    // 根据邮箱获取会员信息
    public UserInfo selectMemberByEmail(String email);
    // 查询所有过期的会员
    public List<UserInfo> selectExpiredMember();
    // 查询所有未过期的会员
    public List<UserInfo> selectNotExpiredMember();
    // 根据会员姓名查询会员信息
    public List<UserInfo> selectMemberByName(String name);
    // 综合信息查询会员信息
    public List<UserInfo> queryMember(Member member);
    // 根据邮箱查询会员号
    public String selectNoByEmail(String email);
    // 更新失效日期
    public int updateEndTimeByNo(@Param("no") String memberNo, @Param("endTime") String endTime);
    // 修改会员信息
    public int updateMemberInfo(Member member);
}
