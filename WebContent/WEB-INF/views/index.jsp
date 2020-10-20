<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/f87cacf99d.js" crossorigin="anonymous"></script>
    <title>OVERCLOCK</title>
    <link href="${pageContext.request.contextPath}/style/index.css" rel="stylesheet" />
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
        	<div class="indexNotice">
        		<div class="indexMenuLabel">
        			<div>번호</div>
        			<div>제목</div>
        			<div>글쓴이</div>
        			<div>작성일</div>
        			<div>조회수</div> 
        		</div>
      		<div>
      		<c:forEach var="n" items="${noticelist}">
        	<div class="noticeList">
        		<a class="notice" href="/notice/detail?id=${n.id}">
        			<div class="noticeId">${n.id}</div>
        			<div class="title">
        				${n.title }
        				<span>[${n.cmtCount}]</span>
        			</div>
        			<div class="writerId">${n.writerId }</div>
        			<div class="regdate"><fmt:formatDate pattern="YYYY-MM-dd" value="${n.regdate }"/></div>
        			<div class="hit"><fmt:formatNumber value="${n.hit }"/></div>
        		</a> 
        	</div>
			</c:forEach>
      			</div>
        	</div>
        	<div class="indexCPU">
        		<div class="indexCpuLabel">
        			<div>번호</div>
        			<div>이미지</div>
	        		<div>제목</div>
        			<div>글쓴이</div>
        			<div>작성일</div>
        			<div>조회</div>
        		</div>
        	<c:forEach var="c" items="${cpuList}">
        	<div class="cpuArticleList">
        		<a class="cpuArticle" href="/board/cpu/detail?id=${c.id}">
        			<div class="ArticleId">${c.id}</div>
        			<div class="thumbnailBox">${c.thumbnail}</div>
        			<div class="title">
        				${c.title }
        				<span>[${c.cmtCount}]</span>
        			</div>
        			<div class="writerId">${c.writerId }</div>
        			<div class="regdate"><fmt:formatDate pattern="YYYY-MM-dd" value="${c.regdate }"/></div>
        			<div class="hit"><fmt:formatNumber value="${c.hit }"/></div>
        		</a> 
        	</div>
			</c:forEach>
        	</div>
        	<div class="indexRAM">
        		<div class="indexRamLabel">
        			<div>번호</div>
        			<div>이미지</div>
	        		<div>제목</div>
        			<div>글쓴이</div>
        			<div>작성일</div>
        			<div>조회</div>
        		</div>
        	<div>
        		<c:forEach var="r" items="${ramList}">
        		<div class="ramArticleList">
        		<a class="ramArticle" href="/board/ram/detail?id=${r.id}">
        			<div class="ArticleId">${r.id}</div>
        			<div class="thumbnailBox">${r.thumbnail}</div>
        			<div class="title">
        				${r.title }
        				<span>[${r.cmtCount}]</span>
        			</div>
        			<div class="writerId">${r.writerId }</div>
        			<div class="regdate"><fmt:formatDate pattern="YYYY-MM-dd" value="${r.regdate }"/></div>
        			<div class="hit"><fmt:formatNumber value="${r.hit }"/></div>
        		</a> 
        	</div>
			</c:forEach>		
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
