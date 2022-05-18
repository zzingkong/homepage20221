<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터가져오기</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<style>
 	table, th, td {
 	border: 1px solid #ccc;
 	border-collapse:collapse; 	
 	}
 	th, td {
 	padding :5px;
 	}
</style>
<body>
<table>
<tr>
	<th>제목</th>
	<th>${result.crudSj}</th>
</tr>
<tr>
	<th>작성자</th>
	<th>${result.userNm}</th>
</tr>
<tr>
	<th>작성일</th>
	<th><fmt:formatDate value="${result.frstRegistPnttm}" pattern="yyyy-MM-dd"/></th>
</tr>
<tr>
<td>내용</td>
	<th>${result.crudCn}</th>
</tr>
</table>
   
  <div class="box-btn">
    <c:url var = "uptUrl" value="/crud/crudRegist.do">
      <c:param name="crudId" value="${result.crudId}"/>
    </c:url>
    <a href="${uptUrl}">수정</a>
    
     <c:url var = "delUrl" value="/crud/delete.do">
      <c:param name="crudId" value="${result.crudId}"/>
    </c:url>
     <a href="${delUrl}" class="btn-del"> 삭제</a>
     
     <a href="/crud/selectList.do"> 목록</a>  
  </div>
  
  <script>
   $(document).ready(function(){
	   $(".btn-del").click(function(){
		   if(!confirm("삭제하시겠습니까?")){
			   return false;
		   }
	   });
   });
  
  </script>
  
</body>
</html>