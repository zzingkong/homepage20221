<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>     
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    

<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
	border:1px solid #ccc;
	border-collapse:collapse;
}
th {
	padding:15px
}
tr>td:nth-child(even) {
 width:300px;
}
a {
	text-decoration: none;	
}

</style>
<meta http-equiv="Content-Language" content="ko">
<title>데이터 가져오기!</title>
</head>
<body>
<c:choose>
 <c:when test="${not empty searchVO.crudId}">
   <c:set var ="actionUrl" value="/crud2/update.do"/>
 </c:when>
 <c:otherwise>
   <c:set var="actionUrl" value="/crud2/insert.do"/>
  </c:otherwise>
  </c:choose>

<!-- 등록폼 -->

<table border="1">
   
   <form action="${actionUrl}" method="post" name="crud2VO">
   <input type="hidden" name="crudId" value="${result.crudId}"/>
   
   <tr>
   <th>
   <label for="crudSj">제목</label> 
   </th>
   <td>
   <input type="text" id="crudSj" name="crudSj" value="${result.crudSj}"/> 
   <br/>
   </td> 
   </tr>
    
   <tr> 
   <th>
   <label for="userNm">작성자</label>
   </th>   
   <td>
   <input type="text" id="userNm" name="userNm" value="${result.userNm}"/> 
   <br/>
   </td>  
   </tr>
   
   <tr>
   <th>
   <label for="crudCn">내용</label>
   </th>   
   <td>
   <textarea rows="10" name="crudCn"><c:out value="${result.crudCn}"/></textarea>
   <br/> 
   </td>
   </tr>
</table>

   <c:choose>   
    <c:when test="${not empty searchVO.crudId}">
  	<button type="submit">수정</button> 
   </c:when>
    <c:otherwise>
    	<button type="submit">등록</button>
     </c:otherwise> 
    </c:choose>  
    <button type="submit"><a href="/crud2/selectList.do">취소</a></button>
 </form> 
</body>
</html>