package com.overclock.web.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.entity.Member;
import com.overclock.web.service.MemberService;



@WebServlet(urlPatterns = "/member/join", name="join")
public class JoinController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberJoin.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String nic = request.getParameter("nic");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		
		
		
		
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		member.setNickName(nic);
		member.setName(name);
		member.setEmail(email);
		
		System.out.println(member);

		
		MemberService service = new MemberService();
		
		
		
		int result = service.joinmember(member);
		
		
		
		response.sendRedirect("/member/login");
		
	
	}
}
