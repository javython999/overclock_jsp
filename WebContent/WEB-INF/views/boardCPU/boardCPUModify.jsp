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
        	<form method="post" action="/board/cpu/update" />
        		<div class="conTitle">
        			<input type="hidden" name="id" value="${n.id }">
        			<input type="text" name="title" placeholder="제목을 입력하세요" value="${n.title}"/>
        		</div>
        		<div class="conInfo">
        			<input type="hidden" name="writerId" valuse="${sessioScope.sessionID}"/>
        			<lable for="cpuSelect">CPU선택</lable>
        			<select name="CPU" id="cpuSelect">
	        			<optgroup label="인텔">
	        				<optgroup label="10세대 i9">
	        				<option value="i9-10900k">i9-10900K</option>
	        				<option value="i9-10900">i9-10900</option>
	        				<option value="i9-10850">i9-10850K</option>
	        				<option value="i9-10900KF">i9-10900KF</option>
	        				<option value="i9-10900F">i9-10900F</option>
	        				</optgroup>
	        				<optgroup label="10세대 i7">
	        				<option value="i7-10700k">i7-10700K</option>
	        				<option value="i7-10700">i7-10700</option>
	        				<option value="i7-10700KF">i7-10700KF</option>
	        				<option value="i7-10700F">i7-10700F</option>
	        				</optgroup>
	        				<optgroup label="10세대 i5">
	        				<option value="i5-10600k">i5-10600K</option>
	        				<option value="i5-10600">i5-10600</option>
	        				<option value="i5-10500">i5-10500</option>
	        				<option value="i5-10400">i5-10400</option>
	        				<option value="i5-10600KF">i5-10600KF</option>
	        				<option value="i5-10400F">i5-10400F</option>
	        				</optgroup>
	        				<optgroup label="10세대 i3">
	        				<option value="i3-10320">i3-10320</option>
	        				<option value="i3-10300">i3-10300</option>
	        				<option value="i3-10100">i3-10100</option>
	        				</optgroup>
	        			</optgroup>
	        			<optgroup label="AMD">
	        				<optgroup label="3세대 Ryzen9">
	        				<option value="Ryzen9-3950x">Ryzen9 3950x</option>
	        				<option value="Ryzen9-3900x">Ryzen9 3900x</option>
	        				</optgroup>
	        				<optgroup label="3세대 Ryzen7">
	        				<option value="Ryzen7-3800x">Ryzen7 3800x</option>
	        				<option value="Ryzen7-3700x">Ryzen7 3700x</option>	        				
	        				</optgroup>
	        				<optgroup label="3세대 Ryzen5">
	        				<option value="Ryzen5-3600x">Ryzen5 3600x</option>
	        				<option value="Ryzen5-3600">Ryzen5 3600</option>
	        				<option value="Ryzen5-3500x">Ryzen5 3500x</option>
	        				<option value="Ryzen5-3500">Ryzen5 3500</option>
	        				</optgroup>
	        				<optgroup label="3세대 Ryzen3">
	        				<option value="Ryzen3-3300x">Ryzen3 3300x</option>
	        				<option value="Ryzen3-3100">Ryzen3 3100</option>
        				</optgroup>
        			</optgroup>
        			</select>
        		</div>       
        		<div class="conBody clear">
        			<textarea class="content" name="content" placeholder="내용을 입력하세요">${n.content}</textarea>
				</div>
        	</div>
        	<div class="btnBox">
        		<button><a href="/board/cpu/list">취소</a></button>
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

