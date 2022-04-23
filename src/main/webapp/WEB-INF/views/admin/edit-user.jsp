<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!-- main content -->
<main class="main">
	<div class="container-fluid">
		<div class="row">
			<!-- main title -->
			<div class="col-12">
				<div class="main__title">
					<h2>Chỉnh sửa thông tin người dùng</h2>
				</div>
			</div>
			<!-- end main title -->
			<!-- profile -->
			<div class="col-12">
				<div class="profile__content">
					<!-- profile user -->
					<div class="profile__user">
						<div class="profile__avatar">
							<img src="${applicationScope.rootpath}/img/user.svg" alt="">
						</div>
						<!-- or red -->
						<div class="profile__meta profile__meta--green">
							<h3>
								${form.username} <span>(${form.accept?'Chấp nhận':'Từ chối'})</span>
							</h3>
							<span>ID: ${form.id}</span>
						</div>
					</div>
					<!-- end profile user -->
					<!-- profile tabs nav -->
					<form action="" id="tab-navigator-edit-user" method="post">
						<div id="videoId" style="display: none;">${form.id}</div>
						<div id="attab" style="display: none;">comment</div>
						<ul class="nav nav-tabs profile__tabs" id="profile__tabs" role="tablist">
							<li class="nav-item" role="presentation"><a id="profile-tab" class="nav-link ${currentTab=='profile'?'active':''}" data-toggle="tab" href="#tab-1" role="tab" aria-controls="tab-1" aria-selected="true">Hồ sơ</a></li>
							<li class="nav-item" role="presentation"><a class="nav-link ${currentTab=='comment'?'active':''}" id="comment-tab" data-toggle="tab" href="#tab-2" role="tab" aria-controls="tab-2" aria-selected="false">Bình luận</a></li>
						</ul>
					</form>
					<!-- end profile tabs nav -->
					<!-- profile mobile tabs nav -->
					<div class="profile__mobile-tabs" id="profile__mobile-tabs">
						<div class="profile__mobile-tabs-btn dropdown-toggle" role="navigation" id="mobile-tabs" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<input type="button" value="Profile"> <span></span>
						</div>
						<div class="profile__mobile-tabs-menu dropdown-menu" aria-labelledby="mobile-tabs">
							<ul class="nav nav-tabs" role="tablist">
								<li class="nav-item"><a class="nav-link ${currentTab=='profile'?'active':''}" id="1-tab" data-toggle="tab" href="#tab-1" role="tab" aria-controls="tab-1" aria-selected="true">Chi tiết hồ sơ</a></li>
								<li class="nav-item"><a class="nav-link ${currentTab=='comment'?'active':''}" id="2-tab" data-toggle="tab" href="#tab-2" role="tab" aria-controls="tab-2" aria-selected="false">Bình luận</a></li>
							</ul>
						</div>
					</div>
					<!-- end profile mobile tabs nav -->
					<!-- profile btns -->
					<div class="profile__actions">
						<a href="#modal-status3" class="profile__action profile__action--banned open-modal"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
								<path
									d="M12,13a1,1,0,0,0-1,1v3a1,1,0,0,0,2,0V14A1,1,0,0,0,12,13Zm5-4V7A5,5,0,0,0,7,7V9a3,3,0,0,0-3,3v7a3,3,0,0,0,3,3H17a3,3,0,0,0,3-3V12A3,3,0,0,0,17,9ZM9,7a3,3,0,0,1,6,0V9H9Zm9,12a1,1,0,0,1-1,1H7a1,1,0,0,1-1-1V12a1,1,0,0,1,1-1H17a1,1,0,0,1,1,1Z"
								/></svg></a> <a href="#modal-delete3" class="profile__action profile__action--delete open-modal"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
								<path d="M20,6H16V5a3,3,0,0,0-3-3H11A3,3,0,0,0,8,5V6H4A1,1,0,0,0,4,8H5V19a3,3,0,0,0,3,3h8a3,3,0,0,0,3-3V8h1a1,1,0,0,0,0-2ZM10,5a1,1,0,0,1,1-1h2a1,1,0,0,1,1,1V6H10Zm7,14a1,1,0,0,1-1,1H8a1,1,0,0,1-1-1V8H17Z" /></svg></a>
					</div>
					<!-- end profile btns -->
				</div>
			</div>
			<!-- end profile -->
			<!-- content tabs -->
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade ${currentTab=='profile'?'show active':''}" id="tab-1" role="tabpanel" aria-labelledby="1-tab">
					<div class="col-12">
						<div class="row justify-content-center">
							<!-- details form -->
							<div class="col-12 col-lg-8">
								<form action="${applicationScope.rootpath}/admin/user/update" class="profile__form" method="post">
									<input type="hidden" name="sub-action" value="update"> <input type="hidden" name="edit-id" value="${form.id}"><input type="hidden" name="action" value="edit"> <input type="hidden" name="current-tab"
										value="${currentTab}"
									> <input type="hidden" name="id" value="${form.id}">
									<div class="row">
										<div class="col-12">
											<h4 class="profile__title">Thông tin hồ sơ</h4>
										</div>
										<div class="col-12 col-md-6 col-lg-12 col-xl-6">
											<div class="profile__group">
												<label class="profile__label" for="username">Username</label> <input id="username" type="text" name="username" class="profile__input" placeholder="Tên đăng nhập" value="${form.username}" required="required"> <span
													style="color: red; font-size: 13px; visibility: ${validUsername==null?'hidden': 'visible'};"
												>${validUsername}</span>
											</div>
										</div>
										<div class="col-12 col-md-12 col-lg-12 col-xl-12">
											<div class="profile__group">
												<label class="profile__label" for="email">Email</label> <input id="email" type="text" name="email" class="profile__input" placeholder="Email" value="${form.email}" required="required"> <span
													style="color: red; font-size: 13px; visibility: ${validEmail==null?'hidden': 'visible'};"
												>${validEmail}</span>
											</div>
										</div>
										<div class="col-12 col-md-12 col-lg-12 col-xl-12">
											<div class="profile__group">
												<label class="profile__label" for="lastname">Họ tên</label> <input id="fullname" type="text" name="fullname" class="profile__input" placeholder="Họ tên" value="${form.fullname}" required="required"> <span
													style="color: red; font-size: 13px; visibility: ${validFullname==null?'hidden': 'visible'};"
												>${validFullname}</span>
											</div>
										</div>
										<div class="col-12 col-md-6 col-lg-12 col-xl-6">
											<label class="profile__label" for="rights">Quyền</label> <select class="js-example-basic-single" id="rights" name="admin">
												<option value="false" ${form.admin?'':'selected'}>Người dùng</option>
												<option value="true" ${form.admin?'selected':''}>Người quản trị</option>
											</select>
										</div>
										<div class="col-12">
											<button class="profile__btn" type="submit">Lưu</button>
										</div>
									</div>
								</form>
							</div>
							<!-- end details form -->
							<!-- password form -->
							<!--  
							<div class="col-12 col-lg-6">
								<form action="${applicationScope.rootpath}/admin/user?action=edit&edit-id=${form.id}&sub-action=change-password" class="profile__form" method="post">
									<div class="row">
										<div class="col-12">
											<h4 class="profile__title">Đổi mật khẩu</h4>
										</div>
										<div class="col-12 col-md-6 col-lg-12 col-xl-6">
											<div class="profile__group">
												<label class="profile__label" for="oldpass">Mật khẩu hiện tại</label> <input id="oldPass" type="password" name="oldpass" class="profile__input"> <span
													style="color: red; font-size: 13px; visibility: ${validPassword==null?'hidden': 'visible'};"
												>${validFullname}</span>
											</div>
										</div>
										<div class="col-12 col-md-6 col-lg-12 col-xl-6">
											<div class="profile__group">
												<label class="profile__label" for="newpass">Mật khẩu mới</label> <input id="newPass" type="password" name="newpass" class="profile__input"> <span
													style="color: red; font-size: 13px; visibility: ${validPassword==null?'hidden': 'visible'};"
												>${validFullname}</span>
											</div>
										</div>
										<div class="col-12 col-md-6 col-lg-12 col-xl-6">
											<div class="profile__group">
												<label class="profile__label" for="confirmpass">Nhập lại mật khẩu mới</label> <input id="confirmPass" type="password" name="confirmpass" class="profile__input"> <span
													style="color: red; font-size: 13px; visibility: ${validConfirmPassword==null?'hidden': 'visible'};"
												>${validFullname}</span>
											</div>
										</div>
										<div class="col-12">
											<button class="profile__btn" type="submit">Đổi mật khẩu</button>
										</div>
									</div>
								</form>
							</div>-->
							<!-- end password form -->
						</div>
					</div>
				</div>
				<div class="tab-pane fade ${currentTab=='comment'?'show active':''}" id="tab-2" role="tabpanel" aria-labelledby="2-tab">
					<!-- table -->
					<div class="col-12">
						<div class="main__table-wrap">
							<table class="main__table">
								<thead>
									<tr>
										<th>ID</th>
										<th>Video</th>
										<th>Nội dung</th>
										<th>Phản hồi</th>
										<th>Ngày đăng</th>
										<th>Thao tác</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="comment" items="${listComment}">
										<tr>
											<td>
												<div class="main__table-text">${comment.id}</div>
											</td>
											<td>
												<div class="main__table-text" style="max-width: 200px;">${comment.video.title}</div>
											</td>
											<td>
												<div class="main__table-text" style="max-width: 550px;">${comment.content}</div>
											</td>
											<td>
												<div class="main__table-text">${fn:length(comment.replys)}</div>
											</td>
											<td>
												<div class="main__table-text">
													<fmt:formatDate value="${comment.commentDate}" pattern="dd/MM/yyyy" />
												</div>
											</td>
											<td>
												<div class="main__table-btns">
													<a href="#modal-view" class="main__table-btn main__table-btn--view open-modal"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
														<path
																d="M21.92,11.6C19.9,6.91,16.1,4,12,4S4.1,6.91,2.08,11.6a1,1,0,0,0,0,.8C4.1,17.09,7.9,20,12,20s7.9-2.91,9.92-7.6A1,1,0,0,0,21.92,11.6ZM12,18c-3.17,0-6.17-2.29-7.9-6C5.83,8.29,8.83,6,12,6s6.17,2.29,7.9,6C18.17,15.71,15.17,18,12,18ZM12,8a4,4,0,1,0,4,4A4,4,0,0,0,12,8Zm0,6a2,2,0,1,1,2-2A2,2,0,0,1,12,14Z"
															/></svg>
													</a> <a href="#modal-delete" class="main__table-btn main__table-btn--delete open-modal"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
														<path d="M20,6H16V5a3,3,0,0,0-3-3H11A3,3,0,0,0,8,5V6H4A1,1,0,0,0,4,8H5V19a3,3,0,0,0,3,3h8a3,3,0,0,0,3-3V8h1a1,1,0,0,0,0-2ZM10,5a1,1,0,0,1,1-1h2a1,1,0,0,1,1,1V6H10Zm7,14a1,1,0,0,1-1,1H8a1,1,0,0,1-1-1V8H17Z" /></svg>
													</a>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!-- end table -->
					<!-- paginator -->
					<div class="col-12">
						<div class="paginator-wrap">
							<span>10 from 23</span>
							<ul class="paginator">
								<li class="paginator__item paginator__item--prev"><a href="${applicationScope.rootpath}/admin/user-manage/edit/${form.id}/comment?page=1" id="first-page-comment"> <svg xmlns="http://www.w3.org/2000/svg"
											enable-background="new 0 0 24 24" viewBox="0 0 24 24"
										>
											<path d="M8.5,12.8l5.7,5.6c0.4,0.4,1,0.4,1.4,0c0,0,0,0,0,0c0.4-0.4,0.4-1,0-1.4l-4.9-5l4.9-5c0.4-0.4,0.4-1,0-1.4c-0.2-0.2-0.4-0.3-0.7-0.3c-0.3,0-0.5,0.1-0.7,0.3l-5.7,5.6C8.1,11.7,8.1,12.3,8.5,12.8C8.5,12.7,8.5,12.7,8.5,12.8z">
											</path>
											</svg>
								</a></li>
								<li class="paginator__item  ${currentPage==1?' paginator__item--active':''}"><c:choose>
										<c:when test="${currentPage==1}">
											<a id="prev-page-comment" href="${applicationScope.rootpath}/admin/user-manage/edit/${form.id}/comment?page=1">1</a>
										</c:when>
										<c:when test="${currentPage==lastPage}">
											<a id="current-page-comment" href="${applicationScope.rootpath}/admin/user-manage/edit/${form.id}/comment?page=${currentPage-2}">${currentPage-2}</a>
										</c:when>
										<c:otherwise>
											<a id="prev-page-comment" href="${applicationScope.rootpath}/admin/user-manage/edit/${form.id}/comment?page=${currentPage -1}">${currentPage-1}</a>
										</c:otherwise>
									</c:choose></li>
								<li class="paginator__item ${currentPage!=1 && currentPage!=lastPage?' paginator__item--active':''}"><c:choose>
										<c:when test="${currentPage==1}">
											<a id="current-page-comment" href="${applicationScope.rootpath}/admin/user-manage/edit/${form.id}/comment?page=${currentPage+1}">${currentPage+1}</a>
										</c:when>
										<c:when test="${currentPage==lastPage}">
											<a id="current-page-comment" href="${applicationScope.rootpath}/admin/user-manage/edit/${form.id}/comment?page=${currentPage-1}">${currentPage-1}</a>
										</c:when>
										<c:otherwise>
											<a id="current-page-comment" href="${applicationScope.rootpath}/admin/user-manage/edit/${form.id}/comment?page=${currentPage}">${currentPage}</a>
										</c:otherwise>
									</c:choose></li>
								<li class="paginator__item ${currentPage==lastPage?' paginator__item--active':''}"><c:choose>
										<c:when test="${currentPage==1}">
											<a id="next-page-comment" href="${applicationScope.rootpath}/admin/user-manage/edit/${form.id}/comment?page=${currentPage +2}">${currentPage +2}</a>
										</c:when>
										<c:when test="${currentPage==lastPage}">
											<a id="next-page-comment" href="${applicationScope.rootpath}/admin/user-manage/edit/${form.id}/comment?page=${currentPage}">${currentPage}</a>
										</c:when>
										<c:otherwise>
											<a id="next-page-comment" href="${applicationScope.rootpath}/admin/user-manage/edit/${form.id}/comment?page=${currentPage +1}">${currentPage +1}</a>
										</c:otherwise>
									</c:choose></li>
								<li class="paginator__item paginator__item--next"><a href="${applicationScope.rootpath}/admin/user-manage/edit/${form.id}/comment?page=${lastPage}" id="last-page-comment"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
											<path d="M15.54,11.29,9.88,5.64a1,1,0,0,0-1.42,0,1,1,0,0,0,0,1.41l4.95,5L8.46,17a1,1,0,0,0,0,1.41,1,1,0,0,0,.71.3,1,1,0,0,0,.71-.3l5.66-5.65A1,1,0,0,0,15.54,11.29Z"></path></svg></a></li>
							</ul>
						</div>
					</div>
					<!-- end paginator -->
				</div>
			</div>
			<!-- end content tabs -->
		</div>
	</div>
