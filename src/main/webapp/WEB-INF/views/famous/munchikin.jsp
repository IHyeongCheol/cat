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
					<br>
					<h5 style="margin-left:4%;">먼치킨 고양이의 유래 및 역사</h5>
				 <div style="margin-left:5%; margin-bottom:3%; width: 90%;height: 20%;border: none;resize: none;background: white;border-radius: 0.5rem;border: 1px solid #ced4da;text-align: left;" >
				 <img style="float: right; width: 47%; height: 47%;" class="intro-img mb-3 mb-lg-0 rounded" 
					src="${ pageContext.servletContext.contextPath }/resources/static/img/Munchiken2.png"
					 />
				 <br>	
				 	먼치킨 고양이는 짧은 다리를 가져 귀엽다고 유명한 고양이이다. 친화력도 좋아 개냥이라고도 유명하다. 하지만, 이런 짧은 다리를 마냥 귀엽게 볼 수만은 없다. 그 이유와 함께 먼치킨 고양이 특징 및 성격에 대해서도 알아보자. <br>
				 	먼치킨은 1944년 영국 수의학 보고서에서 처음 보고된 고양이다. 세계 2차 대전 중에는 보이지 않다가, 전쟁 후 미국, 러시아 등에서 다시 발견되기 시작했다. 1995년에는 국제 고양이 협회(TICA)에서 하나의 품종으로 인정받게 되었다. <br> 
				 	하지만, TICA와 남아프리카 고양이 위원회 외의 다른 협회들에서는 공식으로 인정하지 않고 있다. 만약 먼치킨을 하나의 품종으로 인정할 경우, 무분별한 교배로 인해 유전병이 더 심해질 수 있기 때문이다.  <br> 
					 먼치킨의 이름은 오즈의 마법사에 등장하는 난쟁이들이 사는 도시 이름이 유래했다고 한다. (도시 이름 및 시민 이름) <br>
 				 </div>
 				 <h5 style="margin-left:4%;">먼치킨 고양이의 외모 및 특징?</h5>
 				 <div style="margin-left:5%; margin-bottom:3%; width: 90%;height: 25%;border: none;resize: none;background: white;border-radius: 0.5rem;border: 1px solid #ced4da;text-align: left;" >
 				 <br>	
 				 		<h6>▶ 크기 및 평균 수명:</h6> 먼치킨은 약 3~4kg 정도 나가는 중소형 고양이로, 약 15~18년 정도가 평균 수명으로 알려져 있다  <br><br>  

						<h6>▶털:</h6> 먼치킨의 경우 장모종과 단모종이 있다. 어느 정도 털 빠짐은 있지만, 털 관리가 어렵지는 않은 편이라고 한다. <br><br>  
 				 		
 				 		<h6>▶짧은 다리:</h6> 먼치킨 고양이의 가장 큰 특징은 다리가 짧다는 것이다. 이는 유전자 조합*에 의해 나타나게 되는 특징인데, 유전자 열성, 우성 조합에 따라 긴 다리(롱레그 먼치킨)로 태어나거나 유산되는 경우도 있다. 다리가 짧아서 특히 점프 능력이 떨어지고, 야생에서의 생존 능력도 떨어지게 된다.
 				 </div>
 				 <h5 style="margin-left:4%;">먼치킨 고양이의 성격?</h5>
 				 <div style="margin-left:5%; margin-bottom:3%; width: 90%;height: 50%;border: none;resize: none;background: white;border-radius: 0.5rem;border: 1px solid #ced4da;text-align: left;" >
 				 <br>
 				 		<h6>▶ 먼치킨은 호기심이 많고 활발한 편이다. 사람들과 쉽게 친해지는 편이라 개냥이라고 불리기도 한다. 또한, 먼치킨 고양이는 똑똑한 편인데. 다리가 짧아 점프 능력이 좋지 않지만, 주변 환경을 잘 활용해 높은 곳에도 잘 올라가곤 한다.</h6>

 				 </div>
 				 <div class="intro-button mx-auto"  style="width:12%; height:5%;">
							<a class="btn btn-primary btn-xl"  href="${ pageContext.servletContext.contextPath }/famous.do">목록</a>
							<br><br>
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