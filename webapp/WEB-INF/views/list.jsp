<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	#wrap {  width:60%; margin:0 auto; display: flex; flex-direction: column; }
	.line { border:1px solid black; width:100%; height:40px; display:flex;}
	.box  { margin-tor:2px; }
	.box:nth-child(1) { text-align : center; border:1px solid black;  height:40px; float:left; flex-basis : 10%;}
	.box:nth-child(2) { border:1px solid black;  height:40px; float:left; flex-basis : 50%; }
	.box:nth-child(3) { text-align : center; border:1px solid black;  height:40px; float:left; flex-basis : 15%;}
	.box:nth-child(4) { text-align : center; border:1px solid black;  height:40px; float:left; flex-basis : 15%;}
	.box:nth-child(5) { text-align : center; border:1px solid black;  height:40px; float:left; flex-basis : 10%;}
	a { text-align : right;}
	h2 { margin:0 auto; text-align:center;}
</style>
<title>답변형 게시판</title>
</head>
<body>

	<!-- 메뉴바 -->
 	<table border="1" cellpadig="0" cellspacing="0" width="500" align="center">
		<tr>
			<c:forEach var="menu" items="${MenuList}">
				<td width="100" height="30" align="center">
					<a href="/Board/List?menu_id=${menu.menu_id}">${menu.menu_name}</a>
				</td>
			</c:forEach>
		</tr>
	</table>


	<!-- 리스트  -->
	<h2>&nbsp;게시판</h2>
	
	<div id="wrap">
	<a href="/Board/WriteForm?bnum=0&lvl=0&step=0&nref=0&par_id=0&menu_id=MENU01">새 글쓰기</a>	
		<div class="line">
			<div class="box">번호</div>
			<div class="box">글제목</div>
			<div class="box">글쓴이</div>
			<div class="box">작성일</div>
			<div class="box">조회수</div>
		</div>
		
		<c:forEach var="brd" items="${BoardList}">
		<div class="line">
			<c:choose>
					<c:when test="${ brd.lvl eq 0}">
						<div class="box"> ${brd.bnum}</div>
					</c:when>
					<c:otherwise>
						<div class="box">&nbsp;</div>
					</c:otherwise>
			</c:choose>
			<c:choose>
					<c:when test="${brd.lvl eq 0 }">
						<c:choose>
							<c:when test="${brd.delnum eq 0}">
								<div class="box" style="padding-left:${brd.lvl*30}px"><a href="/Board/View?idx=${brd.idx}"> ${brd.title}</a></div>
							</c:when>
							<c:otherwise>
								<div class="box" style="padding-left:${brd.lvl*30}px">삭제된 글입니다.</div>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${brd.delnum eq 0}">
								<div class="box"><a href="/Board/View?idx=${brd.idx}"> [답변]${brd.title}</a></div>
							</c:when>
							<c:otherwise>
							  <div class="box" style="padding-left:${brd.lvl*30}px">[답변]삭제된 글입니다.</div>
							</c:otherwise>
						</c:choose>						
					</c:otherwise>
			</c:choose>
			
			<div class="box"> ${brd.writer}</div>
			<div class="box"> ${brd.regdate}</div>
			<div class="box"> ${brd.readcount}</div>
		</div>
		</c:forEach>
	<a href="/">메인이동</a>
	</div>
	
	
	
	<table border="1" cellpadding="0" cellspacing="0" align="center" width="600">
		<caption><h2>&nbsp;게시판</h2></caption>
		<tr>
			<td colspan="5" align="right"> 
				<a href="/Board/WriteForm?bnum=0&lvl=0&step=0&nref=0&par_id=0&menu_id=MENU01">새 글쓰기</a>
			</td>
		</tr>
		<tr>
			<td width="50" align="center">번호</td>
			<td width="250" align="center">글제목</td>
			<td width="100" align="center">글쓴이</td>
			<td width="100" align="center">작성일</td>
			<td width="100" align="center">조회수</td>
		</tr>
	<c:forEach var="brd" items="${BoardList}">
		<tr>
			<td width="50" align="center">
				<c:choose>
					<c:when test="${ brd.lvl eq 0}">
						${brd.bnum}
					</c:when>
					<c:otherwise>
					
					</c:otherwise>
				</c:choose>
			</td>
			<td width="250" style="padding-left:${brd.lvl*30}px">
				<c:choose>
					<c:when test="${brd.lvl eq 0 }">
						<c:choose>
							<c:when test="${brd.delnum eq 0}">
								<a href="/Board/View?idx=${brd.idx}">${brd.title}</a>
							</c:when>
							<c:otherwise>
								<del>삭제된 글입니다.</del>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${brd.delnum eq 0}">
								<a href="/Board/View?idx=${brd.idx}"> [답변]${brd.title}</a>	
							</c:when>
							<c:otherwise>
							  [답변] <s>삭제된 글입니다.</s>
							</c:otherwise>
						</c:choose>
					
					
											
					</c:otherwise>
				</c:choose>
			</td>
			<td width="100" align="center">${brd.writer}</td>
			<td width="100" align="center">${brd.regdate}</td>
			<td width="100" align="center">${brd.readcount}</td>
		</tr>
	</c:forEach>
		<tr>
			<td colspan="5" align="right"> 
				<a href="/">메인이동</a>
			</td>
		</tr>

	</table>
</body>
</html>