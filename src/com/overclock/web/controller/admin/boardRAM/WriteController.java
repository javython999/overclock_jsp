package com.overclock.web.controller.admin.boardRAM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.overclock.web.entity.Cpu;
import com.overclock.web.entity.Ram;
import com.overclock.web.service.BoardCPUService;
import com.overclock.web.service.BoardRAMService;

@MultipartConfig(
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*50, // 각 각의 파일의 최대 용량
		maxRequestSize=1024*1024*50*5 //전체 요청에 대한 파일용량 제한
)

@WebServlet("/admin/board/ram/write")
public class WriteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		request.getRequestDispatcher("/WEB-INF/views/adminBoardRAM/adminBoardRAMWrite.jsp").forward(request, response);
		
	}	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String ram_ = request.getParameter("RAM");					
		String content = request.getParameter("content");
			   content=content.replace("\r\n","<br>");
		String writerId = (String) session.getAttribute("sessionID"); 
		
		String thumbnail = "";
		switch(ram_) {
		case "GSkll_Flare_X":
			thumbnail = "<img class='thumbnail' src='/upload/GSkll_Flare_X.png' />";
			break;
		
		case "GSkll_FORTIS": 
			thumbnail = "<img class='thumbnail' src='/upload/GSkll_FORTIS.png' />";
			break;
			
		case "GSkll_Ripjaws_4":
			thumbnail = "<img class='thumbnail' src='/upload/GSkll_Ripjaws_4.png' />";
			break;
			
		case "GSkll_Ripjaws_V":
			thumbnail = "<img class='thumbnail' src='/upload/GSkll_Ripjaws_V.png' />";
			break;	
			
		case "GSkll_Ripjaws_Z": 
			thumbnail = "<img class='thumbnail' src='/upload/GSkll_Ripjaws_Z.png' />";
			break;
		
		case "GSkll_Trident_Z_NEO":
			thumbnail = "<img class='thumbnail' src='/upload/GSkll_Trident_Z_NEO.png' />";
			break;
		
		case "GSkll_Trident_Z_RGB":
			thumbnail = "<img class='thumbnail' src='/upload/GSkll_Trident_Z_RGB.png' />";
			break;
			
		case "GSkll_Trident_Z_Royal_Gold":
			thumbnail = "<img class='thumbnail' src='/upload/GSkll_Trident_Z_Royal_Gold.png' />";
			break;
		
		case "GSkll_Trident_Z_Royal_Silver":
			thumbnail = "<img class='thumbnail' src='/upload/GSkll_Trident_Z_Royal_Silver.png' />";
			break;
		
		case "GSkll_Trident_Z":
			thumbnail = "<img class='thumbnail' src='/upload/GSkll_Trident_Z.png' />";
			break;
		}
		
				
		
		Ram ram = new Ram();
		ram.setThumbnail(thumbnail);
		ram.setTitle(title);
		ram.setContent(content);
		ram.setWriterId(writerId);
		ram.setRam(ram_);
		
		
		BoardRAMService service = new BoardRAMService();
		int result = service.insertArticle(ram);
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		response.sendRedirect("/admin/board/ram/list");
		}
		
	
}
