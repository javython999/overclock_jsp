package com.overclock.web.controller.boardCPU;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.overclock.web.entity.Cpu;
import com.overclock.web.service.BoardCPUService;

@WebServlet("/board/cpu/modify")
public class ModifyContorller extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int idNum = Integer.parseInt(request.getParameter("id"));
		
		BoardCPUService service = new BoardCPUService();
		
		Cpu Cpu = service.getArticle(idNum);
		


		request.setAttribute("n", Cpu);
		request.getRequestDispatcher("/WEB-INF/views/boardCPU/boardCPUModify.jsp").forward(request, response);
		
	}
}

