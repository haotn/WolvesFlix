<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!-- page title -->
<section class="section section--first section--bg" data-bg="${applicationScope.rootpath}/img/section/section.jpg">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="section__wrap">
					<!-- section title -->
					<h1 class="section__title">Tài khoản FlixGo của tôi</h1>
					<!-- end section title -->
					<!-- breadcrumb -->
					<ul class="breadcrumb">
						<li class="breadcrumb__item"><a href="${applicationScope.rootpath}/home/index">Trang chủ</a></li>
						<li class="breadcrumb__item breadcrumb__item--active">Hồ sơ người dùng</li>
					</ul>
					<!-- end breadcrumb -->
				</div>
			</div>
		</div>
	</div>
</section>
<!-- end page title -->
<c:set var="profile" value="${sessionScope.user}" />
<!-- content -->
<div class="content">
	<!-- profile -->
	<div class="profile">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="profile__content">
						<div class="profile__user">
							<div class="profile__avatar">
								<img src="${applicationScope.rootpath}/img/user.svg" alt="">
							</div>
							<div class="profile__meta">
								<h3>${profile.fullname}</h3>
								<span>ID: ${profile.id}</span>
							</div>
						</div>
						<!-- content tabs nav -->
						<ul class="nav nav-tabs content__tabs content__tabs--profile" id="content__tabs" role="tablist">
							<li class="nav-item" role="presentation"><a class="nav-link active" data-toggle="tab" href="#tab-1" role="tab" aria-controls="tab-1" aria-selected="true">Danh sách yêu thích</a></li>
							<!-- <li class="nav-item" role="presentation">
										<a class="nav-link" data-toggle="tab" href="#tab-2" role="tab"
											aria-controls="tab-2" aria-selected="false">Subscription</a>
									</li> -->
							<li class="nav-item" role="presentation"><a class="nav-link" data-toggle="tab" href="#tab-3" role="tab" aria-controls="tab-3" aria-selected="false">tài khoản</a></li>
						</ul>
						<!-- end content tabs nav -->
						<!-- content mobile tabs nav -->
						<div class="content__mobile-tabs content__mobile-tabs--profile" id="content__mobile-tabs">
							<div class="content__mobile-tabs-btn dropdown-toggle" role="navigation" id="mobile-tabs" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<input type="button" value="Profile"> <span></span>
							</div>
							<div class="content__mobile-tabs-menu dropdown-menu" aria-labelledby="mobile-tabs">
								<ul class="nav nav-tabs" role="tablist">
									<li class="nav-item"><a class="nav-link active" id="1-tab" data-toggle="tab" href="#tab-1" role="tab" aria-controls="tab-1" aria-selected="true">Chi tiết hồ sơ</a></li>
									<!-- <li class="nav-item"><a class="nav-link" id="2-tab" data-toggle="tab"
													href="#tab-2" role="tab" aria-controls="tab-2"
													aria-selected="false">Subscription</a></li> -->
									<li class="nav-item"><a class="nav-link" id="3-tab" data-toggle="tab" href="#tab-3" role="tab" aria-controls="tab-3" aria-selected="false">Tài khoản</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end profile -->
	<div class="container">
		<!-- content tabs -->
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="tab-1" role="tabpanel" aria-labelledby="1-tab">
				<div class="row row--grid">
					<c:forEach var="favorite" items="${profile.favorites}">
						<!-- card -->
						<div class="col-12 col-sm-8 col-lg-6 col-xl-4">
							<div class="card">
								<a href="${applicationScope.rootpath}/home/index?action=watch&id=${favorite.video.id}" class="card__cover"> <img src="${applicationScope.rootpath}/img/poster/${favorite.video.poster}" alt=""> <span class="card__play"> <svg
											xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
										>
												<path
												d="M18.54,9,8.88,3.46a3.42,3.42,0,0,0-5.13,3V17.58A3.42,3.42,0,0,0,7.17,21a3.43,3.43,0,0,0,1.71-.46L18.54,15a3.42,3.42,0,0,0,0-5.92Zm-1,4.19L7.88,18.81a1.44,1.44,0,0,1-1.42,0,1.42,1.42,0,0,1-.71-1.23V6.42a1.42,1.42,0,0,1,.71-1.23A1.51,1.51,0,0,1,7.17,5a1.54,1.54,0,0,1,.71.19l9.66,5.58a1.42,1.42,0,0,1,0,2.46Z"
											/>
											</svg>
								</span>
								</a>
								<div class="card__content">
									<h3 class="card__title">
										<a href="#">${favorite.video.title}</a>
									</h3>
									<span class="card__category"> <c:forEach var="vdg" items="${favorite.video.videoGenres}">
											<a href="#">${vdg.genre.genreName}</a>
										</c:forEach>
									</span> <span class="card__rate">8.4</span>
									<div class="card__action">
										<c:set var="exist" value="false" />
										<c:forEach var="uf" items="${sessionScope.user.favorites}">
											<c:if test="${uf.id==favorite.id}">
												<c:set var="exist" value="true" />
											</c:if>
										</c:forEach>
										<a href="${applicationScope.rootpath}/home/profile?action=like&like-id=${favorite.video.id}"> <i class="bx bx-heart-circle bx-md bx-tada-hover btn__like mx-1 ${exist?'liked':'not__yet-like'}"></i>
										</a><a href="#modal-share-${favorite.video.id}" class="open-modal"> <i class='bx bxs-share bx-md bx-tada-hover btn__share'></i>
										</a>
									</div>
								</div>
							</div>
						</div>
						<!-- end card -->
						<!-- modal status -->
						<div id="modal-share-${favorite.video.id}" class="zoom-anim-dialog mfp-hide modal">
							<h6 class="modal__title">Chia sẻ Video ${favorite.video.id}</h6>
							<p class="modal__text">Nhập Email của bạn bè</p>
							<form action="${applicationScope.rootpath}/home/profile?action=share&share-id=${favorite.video.id}" style="width: 100%;" method="POST">
								<input class="form__input" placeholder="Email" name="mailTo" type="email" required="required">
								<div class="modal__btns">
									<button class="modal__btn modal__btn--dismiss" type="button">Bỏ qua</button>
									<button class="modal__btn modal__btn--apply" type="submit">Áp dụng</button>
								</div>
							</form>
						</div>
						<!-- end modal status -->
					</c:forEach>
				</div>
				<div class="row">
					<!-- paginator -->
					<!-- 
					<div class="col-12">
						<ul class="paginator">
							<li class="paginator__item paginator__item--prev"><a href="#"><svg xmlns="http://www.w3.org/2000/svg" enable-background="new 0 0 24 24" viewBox="0 0 24 24">
												<path d="M8.5,12.8l5.7,5.6c0.4,0.4,1,0.4,1.4,0c0,0,0,0,0,0c0.4-0.4,0.4-1,0-1.4l-4.9-5l4.9-5c0.4-0.4,0.4-1,0-1.4c-0.2-0.2-0.4-0.3-0.7-0.3c-0.3,0-0.5,0.1-0.7,0.3l-5.7,5.6C8.1,11.7,8.1,12.3,8.5,12.8C8.5,12.7,8.5,12.7,8.5,12.8z" />
											</svg></a></li>
							<li class="paginator__item"><a href="#">1</a></li>
							<li class="paginator__item paginator__item--active"><a href="#">2</a></li>
							<li class="paginator__item"><a href="#">3</a></li>
							<li class="paginator__item"><a href="#">4</a></li>
							<li class="paginator__item paginator__item--next"><a href="#"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
												<path d="M15.54,11.29,9.88,5.64a1,1,0,0,0-1.42,0,1,1,0,0,0,0,1.41l4.95,5L8.46,17a1,1,0,0,0,0,1.41,1,1,0,0,0,.71.3,1,1,0,0,0,.71-.3l5.66-5.65A1,1,0,0,0,15.54,11.29Z" />
											</svg></a></li>
						</ul>
					</div> -->
					<!-- end paginator -->
				</div>
			</div>
			<div class="tab-pane fade" id="tab-3" role="tabpanel" aria-labelledby="3-tab">
				<div class="row row--grid">
					<!-- details form -->
					<div class="col-12 col-lg-6">
						<form action="${applicationScope.rootpath}/home/profile?action=update" class="profile__form" method="POST">
							<div class="row">
								<div class="col-12">
									<h4 class="profile__title">Chi tiết hồ sơ</h4>
								</div>
								<input type="hidden" name="id" value="${form.id}">
								<div class="col-12 col-md-6 col-lg-12 col-xl-6">
									<div class="profile__group">
										<label class="profile__label" for="username">Tên đăng nhập</label> <input id="username" type="text" name="username" class="profile__input" value="${form.username}" required="required">
										<div style="color: red; visibility: ${validUsername==null?'hidden':'visible'} ;">${validUsername }</div>
									</div>
								</div>
								<div class="col-12 col-md-6 col-lg-12 col-xl-6">
									<div class="profile__group">
										<label class="profile__label" for="email">Email</label> <input id="email" type="text" name="email" class="profile__input" value="${form.email}" required="required">
										<div style="color: red; visibility: ${validEmail==null?'hidden':'visible'} ;">${validEmail }</div>
									</div>
								</div>
								<div class="col-12 col-md-12 col-lg-12 col-xl-12">
									<div class="profile__group">
										<label class="profile__label" for="lastname">Họ tên</label> <input id="lastname" type="text" name="fullname" class="profile__input" value="${form.fullname}" required="required">
										<div style="color: red; visibility: ${validFullname==null?'hidden':'visible'} ;">${validFullname }</div>
									</div>
								</div>
								<div class="col-12">
									<button class="profile__btn" type="submit">Lưu thay đổi</button>
								</div>
							</div>
						</form>
					</div>
					<!-- end details form -->
					<!-- password form -->
					<div class="col-12 col-lg-6">
						<form action="${applicationScope.rootpath}/home/profile?action=change-password" class="profile__form" method="POST">
							<div class="row">
								<div class="col-12">
									<h4 class="profile__title">Đổi mật khẩu</h4>
								</div>
								<div class="col-12 col-md-6 col-lg-12 col-xl-6">
									<div class="profile__group">
										<label class="profile__label" for="oldpass">Mật khẩu hiện tại</label> <input id="oldpass" type="password" name="currentPass" class="profile__input" required="required"> <span style="color: red; font-size: 13px;">${failCurrentPass}</span>
									</div>
								</div>
								<div class="col-12 col-md-6 col-lg-12 col-xl-6">
									<div class="profile__group">
										<label class="profile__label" for="newpass">Mật khẩu mới</label> <input id="newpass" type="password" name="newPass" class="profile__input" required="required"> <span style="color: red; font-size: 13px;">${failNewPass}</span>
									</div>
								</div>
								<div class="col-12 col-md-6 col-lg-12 col-xl-6">
									<div class="profile__group">
										<label class="profile__label" for="confirmpass">Xác nhận mật khẩu mới</label> <input id="confirmpass" type="password" name="confirm" class="profile__input" required="required"> <span style="color: red; font-size: 13px;">${failConfirm}</span>
									</div>
								</div>
								<div class="col-12">
									<button class="profile__btn" type="submit">Đổi mật khẩu</button>
								</div>
							</div>
						</form>
					</div>
					<!-- end password form -->
				</div>
			</div>
		</div>
		<!-- end content tabs -->
	</div>
</div>
<!-- end content -->