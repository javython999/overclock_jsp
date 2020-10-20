package com.overclock.web.controller.admin.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.service.NoticeService;

@WebServlet("/admin/notice/comment")
public class CommentController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("commentWriteArea");
		String userId = request.getParameter("userId");
		int NoticeId = Integer.parseInt(request.getParameter("pageId"));
		
	
		NoticeService service = new NoticeService();
		int result = service.insertComment(content, userId, NoticeId);
		
		String url = "/admin/notice/detail?id=" + NoticeId;
		
		response.sendRedirect(url);
	}
	
}
