<%@page import="com.overclock.web.entity.Notice"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/style/boardWrite.css" rel="stylesheet" />
<script src="https://kit.fontawesome.com/f87cacf99d.js" crossorigin="anonymous"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/12.0.0/classic/ckeditor.js"></script>
<title>RAM_OVERCLOCK</title>
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
        	<form method="post" action="/board/ram/update" />
        		<div class="conTitle">
        			<input type="hidden" name="id" value="${n.id }">
        			<input type="text" name="title" placeholder="제목을 입력하세요" value="${n.title}"/>
        		</div>
        		<div class="conInfo">
        			<input type="hidden" name="writerId" valuse="${sessioScope.sessionID}"/>
        			<lable for="ramSelect">RAM선택</lable>
        			<select name="RAM" id="ramSelect">
	        			<optgroup label="GSkill">
	        				<option value="GSkll_Flare_X">GSkll_Flare_X</option>
	        				<option value="GSkll_FORTIS">GSkll_FORTIS</option>
	        				<option value="GSkll_Ripjaws_4">GSkll_Ripjaws_4</option>
	        				<option value="GSkll_Ripjaws_V">GSkll_Ripjaws_V</option>
	        				<option value="GSkll_Ripjaws_Z">GSkll_Ripjaws_Z</option>
	        				<option value="GSkll_Trident_Z_NEO">GSkll_Trident_Z_NEO</option>
	        				<option value="GSkll_Trident_Z_RGB">GSkll_Trident_Z_RGB</option>
	        				<option value="GSkll_Trident_Z_Royal_Gold">GSkll_Trident_Z_Royal_Gold</option>
	        				<option value="GSkll_Trident_Z_Royal_Silver">GSkll_Trident_Z_Royal_Silver</option>
	        				<option value="GSkll_Trident_Z">GSkll_Trident_Z</option>
	        			</optgroup>
        			</select>
        		</div>       
        		<div class="conBody clear">
        			<textarea class="content" name="content" placeholder="내용을 입력하세요">${n.content}</textarea>
				</div>
        	</div>
        	<div class="btnBox">
        		<button><a href="/board/ram/list">취소</a></button>
        		<input type="submit" value="수정" onclick="return checkValue();"/>
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

