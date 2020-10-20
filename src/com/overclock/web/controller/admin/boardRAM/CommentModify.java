package com.overclock.web.controller.admin.boardRAM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.service.BoardRAMService;

@WebServlet("/admin/board/ram/comment/modify")
public class CommentModify extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//수정할 댓글의 아이디를 얻어오기
		int idNum = Integer.parseInt(request.getParameter("cmtId"));
		
		//수정한 댓글 내용 가져오기
		String modifyContent = request.getParameter("cmtModifyContent");
		
		//공지글 ID 가져오기
		String pageId = request.getParameter("pageId");
		
		//url주소
		String url = "/admin/board/ram/detail?id=" + pageId;
		
		
		BoardRAMService service = new BoardRAMService();
		service.updateComment(modifyContent, idNum);

		response.sendRedirect(url);
		
	}
}

