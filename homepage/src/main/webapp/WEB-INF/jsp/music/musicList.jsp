<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--기본url --%>
<c:url var="_BASE_PARAM" value="">	
	<c:param name="musicTitle" value="${vo.musicTitle}"/>
	<c:if test="${not empty vo.searchCondition}">
		<c:param name="searchCondition" value="${vo.searchCondition}"/>
	</c:if>
	<c:if test="${not empty vo.searchKeyword}">
		<c:param name="searchKeyword" value="${vo.searchKeyword}"/>
	</c:if>
</c:url>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>검색 상세페이지</title>
    
 <link rel="preconnect" href="https://fonts.googleapis.com">
 <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
 <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&family=Nanum+Gothic:wght@700;800&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="/asset/front/musicList.css?aa">  
<script src="/js/jquery-3.6.0.js"></script>
</head>
<body>
<section class="slider"> 
    <div id="header">
         <h1 class="logo"><a href="/index.do"><img src="/asset/front/images/logo_03.png" alt="sunnymusic" ></a></h1>
        <nav class="gnb">
            <ul>
                <li><a href="/board/selectList.do">공지사항</a></li>                	
                <li>
                	<c:choose>
						<c:when test="${empty USER_INFO.id}">
							<a href="/login/actionLogin.do" class="login">로그인</a>	
						</c:when>
						<c:otherwise>
							<a href="/login/actionLogout.do"><c:out value="${USER_INFO.name}"/>님 로그아웃</a>
						</c:otherwise>
					</c:choose>
                </li>
            </ul>
        </nav>
     </div>
 <div class="background-img">
 
    <div class="result">'<c:out value="${vo.musicTitle}"/>'에 대한 검색 결과입니다</div>
	    <%-- <div class="c">
			 게시물 총수: <c:out value="${paginationInfo.totalRecordCount}"/>건
	    </div> --%>
	    <div id="table_style">       
	    <table class="list"> 
	       	<thead>
		        <tr>
		            <th class="th1">NO</th>
		            <th class="th">아 티 스 트</th>
		            <th class="th">곡  명</th>
		            <th class="th">추 천</th>
		        </tr>
	        </thead>
	        <tbody>
	        	<c:forEach var="v" items="${MusicList}" varStatus="status">
	        		<tr class="tr">
			            <td class="td">${(vo.pageIndex-1)*10+status.count}</td>
			            <td class="td"><c:out value="${v.listArtist}"/></td>
			            <td class="td"><c:out value="${v.listName}"/></td>
			            <td class="td">
			            	<img class="heart" src="/asset/front/images/heart2.png">
			            </td>
			        </tr>
	        	</c:forEach>
							     <script type="text/javascript">       
							       // 객체 변수 함수 이벤트 조건 반복 배열
							       /*  heart.onclick = function() 
							        {							    	  
  	  							      heart.src = "/asset/front/images/f_heart2.png";
  	  							    	   
							        }  */
							       $('.heart').on("click",function(){
							    	   $(this).toggleClass("on");
							    	   if($(this).hasClass("on")){
// 							    	   if(this.src == 'http://localhost/asset/front/images/heart2.png'){
							    		  this.src = "/asset/front/images/f_heart2.png";
							    	   } else {
							    		   this.src = "/asset/front/images/heart2.png";
							    	   }
							       });
    							</script>   
					</tbody>
				  </table>
				</div>
</div>
 <!--페이징 소스 --> 
 <div id="paging">
	<c:url var="pageUrl" value="/music/musicList.do${_BASE_PARAM}"/>
	<c:set var="pagingParam"><c:out value="${pageUrl}"/></c:set>
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="${pagingParam}"/>
  </div> 
</section>     
</body>
</html>