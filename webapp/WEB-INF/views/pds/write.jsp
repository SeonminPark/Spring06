<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 style="text-align:center">새글쓰기</h2>
	<!--  메뉴영역 -->
	<%@include file="/WEB-INF/include/menus.jspf" %>
	
	<br/>
	<br/>
	<!-- 자료입력  -->
	<form action="/PDS/Write" method="POST" enctype="multipart/form-data">
		<table border="1" cellpadding="0" cellspacing="0" width="500" align="center">
			<tr>
				<td width="100" align="center">메뉴이름</td>
				<c:choose>
					<c:when test="${map.reply eq 0}">
					<td width="400">
						<select name="menu_name">
						<c:forEach var="menu" items="${menuList}">
							<option>${menu.menu_name}</option>
						</c:forEach>
						</select>
					</td>
					</c:when>
					<c:otherwise>
					<td width="400">
						<input type="text" name="menu_name" value="${menuVo.menu_name}" readonly/>
					</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td width="100" align="center">작성자</td>
				<td width="400"><input type="text" name="writer" size="15" value="${map.user_id}">
				<!-- value="${mem.user_id}" 로그인한 사용자 아이디처리 -->
				</td>
			</tr>
			<tr>
				<td width="100" align="center">글제목</td>
				<td width="400"><input type="text" name="title" size="15" value=></td>
			</tr>
			<tr>
				<td width="100" align="center">글내용</td>
				<td width="400"><textarea name="cont" style="width:400; height:150px"></textarea></td>
			</tr>
			<tr>
				<td width="100" align="center">파일</td>
				<td width="400">
					<!-- <input type="button"  id="addFileBtn" /> -->
					<input type="file" name="upfile"  id="orgFile" size="50" class="upload-hidden"/>
					<input type="file" name="upfile2" id="orgFile" size="50" class="upload-hidden"/>
					<input type="file" name="upfile3" id="orgFile" size="50" class="upload-hidden"/>
					<input type="file" name="upfile4" id="orgFile" size="50" class="upload-hidden"/>
				</td>
			</tr>
			<tr>
				<td width="500" align="center" colspan="2">
					<input type="submit" value="확인" />
				</td>
			</tr>
			<tr>
				<td width="500" align="right" colspan="2">
					<a href="/PDS/List?menu_id=<c:out value="${map.menu_id}"/>">목록</a>
				</td>
			</tr>
		</table>
		
		<input type="hidden" name="menu_id" value="<c:out value='${map.menu_id}'/>"/>
		<input type="hidden" name="bnum"    value="<c:out value='${map.bnum}'/>"/>
		<input type="hidden" name="lvl"     value="<c:out value='${map.lvl}'/>"/>
		<input type="hidden" name="step"    value="<c:out value='${map.step}'/>"/>
		<input type="hidden" name="nref"    value="<c:out value='${map.nref}'/>"/>
		<input type="hidden" name="par_id"  value="<c:out value='${map.par_id}'/>"/>
		<input type="hidden" name="nowpage"  value="<c:out value='${map.nowpage}'/>"/>
		<input type="hidden" name="pagegrpnum"  value="<c:out value='${map.pagegrpnum}'/>"/>
	</form>
</body>
</html>