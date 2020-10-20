package com.overclock.web.controller.admin.boardRAM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.service.BoardRAMService;

@WebServlet("/admin/board/ram/comment/delete")
public class CommentDeleteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cmtId = Integer.parseInt(request.getParameter("cmtId").trim());
		System.out.println(cmtId);
		
		String page = request.getParameter("pageId");
		
		String url = "/admin/board/ram/detail?id=" + page;
		
		BoardRAMService service = new BoardRAMService();
		int result = service.deleteCommentAdmin(cmtId);
		
		
		response.sendRedirect(url);
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cmtId = Integer.parseInt(request.getParameter("cmtID"));
		
		BoardRAMService service = new BoardRAMService();
		
		int result = service.deleteCommentAdmin(cmtId);
	}
}
