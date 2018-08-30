<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">자료실</h2>
	<!-- 메뉴영역 -->
	<%@include file="/WEB-INF/include/menus.jspf" %>
	<!-- 자료실 영역 -->
		<table border="1" cellpadding="0" cellspacing="0" align="center" width="800">
		<caption><h2>&nbsp;게시판</h2></caption>

		<tr>
			<td width="50" align="center">글번호</td>
			<td width="350" align="center">글제목</td>
			<td width="100" align="center">작성자</td>
			<td width="100" align="center">조회수</td>
			<td width="100" align="center">파일첨부</td>
			<td width="100" align="center">작성일</td>
		</tr>
		<c:forEach var="pds" items="${pdsList}">
			<tr>
				<td width="50" align="center">
					<c:choose>
						<c:when test="${ pds.lvl eq 0}">
							${pds.bnum}
						</c:when>
						<c:otherwise>
						
						</c:otherwise>
					</c:choose>
				</td>
				<td width="250" style="padding-left:${pds.lvl*30}px">
					<c:choose>
						<c:when test="${pds.lvl eq 0 }">
							<c:choose>
								<c:when test="${pds.delnum eq 0}">
									<a href="/PDS/Content?idx=${pds.idx}&menu_id=${menu_id}&nowpage=${pagePdsVo.nowpage}&pagegrpnum=${pagePdsVo.pagegrpnum}">${pds.title}</a>
								</c:when>
								<c:otherwise>
									<del>삭제된 글입니다.</del>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${pds.delnum eq 0}">
									<a href="/PDS/Content?idx=${pds.idx}&menu_id=${menu_id}&nowpage=${pagePdsVo.nowpage}&pagegrpnum=${pagePdsVo.pagegrpnum}"> [답변]${pds.title}</a>	
								</c:when>
								<c:otherwise>
								  [답변] <s>삭제된 글입니다.</s>
								</c:otherwise>
							</c:choose>		
						</c:otherwise>
					</c:choose>
				</td>
				<td width="100" align="center">${pds.writer}</td>
				<td width="100" align="center">${pds.readcount}</td>
				<td width="100" align="center">
					<c:choose>
						<c:when test="${pds.filescount eq 0}">
					
						</c:when>
						<c:otherwise>
							${pds.filescount}개
						</c:otherwise>
					</c:choose>
				</td>
				<td width="100" align="center">${pds.regdate}</td>
			</tr>
		</c:forEach>
		<tr>
			<td width="800" height="30" align="right" colspan="6">
				<!-- 페이징 영역 -->
				<%@include file="/WEB-INF/include/paging.jspf" %>
			 </td>
		</tr>
 		<tr>
			<td colspan="6" align="right"> 
				<a href="/">메인이동</a>
			</td>
		</tr>

	</table>
	
	<a href="/PDS/WriteForm?bnum=0&lvl=0&step=0&nref=0&par_id=0&menu_id=${menu_id}&reply=0&nowpage=${nowpage}&pagegrpnum=${pagegrpnum}">새글 쓰기</a>
	

	
</body>
</html>