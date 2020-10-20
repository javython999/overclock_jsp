package com.overclock.web.controller.boardCPU;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.overclock.web.entity.Cpu;
import com.overclock.web.service.BoardCPUService;

@MultipartConfig(
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*50, // 각 각의 파일의 최대 용량
		maxRequestSize=1024*1024*50*5 //전체 요청에 대한 파일용량 제한
)

@WebServlet("/board/cpu/write")
public class WriteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		request.getRequestDispatcher("/WEB-INF/views/boardCPU/boardCPUWrite.jsp").forward(request, response);
		
	}	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String cpu_ = request.getParameter("CPU");					
		String content = request.getParameter("content");
			   content=content.replace("\r\n","<br>");
		String writerId = (String) session.getAttribute("sessionID"); 
		
		String thumbnail = "";
		switch(cpu_) {
		case "i9-10900k":
		case "i9-10900" :
		case "i9-10850" :
		case "i9-10900KF" :
		case "i9-10900F" :
			thumbnail = "<img class='thumbnail' src='/upload/intel10th_i9.png' />";
			break;
		
		case "i7-10700k": 
		case "i7-10700":
		case "i7-10700KF":
		case "i7-10700F":
			thumbnail = "<img class='thumbnail' src='/upload/intel10th_i7.png' />";
			break;
			
		case "i5-10600k":
		case "i5-10600":
		case "i5-10500":
		case "i5-10600KF":
		case "i5-10400F":
			thumbnail = "<img class='thumbnail' src='/upload/intel10th_i5.png' />";
			break;
			
		case "i3-10320":
		case "i3-10300":
		case "i3-10100":
			thumbnail = "<img class='thumbnail' src='/upload/intel10th_i3.png' />";
			break;	
			
		case "Ryzen9-3950x": 
		case "Ryzen9-3900x":
			thumbnail = "<img class='thumbnail' src='/upload/AMD3rd_Ryzen9.png' />";
			break;
		
		case "Ryzen7-3800x":
		case "Ryzen7-3700x":
			thumbnail = "<img class='thumbnail' src='/upload/AMD3rd_Ryzen7.png' />";
			break;
		
		case "Ryzen5-3600x":
		case "Ryzen5-3600":
		case "Ryzen5-3500x":
		case "Ryzen5-3500":
			thumbnail = "<img class='thumbnail' src='/upload/AMD3rd_Ryzen5.png' />";
			break;
			
		case "Ryzen3-3300x":
		case "Ryzen3-3100":
			thumbnail = "<img class='thumbnail' src='/upload/AMD3rd_Ryzen3.png' />";
			break;
		}
		
				
		
		Cpu cpu = new Cpu();
		cpu.setThumbnail(thumbnail);
		cpu.setTitle(title);
		cpu.setContent(content);
		cpu.setWriterId(writerId);
		cpu.setCpu(cpu_);
		
		
		BoardCPUService service = new BoardCPUService();
		int result = service.insertArticle(cpu);
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		response.sendRedirect("/board/cpu/list");
		}
		
	
}
