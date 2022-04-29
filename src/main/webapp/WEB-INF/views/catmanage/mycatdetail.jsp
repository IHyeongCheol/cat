<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>3차 프로젝트 1조</title>
        <link rel="icon" type="image/x-icon" href="${ pageContext.servletContext.contextPath }/resources/assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${ pageContext.servletContext.contextPath }/resources/css/styles.css" rel="stylesheet" />
    </head>
    
<style>
.bubble
{
position: relative;
width: 100%;
height: 12%;
padding: 0px;
background: #FFFF;
-webkit-border-radius: 31px;
-moz-border-radius: 31px;
border-radius: 31px;
border: #E19600 solid 5px;
}
.bubble:after
{
content: '';
position: absolute;
border-style: solid;
border-width: 30px 50px 0;
border-color: #FFFF transparent;
display: block;
width: 0;
z-index: 1;
bottom: -30px;
left: 53%;
}
.bubble:before
{
content: '';
position: absolute;
border-style: solid;
border-width: 35px 55px 0;
border-color: #E19600 transparent;
display: block;
width: 0;
z-index: 0;
bottom: -37px;
left: 52%;
}

.bubble2
{
position: relative;
width: 250px;
height: 120px;
padding: 0px;
background: #FFFFFF;
-webkit-border-radius: 24px;
-moz-border-radius: 24px;
border-radius: 24px;
}

.bubble2:after
{
content: '';
position: absolute;
border-style: solid;
border-width: 0px 0px 0;
border-color: #FFFFFF transparent;
display: block;
width: 0;
z-index: 1;
bottom: -0px;
left: 26px;
}
</style>
    
    <body>
    	<c:import url="/WEB-INF/views/common/loginSession.jsp" />
        <c:import url="/WEB-INF/views/common/menubar.jsp" />
        <section class="page-section cta">
            <div class="container">
                <div class="row">
                    <div class="col-xl-9 mx-auto">
                        <div class="cta-inner bg-faded text-center rounded">
							<form action="catdelete.do" method="post">
								<input type="hidden" name="duser_id" value="${ sessionScope.loginMember.user_id }">
								<input type="hidden" name="dcat_name" value="${ cat_name }">
								<input type="hidden" name="drepeat_number" value="${ repeat_number }">
								
								<div class="row">
									<div style="width: 70%; height: 10%;" class="col-xl-6">
										<h2 style="align:center; width: 100%; height: 50%;" class="bubble site-heading-upper text-primary mb-3">
											<br>집사야 내 정보다냥 관리 잘해라냥~!<br><br>
										</h2>
									</div>
									<div style="width: 100%; height: 50%;" class="col-xl-6">
										<img style="width: 70%; height: 70%;" src="${ pageContext.servletContext.contextPath }/resources/ai_cat_img/${ imgfile }">
									</div>
									
								</div>
								<div class="cta-inner bg-faded text-center" style="width: 100%; height: 40%;" class="col-xl-6">
									<h2 style="width: 100%; height: 50%;" class="bubble2 site-heading-upper text-primary mb-3">
										<br>이름 : ${ cat_name } <hr>
										 품종 : ${ breed } <hr>
										 나이 : ${ age }살 <hr>
										 성장도 : ${ stature } <hr>
										 체중 : ${ weight }kg <hr>
										 중성화 여부 : ${ neutering } <hr>
										비만도 측정 횟수 : ${ repeat_number }회 <hr>
										비만도 측정 값(소숫점) : ${ measure_value }% <hr>
										기록일 : ${ measure_date } <br><br>
									</h2>
									<input style="padding:10px; margin:10px;" class="btn btn-primary btn-block" type="submit" value="기록 삭제하기">	
									<a style="padding:10px; margin:10px;" class="btn btn-primary btn-block" href="javascript:history.go(-1);">이전 페이지로</a>
								</div>
							</form>
                        </div>
                    </div>
                </div>
            </div>
        </section> 
        <c:import url="/WEB-INF/views/common/footer.jsp" />
    </body>
</html>
