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
	<h2 style="text-align:center">수정하기</h2>
	<!--  메뉴영역 -->
	<%@include file="/WEB-INF/include/menus.jspf" %>
	
	<br/>
	<br/>
	<!-- 자료입력  -->
	<form action="/PDS/Update?menu_id=${pdsVo.menu_id}" method="POST" enctype="multipart/form-data">
		<table border="1" cellpadding="0" cellspacing="0" width="500" align="center">
			<tr>
				<td width="100" align="center">메뉴이름</td>
				<td width="400">
					<input type="text" name="menu_name" value="${menuVo.menu_name}" readonly/>
				</td>
			</tr>
			<tr>
				<td width="100" align="center">작성자</td>
				<td width="400"><input type="text" name="writer" size="15" value="${map.user_id}" readonly/>
				<!-- value="${mem.user_id}" 로그인한 사용자 아이디처리 -->
				</td>
			</tr>
			<tr>
				<td width="100" align="center">글제목</td>
				<td width="400"><input type="text" name="title" size="15" value="${pdsVo.title}"></td>
			</tr>
			<tr>
				<td width="100" align="center">글내용</td>
				<td width="400"><textarea name="cont" style="width:400; height:150px" >${pdsVo.cont}</textarea></td>
			</tr>
			
			<tr>
				<td width="100" align="center">파일</td>
				<td width="400">
					<!-- <input type="button"  id="addFileBtn" /> -->
					<input type="file" name="upfile"  id="orgFile" size="50" class="upload-hidden" value="${file.sfilename}"/>
					<input type="file" name="upfile2" id="orgFile" size="50" class="upload-hidden" value="${file.sfilename}"/>
					<input type="file" name="upfile3" id="orgFile" size="50" class="upload-hidden" value="${file.sfilename}"/>
					<input type="file" name="upfile4" id="orgFile" size="50" class="upload-hidden" value="${file.sfilename}"/>
				</td>
			</tr>
			
			<tr >
				<td width="100" height="30" align="center">파일명</td>
				<td width="400" colspan="3">
					<c:forEach var="file" items="${filesList}">
					<a href="<c:out value='/download/external/${file.sfilename}'/>">${file.filename}</a>
					</c:forEach>
				</td>
			</tr>
			
			<tr>
				<td width="500" align="center" colspan="2">
					<input type="submit" value="수정" />
				</td>
			</tr>
			<tr>
				<td width="500" align="right" colspan="2">
					<a href="/PDS/List?menu_id=<c:out value="${map.menu_id}"/>">목록</a>
				</td>
			</tr>
			
		</table>
		
		<input type="hidden" name="menu_id"     value="<c:out value='${map.menu_id}'/>"/>
		<input type="hidden" name="idx"         value="<c:out value='${pdsVo.idx}'/>"/>
		<input type="hidden" name="bnum"        value="<c:out value='${pdsVo.bnum}'/>"/>
		<input type="hidden" name="lvl"         value="<c:out value='${pdsVo.lvl}'/>"/>
		<input type="hidden" name="step"        value="<c:out value='${pdsVo.step}'/>"/>
		<input type="hidden" name="nref"        value="<c:out value='${pdsVo.nref}'/>"/>
		<input type="hidden" name="par_id"      value="<c:out value='${pdsVo.par_id}'/>"/>
		<input type="hidden" name="nowpage"     value="<c:out value='${map.nowpage}'/>"/>
		<input type="hidden" name="pagegrpnum"  value="<c:out value='${map.pagegrpnum}'/>"/>
	</form>
</body>
</html>