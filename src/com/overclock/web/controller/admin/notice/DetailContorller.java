package com.overclock.web.controller.admin.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.overclock.web.entity.Comment;
import com.overclock.web.entity.Notice;
import com.overclock.web.service.NoticeService;

@WebServlet("/admin/notice/detail")
public class DetailContorller extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String test = (String) session.getAttribute("permission");

		
		if(test != "T0k2n") {
			session.invalidate();
			response.sendRedirect("/member/login");
		} else {
		int idNum = Integer.parseInt(request.getParameter("id"));
		
		NoticeService service = new NoticeService();
		
		Notice notice = service.getNoticeAdmin(idNum);
		List<Comment> comment = service.getComment(idNum);
		
		int lastId = service.getLastCount();

		request.setAttribute("n", notice);
		request.setAttribute("c", comment);
		request.setAttribute("lastId", lastId);
		request.getRequestDispatcher("/WEB-INF/views/adminNotice/adminNoticeDetail.jsp").forward(request, response);
		}
		
	}
}

