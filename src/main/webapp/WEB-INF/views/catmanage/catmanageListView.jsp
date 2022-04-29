<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>3차 프로젝트 1조</title>
<link rel="icon" type="image/x-icon" href="${ pageContext.servletContext.contextPath }/resources/assets/favicon.ico" />
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${ pageContext.servletContext.contextPath }/resources/css/styles.css" rel="stylesheet" />

<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="${ pageContext.servletContext.contextPath }/resources/js/scripts.js"></script>
<script type="text/javascript">
	function showWriteForm(){
	   location.href = "${ pageContext.servletContext.contextPath }/bwform.do";
	}
</script>   

<!-- 공지사항 -->
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		showDiv();
		
		$("input[name=item]").on("change", function(){
			showDiv();
		});
	});
	
	function showDiv(){
		$("#titleDiv").css("display", "block");
		$("#writerDiv").css("display", "none");
		$("#dateDiv").css("display", "none");
		if($("input[name=item]").eq(0).is(":checked")){
			$("#titleDiv").css("display", "block");
			$("#writerDiv").css("display", "none");
			$("#dateDiv").css("display", "none");
		}
		if($("input[name=item]").eq(1).is(":checked")){
			$("#titleDiv").css("display", "none");
			$("#writerDiv").css("display", "block");
			$("#dateDiv").css("display", "none");
		}
		if($("input[name=item]").eq(2).is(":checked")){
			$("#titleDiv").css("display", "none");
			$("#writerDiv").css("display", "none");
			$("#dateDiv").css("display", "block");
		}
	}
</script>

</head>
<body>
	<c:import url="/WEB-INF/views/common/loginSession.jsp" />
	<c:import url="/WEB-INF/views/common/menubar.jsp" />
	<section class="page-section cta">
            <div class="container">
                <div class="row">
                    <div class="col-xl-9 mx-auto">
                    <h1 align="center">내 고양이 관리 페이지</h1>
                    <br>
                        <div class="cta-inner bg-faded text-center rounded">
<!-- =============================================================================== -->

<% int num = 1; %>
<div class="row">
	<c:forEach items="${ requestScope.list }" var="cm">
		<div style="width: 25%; height: 5%;" class="col-xl-6">
	<!-- 공지 제목 클릭시 상세보기로 넘어가게 처리 -->
			<c:url var="cmdt" value="/cmdetail.do">
				<c:param name="user_id" value="${ cm.user_id }" />
				<c:param name="cat_name" value="${ cm.cat_name }" />
				<c:param name="repeat_number" value="${ cm.repeat_number }" />
			</c:url>
			<img style="width: 80%; height: 80%;" src="${ pageContext.servletContext.contextPath }/resources/ai_cat_img/${ cm.cat_rename_img }">
			<button style="width: 80%;" class="btn btn-primary btn-block" onclick="javascript:location.href='${ cmdt }'">${ cm.cat_name }</button>
			<h6>(기록일 : ${ cm.measure_date })</h6>
			<br>
		</div>
	</c:forEach>
</div>
	<br>
<c:import url="/WEB-INF/views/common/paging.jsp" />
<!-- =============================================================================== -->
</div>
<br>
	<!-- 로그인한 회원이 관리자인 경우는 공지사항 등록 버튼이 보이게 함 -->
<center>
	<c:if test="${ loginMember.admin_ok eq 'Y' }"><button style="background-color: white;" class="btn btn-primary btn-block" onclick="javascript:location.href='noticewrite.do';">공지 등록</button></c:if>
</center>	
<br>
	<!-- 검색 항목 -->
	<center>
	<div style="background: #f6e1c5; border-radius: 0.25rem; border-radius: 0.5rem;border: 1px solid #e6a756; padding: 5px; margin: 5px;">
		<div>
			<h2>검색할 고양이 이름을 작성해주세요.</h2>
		</div>
		<div>
			<form action="cmsearchName.do?user_id=${ sessionScope.loginMember.user_id }" method="post">
				<label >내 고양이 이름 : <input type="search" name="keyword"></label>
				<input style="background-color: white;" class="btn btn-primary btn-block" type="submit" value="검색">
			</form>
		</div>

		<button style="margin-bottom: 1%; background-color: white;" class="btn btn-primary btn-block" onclick="javascript:location.href='${ pageContext.servletContext.contextPath }/mycat.do;'">전체
								보기</button>
		</div>
	</center>

</div>
</div>
</div>
</section>
<c:import url="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
