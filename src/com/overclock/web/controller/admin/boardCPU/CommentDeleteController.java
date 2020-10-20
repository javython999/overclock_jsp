package com.overclock.web.controller.admin.boardCPU;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.service.BoardCPUService;
import com.overclock.web.service.NoticeService;

@WebServlet("/admin/board/cpu/comment/delete")
public class CommentDeleteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cmtId = Integer.parseInt(request.getParameter("cmtId").trim());
		System.out.println(cmtId);
		
		String page = request.getParameter("pageId");
		
		String url = "/admin/board/cpu/detail?id=" + page;
		
		BoardCPUService service = new BoardCPUService();
		int result = service.deleteCommentAdmin(cmtId);
		
		
		response.sendRedirect(url);
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cmtId = Integer.parseInt(request.getParameter("cmtID"));
		
		BoardCPUService service = new BoardCPUService();
		
		int result = service.deleteCommentAdmin(cmtId);
	}
}
