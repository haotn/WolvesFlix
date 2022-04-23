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
<link rel="stylesheet" href="${applicationScope.rootpath}/css/custom/valid-form.css">
<!-- Favicons -->
<link rel="icon" type="image/png" href="${applicationScope.rootpath}/img/logo-fivewolves/red-logo.png" sizes="32x32">
<link rel="apple-touch-icon" href="${applicationScope.rootpath}/img/logo-fivewolves/red-logo.png">
<meta name="description" content="Online Movies, TV Shows & Cinema HTML Template">
<meta name="keywords" content="">
<meta name="author" content="Dmitry Volkov">
<title>WolvesFlix Signup</title>
</head>
<body class="body">
	<div class="sign section--bg" data-bg="${applicationScope.rootpath}/img/section/section.jpg">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="sign__content">
						<!-- registration form -->
						<form action="${applicationScope.rootpath}/access/signup" class="sign__form" method="post">
							<a href="${applicationScope.rootpath}/home/index" class="sign__logo"> <img src="${applicationScope.rootpath}/img/logo-fivewolves/logo.png" alt="">
							</a>
							<div class="sign__group">
								<input type="text" id="input__fullname-signup" class="sign__input" value="${form.fullname}" placeholder="Họ tên" name="fullname"> <span style="color: red; font-size: 13px; visibility: ${validFullname==null?'hidden': 'visible'};">${validFullname}</span>
							</div>
							<div class="sign__group">
								<input type="text" class="sign__input" value="${form.email}" placeholder="Email" name="email"> <span style="color: red; font-size: 13px; visibility: ${validEmail==null?'hidden': 'visible'};">${validEmail}</span>
							</div>
							<div class="sign__group">
								<input type="text" class="sign__input" value="${form.username}" placeholder="Tên đăng nhập" name="username"> <span style="color: red; font-size: 13px; visibility: ${validUsername==null?'hidden': 'visible'};">${validUsername}</span>
							</div>
							<div class="sign__group">
								<input type="password" class="sign__input pass__input" value="${form.password}" placeholder="Mật khẩu" name="password"> <span style="color: red; font-size: 13px; visibility: ${validPassword==null?'hidden': 'visible'};">${validPassword}</span>
							</div>
							<div class="sign__group">
								<input type="password" class="sign__input pass__input" value="${confirmPassword}" placeholder="Nhập lại mật khẩu" name="confirm-password"> <span
									style="color: red; font-size: 13px; visibility: ${validConfirmPassword==null?'hidden': 'visible'};"
								>${validConfirmPassword}</span>
							</div>
							<div class="sign__group sign__group--checkbox">
								<input id="show-hide" type="checkbox" onclick="showHidePass()"> <label class="label-password" for="show-hide">Hiện mật khẩu</label>
							</div>
							<button class="sign__btn" type="submit">Đăng ký</button>
							<span class="sign__text">Đã có tài khoản? <a href="${applicationScope.rootpath}/access/signin">Đăng nhập ngay!</a></span>
						</form>
						<!-- registration form -->
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
	<script src="${applicationScope.rootpath}/js/custom/valid-form-signin.js" charset="UTF-8"></script>
	<script src="${applicationScope.rootpath}/js/custom/signup.js" charset="UTF-8"></script>
</body>
</html>