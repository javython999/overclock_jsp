package com.overclock.web.controller.admin.boardRAM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.overclock.web.entity.RamView;
import com.overclock.web.service.BoardRAMService;

@WebServlet("/admin/board/ram/list")
public class ListContorller extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String test = (String) session.getAttribute("permission");

		
		if(test != "T0k2n") {
			session.invalidate();
			response.sendRedirect("/member/login");
		} else {
		 
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
		List<RamView> list = service.getArticleList(field, query, page);
		service.getPubArticleId();
		int count = service.getArticleCount(field, query);
		
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);

		request.getRequestDispatcher("/WEB-INF/views/adminBoardRAM/adminBoardRAMList.jsp").forward(request, response);
		
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String test = (String) session.getAttribute("permission");

		
		if(test != "T0k2n") {
			session.invalidate();
			response.sendRedirect("/member/login");
		} else {
		String[] openIds = request.getParameterValues("open-id");
		String[] delIds = request.getParameterValues("del-id");
		String cmd = request.getParameter("cmd");
		String ids_ = request.getParameter("ids");
		String[] ids = ids_.trim().split(" ");
		
		BoardRAMService service = new BoardRAMService();
		
		
		switch(cmd) {
			case "공개설정":
				if(openIds != null && openIds.length != 0) {
				for(String openId : openIds)
					System.out.println("open id :" + openId);
				
				List<String> oids = Arrays.asList(openIds);
				List<String> cids = new ArrayList(Arrays.asList(ids));
				cids.removeAll(oids);
				
				
				service.pubArticleAll(oids, cids);    
				} else {
					service.noPubArticleAll(ids);
				}
				break;		
			
			case "삭제":
					
					int[] ids1 = new int[delIds.length];
					for(int i=0; i<delIds.length; i++)
						ids1[i] = Integer.parseInt(delIds[i]);
					
					int result = service.deleteArticleAll(ids1);
				break;
		}
		
		response.sendRedirect("list");
		}
	}
}

