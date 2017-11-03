package cn.goldlone.action.master;

import cn.goldlone.utils.Checks;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CN on 2017/11/3.
 */
public class SelectMemberInfoAction extends ActionSupport {

    private String gotoUrl;
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if(Checks.checkCookie(request)) {
            if(Checks.checkPower("", 2))
                return SUCCESS;
            else
                return "power";
        }
        gotoUrl = "/master/selectMemberInfo";

        return LOGIN;
    }


    public String getGotoUrl() {
        return gotoUrl;
    }

    public void setGotoUrl(String gotoUrl) {
        this.gotoUrl = gotoUrl;
    }
}
