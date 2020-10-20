package com.overclock.web.controller.index;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.entity.CpuView;
import com.overclock.web.entity.NoticeView;
import com.overclock.web.entity.RamView;
import com.overclock.web.service.BoardCPUService;
import com.overclock.web.service.BoardRAMService;
import com.overclock.web.service.NoticeService;

@WebServlet("/index")
public class IndexController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService noticeService = new NoticeService();
		List<NoticeView> noticelist = noticeService.getIndexNoticeList();
		
		BoardCPUService cpuService = new BoardCPUService();
		List<CpuView> cpuList = cpuService.getIndexArticleList();
		
		BoardRAMService ramService = new BoardRAMService();
		List<RamView> ramList = ramService.getIndexArticleList();
		
		
		request.setAttribute("noticelist", noticelist);
		request.setAttribute("cpuList", cpuList);
		request.setAttribute("ramList", ramList);
		
	
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
	}	
}
