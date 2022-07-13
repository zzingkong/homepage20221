<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>     
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<title>JSTL</title>
</head>
<body>
 	<form id="frm" action="/temp/insertAjax.do" method-"post" name="tempVO">
		<label for="tempVal">값정보 : </label>
		<input type="text" id="tempVal" name="tempVal" value="${result.tempVal}"/>
		<button id="btn-submit" type="button">등록</button>
	</form>
	
	<div id="ajax-box">
		<c:import url="/temp/ajaxList.do" charEncoding="utf-8">
		</c:import>	
	</div>
<script>
	$(document).ready(function(){
		$("#btn-submit").click(function(){
			var tempVal = $("tempVal").val();
			
			if(tempVal) {
				$.ajax({
					url: "/temp/ajaxList.do",
					type: "post",
					data: {"tempVal" : tempVal},
					dataType: function(data) {
						$("#ajax-box").html(data);						
					}, error : function() {
						alert("error : list");
					}
				});
			}else {
				alert("내용을 입력해주세요.");
			}
		});		
	});
</body>
</html>