package com.overclock.web.controller.admin.boardRAM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.service.BoardRAMService;


@WebServlet("/admin/board/ram/delete")
public class DeleteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id_ = request.getParameter("id");
		int id = Integer.parseInt(id_);
		
		BoardRAMService service = new BoardRAMService();
		service.deleteArticle(id);
		
		response.sendRedirect("/admin/board/ram/list");
	}
		
	
}
