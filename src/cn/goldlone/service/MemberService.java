package cn.goldlone.service;

import cn.goldlone.model.UserInfo;
import cn.goldlone.po.Member;

import java.util.List;

/**
 * Created by CN on 2018/1/4.
 */
public interface MemberService {

    // 添加会员信息
    public boolean addMember(Member member);
    // 批量添加会员信息
    public boolean addMember(List<Member> list);
    // 批量添加会员信息
    public boolean addMemberByUserInfo(List<UserInfo> list);

}
