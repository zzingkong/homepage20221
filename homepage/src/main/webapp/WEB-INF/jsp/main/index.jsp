<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&family=Nanum+Gothic:wght@700;800&family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Concert+One&display=swap" rel="stylesheet">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
     <title>sunnymusic</title>
    <link rel="stylesheet" type="text/css" href="/asset/front/style.css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
    <section class="slider">  

	 <div id="header">
        <h1 class="logo"><img src="/asset/front/images/logo_03.png" alt="sunnymusic"></h1>
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
     	
     <div class="sl-txt">
     	<script>
	     	var typingBool = false; 
	     	var typingIdx=0; 
	     	var liIndex = 0;
	     	var liLength = $(".typing-txt>ul>li").length;
	     	var del = -1;
	     	var repeatInt= null;
	     	var tyInt = null;


	     	// 타이핑될 텍스트를 가져온다 
	     	var typingTxt = $(".typing-txt>ul>li").eq(liIndex).text(); 
	
	     	typingTxt=typingTxt.split(""); // 한글자씩 자른다. 
	
	     	if(typingBool==false){ 
	     	  // 타이핑이 진행되지 않았다면 
	     	    typingBool=true; 
	     	    tyInt = setInterval(typing,200); // 첫번재 반복동작 
	     	} 
	     	     
	     	function typing(){ 
	     	  if(typingIdx<typingTxt.length){ 
	     	    // 타이핑될 텍스트 길이만큼 반복 
	     	   $(".typing").append(typingTxt[typingIdx]); 
	     	    // 한글자씩 이어준다. 
	     	     typingIdx++; 
	     	    if(typingIdx == typingTxt.length){
	     	      //첫번째 단어가 써지면 1분쉰다.
	     	        clearInterval(tyInt);
	     	         setTimeout(function(){
	     	           tyInt = setInterval(typing,200);
	     	         },1000);
	     	       }
	     	   } else{ 
	     	     
	     	     //한문장이끝나면
	     	       if(-typingTxt.length-1 < del ){
	     	         //한글자씩 지운다.
	     	          $(".typing").html(typingTxt.slice(0, del))
	     	          del--;
	     	       }else{
	     	         if(liIndex >= liLength-1){
	     	              liIndex=0;
	     	         }else{
	     	           liIndex++;
	     	         }
	     	         
	     	         //변수초기화 
	     	         typingIdx=0;
	     	         del= -1;
	     	         typingTxt = $(".typing-txt>ul>li").eq(liIndex).text(); 
	     	         
	     	         //1분후 다음분장 타이핑 
	     	         clearInterval(tyInt);
	     	         setTimeout(function(){
	     	           tyInt = setInterval(typing,200);
	     	         },1000);
	     	       }  	     
	     	    } 
	     	}       	
     	</script>
        <div class="typing-txt">
	          <ul>
			    <li>기분은</li>			     
			</ul>	
		</div> 
			오늘 너의 <p class="typing"></p> 어때?
	  
        
        	<form id="list_action" action="/music/musicList.do">        	
        	<div class="main_search">
            	<input type="text" placeholder="검색" name="musicTitle">
           		<span id="action"><img class="search2" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"></span>           	
       			<span class="tag">#기쁨</span>    
        		<span class="tag">#이별</span>  
        		<span class="tag">#여행</span> 
    		<div>        
       		</form> 
       		<script type="text/javascript"> 
       			document.querySelector('#action').onclick=function(){
       				document.querySelector('#list_action').submit();
       			}
       		</script>   
        </div>
    </section>
    <section class="content">       
        <div class="item-wrap">
            <h3 class="m_title">오늘의 노래</h3>
             <span class="wrap">
		        <span><img src="../../../asset/front/images/PSY.JPG"><a class="fir" href="">PSY</a></span>
		        <span><img src="../../../asset/front/images/IDLE.JPG"><a class="sec" href="">IDLE</a></span>
		        <span><img src="../../../asset/front/images/Im.JPG"><a class="thi" href="">Im</a></span>
		        <span><img src="../../../asset/front/images/IVE.JPG"><a class="for" href="">IVE</a></span>
		        <span><img src="../../../asset/front/images/BTS.JPG"><a class="fif" href="/music/musicselectList.do">BTS</a></span>
		        <span><img src="../../../asset/front/images/MeloMance.JPG"><a class="six" href="">MeloMance</a></span>  
	   		 </span>
        </div>     
        <div class="item-wrap2">            
            <h3 class="m_title">인기순위차트</h3>
            <table class="type5">
				  <tr>
				    <th scope="row">1</th>
				    <td>팡파레</td>
				  </tr>
				  <tr>
				    <th scope="row" class="even">2</th>
				    <td class="even">우연히 봄</td>
				  </tr>
				  <tr>
				    <th scope="row">3</th>
				    <td>밥만잘먹더라</td>
				  </tr>
				  <tr>
				    <th scope="row" class="even">4</th>
				    <td class="even">사랑했나봐</td>
				  </tr>
				   <tr>
				    <th scope="row">5</th>
				    <td>무릎</td>
				  </tr>
				 
				  </tbody>
				</table>
       </div> 
    </section>

<div class="dim"></div>
<!-- 로그인 -->
<div class="layer-popup layer-login" style="display:none;">
<header class="layer-header">
	<span class="logo">
		<span class="img-logo">Sunny Music</span>
	</span>
	<button type="button" class="layer-close"><span>팝업 닫기</span></button>
</header>
<div class="layer-body">
		<form action="/login/actionLogin.do" id="frmLogin" name="frmLogin" method="post" onsubmit="return vali()">
		<input type="hidden" name="userSe" value="USR"/>
		<fieldset>
			<legend>로그인을 위한 아이디/ 비밀번호 입력</legend>
			<div class="ipt-row">
				<input type="text" id="loginId" name="id" placeholder="아이디" required="required">
			</div>
			<div class ="ipt-row">
				<input type="password" id="loginPw" name="password" placeholder="비밀번호" required="required">
			</div>
			<button type="submit" class="btn-login"><span>로그인</span></button>
		</fieldset>
		</form>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/main/Footer.jsp"%>
<script>
$(document).ready(function() {
	//로그인
	$(".login").click(function(){
		$(".dim, .layer-login").fadeIn();
		return false;
	});
	//레이어닫기
	$(".layer-close").click(function(){
		$(".dim, .layer-login").fadeOut();
		return false;
	});
});
function vali() {
	if(!$("#loginId").val()) {
		alert("아이디를 입력해주세요.");
		$("#loginId").focus();
		return false;
	}
	if(!$("#loginPw").val()) {
		alert("비밀번호를 입력해주세요");
		$("#loginPw").focus();
		return false;
	}
}
<c:if test ="${not empty loginMessage}">
	alert("${loginMessage}");
</c:if>
</script>
</body>
</html>