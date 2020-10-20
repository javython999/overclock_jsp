package com.overclock.web.controller.boardRAM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.entity.Ram;
import com.overclock.web.service.BoardRAMService;


@WebServlet("/board/ram/update")
public class UpdateController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id_ = request.getParameter("id");
		int id = Integer.parseInt(id_);
		String ram_ = request.getParameter("RAM");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
			   content=content.replace("\r\n","<br>");	
		
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
		ram.setId(id);
		ram.setThumbnail(thumbnail);
		ram.setTitle(title);
		ram.setContent(content);
		ram.setRam(ram_);
		
		
		BoardRAMService service = new BoardRAMService();
		int result = service.updateArticle(ram);
		
		
		
		
		response.sendRedirect("/board/ram/list");
		}
		
	
}
