<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/js/WriteForm.js"></script>
<title>게시판(새글쓰기)</title>



</head>
<body>
<form method="POST" action="/Board/Write" id="frmWrite">
	<input type="hidden" name="menu_id" value="${map.menu_id}"/>
	<input type="hidden" name="par_id" value="${map.par_id}"/>
	<input type="hidden" name="bnum" value="${map.bnum}"/>
	<input type="hidden" name="lvl"  value="${map.lvl}"/>
	<input type="hidden" name="step" value="${map.step}"/>
	<input type="hidden" name="nref" value="${map.nref}"/>

	
	<table border="1" cellpadding="0" cellspacing="0" width="500" align="center">
		<caption>
			<h2>글쓰기</h2>
		</caption>
		<tr>
			<td width="100" height="30" align="center">게시판 아이디</td>
			<td width="150" height="30">
				<select name="menu_name">
					<c:forEach var="menu" items="${MenuList}">
						<option>${menu.menu_name}</option>
					</c:forEach>
				</select>
				<%-- <input type="text"  name="menu_id" size="15" value="${map.menu_id}"/> --%>
			</td>
		</tr>
		<tr>
			<td width="100" height="30" align="center">작성자</td>
			<td width="150" height="30">
				<input type="text" name="writer" size="15" id="writer"/>
			</td>
		</tr>
		<tr>
			<td width="100" height="30" align="center">제목</td>
			<td width="400" height="30" colspan="3">
				<input type="text" name="title" size="50" id="title"/>
			</td>
		</tr>
		<tr>
			<td width="100" height="30" align="center">내용</td>
			<td width="400" >
				<textarea name="cont" style="width:400px; height:150px" id="cont"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" width="500" height="30">
				<input type="submit" value="확인" id="btn1"/>
				<input type="image" src="" id="btn2"/>
				<button id="btnOk" id="btn3">확인</button>
				<input type="button" value="확인" id="btn4"/>
				<a id="btn5" href="/Board/Content">목록</a>
				<a href="/Board/List?menu_id=${map.menu_id}">목록</a>
				<a href="/">메인이동</a>
			</td>
		</tr>
	</table>
</form>
</body>
</html>