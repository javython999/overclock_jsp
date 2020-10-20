package com.overclock.web.controller.admin.boardRAM;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.overclock.web.service.BoardRAMService;


@WebServlet("/admin/board/ram/pagemove")
public class ArticlePageMoveController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int current = Integer.parseInt(request.getParameter("idNum"));
		String test = request.getParameter("btnName");
		
		BoardRAMService service = new BoardRAMService();
		ArrayList<Integer> array = service.getPubArticleId(); //배열된 공지사항의 ID값을 배열로 가져오기
		int arrayLength = array.size()-1; //배열의 길이
		int currentIndex = array.indexOf(current); //현재 페이지가 배열의 몇번째 인덱스인지

		if(test.equals("prev")) {
			//이전 버튼일 경우
			int destinationId = array.get(currentIndex+1);
			int destinationIndex = array.indexOf(destinationId);
			String url = "/admin/board/ram/detail?id="+destinationId;
			response.sendRedirect(url);
		} else if (test.equals("next")) {
			//다음 버튼일 경우
			int destinationId = array.get(currentIndex-1);
			int destinationIndex = array.indexOf(destinationId);
				String url = "/admin/board/ram/detail?id="+destinationId;
				response.sendRedirect(url);	
		}
		
	}
}
