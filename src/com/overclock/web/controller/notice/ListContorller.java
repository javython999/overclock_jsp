package com.overclock.web.controller.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.entity.Notice;
import com.overclock.web.entity.NoticeView;
import com.overclock.web.service.NoticeService;

@WebServlet("/notice/list")
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
		
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getNoticePubList(field, query, page);
		int count = service.getNoticeCount(field, query);
		

		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		
		request.getRequestDispatcher("/WEB-INF/views/notice/noticeList.jsp").forward(request, response);
		
	}
}

