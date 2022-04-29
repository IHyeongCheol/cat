<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>3차 프로젝트 1조</title>
<!-- --------------웹 스타일 링크----------------------------------- -->
<link rel="icon" type="image/x-icon"
	href="${ pageContext.servletContext.contextPath }/resources/assets/favicon.ico" />
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link
	href="${ pageContext.servletContext.contextPath }/resources/css/styles.css"
	rel="stylesheet" />

<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<!-- Bootstrap core JS-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script
	src="${ pageContext.servletContext.contextPath }/resources/js/scripts.js"></script>
<!-- ----------------------------------------------------------------------------------------- -->
</head>
<body>
	<c:import url="/WEB-INF/views/common/loginSession.jsp" />
	<c:import url="/WEB-INF/views/common/menubar.jsp" />
	<hr style="clear: both;">

	<section class="page-section cta">
		<div class="container">
			<div class="row">
				<div class="col-xl-9 mx-auto">
				<div style="background: #f6e1c5; border-radius: 0.25rem; border-radius: 0.5rem;border: 1px solid #e6a756; padding: 5px; margin: 5px;">
				<img style="float: left; height: 150px;" class="intro-img mb-3 mb-lg-0 rounded" 
					src="${ pageContext.servletContext.contextPath }/resources/static/img/bengal.jpg"
					 />
					
					<br>
					<h5 style="margin-left:4%;">벵갈고양이의 역사</h5>
				 <div style="margin-left:5%; margin-bottom:3%; width: 90%;height: 100%;border: none;resize: none;background: white;border-radius: 0.5rem;border: 1px solid #ced4da;text-align: left;" >
				 <br>	
				 	야생고양이와 집고양이가 브리딩된 고양이. 유일하게 야생고양이와 집고양이가 성공적으로 짝지어진 뱅갈 고양이. 표범을 떠올리게 하는 아름답고 화려한 무늬가 특징이다. 조용하고 은밀하게 움직이는 야생고양이의 특성을 받아 우아한 몸짓을 자랑한다. <br>
 				 </div>
 				 <h5 style="margin-left:4%;">벵갈고양이의 특징?</h5>
 				 <div style="margin-left:5%; margin-bottom:3%; width: 90%;height: 100%;border: none;resize: none;background: white;border-radius: 0.5rem;border: 1px solid #ced4da;text-align: left;" >
 				 <br>	
 				 		<h6>▶ 외형:</h6>   뱅갈 고양이는 길고 유려한 근육을 자랑하는 중대형 고양이다. 큰 몸집과 골격으로 무거운 편이다. 앞다리보다 약간 긴 뒷다리와 근육질의 몸매는 타고난 운동 체질을 보여준다. 이는 야생고양이의 특성이 남아 있는 것으로 생각된다. <br> 
 				 						   뱅갈은 두상 골격에서부터 차이가 있다. 역삼각형보다 턱에서 주둥이까지 길이가 좀 더 길다. 위에서 보는 것 보다 아래에서 보는 것이 더 잘 보인다. <br><br>
 				 		<h6>cf) 어린 뱅갈 고양이 특징 </h6>
						어린 뱅갈 고양이는 생후 3개월까지는 색상과 무늬가 모호한 편이나, 아래와 같은 외모적 특징을 지닌다. <br><br>
 				 						 

						<h6>▶털:</h6> 뱅갈 고양이는 숱이 많고 부드러운 털로 유명하다. 표범 무늬의 독특한 반점은 반원, 로제트, 마블링 같은 다양한 모양으로 나타난다. 색상은 브라운, 실버, 스노우가 공인되어 있지만 차콜이나 블루 색상도 있다.  뱅갈의 털은 금이나 진주를 뿌려 놓은 듯 반짝이는데 이를 글리터링(glittering)이라 부른다. 이는 자연발생적 특징으로 아름다운 무늬를 더욱 더 돋보이게 해준다.
									   배와 가슴의 색상이 옅은 베이지를 띈다. 가슴에 흰 털이 날 경우 뱅갈이 아닐 확률이 높다.  꼬리 끝과 발바닥의 색상이 어둡거나 검은색이다.  이마에 M자 문양이 보인다. ‘
 									  <br><br>  
 				 		
 				 		<h6>cf) 무늬의 모양에 따른 이름 </h6> 
 				 		<h6>스팟: 이름 그대로 점박이의 형태를 띈 모양 </h6> 
 				 		<h6>로젯:  두가지 색으로 된 장미 꽃잎같은 반달 모양 </h6>
 				 		<h6>도넛 로젯: 도넛처럼 바깥쪽이 띠 형태를 띄고 있는 모양 </h6>
 				 		<h6>클라우드: 구름 모양으로 길게 늘어난 모양 </h6>
 				 		<h6>마블: 무작위로 배열된 마블 모양</h6> 
 				 </div>
 				 <div class="intro-button mx-auto"  style="width:12%; height:5%;">
							<a class="btn btn-primary btn-xl"  href="${ pageContext.servletContext.contextPath }/famous.do">목록</a>
						</div>
 				 
				</div>
				</div>
			</div>
		</div>
	</section>
	<hr style="clear: both;">
	<c:import url="/WEB-INF/views/common/footer.jsp" />
</body>
</html>