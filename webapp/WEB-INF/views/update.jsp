<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="/Board/Update">
	<input type="hidden" name="menu_id" value="${brd.menu_id}" />
	<table border="1" cellpadding="0" cellspacing="0" width="500" align="center">
		<caption>
			<h2>게시물 수정</h2>
		</caption>
		<tr>
			<td width="100" height="30" align="center">번호</td>
			<td width="150" height="30">
				<input type="text" size="15" name="idx" value="${brd.idx}" readonly/>
			</td>
		</tr>
		<tr>
			<td width="100" height="30" align="center">작성자</td>
			<td width="150" height="30">
				<input type="text" size="15" value="${brd.writer}" readonly/>
			</td>
		</tr>
		<tr>
			<td width="100" height="30" align="center">제목</td>
			<td width="400" height="30" colspan="3">
				<input type="text" name="title" size="50" value="${brd.title}"/>
			</td>
		</tr>
		<tr>
			<td width="100" height="30" align="center">내용</td>
			<td width="400" >
				<textarea name="cont" style="width:400px; height:150px">${brd.cont}</textarea>
			</td>
		</tr>
		<tr>
			<td width="500" height="30">
				<input type="submit" value="확인" />
				<a href="/Board/List">목록</a>

			</td>
		</tr>
	</table>
</form>
</body>
</html>