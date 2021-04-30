<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%--========================import module start============================= --%>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="keywords"
	content="htmlcss bootstrap menu, navbar, mega menu examples" />
<meta name="description"
	content="Bootstrap navbar examples for any type of project, Bootstrap 4" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- Bootstrap files (jQuery first, then Popper.js, then Bootstrap JS) -->

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous">
	
</script>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
<!--CDN 링크 -->

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css"
	href="//fonts.googleapis.com/css?family=Droid+Sans" />
	
<%-- layou css import --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/layoutAdmin.css" />		
	
<%--폰트 --%>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />


<%--=========================font=============================== --%>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

.notosanskr * {
	font-family: 'Noto Sans KR', sans-serif;
}

@font-face {
	font-family: 'Chosunilbo_myungjo';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Chosunilbo_myungjo.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

@font-face {
	font-family: 'Wemakeprice-Bold';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Wemakeprice-Bold.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
/* ===============font-end=========== */

/* ===============scroll-button===============*/
.btn_top{
    display: inline-block;
    text-decoration: none;
    background: #ffffff;
    color: #ffffff;
    width: 50px;
    height: 50px;
    line-height: 50px;
    border-radius: 50%;
    text-align: center;
    vertical-align: middle;
    overflow: hidden;
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.29);
    border-bottom: solid 3px #b1b0b0;
    transition: .4s;
}

.btn_top:active{
    -ms-transform: translateY(2px);
    -webkit-transform: translateY(2px);
    transform: translateY(2px);
    box-shadow: 0 0 1px rgba(0, 0, 0, 0.15);
    border-bottom: none;
}
/*=======================scroll-button-end=================*/
</style>


<title><tiles:insertAttribute name="title" /></title>

<script language="JavaScript" type="text/javascript">
$(document).ready(function(){  
	 $('#sidebar-left').css('height', $('#container').height()+40)
});
</script>

</head>
<body>
	<div id="container">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div>
		<div id="sidebar-left">
          <tiles:insertAttribute name="side"/> 
      	</div>
		<div id="content">
			<tiles:insertAttribute name="body" />
			<!-- top down scroll -->
			<div style="position: fixed; top: 80%; left: 95%;">
				<div class="upBtn">
					<a class="btn_top">TOP</a>

				</div>

				<script>
					$(".upBtn").click(function() {
						$('html, body').animate({
							scrollTop : 0
						}, 200);
					});
				</script>
				<!-- end top down scroll -->
		</div>
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>