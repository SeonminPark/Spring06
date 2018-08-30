<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/Menus/Update" method="POST">
		<table border="1" cellpadding="0" cellspacing="0" align="center" width="500">
			<caption><h2>메뉴 추가</h2></caption>
			<tr>
				<td width="25" height="30" align="center">메뉴아이디</td>
				<td width="25" height="30" align="center">
					<input type="text" size="10" name="menu_id" value="${menu.menu_id}" readonly/>
				</td>
				<td width="25" height="30" align="center">메뉴시퀀스</td>
				<td width="25" height="30" align="center">
					<input type="text" name="menu_seq" size="10" value="${menu.menu_seq}"/>
				</td>
			</tr>
			<tr>
				<td width="100" height="30" align="center" colspan="2">메뉴이름</td>
				<td colspan="2">
					<input type="text" name="menu_name" size="35" value="${menu.menu_name}"/>
				</td>
			</tr>
			<tr>
				<td width='500' height='30' align='center' colspan='4'>
					<input type="submit" value="메뉴수정"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>