<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/common.css" />
</head>
<body>  
  <table>
   <caption><h2>글내용보기</h2></caption>
   <tr>
    <td width="100" align="center">글번호</td>
    <td width="100" align="center"> ${ boardVo.idx } </td>
   </tr>
   <tr>
    <td width="100" align="center">제목</td>
    <td width="100" align="center"> ${ boardVo.title } </td>
   </tr>
   <tr>
    <td width="100" align="center">이름</td>
    <td width="100" align="center"> ${ boardVo.name } </td>
   </tr>
   <tr>
    <td width="100" align="center">날짜</td>
    <td width="100" align="center"> ${ boardVo.regdate } </td>
   </tr>
   <tr>
    <td  align="center" colspan="2">     
     <a href="/Board/UpdateForm?idx=${ boardVo.idx }">수정</a>&nbsp;&nbsp;
     <a href="/Board/Delete?idx=${ boardVo.idx }">삭제</a>&nbsp;&nbsp;     
     <a href="/Board/List">목록으로</a>&nbsp;&nbsp;
    </td>        
   </tr>
  </table> 
</body>
</html>




