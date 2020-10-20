package com.overclock.web.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.overclock.web.service.MemberService;

@WebServlet("/member/join/idoverlapcheck")
public class IdOverlapCheck extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json; charset=utf-8");
		String id = request.getParameter("id");
		
		MemberService service = new MemberService();
		String result = service.idoverlapcheck(id);
		PrintWriter pw = response.getWriter();
		pw.write(result);
		pw.close();
		 
	}
	
}
