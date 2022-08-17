<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>     
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<title>데이터 가져오기!</title>
</head>
<style>
	table,th,td {
	border: 1px solid #ccc;
	border-collapse:collapse;
	}
	th, td {
	padding : 5px;
	}
	#paging_div ul li {
	display: inline-block;
	padding:5px;	
	}
	a {
	text-decoration: none;	
	}
</style>
<body>

<table>
 <thead>
  <tr>
  		<th>ID</th>
  		<th>제목</th>
  		<th>작성자</th>
  		<th>작성일</th>
  		<th>관리</th>
  </tr>
  </thead>
  <tbody>
  	
     <c:forEach var="result" items="${resultList}">
     <tr>
      	<td><c:out value="${result.crudId}"/></td>
      	<td>
      	<c:url var="viewUrl" value="/crud2/select.do"> 
      	<c:param name="crudId" value="${result.crudId}"/>
         </c:url>
      <a href="${viewUrl}"><c:out value="${result.crudSj}"/></a>
      </td>
      
      <td><c:out value="${result.userNm}"/></td>         
      <td><c:out value="${result.frstRegistPnttm}"/></td>
      <td><c:url var="delUrl" value="/crud2/delete.do">
      <c:param name="crudId" value="${result.crudId}"/>
      </c:url>
      <form>
      <button type="submit"><a href="${delUrl}" class="btn-del">삭제</a></td>     
      </form> 
     </tr>
    </c:forEach>
 </tbody>   
   </table>   
   
   <div id="paging_div">   
     <ul class="paging_align">
      <c:url var ="pageUrl" value="/crud2/selectList.do?"/>
      <c:set var ="pagingParam"><c:out value="${pageUrl}"/></c:set>
      <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="${pagingParam}"/> 
     </ul>   
   </div>
   <form>
   <a href="/crud2/crud2Regist.do">글쓰기</a>
   </form>
     
</body>
</html>