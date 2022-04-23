<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- CSS -->
<link rel="stylesheet" href="${applicationScope.rootpath}/css/bootstrap-reboot.min.css">
<link rel="stylesheet" href="${applicationScope.rootpath}/css/bootstrap-grid.min.css">
<link rel="stylesheet" href="${applicationScope.rootpath}/css/owl.carousel.min.css">
<link rel="stylesheet" href="${applicationScope.rootpath}/css/jquery.mCustomScrollbar.min.css">
<link rel="stylesheet" href="${applicationScope.rootpath}/css/nouislider.min.css">
<link rel="stylesheet" href="${applicationScope.rootpath}/css/plyr.css">
<link rel="stylesheet" href="${applicationScope.rootpath}/css/photoswipe.css">
<link rel="stylesheet" href="${applicationScope.rootpath}/css/default-skin.css">
<link rel="stylesheet" href="${applicationScope.rootpath}/css/main.css">
<!-- Favicons -->
<link rel="icon" type="image/png" href="${applicationScope.rootpath}/img/logo-fivewolves/red-logo.png" sizes="32x32">
<link rel="apple-touch-icon" href="${applicationScope.rootpath}/img/logo-fivewolves/red-logo.png">
<meta name="description" content="Online Movies, TV Shows & Cinema HTML Template">
<meta name="keywords" content="">
<meta name="author" content="Dmitry Volkov">
<title>WolvesFlix Signin</title>
</head>
<body class="body">
	<div class="sign section--bg" data-bg="${applicationScope.rootpath}/img/section/section.jpg">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="sign__content">
						<!-- authorization form -->
						<form action="${applicationScope.rootpath}/access/signin" class="sign__form" method="POST">
							<a href="${applicationScope.rootpath}/home/index" class="sign__logo"> <img src="${applicationScope.rootpath}/img/logo-fivewolves/logo.png" alt="">
							</a>
							<div style="height: 40px;">
								<p style="color: white; text-align: center; color: red;">${message}</p>
							</div>
							<div class="sign__group">
								<input name="username" id="input__username-signin" type="text" class="sign__input" placeholder="Tên đăng nhập">
							</div>
							<div class="sign__group">
								<input name="password" type="password" class="sign__input pass__input" placeholder="Mật khẩu">
							</div>
							<div class="sign__group sign__group--checkbox">
								<input id="show-hide-pass" type="checkbox" onclick="showHidePass()"> <label for="show-hide-pass">Hiện mật khẩu</label>
							</div>
							<div class="sign__group sign__group--checkbox">
								<input id="remember" name="remember" type="checkbox" checked="checked"> <label for="remember">Nhớ mật khẩu</label>
							</div>
							<button class="sign__btn" type="submit">Đăng nhập</button>
							<span class="sign__text">Chưa có tài khoản? <a href="${applicationScope.rootpath}/access/signup">Đăng ký ngay!</a></span> <span class="sign__text"><a href="${applicationScope.rootpath}/access/forgot">Quên mật khẩu?</a></span>
						</form>
						<!-- end authorization form -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- JS -->
	<script src="${applicationScope.rootpath}/js/jquery-3.6.0.min.js"></script>
	<script src="${applicationScope.rootpath}/js/bootstrap.bundle.min.js"></script>
	<script src="${applicationScope.rootpath}/js/owl.carousel.min.js"></script>
	<script src="${applicationScope.rootpath}/js/jquery.mousewheel.min.js"></script>
	<script src="${applicationScope.rootpath}/js/jquery.mCustomScrollbar.min.js"></script>
	<script src="${applicationScope.rootpath}/js/wNumb.js"></script>
	<script src="${applicationScope.rootpath}/js/nouislider.min.js"></script>
	<script src="${applicationScope.rootpath}/js/plyr.min.js"></script>
	<script src="${applicationScope.rootpath}/js/jquery.morelines.min.js"></script>
	<script src="${applicationScope.rootpath}/js/photoswipe.min.js"></script>
	<script src="${applicationScope.rootpath}/js/photoswipe-ui-default.min.js"></script>
	<script src="${applicationScope.rootpath}/js/main.js"></script>
	<script src="${applicationScope.rootpath}/js/custom/signin.js"></script>
</body>
</html>