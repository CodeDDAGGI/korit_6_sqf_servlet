<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean isSuccess = (boolean)request.getAttribute("isSuccess");
%>    
    <%-- post요청할때 인코딩된건 다깨짐 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	html,body {
		height: 100%;
	}
	
	.container {
		display: flex;
		justify-content :center;
		align-items: center;
		
		width: 100%;
		height: 100%;
	}
	
	.msg-box {
		display: flex;
		flex-direction:column;
		align-items:center;
		justify-content:center;
		box-sizing:border-box;
		border: 2px solid #dbdbdb;
		width: 400px;
		height: 400px;
	}
	
</style>
</head>
<body>
	<div class="container">
		<div class="msg-box">
		<%-- isSuccess를 다운캐스팅해서 조건 추가 --%>
		<% 
			if(isSuccess) {
		%>
				<h1>제작사 추가 성공!!</h1>		
		<%
			} else {
		%>
				<h1>제작사 추가 실패 !!</h1>
		<% 
			}
		%>
		
			<button onclick="location.href = 'http://localhost:8080/dvd/producer/add'">확인</button>
			<button onclick="history.back()">뒤로가기</button>
		</div>
	</div>
</body>
</html>