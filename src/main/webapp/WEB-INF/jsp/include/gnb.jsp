
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header h-100 bg-info d-flex justify-content-between">
	<%-- logo를 y축으로 .header의 가운데에 위치 --%>
	<div class="logo d-flex align-items-center">
		<h1 class="text-white ml-3"><a href="/timeline/timeline_view" class="text-white">Marondalgram</a></h1>
	</div>
	<div class="login-info d-flex align-items-end mb-3 mr-5">
		
		<c:choose>
			<c:when test="${not empty userId}">
				<div>
					
					<span class="text-white">${userName}님 안녕하세요</span> 
					<a href="/user/sign_out">로그아웃</a>
				</div>
			</c:when>
			<c:otherwise>
				<div>
					
					<a href="/user/sign_in_view">로그인</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>