package com.overclock.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.overclock.web.entity.Notice;
import com.overclock.web.service.NoticeService;

@MultipartConfig(
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*50, // 각 각의 파일의 최대 용량
		maxRequestSize=1024*1024*50*5 //전체 요청에 대한 파일용량 제한
)

@WebServlet("/admin/notice/write")
public class WriteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String test = (String) session.getAttribute("permission");

		
		if(test != "T0k2n") {
			session.invalidate();
			response.sendRedirect("/member/login");
		} else {
	
		request.getRequestDispatcher("/WEB-INF/views/adminNotice/adminNoticeWrite.jsp").forward(request, response);
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
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		content=content.replace("\r\n","<br>");
		Collection<Part> parts = request.getParts();
		StringBuilder builder = new StringBuilder();
		for(Part p : parts) {
			
			if(!p.getName().equals("file")) continue;
			if(p.getSize() == 0) continue; //업로드 파일이 없을시 패스하기
			
			Part filePart = p;
			String fileName = filePart.getSubmittedFileName();
			builder.append(fileName);
			builder.append(",");
			
			InputStream fis = filePart.getInputStream();
	
			String realPath = request.getServletContext().getRealPath("/upload");
			
			File path = new File(realPath);
			if(!path.exists())
				path.mkdirs();
			
			String filePath = realPath + File.separator + fileName;
			FileOutputStream fos = new FileOutputStream(filePath);
		
			byte[] buf = new byte[1024];
			int size = 0;
			while((size=fis.read(buf)) != -1) 
				fos.write(buf, 0, size);
				fos.close();
				fis.close();
		

		builder.delete(builder.length()-1, builder.length());
		}
		
		boolean pub = true;
		if(isOpen == null)
			pub = false;
		
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setWriterId("관리자");
		notice.setPub(pub);
		notice.setFiles(builder.toString());
		
		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		response.sendRedirect("/admin/notice/list");
		}
		
	}
}
