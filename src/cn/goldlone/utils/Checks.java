package cn.goldlone.utils;

import cn.goldlone.dao.MemberDao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by CN on 2017/11/3.
 */
public class Checks {

    /**
     * 验证登录
     * 10001 - 登录成功
     * 10002 - 密码错误
     * 10003 - 未注册
     * @param email
     * @param password
     * @return
     */
    public static int checkLogin(String email, String password, HttpServletRequest request) {
        ArrayList<String> list = (new MemberDao()).login(email);
        if(list.size()==0) {
            return 10003;
        }
        if(list.get(0).equals(password)) {
            request.getSession(true).setAttribute("memberNo", list.get(1));
            request.getSession(true).setMaxInactiveInterval(30);
            return 10001;
        }
        else {
            return 10002;
        }
    }


    /**
     * 检查Coolie是否合法
     * 目前取消SSO
     * @param request
     * @return
     */
    public static boolean checkCookie(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        if(cookies!=null) {
//            for(Cookie cookie : cookies) {
//                if(cookie.getName().equals("ssocookie")
//                        && cookie.getValue().equals("sso")) {
//                    return true;
//                }
//            }
//        }

        return true;
    }

    /**
     * 检查是否具有权限
     * @param power
     * @return
     */
    public static boolean checkPower(String memeberNo, int power) {
        System.out.println(memeberNo);
        System.out.println((new MemberDao()).selectPower(memeberNo));
        if(power <= (new MemberDao()).selectPower(memeberNo))
            return true;
        return false;
    }

    /**
     * 检查Session是否合格
     * @param request
     * @return
     */
    public static boolean checkSession(HttpServletRequest request) {
        if(request.getSession().getAttribute("memberNo") != null)
            return true;
        return false;
    }

}
