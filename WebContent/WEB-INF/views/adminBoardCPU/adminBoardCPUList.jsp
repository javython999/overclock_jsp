<%@page import="com.overclock.web.entity.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/style/adminBoardCPUList.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminBoardCPUList.js" defer></script>
<script src="https://kit.fontawesome.com/f87cacf99d.js" crossorigin="anonymous"></script>
<title>CPU_OVERCLCOK</title>
</head>
 <body>
    <div id="wrapper">
      <header id="header">
        <span>
          <a href="/index">OverClock </a>
        </span>
      </header>
      <div class="loginInfo">
      		<c:if test="${empty sessionScope.sessionID}">
      			<a href="/member/join" class="joinBtn">회원가입</a>
        		<a href="/member/login"class="loginBtn">로그인</a>
      		</c:if>
      		<c:if test="${!empty sessionScope.sessionID}">
      			<span class="welcomMsg">${sessionScope.nickname}님 반갑습니다.</span>
      			<a href="/member/logout" class="logoutBtn">로그아웃</a>
      		</c:if>
       </div>
      <!-- 헤더 -->
      <section id="body">
        <aside id="nav">
          <ul id="menu">
            <li>
            	<c:if test="${sessionScope.sessionID != 'admin'}">
      			<a href="/notice/list"><i class="fas fa-check"></i>공지사항</a>
      			</c:if>
              	<c:if test="${sessionScope.sessionID == 'admin'}">
      			<a href="/admin/notice/list"><i class="fas fa-check"></i>공지사항</a>
      			</c:if>
            </li>
             <li>
            	<c:if test="${sessionScope.sessionID != 'admin'}">
      			<a href="/board/cpu/list"><i class="fas fa-microchip"></i>CPU_OC</a>
      			</c:if>
              	<c:if test="${sessionScope.sessionID == 'admin'}">
      			<a href="/admin/board/cpu/list"><i class="fas fa-microchip"></i>CPU_OC</a>
      			</c:if>
            </li>
            <li>
            	<c:if test="${sessionScope.sessionID != 'admin'}">
      			<a href="/board/ram/list"><i class="fas fa-memory"></i>RAM_OC</a>
      			</c:if>
              	<c:if test="${sessionScope.sessionID == 'admin'}">
      			<a href="/admin/board/ram/list"><i class="fas fa-memory"></i>RAM_OC</a>
      			</c:if>
            </li>
          </ul>
        </aside>
        <section id="content">
        	<div class="boardTop">
        		<div>번호</div>
        		<div>이미지</div>
        		<div>제목</div>
        		<div>글쓴이</div>
        		<div>작성일</div>
        		<div>조회</div>   
        		<div>공개</div>
        		<div>삭제</div>     	
        	</div>
        	<form action="list" method="post" class="boardCPUControll">
        	<div class="boardBottom">
			<c:forEach var="n" items="${list }">
        	<div class="boardList">
        		<div class="boardId">${n.id}</div>
        		<div class="thumbnailBox">${n.thumbnail}</div>
        		<div class="title">
        			<a href="detail?id=${n.id}">
        				${n.title }
        				<span>[${n.cmtCount}]</span>
        			</a>        			
        		</div>
        		<div class="nick">${n.nickName }</div>
        		<div class="regdate"><fmt:formatDate pattern="YYYY-MM-dd" value="${n.regdate }"/></div>
        		<div class="hit"><fmt:formatNumber value="${n.hit }"/></div>
        		<c:set var="open" value=""/>
        		<c:if test="${n.pub}">
        		<c:set var="open" value="checked"/>
        		</c:if>
        		<div class="pubBtn"><i class="fas fa-lock-open"></i><input type="checkbox" name="open-id" ${open} value="${n.id}" /></div>
        		<div class="deleteBtn"><i class="fas fa-trash-alt"></i><input type="checkbox" name="del-id"  value="${n.id}" /></div>
        	</div>
			</c:forEach>
        	</div>
        	<div class="adminMenu">
        		<c:set var="ids" value=""/>
        		<c:forEach var="n" items="${list}">
        			<c:set var="ids" value="${ids} ${n.id}"/>
        		</c:forEach> 
        		<input type="hidden" name="ids" value="${ids}" />
        		<input type="submit" name="cmd" value="공개설정" onclick="return askPub();"/>
        		<input type="submit" name="cmd" value="삭제" onclick="return askDelete();"/>
        		<button><a href="/admin/board/cpu/write">글쓰기</a></button>
        	</div>
        	</form>
        	<div class="pager">
        		<c:set var="page" value="${(param.p == null)?1:param.p}"></c:set>
        		<c:set var="startNum" value="${page-(page-1)%5}"></c:set>
        		<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10), '.')}"></c:set>
        		<div class="btnWrap">
        			<c:if test="${startNum > 1}">
        				<a class="prevBtn" href="?p=${startNum-1}&t=&q="><i class="fas fa-caret-left"></i></a>
        			</c:if>
        			<c:if test="${startNum <= 1}">
        			<span class="prevBtn" onclick="alert('이전 페이지가 없습니다.');"><i class="fas fa-caret-left"></i></span>
        			</c:if>
        		</div>
        		<ul class="pageNum">
        			<c:forEach var="i" begin="0" end="4">
        			<c:if test="${(startNum+i) <=lastNum}">
        				<li><a class="PageNumberBtn ${(page == (startNum+i))?'currentPage':''}" href="?p=${startNum+i}&f=${param.f}&q=${param.q}">${startNum+i}</a></li>
        			</c:if>
        			</c:forEach>        	
	        	</ul>
	        	<div class="btnWrap">
	        		<c:if test="${startNum+4 <= lastNum}">
	        			<a class="nextBtn" href="?p=${startNum+5}&t=&q="><i class="fas fa-caret-right"></i></a>
					</c:if>
					<c:if test="${startNum+4 >= lastNum}">
						<span class="nextBtn" onclick="alert('다음 페이지가 없습니다.');"><i class="fas fa-caret-right"></i></span>
					</c:if>
	        	</div>
        	</div>
        	<div class="writeBtn">
        		<c:if test="${!empty sessionScope.sessionID}">
        		<a href="/board/cpu/write"><i class="fas fa-pen-square">글쓰기</i></a>
        		</c:if>
        	</div>
        	<div class="searchBox">
	        	<form>
						<select name="f">
							<option ${(param.f == "title")?"selected": "" }  value="title">제목</option>
							<option ${(param.f == "writer_Id")?"selected": "" } value="writer_Id">작성자</option>
							<option ${(param.f == "cpu")?"selected": "" } value="cpu">CPU</option>
						</select> 
						<input type="text" name="q" value="${param.q}"/>
						<input class="searchBtn" type="submit" value="검색" />
				</form>
	        </div>
        </section>
      </section>
      <!-- 콘텐츠 -->
      <footer id="footer">
        <div>세종특별자치시 한누리대로 411</div>
        <div>대표: 홍길동　사업자등록번호: 123-12-12345</div>
        <div>이메일: admin@overclock.com</div>
        <div>OverClock2020ⓒ All Rights Reserved</div>
      </footer>
      <!-- 푸터 -->
    </div>
  </body>
</html>

