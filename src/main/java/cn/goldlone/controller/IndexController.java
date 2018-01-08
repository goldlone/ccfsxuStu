package cn.goldlone.controller;

import cn.goldlone.mapper.MemberMapper;
import cn.goldlone.model.LoginInfo;
import cn.goldlone.model.Result;
import cn.goldlone.utils.MybatisUtils;
import cn.goldlone.utils.ResultUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CN on 2018/1/8.
 */
@RestController
public class IndexController /*implements ErrorController*/ {

    @GetMapping("/")
    public String index() {
        return "redirect:/index.html";
    }

    /*
    @RequestMapping("/error")
    @Override
    public String getErrorPath() {
        return "走错地了";
    }
    */

    /**
     * 是否登录
     * @param request
     * @return
     */
    @PostMapping("/isLogin")
    public boolean isLogin(HttpServletRequest request) {
        if(request.getSession().getAttribute("memberNo")!=null)
            return true;
        return false;
    }

    /**
     * 会员登录
     * @param request
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(HttpServletRequest request, String email, String password) {
        SqlSession sqlSession = MybatisUtils.openSqlSession();
        MemberMapper mm = sqlSession.getMapper(MemberMapper.class);
        System.out.println(email+password);
        LoginInfo info = mm.login(email);
        Result result = null;
        if(info==null) {
            result =  ResultUtils.error(1, "会员信息不存在");
        } else {
            if(info.getPassword().equals(DigestUtils.sha256Hex(password))) {
                result = ResultUtils.success(null, "登录成功");
                request.getSession(true).setAttribute("memberNo", info.getMemberNo());
                request.getSession(true).setAttribute("power", info.getPower());
                request.getSession(true).setMaxInactiveInterval(30);
            }
            else
                result = ResultUtils.error(2, "登录密码错误");
        }
        return result;
    }

    @PostMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        request.getSession().removeAttribute("memberNo");
        return true;
    }


}
