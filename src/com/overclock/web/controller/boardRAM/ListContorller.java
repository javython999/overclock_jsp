package com.overclock.web.controller.boardRAM;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.entity.RamView;
import com.overclock.web.service.BoardRAMService;

@WebServlet("/board/ram/list")
public class ListContorller extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		 
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		
		String field = "title";
		if(field_ != null && !field_.equals("")) 
			field = field_;
		
		String query = "";
		if(query_ != null && !query_.equals("")) 
			query = query_;
		
		
		int page = 1;
		if(page_ != null && !page_.equals("")) 
			page = Integer.parseInt(page_);
		
		
		BoardRAMService service = new BoardRAMService();
		List<RamView> list = service.getArticlePubList(field, query, page);
		service.getPubArticleId();
		int count = service.getArticleCount(field, query);
		
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);

		request.getRequestDispatcher("/WEB-INF/views/boardRAM/boardRAMList.jsp").forward(request, response);
		
	}
}

