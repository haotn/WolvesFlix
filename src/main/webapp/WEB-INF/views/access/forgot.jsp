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
<title>WolvesFlix Forgot Password</title>
</head>
<body class="body">
	<div class="sign section--bg" data-bg="${applicationScope.rootpath}/img/section/section.jpg">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="sign__content">
						<!-- authorization form -->
						<form action="${applicationScope.rootpath}/access/forgot" class="sign__form" method="post">
							<a href="${applicationScope.rootpath}/home/index" class="sign__logo"> <img src="${applicationScope.rootpath}/img/logo-fivewolves/logo.png" alt="">
							</a>
							<div class="sign__group">
								<input type="email" name="email" class="sign__input" placeholder="Email" required="required">
							</div>
							<div class="sign__group sign__group--checkbox">
								<input id="remember" name="remember" type="checkbox" checked="checked"> <label for="remember">Tôi đồng ý với <a href="#">Chính sách bảo mật</a>
								</label>
							</div>
							<button class="sign__btn" type="submit">Nhận mật khẩu</button>
							<span class="sign__text">Chúng tôi sẽ gửi mật khẩu tới địa chỉ Email của bạn.</span>
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
</body>
</html>