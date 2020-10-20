package com.overclock.web.controller.admin.boardCPU;

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

import com.overclock.web.entity.CpuView;
import com.overclock.web.service.BoardCPUService;

@WebServlet("/admin/board/cpu/list")
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
		
		
		BoardCPUService service = new BoardCPUService();
		List<CpuView> list = service.getArticleList(field, query, page);
		service.getPubArticleId();
		int count = service.getArticleCount(field, query);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.getRequestDispatcher("/WEB-INF/views/adminBoardCPU/adminBoardCPUList.jsp").forward(request, response);
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
		
		BoardCPUService service = new BoardCPUService();
		
		
		switch(cmd) {
			case "공개설정":
				if(openIds != null && openIds.length != 0) {
				for(String openId : openIds)
					System.out.println("open id :" + openId);
				
				List<String> oids = Arrays.asList(openIds);
				List<String> cids = new ArrayList(Arrays.asList(ids));
				cids.removeAll(oids);
				System.out.println("전체 공지ID : " + Arrays.asList(ids));
				System.out.println("공개 공지ID : " + oids);
				System.out.println("비공개 공지ID : " + cids);
				
				
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

