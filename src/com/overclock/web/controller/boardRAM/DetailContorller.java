package com.overclock.web.controller.boardRAM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.entity.Comment;
import com.overclock.web.entity.Ram;
import com.overclock.web.service.BoardRAMService;

@WebServlet("/board/ram/detail")
public class DetailContorller extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		int idNum = Integer.parseInt(request.getParameter("id"));
		
		BoardRAMService service = new BoardRAMService();
		Ram ram = new Ram();
		
		ram = service.getArticle(idNum);
		
		
		List<Comment> comment = service.getComment(idNum);
		
		
		ArrayList pageList = service.getPubArticleId();
		int firstId = (int) pageList.get(pageList.size()-1);
		int lastId =  (int) pageList.get(0);
		
		request.setAttribute("n", ram);
		request.setAttribute("c", comment);
		request.setAttribute("firstId", firstId);
		request.setAttribute("lastId", lastId);
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/boardRAM/boardRAMDetail.jsp").forward(request, response);
		
	}
}