</main>
<!-- end main content -->
<!-- modal view -->
<div id="modal-view" class="zoom-anim-dialog mfp-hide modal modal--view">
	<div class="comments__autor">
		<img class="comments__avatar" src="${applicationScope.rootpath}/img/user.svg" alt=""> <span class="comments__name">John Doe</span> <span class="comments__time">30.08.2018, 17:53</span>
	</div>
	<p class="comments__text">There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going
		to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.</p>
	<div class="comments__actions">
		<div class="comments__rate">
			<span><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
					<path
						d="M21.3,10.08A3,3,0,0,0,19,9H14.44L15,7.57A4.13,4.13,0,0,0,11.11,2a1,1,0,0,0-.91.59L7.35,9H5a3,3,0,0,0-3,3v7a3,3,0,0,0,3,3H17.73a3,3,0,0,0,2.95-2.46l1.27-7A3,3,0,0,0,21.3,10.08ZM7,20H5a1,1,0,0,1-1-1V12a1,1,0,0,1,1-1H7Zm13-7.82-1.27,7a1,1,0,0,1-1,.82H9V10.21l2.72-6.12A2.11,2.11,0,0,1,13.1,6.87L12.57,8.3A2,2,0,0,0,14.44,11H19a1,1,0,0,1,.77.36A1,1,0,0,1,20,12.18Z"
					/></svg>12</span> <span>7<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
					<path
						d="M19,2H6.27A3,3,0,0,0,3.32,4.46l-1.27,7A3,3,0,0,0,5,15H9.56L9,16.43A4.13,4.13,0,0,0,12.89,22a1,1,0,0,0,.91-.59L16.65,15H19a3,3,0,0,0,3-3V5A3,3,0,0,0,19,2ZM15,13.79l-2.72,6.12a2.13,2.13,0,0,1-1.38-2.78l.53-1.43A2,2,0,0,0,9.56,13H5a1,1,0,0,1-.77-.36A1,1,0,0,1,4,11.82l1.27-7a1,1,0,0,1,1-.82H15ZM20,12a1,1,0,0,1-1,1H17V4h2a1,1,0,0,1,1,1Z"
					/></svg></span>
		</div>
	</div>
