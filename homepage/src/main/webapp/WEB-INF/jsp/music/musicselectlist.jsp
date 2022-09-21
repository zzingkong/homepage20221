<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/asset/front/musicselectlist.css">
    <title>앨범페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <script src="/js/jquery-3.6.0.js"></script>
</head>

<body>
    <div id="header">
        <h1 class="logo"><a href="/index.do"><img src="../../../asset/front/images/logo_03.png" alt="sunnymusic"></a></h1>
        <nav class="gnb">
            <ul>
                <li><a href="/board/selectList.do">공지사항</a></li>
                <li>
                    <c:choose>
                        <c:when test="${empty USER_INFO.id}">
                            <a href="/login/actionLogin.do" class="login">로그인</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/login/actionLogout.do">
                                <c:out value="${USER_INFO.name}" />님 로그아웃
                            </a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li>
               	 <a href="/join/memberType.do">회원가입</a>	
                </li>
            </ul>
        </nav>
    </div>
    <hr>
    <div class="song_name">
        <strong class="none">앨범정보</strong>
    </div>
    <div class="box">
        <div class="album">
            <img src="../../../asset/front/images/방탄소년단.JPG">
        </div>
            <div class="text">
                <dl>
                    <dt>발매일</dt>
                    <dd>2018.08.24</dd>
                    <dt>장르</dt>
                    <dd>랩/힙합</dd>
                    <dt>발매사</dt>
                    <dd>YG PLUS</dd>
                    <dt>기획사</dt>
                    <dd>BIGHIT MUSIC</dd>
                </dl>
            </div>
        </div>
       
    </div>
    <div class="song_name">
        <strong class="none">수록곡</strong>
    </div>
    <table class="music_detail">
        <thead>
            <tr>
                <th>번호</th>
                <th>곡정보</th>
                <th>추천</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>DNA</td>
                <td>   
	              	<img class="heart" src="/asset/front/images/heart2.png">
                 </td>
            </tr>
            <tr>
                <td>2</td>
                <td>보조개</td>
                <td>
                  <img class="heart" src="/asset/front/images/heart2.png">
                </td>
            </tr>
            <tr>
                <td>3</td>
                <td>Her</td>
                <td>
                 <img class="heart" src="/asset/front/images/heart2.png">
                </td>
            </tr>
            <tr>
                <td>4</td>
                <td>Ipsum</td>
                <td>
                  <img class="heart" src="/asset/front/images/heart2.png">
                </td>
            </tr>
            <tr>
                <td>5</td>
                <td>Tear</td>
                <td>
                  <img class="heart" src="/asset/front/images/heart2.png">
                </td>
            </tr>
            <tr>
                <td>6</td>
                <td>불타오르네</td>
                <td>
                	<img class="heart" src="/asset/front/images/heart2.png">
                </td>
            </tr>
            <tr>
                <td>7</td>
                <td>butter</td>
                <td>
                	<img class="heart" src="/asset/front/images/heart2.png">
                </td>
            </tr>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
      
<script>

$('.heart').on("click",function(){
 $(this).toggleClass("on");
 if($(this).hasClass("on")){
 this.src = "/asset/front/images/f_heart2.png";
 } else {
  this.src = "/asset/front/images/heart2.png";
 }
});
</script>
</body>

</html>