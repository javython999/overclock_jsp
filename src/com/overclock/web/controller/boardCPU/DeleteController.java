package com.overclock.web.controller.boardCPU;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.service.BoardCPUService;


@WebServlet("/board/cpu/delete")
public class DeleteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id_ = request.getParameter("id");
		int id = Integer.parseInt(id_);
		
		BoardCPUService service = new BoardCPUService();
		service.deleteArticle(id);
		
		response.sendRedirect("/board/cpu/list");
	}
		
	
}
