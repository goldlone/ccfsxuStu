package cn.goldlone.controller;

import cn.goldlone.model.Result;
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
public class IndexController extends BaseController implements ErrorController {

    @RequestMapping("/error")
    @Override
    public String getErrorPath() {
        return "走错地了";
    }

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
     * 获取权限
     * @param request
     * @return
     */
    @PostMapping("/power")
    public Integer getPower(HttpServletRequest request) {
        Integer power = null;
        if((power = (Integer)request.getSession().getAttribute("power"))!=null) {
            return power;
        }
        return -1;
    }

    @PostMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        request.getSession().removeAttribute("memberNo");
        return true;
    }


}
