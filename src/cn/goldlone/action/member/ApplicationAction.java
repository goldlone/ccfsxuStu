package cn.goldlone.action.member;

import cn.goldlone.utils.Checks;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CN on 2017/11/3.
 */
public class ApplicationAction extends ActionSupport{

    private String gotoUrl;
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if(Checks.checkSession(request) && Checks.checkCookie(request)) {
            return SUCCESS;
        }

        gotoUrl = "/member/application";
        return LOGIN;
    }


    public String getGotoUrl() {
        return gotoUrl;
    }

    public void setGotoUrl(String gotoUrl) {
        this.gotoUrl = gotoUrl;
    }
}
