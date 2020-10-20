package com.overclock.web.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.service.NoticeService;

@WebServlet("/notice/comment")
public class CommentController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("commentWriteArea");
		String userId = request.getParameter("userId");
		int NoticeId = Integer.parseInt(request.getParameter("pageId"));
		
		System.out.println(content);
		System.out.println(userId);
		System.out.println(NoticeId);
		
		NoticeService service = new NoticeService();
		int result = service.insertComment(content, userId, NoticeId);
		
		String url = "/notice/detail?id=" + NoticeId;
		
		response.sendRedirect(url);
	}
	
}
