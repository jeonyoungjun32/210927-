<!-- 개 하나의 상세 정보를 출력해주는 뷰페이지  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>

<%-- <%@page import="vo.Dog"%> --%> <!-- 추가 -->
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#viewForm{
	margin:auto;
	width:640px;
	border:1px solid red;
}

h2{
	text-align:center;
}

img{
 	width: 280px;
 	height: 280px;
 	border: none;
}

# content_main{
	height:300px;
}

#content_left{
	width: 300px;
	float:left;
}

#content_right{
	width: 340px;
	float:left;
}

#desc{
	height: 170px;
	background: skyblue;
}

#commandList{
	text-align: center;

}
</style>
</head>
<body>
<!-- ★혜-752p-두번째 그림 -->
<section id="viewForm">
	<h2>${ice.kind}의 상세정보</h2>	
	<section id = "content_main">
		<section id = "content_left">
			<img src = "images/${ice.image}" />
		</section>
		<section id = "content_right"> 
			<b>이름 :</b> ${ice.name} <br/>
			<b>종류 :</b> ${ice.kind} <br/>
			<b>가격 :</b> ${ice.price} <br/>
			<b>갯수 :</b> ${ice.count} <br/>
			<p id="desc">
				<b>내용 :</b> ${ice.country} <br/>
			</p>
		</section>
	</section>
	
	<div style="clear:both"></div>
	
	<nav id="commandList">
		<a href="iceList.ice">쇼핑 계속하기</a>
		<a href="iceCartAdd.ice?id=${ice.id}">장바구니에 담기</a>
	</nav>
</section>
</body>
</html>




