package cn.goldlone.action.api;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.goldlone.model.RegistInfo;
import cn.goldlone.utils.Checks;
import cn.goldlone.utils.ImportMemberInfo;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.goldlone.dao.MemberDao;
import cn.goldlone.entity.Member;
import cn.goldlone.model.UserInfo;

/**
 * 响应会员实体对应的相关操作
 * @author CN
 * 创建时间: 2017-10-15 01:03
 */
public class MemberAction extends ActionSupport implements ModelDriven<Member>{
	
	private Member user = new Member();
	private MemberDao dao = new MemberDao();
	private String gotoUrl;
	private File memberFile;
	
	/**
	 * 会员登录
	 * 10001 - 登录成功
	 * 10002 - 密码错误
	 * 10003 - 未注册
	 * @return
	 */
	public String login() throws IOException {
		if(user.getEmail()==null || "".equals(user.getEmail())) {
			// 邮箱为空
			this.addFieldError("loginError", "邮箱不能为空");
			return INPUT;
		}
		if(user.getPasswd()==null || "".equals(user.getPasswd())) {
			// 密码为空
			this.addFieldError("loginError", "密码不能为空");
			return INPUT;
		}
		int num = Checks.checkLogin(user.getEmail(), user.getPasswd(), ServletActionContext.getRequest());
		switch (num) {
			case 10001:
//				Cookie cookie = new Cookie("ssocookie", "sso");
//				cookie.setPath("/");
//				HttpServletResponse response = ServletActionContext.getResponse();
//				response.addCookie(cookie);
				return SUCCESS;
			case 10002:
				this.addFieldError("loginError", "密码错误，初始密码为会员号");
				break;
			case 10003:
				this.addFieldError("loginError", "未注册，请联系CCFSXU学生分会");
				break;
		}

		return INPUT;
	}

	/**
	 * 注销登录
	 * @return
	 */
	public String logout() {
		ServletActionContext.getRequest().getSession().invalidate();
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		return LOGIN;
	}

	/**
	 * 获取年级集合
	 * @return
	 * @throws IOException
	 */
	public String getGradeSet() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ArrayList<Integer> list = dao.selectGradeSet();
		JSONObject res = new JSONObject();
		res.put("len", list.size());
		res.put("data", new JSONArray(list));
		res.put("ret", true);
		res.put("code", 10001);

		System.out.println(res.toString());
		out.print(res.toString());
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 获取所有会员信息
	 * @return
	 * @throws IOException
	 */
	public String selectAllMember() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		JSONObject res = new JSONObject();
		
		list = dao.selectAllMember();
		res.put("code", 10001);
		res.put("ret", true);
		res.put("len", list.size());
		res.put("data", new JSONArray(list));
		
		System.out.println(res.toString());
		out.print(res.toString());
		out.flush();
		out.close();
		
		return null;
	}
	
	/**
	 * 查询会员信息
	 * @return
	 * @throws IOException
	 */
	public String queryMember() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		JSONObject res = new JSONObject();
		
		list = dao.queryMember(user);
		res.put("code", 10001);
		res.put("ret", true);
		res.put("len", list.size());
		res.put("data", new JSONArray(list));
		
		System.out.println(res.toString());
		out.print(res.toString());
		out.flush();
		out.close();

		return null;
	}

	/**
	 * 查询过期或未过期会员信息
	 * @return
	 * @throws IOException 
	 */
	public String selectExpiredMember() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		
		String expired = request.getParameter("expired");
		JSONObject res = new JSONObject();
		res.put("ret", false);
		if(expired == null || expired.equals("")) {
			// 传递参数有问题
			res.put("code", 10002);
		}
		if(expired.equals("1")) {
			list = dao.selectExpiredMember(true);
			res.put("ret", true);
			res.put("code", 10001);
		} else {
			list = dao.selectExpiredMember(false);
			res.put("ret", true);
			res.put("code", 10001);
		}
		res.put("len", list.size());
		res.put("data", new JSONArray(list));
		
		System.out.println(res.toString());
		out.print(res.toString());
		out.flush();
		out.close();
		
		return null;
	}
	
	/**
	 * 根据会员号查询会员信息
	 * @return
	 * @throws IOException
	 */
	public String selectMemberByNo() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
        System.out.println(request.getParameter("no"));
        System.out.println(user.getNo());
        list = dao.selectMemberByNo(user.getNo());
		JSONObject res = new JSONObject();
		res.put("len", list.size());
		res.put("data", new JSONArray(list));
		res.put("code", 10001);
		
		System.out.println(res.toString());
		out.print(res.toString());
		out.flush();
		out.close();
		return null;
	}

    /**
     * 接收会员目录文件信息
     * @return
     * @throws IOException
     */
	public String receiveMemberFile() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONObject res = new JSONObject();
        ImportMemberInfo importMemberInfo = new ImportMemberInfo();
        boolean ret = importMemberInfo.importMemberInfo(memberFile);
        res.put("ret", ret);
		out.print(res.toString());
		out.flush();
		out.close();
		return null;
	}

    /**
     * 新增会员信息
     * @return
     */
	public String addMember() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();

        user.setPasswd(user.getNo());
        boolean ret = false;
        RegistInfo info = dao.addMember(user);
        if(info.getCode() == 10001)
            ret = true;
        res.put("code", info.getCode());
        res.put("mes", info.getInfo());
        res.put("ret", ret);
        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

    /**
     * 修改会员信息
     * @return
     */
    public String updateMember() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject res = new JSONObject();

        System.out.println(user);
        boolean ret = dao.updateMemeberInfo(user);
        res.put("ret", ret);
        out.print(res.toString());
        out.flush();
        out.close();
        return null;
    }

	@Override
	public Member getModel() {
		return user;
	}

	public String getGotoUrl() {
		return gotoUrl;
	}

	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}

	public File getMemberFile() {
		return memberFile;
	}

	public void setMemberFile(File memberFile) {
		this.memberFile = memberFile;
	}
}
