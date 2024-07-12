<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/dvd/producer/add" method="post">
	<%-- form에선 get과 post만 사용가능 --%>>
		<label>제작사</label>
		<input name="producer" placeholder="제작사명을 입력하세요">	
		<button type="submit">추가</button>
	</form>
</body>
</html>