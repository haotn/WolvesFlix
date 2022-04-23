<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- details -->
<section class="section details">
	<!-- details background -->
	<div class="details__bg" data-bg="${applicationScope.rootpath}/img/home/home__bg.jpg"></div>
	<!-- end details background -->
	<!-- details content -->
	<div class="container">
		<div class="row">
			<!-- title -->
			<div class="col-12">
				<h1 class="details__title">${video.title}</h1>
			</div>
			<!-- end title -->
			<!-- player -->
			<div class="col-12 col-xl-12">
				<iframe style="width: 100%; height: 625px;" src="https://www.youtube.com/embed/${video.youtubeID}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
					allowfullscreen
				></iframe>
			</div>
			<!-- end player -->
		</div>
		<div class="row justify-content-center">
			<!-- content -->
			<div class="col-12 col-xl-12">
				<div class="card card--details">
					<!-- card cover -->
					<div class="card__cover">
						<img src="${applicationScope.rootpath}/img/poster/${video.poster}" alt="">
					</div>
					<!-- end card cover -->
					<!-- card content -->
					<div class="card__content">
						<ul class="card__meta">
							<li><span>Thể loại:</span> <c:forEach var="videoGenre" items="${video.videoGenres}">
									<a>${videoGenre.genre.genreName}</a>
								</c:forEach></li>
							<li><span>Lượt xem:</span> ${fn:length(video.views)}</li>
							<li><span>Lượt thích:</span> ${fn:length(video.favorites)}</li>
							<li><span>Ngày đăng</span> 2017</li>
						</ul>
						<div class="card__description card__description--details">${video.description}</div>
						<div class="card__action">
							<c:set var="exist" value="false" />
							<c:forEach var="vf" items="${video.favorites}">
								<c:forEach var="uf" items="${sessionScope.user.favorites}">
									<c:if test="${vf.id==uf.id}">
										<c:set var="exist" value="true" />
									</c:if>
								</c:forEach>
							</c:forEach>
							<a href="${applicationScope.rootpath}/home/index?action=watch&watch-id=${video.id}&sub-action=like&like-id=${video.id}"> <i class="bx bx-heart-circle bx-md bx-tada-hover btn__like mx-1 ${exist?'liked':'not__yet-like'}"></i>
							</a><a href="#modal-share-${video.id}" class="open-modal"> <i class='bx bxs-share bx-md bx-tada-hover btn__share'></i>
							</a>
						</div>
					</div>
					<!-- end card content -->
				</div>
			</div>
			<!-- end content -->
		</div>
	</div>
	<!-- end details content -->
