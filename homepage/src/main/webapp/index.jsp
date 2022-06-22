<%--
  Class Name : index.jsp
  Description : 최초화면으로 메인화면으로 이동한다.(system)
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javaScript">document.location.href="<c:url value='/cmm/main/mainPage.do'/>"</script>
 
<!-- 
<script type="text/javaScript">document.location.href="<c:url value='/cmm/main/mainPage.do'/>"</script> 여기로 링크를 이동하게 하면 되는건가봐--> 

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
<title>수업용 게시판</title>
<link href="" rel=""/>
</head>
<body>