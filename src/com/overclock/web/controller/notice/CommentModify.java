package com.overclock.web.controller.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.entity.Comment;
import com.overclock.web.entity.Notice;
import com.overclock.web.service.NoticeService;

@WebServlet("/notice/comment/modify")
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
		String url = "/notice/detail?id=" + pageId;
		
		
		NoticeService service = new NoticeService();
		service.updateComment(modifyContent, idNum);

		response.sendRedirect(url);
		
	}
}

