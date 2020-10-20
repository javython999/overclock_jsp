<%@page import="com.overclock.web.entity.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/style/adminNoticeWrite.css" rel="stylesheet" />
<script src="https://kit.fontawesome.com/f87cacf99d.js" crossorigin="anonymous"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/12.0.0/classic/ckeditor.js"></script>
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
        	<form method="post" action="write" enctype="multipart/form-data">
        		<div class="conTitle">
        			<input type="text" name="title" placeholder="제목을 입력하세요">
        			<div><label for="pub">공개</label><input id="pub" type="checkbox" name="open" value="true" checked/></div>
        		</div>
        		<div class="conInfo">
        			<div>${n.writerId }</div>
        			<div><fmt:formatNumber value="${n.hit }"/></div>
        			<div><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${n.regdate }"/></div>
        		</div>
       <!--  --> 		<div class="file clear">
        			<div class="fileLabel">첨부파일</div>
        			<div class="fileLink">
        				<input type="file" name="file" />
        			</div>
        		</div>
        		<div class="conBody clear">
        			<textarea class="content" name="content" placeholder="내용을 입력하세요"></textarea>
				</div>
        	</div>
        	<div class="btnBox">
        		<button><a href="/admin/notice/list">취소</a></button>
        		<input type="submit" value="등록"/>
        	</div>
        	</form>
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
    <%-- <script src="${pageContext.request.contextPath}/js/ckeditor.js"></script> --%>
  </body>
</html>

