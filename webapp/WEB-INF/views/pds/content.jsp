<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>내용보기</h2>
	<!-- 메뉴영역 -->
	<%@include file="/WEB-INF/include/menus.jspf" %>
	<table border="1" cellpadding="0" cellspacing="0" width="500" align="center">
		<caption>
			<h2>${pdsVo.menu_name}게시물 내용보기</h2>
		</caption>
		<tr>
			<td width="100" height="30" align="center">게시판 이름</td>
			<td height="30" colspan="3">
				${pdsVo.menu_name}
			</td>
		</tr>
		<tr>
			<td width="100" height="30" align="center">작성자</td>
			<td width="150" height="30">
				${pdsVo.writer}
			</td>
			<td width="100" height="30" align="center">작성일</td>
			<td width="150" height="30">
				${pdsVo.regdate}
			</td>
		</tr>
		<tr>
			<td width="100" height="30" align="center">글번호</td>
			<td width="150" height="30">
				${pdsVo.bnum}
			</td>
			<td width="100" height="30" align="center">조회수</td>
			<td width="150" height="30">
				${pdsVo.readcount}
			</td>
		</tr>
		<tr>
			<td width="100" height="30" align="center">제목</td>
			<td width="400" height="30" colspan="3">
				${pdsVo.title}
			</td>
		</tr>
		<tr>
			<td width="500" height="300" colspan="4">
				${pdsVo.cont}
			</td>
		</tr>
		
		<c:forEach var="file" items="${filesList}">
		<tr >
			<td width="100" height="30" align="center">파일명</td>
			<td width="400" colspan="3">
				<a href="<c:out value='/download/external/${file.sfilename}'/>">${file.filename}</a>
			</td>
		</tr>
		</c:forEach>
		
		<tr>
			<td width="500" colspan="4">
				<a href="/PDS/List?menu_id=${pdsVo.menu_id}&nowpage=${nowpage}&pagegrpnum=${pagegrpnum}">목록</a>&nbsp;&nbsp;
				<a href="/PDS/WriteForm?bnum=0&lvl=0&step=0&nref=0&par_id=0&menu_id=${pdsVo.menu_id}&reply=0&nowpage=${nowpage}&pagegrpnum=${pagegrpnum}">새글쓰기</a>&nbsp;&nbsp;
				<a href="/PDS/WriteForm?bnum=${pdsVo.bnum}&lvl=${pdsVo.lvl}&step=${pdsVo.step}&nref=${pdsVo.nref}&par_id=${pdsVo.idx}&menu_id=${pdsVo.menu_id}&reply=1&nowpage=${nowpage}&pagegrpnum=${pagegrpnum}">답글쓰기</a>&nbsp;&nbsp;
				<c:choose>
					<c:when test="${user_id eq pdsVo.writer}">
						<a href="/PDS/UpdateForm?idx=${pdsVo.idx}&menu_id=${pdsVo.menu_id}&nowpage=${nowpage}&pagegrpnum=${pagegrpnum}">수정</a>&nbsp;&nbsp;
						<a href="/PDS/Delete?idx=${pdsVo.idx}&nref=${pdsVo.nref}&lvl=${pdsVo.lvl}&step=${pdsVo.step}&menu_id=${pdsVo.menu_id}&par_id=${pdsVo.par_id}&nowpage=${nowpage}&pagegrpnum=${pagegrpnum}">삭제</a>&nbsp;&nbsp;
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
</body>
</html>