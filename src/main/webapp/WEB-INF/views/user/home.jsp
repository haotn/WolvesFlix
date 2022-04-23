<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!-- home -->
<section class="home">
	<fmt:setBundle basename="home" scope="request" />
	<!-- home bg -->
	<div class="owl-carousel home__bg">
		<div class="item home__cover" data-bg="${applicationScope.rootpath}/img/home/home__bg.jpg"></div>
		<div class="item home__cover" data-bg="${applicationScope.rootpath}/img/home/home__bg2.jpg"></div>
		<div class="item home__cover" data-bg="${applicationScope.rootpath}/img/home/home__bg3.jpg"></div>
		<div class="item home__cover" data-bg="${applicationScope.rootpath}/img/home/home__bg4.jpg"></div>
	</div>
	<!-- end home bg -->
	<div class="container">
		<div class="row">
			<div class="col-12">
				<h1 class="home__title">
					<b> <fmt:message key="home.mostview" />
					</b>
				</h1>
				<button class="home__nav home__nav--prev" aria-label="prev card" type="button">
					<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
								<path d="M17,11H9.41l3.3-3.29a1,1,0,1,0-1.42-1.42l-5,5a1,1,0,0,0-.21.33,1,1,0,0,0,0,.76,1,1,0,0,0,.21.33l5,5a1,1,0,0,0,1.42,0,1,1,0,0,0,0-1.42L9.41,13H17a1,1,0,0,0,0-2Z" />
							</svg>
				</button>
				<button class="home__nav home__nav--next" aria-label="next card" type="button">
					<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
								<path d="M17.92,11.62a1,1,0,0,0-.21-.33l-5-5a1,1,0,0,0-1.42,1.42L14.59,11H7a1,1,0,0,0,0,2h7.59l-3.3,3.29a1,1,0,0,0,0,1.42,1,1,0,0,0,1.42,0l5-5a1,1,0,0,0,.21-.33A1,1,0,0,0,17.92,11.62Z" />
							</svg>
				</button>
			</div>
			<div class="col-12">
				<div class="owl-carousel home__carousel">
					<c:forEach var="item" items="${topViews}">
						<!-- card -->
						<div class="card card--big">
							<a href="${applicationScope.rootpath}/home/index?action=watch&watch-id=${item.id}" class="card__cover"> <img src="${applicationScope.rootpath}/img/poster/${item.poster}" alt=""> <span class="card__play"> <svg
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
									<a href="#">${item.title}</a>
								</h3>
								<span class="card__category"> <c:forEach var="vdg" items="${item.videoGenres}">
										<a>${vdg.genre.genreName}</a>
									</c:forEach>
								</span>
							</div>
						</div>
						<!-- end card -->
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- end home -->
<section class="content">
	<div class="content__head">
		<!-- page title -->
		<section class="section section--first section--bg" data-bg="${applicationScope.rootpath}/img/section/section.jpg" style="margin-top: 0px !important;">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<div class="section__wrap">
							<!-- section title -->
							<h1 class="section__title">
								<fmt:message key="home.videolist" />
							</h1>
							<!-- end section title -->
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- end page title -->
	</div>
	<!-- filter -->
	<div id="filter" class="filter">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<form action="${applicationScope}/home/index">
						<div class="filter__content">
							<div class="filter__items"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- end filter -->
	<!-- catalog -->
	<div class="catalog">
		<div class="container">
			<div class="row row--grid">
				<c:forEach var="video" items="${videos}">
					<!-- card -->
					<div class="col-12 col-sm-8 col-lg-6 col-xl-4">
						<div class="card">
							<a href="${applicationScope.rootpath}/home/index?action=watch&watch-id=${video.id}" class="card__cover"> <img src="${applicationScope.rootpath}/img/poster/${video.poster}" alt=""> <span class="card__play"> <svg
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
									<a href="#">${video.title}</a>
								</h3>
								<span class="card__category"> <c:forEach var="vdgenre" items="${video.videoGenres}">
										<a href="#">${vdgenre.genre.genreName}</a>
									</c:forEach>
								</span>
								<div class="card__action">
									<c:set var="exist" value="false" />
									<c:forEach var="uf" items="${sessionScope.user.favorites}">
										<c:forEach var="vf" items="${video.favorites}">
											<c:if test="${uf.id==vf.id}">
												<c:set var="exist" value="true" />
											</c:if>
										</c:forEach>
									</c:forEach>
									<a href="${applicationScope.rootpath}/home/index?action=like&like-id=${video.id}"> <i class="bx bx-heart-circle bx-md bx-tada-hover btn__like mx-1 ${exist?'liked':'not__yet-like'}"></i>
									</a><a href="#modal-share-${video.id}" class="open-modal"> <i class='bx bxs-share bx-md bx-tada-hover btn__share'></i>
									</a>
								</div>
							</div>
						</div>
					</div>
					<!-- end card -->
					<!-- modal status -->
					<div id="modal-share-${video.id}" class="zoom-anim-dialog mfp-hide modal">
						<h6 class="modal__title">Chia sẻ Video ${video.id}</h6>
						<p class="modal__text">Nhập Email của bạn bè</p>
						<form action="${applicationScope.rootpath}/home/index?action=share&share-id=${video.id}" method="POST" style="width: 100%;">
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
				<div class="col-12">
					<ul class="paginator">
						<li id="page-home-first" class="paginator__item paginator__item--prev"><a href="${applicationScope.rootpath}/home/index?page=1&search=${sessionScope.search}"> <svg xmlns="http://www.w3.org/2000/svg" enable-background="new 0 0 24 24"
									viewBox="0 0 24 24"
								>
									<path d="M8.5,12.8l5.7,5.6c0.4,0.4,1,0.4,1.4,0c0,0,0,0,0,0c0.4-0.4,0.4-1,0-1.4l-4.9-5l4.9-5c0.4-0.4,0.4-1,0-1.4c-0.2-0.2-0.4-0.3-0.7-0.3c-0.3,0-0.5,0.1-0.7,0.3l-5.7,5.6C8.1,11.7,8.1,12.3,8.5,12.8C8.5,12.7,8.5,12.7,8.5,12.8z" />
								</svg>
						</a></li>
						<li id="page-home-prev" class="paginator__item ${currentPage==1?' paginator__item--active':''}"><c:choose>
								<c:when test="${currentPage==1}">
									<a href="${applicationScope.rootpath}/home/index?page=1&search=${sessionScope.search}">1</a>
								</c:when>
								<c:when test="${currentPage==lastPage}">
									<a href="${applicationScope.rootpath}/home/index?page=${currentPage-2}&search=${sessionScope.search}">${currentPage-2}</a>
								</c:when>
								<c:otherwise>
									<a href="${applicationScope.rootpath}/home/index?page=${currentPage-1}&search=${sessionScope.search}">${currentPage-1}</a>
								</c:otherwise>
							</c:choose></li>
						<c:if test="${lastPage>1}">
							<li id="page-home-current" class="paginator__item ${currentPage>1&&currentPage<lastPage?'paginator__item--active':''}"><c:choose>
									<c:when test="${currentPage==1}">
										<a href="${applicationScope.rootpath}/home/index?page=${currentPage+1}&search=${sessionScope.search}">${currentPage+1}</a>
									</c:when>
									<c:when test="${currentPage==lastPage}">
										<a href="${applicationScope.rootpath}/home/index?page=${currentPage-1}&search=${sessionScope.search}">${currentPage-1}</a>
									</c:when>
									<c:otherwise>
										<a>${currentPage}</a>
									</c:otherwise>
								</c:choose></li>
							<c:if test="${lastPage>2}">
								<li id="page-home-next" class="paginator__item ${currentPage==lastPage&&currentPage!=1?'paginator__item--active':''}"><c:choose>
										<c:when test="${currentPage==1}">
											<a href="${applicationScope.rootpath}/home/index?page=${currentPage+2}&search=${sessionScope.search}">${currentPage+2}</a>
										</c:when>
										<c:when test="${currentPage==lastPage}">
											<a href="${applicationScope.rootpath}/home/index?page=${currentPage}&search=${sessionScope.search}">${currentPage}</a>
										</c:when>
										<c:otherwise>
											<a href="${applicationScope.rootpath}/home/index?page=${currentPage+1}&search=${sessionScope.search}">${currentPage+1}</a>
										</c:otherwise>
									</c:choose></li>
							</c:if>
						</c:if>
						<li id="page-home-last" class="paginator__item paginator__item--next"><a href="${applicationScope.rootpath}/home/index?page=${lastPage}&search=${sessionScope.search}"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
									<path d="M15.54,11.29,9.88,5.64a1,1,0,0,0-1.42,0,1,1,0,0,0,0,1.41l4.95,5L8.46,17a1,1,0,0,0,0,1.41,1,1,0,0,0,.71.3,1,1,0,0,0,.71-.3l5.66-5.65A1,1,0,0,0,15.54,11.29Z" />
								</svg>
						</a></li>
					</ul>
				</div>
				<!-- end paginator -->
			</div>
		</div>
	</div>
	<!-- end catalog -->
</section>