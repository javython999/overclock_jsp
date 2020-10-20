<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원가입</title>
    <link href="${pageContext.request.contextPath}/style/memberJoin.css" rel="stylesheet" />
     <script src="https://kit.fontawesome.com/f87cacf99d.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/join.js"></script>
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
      			<span class="welcomMsg">${sessionScope.sessionID}님 반갑습니다.</span>
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
            <li><a href="#"><i class="fas fa-memory"></i>RAM_OC</a></li>
          </ul>
        </aside>
        <section id="content">
        	<form method="post" action="/member/join" class="form" name="userInfo" onsubmit="return checkValue()">
        		<div class="joinForm">
        			<label for="id">아이디</label><input type="text" name="id" id="id" class="inputId" onkeyup="idOverlapCheck(this)"/>
        			<div class="idcheckMsg"></div>
        			<input type="hidden" id="idchecker"/>
        			<label for="pw">비밀번호</label><input type="password" name="pw" id="pw" class="inputPw"/>
        			<label for="pwc">비밀번호 확인</label><input type="password" name="pwc" id="pwc"class="inputPwc"/>
        			<label for="nic">닉네임</label><input type="text" name="nic" id="nic" class="inputNic" onkeyup="nickOverlapCheck(this)"/>
        			<div class="nickcheckMsg"></div>
        			<input type="hidden" id="nickchecker"/>
        			<label for="name">이름</label><input type="text" name="name" id="name" class="inputName"/>
        			<label for="email">이메일</label><input type="text" name="email" id="email" class="inputEmail" />
        		</div>
        		<div class="joinBtnBox">
        			<button><a href="/index">취소</a></button>
        			<input type="submit" value="가입"/>
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
  </body>
</html>
