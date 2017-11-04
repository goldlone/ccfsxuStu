package cn.goldlone.action.master;

import cn.goldlone.utils.Checks;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * 插入会员信息
 * Created by CN on 2017/11/3.
 */
public class InsertMemberInfoAction extends ActionSupport {

    private String gotoUrl;
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if(Checks.checkCookie(request) && Checks.checkSession(request)) {
            if(Checks.checkPower(request.getSession().getAttribute("memberNo").toString(), 2))
                return SUCCESS;
            else
                return "power";
        }
        gotoUrl = "/master/insertMemberInfo";

        return LOGIN;
    }


    public String getGotoUrl() {
        return gotoUrl;
    }

    public void setGotoUrl(String gotoUrl) {
        this.gotoUrl = gotoUrl;
    }
}
