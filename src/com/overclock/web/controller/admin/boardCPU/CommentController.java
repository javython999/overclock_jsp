package com.overclock.web.controller.admin.boardCPU;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.service.BoardCPUService;
import com.overclock.web.service.NoticeService;

@WebServlet("/admin/board/cpu/comment")
public class CommentController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("commentWriteArea");
		String userId = request.getParameter("userId");
		int articleId = Integer.parseInt(request.getParameter("pageId"));
		
		System.out.println(content);
		System.out.println(userId);
		System.out.println(articleId);
		
		BoardCPUService service = new BoardCPUService();
		int result = service.insertComment(content, userId, articleId);
		
		String url = "/admin/board/cpu/detail?id=" + articleId;
		
		response.sendRedirect(url);
	}
	
}
