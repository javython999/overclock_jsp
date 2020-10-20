package com.overclock.web.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.overclock.web.entity.Member;
import com.overclock.web.service.MemberService;



@WebServlet(urlPatterns = "/member/login", name="login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberLogin.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
	
		MemberService service = new MemberService();
		
		
		
		int result = service.loginmember(member);
		HttpSession session = request.getSession();
		String msg ="";
		String adminTest = "admin";
		if(result == 1) {
			String nickname = service.nickNameInfo(id);
			// 로그인 성공
			if(!id.equals(adminTest)) {
				System.out.println("아이디 : " + id);
				session.setAttribute("sessionID", id);
				session.setAttribute("permission", "user");
				session.setAttribute("nickname", nickname);
				msg = "/index";	
			} else if (id.equals(adminTest)) {
				System.out.println("아이디 : " + id);
				session.setAttribute("sessionID", id);
				session.setAttribute("permission", "T0k2n");
				session.setAttribute("nickname", nickname);
				msg = "/index";	
			}
			
		} else if (result == 2){
			msg = "/member/login?msg=2";
		} else if (result == 3){
			msg = "/member/login?msg=3";
		} else if (result == 4){
			msg = "/member/login?msg=4";
		} else if (result == 5){
			msg = "/member/login?msg=5";
		} else if (result == 0){
			msg = "/member/login?msg=0";
		}
		response.sendRedirect(msg);
	}
}