</section>
<!-- end details -->
<!-- content -->
<section class="content">
	<div class="content__head">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<!-- content title -->
					<h2 class="content__title">Khám phá</h2>
					<!-- end content title -->
					<!-- content tabs nav -->
					<ul class="nav nav-tabs content__tabs" id="content__tabs" role="tablist">
						<li class="nav-item" role="presentation"><a class="nav-link active" data-toggle="tab" href="#tab-1" role="tab" aria-controls="tab-1" aria-selected="true">Bình luận</a></li>
						<li class="nav-item" role="presentation"><a class="nav-link" data-toggle="tab" href="#tab-2" role="tab" aria-controls="tab-2" aria-selected="false">Đề xuất cho bạn</a></li>
						<li class="nav-item" role="presentation"><a class="nav-link" data-toggle="tab" href="#tab-3" role="tab" aria-controls="tab-3" aria-selected="false">Lịch sử xem</a></li>
					</ul>
					<!-- end content tabs nav -->
					<!-- content mobile tabs nav -->
					<div class="content__mobile-tabs" id="content__mobile-tabs">
						<div class="content__mobile-tabs-btn dropdown-toggle" role="navigation" id="mobile-tabs" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<input type="button" value="Comments"> <span></span>
						</div>
						<div class="content__mobile-tabs-menu dropdown-menu" aria-labelledby="mobile-tabs">
							<ul class="nav nav-tabs" role="tablist">
								<li class="nav-item"><a class="nav-link active" id="1-tab" data-toggle="tab" href="#tab-1" role="tab" aria-controls="tab-1" aria-selected="true">Bình luận</a></li>
								<li class="nav-item"><a class="nav-link" id="2-tab" data-toggle="tab" href="#tab-2" role="tab" aria-controls="tab-2" aria-selected="false">Đề xuất cho bạn</a></li>
								<li class="nav-item"><a class="nav-link" id="3-tab" data-toggle="tab" href="#tab-3" role="tab" aria-controls="tab-3" aria-selected="false">Lịch sử xem</a></li>
							</ul>
						</div>
					</div>
					<!-- end content mobile tabs nav -->
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-12 col-lg-12 col-xl-12">
				<!-- content tabs -->
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="tab-1" role="tabpanel" aria-labelledby="1-tab">
						<div class="row">
							<!-- section title -->
							<div class="col-12">
								<h2 class="section__title section__title--sidebar">
									Bình luận về <i>${video.title}</i>
								</h2>
							</div>
							<!-- end section title -->
							<!-- comments -->
							<div class="col-12">
								<div class="comments">
									<ul class="comments__list">
										<c:forEach var="comment" items="${listComment}">
											<li class="comments__item">
												<div class="comments__autor">
													<img class="comments__avatar" src="${applicationScope.rootpath}/img/user.svg" alt=""> <span class="comments__name">${comment.user.fullname}</span> <span class="comments__time">${comment.commentDate}</span>
												</div>
												<p class="comments__text">${comment.content}</p>
												<div class="comments__actions">
													<div class="comments__rate">
														<button type="button">
															<i class="icon ion-md-thumbs-up"></i>12
														</button>
														<button type="button">
															7<i class="icon ion-md-thumbs-down"></i>
														</button>
													</div>
													<button type="button">
														<i class="icon ion-ios-share-alt"></i>Reply
													</button>
													<button type="button">
														<i class="icon ion-ios-quote"></i>Quote
													</button>
												</div>
											</li>
											<c:forEach var="answer" items="${listReply}">
												<c:if test="${answer.comment.id==comment.id}">
													<c:choose>
														<c:when test="${answer.hasQuote}">
															<li class="comments__item comments__item--quote">
																<div class="comments__autor">
																	<img class="comments__avatar" src="${applicationScope.rootpath}/img/user.svg" alt=""> <span class="comments__name">${answer.user.fullname}</span> <span class="comments__time">${answer.replyDate}</span>
																</div>
																<p class="comments__text">
																	<span>${answer.comment.content}</span>${answer.content}
																</p>
																<div class="comments__actions">
																	<div class="comments__rate">
																		<button type="button">
																			<i class="icon ion-md-thumbs-up"></i>11
																		</button>
																		<button type="button">
																			1<i class="icon ion-md-thumbs-down"></i>
																		</button>
																	</div>
																	<button type="button">
																		<i class="icon ion-ios-share-alt"></i>Reply
																	</button>
																	<button type="button">
																		<i class="icon ion-ios-quote"></i>Quote
																	</button>
																</div>
															</li>
														</c:when>
														<c:otherwise>
															<li class="comments__item comments__item--answer">
																<div class="comments__autor">
																	<img class="comments__avatar" src="${applicationScope.rootpath}/img/user.png" alt=""> <span class="comments__name">${answer.user.fullname}</span> <span class="comments__time">${answer.replyDate}</span>
																</div>
																<p class="comments__text">${answer.content}</p>
																<div class="comments__actions">
																	<div class="comments__rate">
																		<button type="button">
																			<i class="icon ion-md-thumbs-up"></i>8
																		</button>
																		<button type="button">
																			3<i class="icon ion-md-thumbs-down"></i>
																		</button>
																	</div>
																	<button type="button">
																		<i class="icon ion-ios-share-alt"></i>Reply
																	</button>
																	<button type="button">
																		<i class="icon ion-ios-quote"></i>Quote
																	</button>
																</div>
															</li>
														</c:otherwise>
													</c:choose>
												</c:if>
											</c:forEach>
										</c:forEach>
									</ul>
									<form action="${applicationScope.rootpath}/add-comment/${video.id}" class="form">
										<textarea id="text" name="text" class="form__textarea" placeholder="Add comment"></textarea>
										<button type="button" class="form__btn">Đăng</button>
									</form>
								</div>
							</div>
							<!-- end comments -->
						</div>
					</div>
					<div class="tab-pane fade" id="tab-2" role="tabpanel" aria-labelledby="2-tab">
						<div class="row row--grid">
							<c:forEach var="item" items="${listRecommend}">
								<!-- card -->
								<div class="col-12 col-sm-8 col-lg-6 col-xl-4 mt-5">
									<div class="card">
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
											<span class="card__category"> <c:forEach var="vdgenre" items="${item.videoGenres}">
													<a href="#">${vdgenre.genre.genreName}</a>
												</c:forEach>
											</span>
											<div class="card__action">
												<c:set var="exist" value="false" />
												<c:forEach var="uf" items="${sessionScope.user.favorites}">
													<c:forEach var="vf" items="${item.favorites}">
														<c:if test="${uf.id==vf.id}">
															<c:set var="exist" value="true" />
														</c:if>
													</c:forEach>
												</c:forEach>
												<a href="${applicationScope.rootpath}/home/index?action=watch&watch-id=${video.id}&sub-action=like&like-id=${item.id}&current-tab=2"> <i class="bx bx-heart-circle bx-md bx-tada-hover btn__like mx-1 ${exist?'liked':'not__yet-like'}"></i>
												</a><a href="#modal-share-${item.id}" class="open-modal"> <i class='bx bxs-share bx-md bx-tada-hover btn__share'></i>
												</a>
											</div>
										</div>
									</div>
								</div>
								<!-- end card -->
								<!-- modal status -->
								<div id="modal-share-${item.id}" class="zoom-anim-dialog mfp-hide modal">
									<h6 class="modal__title">Chia sẻ Video ${video.id}</h6>
									<p class="modal__text">Nhập Email của bạn bè</p>
									<form action="${applicationScope.rootpath}/home/index?action=watch&watch-id=${video.id}&sub-action=share&share-id=${item.id}&current-tab=2" method="POST" style="width: 100%;">
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
					</div>
					<div class="tab-pane fade" id="tab-3" role="tabpanel" aria-labelledby="3-tab">
						<div class="row justify-content-end mt-3">
							<div class="col-5">
								<a href="${applicationScope.rootpath}/home/index?action=watch&watch-id=${video.id}&sub-action=clear-history">
									<button class="modal__btn modal__btn--apply">Xóa lịch sử xem</button>
								</a>
							</div>
						</div>
						<div class="row row--grid">
							<c:choose>
								<c:when test="${listHistory==null}">
									<div class="col-12 text-center">
										<h1 style="color: white;">Không có lịch sử xem</h1>
									</div>
								</c:when>
								<c:otherwise>
									<c:forEach var="item" items="${listHistory}">
										<!-- card -->
										<div class="col-12 col-sm-8 col-lg-6 col-xl-4 mt-5">
											<div class="card">
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
													<span class="card__category"> <c:forEach var="vdgenre" items="${item.videoGenres}">
															<a href="#">${vdgenre.genre.genreName}</a>
														</c:forEach>
													</span>
													<div class="card__action">
														<c:set var="exist" value="false" />
														<c:forEach var="uf" items="${sessionScope.user.favorites}">
															<c:forEach var="vf" items="${item.favorites}">
																<c:if test="${uf.id==vf.id}">
																	<c:set var="exist" value="true" />
																</c:if>
															</c:forEach>
														</c:forEach>
														<a href="${applicationScope.rootpath}/home/index?action=watch&watch-id=${video.id}&sub-action=like&like-id=${item.id}&current-tab=3"> <i class="bx bx-heart-circle bx-md bx-tada-hover btn__like mx-1 ${exist?'liked':'not__yet-like'}"></i>
														</a><a href="#modal-share-${item.id}" class="open-modal"> <i class='bx bxs-share bx-md bx-tada-hover btn__share'></i>
														</a>
													</div>
												</div>
											</div>
										</div>
										<!-- end card -->
										<!-- modal status -->
										<div id="modal-share-${item.id}" class="zoom-anim-dialog mfp-hide modal">
											<h6 class="modal__title">Chia sẻ Video ${video.id}</h6>
											<p class="modal__text">Nhập Email của bạn bè</p>
											<form action="${applicationScope.rootpath}/home/index?action=watch&watch-id=${video.id}&sub-action=share&share-id=${item.id}&current-tab=3" method="POST" style="width: 100%;">
												<input class="form__input" placeholder="Email" name="mailTo" type="email" required="required">
												<div class="modal__btns">
													<button class="modal__btn modal__btn--dismiss" type="button">Bỏ qua</button>
													<button class="modal__btn modal__btn--apply" type="submit">Áp dụng</button>
												</div>
											</form>
										</div>
										<!-- end modal status -->
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<!-- end content tabs -->
			</div>
		</div>
	</div>
</section>
<!-- end content -->
