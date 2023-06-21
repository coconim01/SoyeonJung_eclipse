<!-- 
	페이지명 : 메인 페이지 (home.jsp 파일)
	작성일자 : 2023-03-09
	작성자 : 최영민
	-->


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./../common/bootstrap5.jsp"%>
<%@ include file="./../common/common.jsp"%>



<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>탈출의 민족 home</title>
    <!-- jquery를 사용할 수 있게 연결해주는 링크 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <!-- js로 연결해주는 링크 -->
    <script type="text/javascript" src="./freeBoardDetail.js"></script>
    <!-- css로 연결해주는 링크 -->
    <link rel="stylesheet" href="./freeBoardDetail.css">
    <!-- ajax를 사용할 수 있게 연결해주는 링크 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <style>
	@import url('https://fonts.googleapis.com/css2?family=Dongle:wght@300&display=swap');
	/*body {font-family: 'Dongle', sans-serif;} */
	
	/*나눔고딕*/
	@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
	
	/* 우아한형제들 한나체Pro */
	@font-face {
	font-family: 'BMHANNAPro';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_seven@1.0/BMHANNAPro.woff') format('woff');
	font-weight: normal;
	font-style: normal;
	}

        /* Add styles here */

		/* 메인페이지 제목 스타일 통일 시킴 */
		.navbar {
		padding: 8px 0;
		}
		
		.navbar-brand {
		margin-top: -50px;
		}
		
		h2 {
		font-size: 32px;
		color: white;
		}
		
        /* 배너 (슬라이더) */
        .carousel-inner>.item>img,
        .carousel-inner>.item>a>img {
            margin: auto;
        }

		/* 땡겨요 */
		body {
		height: 600px;
		}
		#stopSection {
		background-color: yellow;
		}
		#result {
		font-size: 60px;
		color: #03D0B7;
		text-align: center;
		margin-top: 5px;
		}
		
		.pull{
		font-family: 'BMHANNAPro';
		margin: 50px 0 30px 0;
		}
		
		.pull-box {
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
		}
		
		.pull02 {
		color: #03D0B7;
		}
		
		.pull-img {
		width: 100px;
		margin: 0 30px 50px 0;
		}
		
		.img-b {
		margin-left: 30px;
		transform: scaleX(-1);
		}		
		
		
        /* 장르 (카드) */

        /* 카드 전체를 감싸고 있는 container div */
        .card-container {
            margin-top: 20px;
        }

        /* 카드 각각을 감싸고 있는 wrapper div */
        .card-wrap {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* 카드 각각을 감싸고 있는 div */
        .card-box {
            width: 300px;
            height: 250px;
            border-radius: 50%;
            overflow: hidden;
        }

        .card-box>a {
            width: 100%;
            height: 100%;
            position: relative;
        }

        /* 이미지를 담고 있는 이미지 박스 div */
        .card-img-box {
            position: absolute;
            left: 50%;
            top: 60%;
            transform: translate(-50%, -50%);
            width: 100%;
            height: 100%;
        }

        /* 삽입된 이미지 img*/
        .card-img {
            width: 70%;
            object-fit: cover;
            align-self: center;
        }

        /* 장르 타이틀 박스 div */
        .card-title-box {
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            opacity: 0;
            width: 100%;
            height: 100%;
            transition-duration: 0.5s;
            transition-timing-function: ease-out;
            background-color: white;
        }

        /* 장르 카드에 마우스 오버 시 */
        .card-title-box:hover {
            opacity: .8;
        }

        /* 장르 타이틀 텍스트 p */
        .card-title-text {
        	font-family: 'BMHANNAPro';
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
            font-size: 36px;
            color: #03D0B7;
        }

        /* 웹사이트 바닥 부분 */
        .footer {
            height: 100px;
            position: relative;
        }

        /* 저작권 표시 */
        #Copyright>p {
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }

    </style>
</head>