</div>
<!-- end modal view -->
<!-- modal delete -->
<div id="modal-delete" class="zoom-anim-dialog mfp-hide modal">
	<h6 class="modal__title">Comment delete</h6>
	<p class="modal__text">Are you sure to permanently delete this comment?</p>
	<div class="modal__btns">
		<button class="modal__btn modal__btn--apply" type="button">Delete</button>
		<button class="modal__btn modal__btn--dismiss" type="button">Dismiss</button>
	</div>
</div>
<!-- end modal delete -->
<!-- modal view -->
<div id="modal-view2" class="zoom-anim-dialog mfp-hide modal modal--view">
	<div class="reviews__autor">
		<img class="reviews__avatar" src="${applicationScope.rootpath}/img/user.svg" alt=""> <span class="reviews__name">Best Marvel movie in my opinion</span> <span class="reviews__time">24.08.2018, 17:53 by John Doe</span> <span
			class="reviews__rating"
		>8.4</span>
	</div>
	<p class="reviews__text">There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going
		to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.</p>
</div>
<!-- end modal view -->
<!-- modal delete -->
<div id="modal-delete2" class="zoom-anim-dialog mfp-hide modal">
	<h6 class="modal__title">Review delete</h6>
	<p class="modal__text">Are you sure to permanently delete this review?</p>
	<div class="modal__btns">
		<button class="modal__btn modal__btn--apply" type="button">Delete</button>
		<button class="modal__btn modal__btn--dismiss" type="button">Dismiss</button>
	</div>
</div>
<!-- end modal delete -->
<!-- modal status -->
<div id="modal-status3" class="zoom-anim-dialog mfp-hide modal">
	<h6 class="modal__title">Thay đổi trạng thái</h6>
	<p class="modal__text">Bạn có chắc muốn thay đổi trạng thái?</p>
	<div class="modal__btns">
		<button class="modal__btn modal__btn--apply" type="button">Áp dụng</button>
		<button class="modal__btn modal__btn--dismiss" type="button">Bỏ qua</button>
	</div>
</div>
<!-- end modal status -->
<!-- modal delete -->
<div id="modal-delete3" class="zoom-anim-dialog mfp-hide modal">
	<h6 class="modal__title">Xóa người dùng</h6>
	<p class="modal__text">Bạn có chắc muốn xóa người dùng này?</p>
	<div class="modal__btns">
		<button class="modal__btn modal__btn--apply" type="button">Xóa</button>
		<button class="modal__btn modal__btn--dismiss" type="button">Bỏ qua</button>
	</div>
</div>
<!-- end modal delete -->
