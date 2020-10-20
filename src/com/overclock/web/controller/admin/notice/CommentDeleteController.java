package com.overclock.web.controller.admin.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.service.NoticeService;

@WebServlet("/admin/notice/comment/delete")
public class CommentDeleteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cmtId = Integer.parseInt(request.getParameter("cmtId"));
		
		String page = request.getParameter("pageId");
		
		String url = "/admin/notice/detail?id=" + page;
		System.out.println(url);
		System.out.println(cmtId);
		
		NoticeService service = new NoticeService();
		int result = service.deleteCommentAdmin(cmtId);
		
		
		response.sendRedirect(url);
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cmtId = Integer.parseInt(request.getParameter("cmtID"));
		
		NoticeService service = new NoticeService();
		
		int result = service.deleteCommentAdmin(cmtId);
	}
}
