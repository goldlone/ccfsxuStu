package cn.goldlone.controller;

import cn.goldlone.model.LoginInfo;
import cn.goldlone.model.Result;
import cn.goldlone.po.Member;
import cn.goldlone.service.impl.MemberServiceImpl;
import cn.goldlone.utils.ExcelUtils;
import cn.goldlone.utils.ResultUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * Created by CN on 2018/1/8.
 */
@RestController
public class MemberController extends BaseController {

    @Autowired
    private MemberServiceImpl ms;


    /**
     * 会员登录
     * @param request
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/loginVerify")
    public Result login(HttpServletRequest request, String email, String password) {
        LoginInfo info = ms.login(email);
        Result result = null;
        if(info==null) {
            result =  ResultUtil.error(1, "会员信息不存在");
        } else {
            if(info.getPassword().equals(DigestUtils.sha256Hex(password))) {
                result = ResultUtil.success(null, "登录成功");
                request.getSession(true).setAttribute("memberNo", info.getMemberNo());
                request.getSession(true).setAttribute("power", info.getPower());
                request.getSession(true).setMaxInactiveInterval(3600);
            }
            else
                result = ResultUtil.error(2, "登录密码错误");
        }
        return result;
    }

    /**
     * 获取年级集合
     * @return
     */
    @PostMapping("/member/getGradeSet")
    public Result getGradeSet() {
        return ms.getGradeSet();
    }

    /**
     * 综合查询会员信息
     * @param member
     * @return
     */
    @PostMapping("/member/query")
    public Result queryMember(Member member) {
        return ms.queryMember(member);
    }

    /**
     * 手动录入会员信息
     * @param member
     * @return
     */
    @PostMapping("/member/addOne")
    public Result addMember(Member member) {
        return ms.addMember(member);
    }

    /**
     * 接收会员信息文件
     * @param file
     * @return
     */
    @PostMapping("/member/addByFile")
    public Result addMemberByFile(@RequestParam("file")MultipartFile file) {
        return ms.addMemberByUserInfo(ExcelUtils.importMemberInfo(file));
    }

    /**
     * 更新会员信息
     * @param member
     * @return
     */
    @PostMapping("/member/update")
    public Result updateMember(Member member) {
        return ms.updateMember(member);
    }

}
