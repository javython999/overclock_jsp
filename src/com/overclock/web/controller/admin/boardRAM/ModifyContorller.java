package com.overclock.web.controller.admin.boardRAM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.entity.Ram;
import com.overclock.web.service.BoardRAMService;

@WebServlet("/admin/board/ram/modify")
public class ModifyContorller extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int idNum = Integer.parseInt(request.getParameter("id"));
		
		BoardRAMService service = new BoardRAMService();
		
		Ram ram = service.getArticle(idNum);
		


		request.setAttribute("n", ram);
		request.getRequestDispatcher("/WEB-INF/views/adminBoardRAM/adminBoardRAMModify.jsp").forward(request, response);
		
	}
}

