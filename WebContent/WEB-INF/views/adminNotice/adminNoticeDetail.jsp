<%@page import="com.overclock.web.entity.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/style/adminNoticeDetail.css" rel="stylesheet" />
<script src="https://kit.fontawesome.com/f87cacf99d.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js" defer></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/adminNoticeList.js" defer></script>
<title>공지사항</title>
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
      <!-- 헤더 -->
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
        			<div>${n.writerId }</div>
        			<div><fmt:formatNumber value="${n.hit }"/></div>
        			<div><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${n.regdate }"/></div>
        		</div>
        		<div class="file clear">
        			<c:if test="${n.files != null }">
        			<div class="fileLabel">첨부파일</div>
        			<div class="fileLink">
        				<c:forTokens var="fileName" items="${n.files }" delims=",">
        					<a download class="fileItem" href="/upload/${fileName}" >${fileName}</a>
        				</c:forTokens>
        			</div>
        			</c:if>
        		</div>
        		<div class="conBody clear">${n.content }</div>
        		<div>
        	<div class="adminBtn">
        		<button><a href="/admin/notice/modify?id=${n.id}">수정</a></button>
        	</div>
        	<div class="btnBox">
        		<c:set var="lastId" value="${lastId}"/>
        		<c:if test="${n.id > 1}">
        		<button class="prevBtn"><a href="/admin/notice/detail?id=${n.id-1}">이전</a></button>
        		</c:if>
        		<c:if test="${n.id <= 1}">
        		<button class="prevBtn"><a onclick="alert('이전 글이 없습니다.');">이전</a></button>
        		</c:if>
        		<button class="listBtn"><a href="/admin/notice/list">목록</a></button>
        		<c:if test="${n.id < lastId }">
        		<button class="nextBtn"><a href="/admin/notice/detail?id=${n.id+1}">다음</a></button>
        		</c:if>
        		<c:if test="${n.id >= lastId }">
        		<button class="nextBtn"><a onclick="alert('다음 글이 없습니다.');">다음</a></button>
        		</c:if>
        	</div>
        	</div>
        		<div class="commentBox">
        			<div class="commentTitle">댓글</div>
        			<c:if test="${!empty sessionScope.sessionID}">
        			<div>
        				<form method="post" action="/admin/notice/comment">
        				<div class="commentWrite">
        					<textarea class="commentWriteArea" name="commentWriteArea" placeholder="댓글을 입력하세요"></textarea>
        				</div>
        				<input type="hidden" value="${n.id}" name="pageId"/>
        				<input type="hidden" value="${sessionScope.sessionID}" name="userId"/>
        				<div class="commentButton"><input type="submit" value="댓글작성" class="commentButton cmtBtn"></div>
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
							<input type="submit" value="삭제" onclick="return askDelete();" class="cmtDltBtn"/>
						</form>
						</c:forEach>
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

