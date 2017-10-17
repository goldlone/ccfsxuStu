package cn.goldlone.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	/**
	 * 会员登录
	 * @return
	 */
	public String login() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONObject res = new JSONObject();

		if(user.getEmail()==null || "".equals(user.getEmail())) {
			// 邮箱为空
			res.put("ret", false);
			res.put("code", 10004);
			System.out.println(res.toString());
			out.print(res.toString());
			out.flush();
			out.close();
			return null;
		}
		if(user.getPasswd()==null || "".equals(user.getPasswd())) {
			// 密码为空
			res.put("ret", false);
			res.put("code", 10005);
			System.out.println(res.toString());
			out.print(res.toString());
			out.flush();
			out.close();
			return null;
		}
		int num = dao.login(user.getEmail(), user.getPasswd());
		switch (num) {
			case 10001:
				// 登录成功
				res.put("ret", true);
				res.put("code", 10001);
				break;
			case 10002:
				// 密码错误
				res.put("ret", false);
				res.put("code", 10002);
				break;
			case 10003:
				// 未注册
				res.put("ret", false);
				res.put("code", 10003);
				break;
		}
		System.out.println(res.toString());
		out.print(res.toString());
		out.flush();
		out.close();

		return null;
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

	@Override
	public Member getModel() {
		return user;
	}
}
