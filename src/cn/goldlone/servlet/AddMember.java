package cn.goldlone.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import cn.goldlone.dao.MemberDao;
import cn.goldlone.entity.Member;
import cn.goldlone.model.RegistInfo;
import cn.goldlone.utils.IOUtils;

/**
 * Setvlet 添加会员信息
 */
public class AddMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String result = IOUtils.inputStreamToString(request.getInputStream());
		System.out.println(result);
		JSONObject json = new JSONObject();

		if(result!=null && !result.equals("")){
			JSONTokener jt = new JSONTokener(result);
			JSONObject res = new JSONObject(jt);
			try{
				Member user = new Member(res.getString("no"), res.getString("name"), 
						res.getString("stuNo"), res.getString("phone"), 
						res.getString("email"), res.getString("discipline"), 
						res.getInt("grade"), res.getInt("classNum"), 
						res.getInt("degreeNo"), res.getString("id"), 
						res.getString("startTime"), res.getString("endTime"), 
						res.getInt("memberTypeNo"), res.getString("passwd"), 
						res.getInt("power"), res.getInt("addScore"));
				MemberDao dao = new MemberDao();
				RegistInfo info = dao.addMember(user);
				json.put("ret", info.isSuccess());
				json.put("code", info.getCode());
				json.put("info", info.getInfo());
			} catch(JSONException e) {
				json.put("ret", false);
				json.put("code", 10004);
				json.put("info", "失败，请求信息不完整");
			}
		} else {
			json.put("ret", false);
			json.put("code", 10005);
			json.put("info", "失败，请求信息为空");
		}
		
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
