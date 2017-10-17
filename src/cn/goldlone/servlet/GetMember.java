package cn.goldlone.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.goldlone.dao.MemberDao;
import cn.goldlone.model.UserInfo;

@WebServlet(description = "获取会员信息", urlPatterns = { "/api/getMember" })
public class GetMember extends HttpServlet {

	public void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		MemberDao dao = new MemberDao();
		ArrayList<UserInfo> list = dao.selectAllMember();
		JSONObject ret = new JSONObject();
		ret.put("len", list.size());
		JSONArray arr = new JSONArray();
		JSONObject json = null;
		for(int i=0; i<list.size(); i++) {
			json = new JSONObject();
			json.put("no", list.get(i).getNo());
			json.put("name", list.get(i).getName());
			json.put("email", list.get(i).getEmail());
			json.put("grade", list.get(i).getGrade());
			json.put("endTime", list.get(i).getEndTime());
			arr.put(json);
		}
		System.out.println(arr);
		ret.put("data", arr);
		out.print(ret.toString());
		System.out.println(json);
		out.flush();
		out.close();
	}

}