<body>
    <div id="stopSection"></div>
    
    <!-- Slider -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="<%=contextPath%>/image/030303.jpg" alt="Slider 1">
            </div>
            <div class="item">
                <img src="<%=contextPath%>/image/040404.jpg" alt="Slider 2">
            </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    
    <!-- 땡겨요 -->
	<h3 class="pull pull01" align="center">오늘의 테마 장르는?!</h3>
	<div class="pull-box">
		<img class="pull-img img-a" alt="이미지" src="https://em-content.zobj.net/source/microsoft-teams/337/party-popper_1f389.png">
		<div class="pull pull02" id="result"></div>
		<img class="pull-img img-b" alt="이미지" src="https://em-content.zobj.net/source/microsoft-teams/337/party-popper_1f389.png">
	</div>
	
    <!-- Cards -->
    <div class="container card-container">
        <div class="row">
            <div class="col-md-4 card-wrap">
                <div class="card-box">
                   <a href="<%=notWithFormTag%>prAllList&modeGenre=공포&modeLevel=all&modeArea=all&keyword=" class="btn">
                        <div class="card-img-box">
                            <img class="card-img-top card-img" src="https://em-content.zobj.net/source/microsoft-teams/337/face-screaming-in-fear_1f631.png"
                                alt="공포">
                        </div>
                        <div class="card-title-box">
                            <p class="card-title-text">공포</p>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-4 card-wrap">
                <div class="card-box">
                     <a href="<%=notWithFormTag%>prAllList&modeGenre=스릴러&modeLevel=all&modeArea=all&keyword=" class="btn">
                        <div class="card-img-box">
                            <img class="card-img-top card-img" src="https://em-content.zobj.net/source/microsoft-teams/337/fearful-face_1f628.png"
                                alt="스릴러">
                        </div>
                        <div class="card-title-box">
                            <p class="card-title-text">스릴러</p>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-4 card-wrap">
                <div class="card-box">
                     <a href="<%=notWithFormTag%>prAllList&modeGenre=코믹&modeLevel=all&modeArea=all&keyword=" class="btn">
                        <div class="card-img-box">
                            <img class="card-img-top card-img" src="https://em-content.zobj.net/source/microsoft-teams/337/rolling-on-the-floor-laughing_1f923.png"
                                alt="코믹">
                        </div>
                        <div class="card-title-box">
                            <p class="card-title-text">코믹</p>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 card-wrap">
                <div class="card-box">
                     <a href="<%=notWithFormTag%>prAllList&modeGenre=역사&modeLevel=all&modeArea=all&keyword=" class="btn">
                        <div class="card-img-box">
                            <img class="card-img-top card-img" src="https://em-content.zobj.net/thumbs/160/apple/325/moai_1f5ff.png"
                                alt="역사">
                        </div>
                        <div class="card-title-box">
                            <p class="card-title-text">역사</p>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-4 card-wrap">
                <div class="card-box">
                     <a href="<%=notWithFormTag%>prAllList&modeGenre=판타지&modeLevel=all&modeArea=all&keyword=" class="btn">
                        <div class="card-img-box">
                            <img class="card-img-top card-img" src="https://em-content.zobj.net/source/microsoft-teams/337/unicorn_1f984.png"
                                alt="판타지">
                        </div>
                        <div class="card-title-box">
                            <p class="card-title-text">판타지</p>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-4 card-wrap">
                <div class="card-box">
                     <a href="<%=notWithFormTag%>prAllList&modeGenre=감동&modeLevel=all&modeArea=all&keyword=" class="btn">
                        <div class="card-img-box">
                            <img class="card-img-top card-img" src="https://em-content.zobj.net/source/microsoft-teams/337/hugging-face_1f917.png"
                                alt="감동">
                        </div>
                        <div class="card-title-box">
                            <p class="card-title-text">감동</p>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 card-wrap">
                <div class="card-box">
                     <a href="<%=notWithFormTag%>prAllList&modeGenre=SF&modeLevel=all&modeArea=all&keyword=" class="btn">
                        <div class="card-img-box">
                            <img class="card-img-top card-img" src="https://em-content.zobj.net/source/microsoft-teams/337/alien_1f47d.png
                            "
                                alt="SF">
                        </div>
                        <div class="card-title-box">
                            <p class="card-title-text">SF</p>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-4 card-wrap">
                <div class="card-box">
                     <a href="<%=notWithFormTag%>prAllList&modeGenre=동화&modeLevel=all&modeArea=all&keyword=" class="btn">
                        <div class="card-img-box">
                            <img class="card-img-top card-img" src="https://em-content.zobj.net/source/microsoft-teams/337/castle_1f3f0.png"
                                alt="동화">
                        </div>
                        <div class="card-title-box">
                            <p class="card-title-text">동화</p>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-md-4 card-wrap">
                <div class="card-box">
                     <a href="<%=notWithFormTag%>prAllList&modeGenre=야외&modeLevel=all&modeArea=all&keyword=" class="btn">
                        <div class="card-img-box">
                            <img class="card-img-top card-img" src="https://em-content.zobj.net/source/microsoft-teams/337/person-running_light-skin-tone_1f3c3-1f3fb_1f3fb.png
                            "
                                alt="야외">
                        </div>
                        <div class="card-title-box">
                            <p class="card-title-text">야외</p>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
    

	
	<script type="text/javascript">

		let reachedStopSection = false;
		
		const resultDiv = document.getElementById('result');
		resultDiv.innerHTML = "두구두구두구";

		document.addEventListener('scroll', function() {
			if (!reachedStopSection && window.scrollY >= document.getElementById('stopSection').offsetTop) {
				reachedStopSection = true;
				var i =	Math.floor(Math.random() * 9) + 1;
				switch(i){
				case 1 : resultDiv.innerHTML = "공포";
				break;
				case 2 : resultDiv.innerHTML = "코믹";
				break;
				case 3 : resultDiv.innerHTML = "역사";
				break;
				case 4 : resultDiv.innerHTML = "SF";
				break;
				case 5 : resultDiv.innerHTML = "감동";
				break;
				case 6 : resultDiv.innerHTML = "야외";
				break;
				case 7 : resultDiv.innerHTML = "판타지";
				break;
				case 8 : resultDiv.innerHTML = "동화";
				break;
				case 9 : resultDiv.innerHTML = "스릴러";
				break;
				default : resultDiv.innerHTML = "--";
				}
				resultDiv.style.display = 'block';
			} else if (reachedStopSection && window.scrollY < document.getElementById('stopSection').offsetTop) {
				reachedStopSection = false;
				const resultDiv = document.getElementById('result');
				resultDiv.innerHTML = "두구두구두구";

				resultDiv.style.display = 'block';
				document.documentElement.scrollTop = 0;
			}
		});
		
	</script>
    <!-- Footer -->
    <footer>
        <div class="footer">
            <div id="Copyright">
                <p>
                    Copyright 2023. 탈출의민족 Co. all rights reserved
                </p>
            </div>
        </div>
    </footer>
</body>

</html>