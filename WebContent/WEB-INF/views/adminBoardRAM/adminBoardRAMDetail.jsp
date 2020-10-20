<%@page import="com.overclock.web.entity.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/style/adminBoardCPUDetail.css" rel="stylesheet" />
<script src="https://kit.fontawesome.com/f87cacf99d.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/boardCPUDetail.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/articlePageBtn.js" defer></script>
<title>CPU_OVERCLOCK</title>
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
        	<div class="contentWrap">
        		<div class="conTitle">${n.title}</div>
        		<div class="conInfo">
        			<div>${n.nickName }</div>
        			<div><fmt:formatNumber value="${n.hit }"/></div>
        			<div><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${n.regdate }"/></div>
        		</div>
        		<div class="conBody clear">
        			<span>${n.thumbnail }</span><br>
        			${n.content}
        		</div>
        		
        		<div class="articleBtn">
        			<c:if test="${sessionScope.sessionID == n.writerId}">
        				<a href="/board/cpu/modify?id=${n.id}"><i class="fas fa-pen-nib">수정</i></a>
        				<a href="/board/cpu/delete?id=${n.id}" onclick="return confirm('삭제하시겠습니까?')"><i class="fas fa-trash-alt">삭제</i></a>
        			</c:if>
        		</div>
        		<div class="btnBox">
        			<c:set var="lastId" value="${lastId}"/>
        			<c:set var="firstId" value="${firstId}"/>
        			<c:set var="currentId" value="${n.id}" />
        			<!-- 이전글 버튼-->
        			<c:if test="${firstId < currentId}">
        			<form class="prevBtn" action="/board/cpu/pagemove" method="get">
        			<i class="fas fa-caret-left" onclick="prevBtn();">
        				<input type="hidden" name="btnName" value="prev"/>
        				<input type="hidden" name="idNum" value="${n.id}"/>
        			</i>
        			</form>
        			</c:if>
        			<c:if test="${firstId >= currentId}">
        			<i class="fas fa-caret-left" onclick="alert('이전 글이 없습니다.');"></i>
        			</c:if>
        			<!-- 글 목록 버튼 -->
        			<a href="/board/cpu/list"><i class="far fa-list-alt"></i></a>
        			<!-- 다음 글 버튼 -->
        			<c:if test="${lastId > currentId}">
        			<form class="nextBtn" action="/board/ram/pagemove" method="get">
        			<i class="fas fa-caret-right" onclick="nextBtn();">
        				<input type="hidden" name="btnName" value="next"/>
        				<input type="hidden" name="idNum" value="${n.id}"/>
        			</i>
        			</form>
        			</c:if>
        			<c:if test="${lastId <= currentId}">
        			<i class="fas fa-caret-right" onclick="alert('다음 글이 없습니다.');"></i>
        			</c:if>
        			
        		</div>
        		<div class="commentBox">
        			<div class="commentTitle">댓글</div>
        			<c:if test="${!empty sessionScope.sessionID}">
        			<div>
        				<form method="post" action="/board/ram/comment">
        				<div class="commentWrite">
        					<textarea class="commentWriteArea" name="commentWriteArea" placeholder="댓글을 입력하세요"></textarea>
        				</div>
        				<input type="hidden" value="${n.id}" name="pageId"/>
        				<input type="hidden" value="${sessionScope.sessionID}" name="userId"/>
        				<div class="commentButton"><input type="submit" value="댓글작성" class="commentButton cmtBtn" onclick="checkValue();"></div>
        				</form>
        			</div>
        			</c:if>
        			<div class="commentList clear">
        				<c:forEach var="c" items="${c}" varStatus="status">
        				<form class="deletTarget" method="get" action="/admin/notice/comment/delete">
        					<div class="commentNick">${c.nickname}</div>
							<div class="commentContent">${c.content}</div>
							<div class="commentRegdate">${c.regdate}</div>
							<input type="hidden" name="cmtId" value="${c.id}" />
							<input type="hidden" name="pageId" value="${n.id }" />
							<input class="deleteBtn" type="submit" value="삭제" onclick="return askDelete();" class="cmtDltBtn"/>
						</form>
						</c:forEach>
					</div>
					</div>
        		</div>
        		
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

