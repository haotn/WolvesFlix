<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!-- main content -->
<main class="main">
	<input type="hidden" id="current-page" value="${currentPage}"> <input type="hidden" id="search-in-list-user" value="${searchInUserList}"> <input type="hidden" id="sort-by" value="${sortBy}">
	<div class="container-fluid">
		<div class="row">
			<!-- main title -->
			<div class="col-12">
				<div class="main__title">
					<h2>Người dùng</h2>
					<span class="main__title-stat">Tổng 3,702</span>
					<div class="main__title-wrap">
						<!-- filter sort -->
						<div class="filter" id="filter__sort">
							<span class="filter__item-label">Sắp xếp theo:</span>
							<c:set var="orderby" value="ID" />
							<c:choose>
								<c:when test="${sortBy==2}">
									<c:set var="orderby" value="Tên đăng nhập" />
								</c:when>
								<c:when test="${sortBy==3}">
									<c:set var="orderby" value="Ngày đăng ký" />
								</c:when>
							</c:choose>
							<div class="filter__item-btn dropdown-toggle" role="navigation" id="filter-sort" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<input type="button" value="${orderby}"> <span></span>
							</div>
							<ul class="filter__item-menu dropdown-menu scrollbar-dropdown" aria-labelledby="filter-sort">
								<li class="user__sort-by" value="1">ID</li>
								<li class="user__sort-by" value="2">Tên đăng nhập</li>
								<li class="user__sort-by" value="3">Ngày tham gia</li>
							</ul>
						</div>
						<!-- end filter sort -->
						<!-- search -->
						<form class="main__title-form">
							<input id="search" type="text" placeholder="Tìm người dùng..." value="${searchInUserList}">
							<button type="button" id="btn-search-user">
								<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
											<path d="M21.71,20.29,18,16.61A9,9,0,1,0,16.61,18l3.68,3.68a1,1,0,0,0,1.42,0A1,1,0,0,0,21.71,20.29ZM11,18a7,7,0,1,1,7-7A7,7,0,0,1,11,18Z">
											</path>
										</svg>
							</button>
						</form>
						<!-- end search -->
					</div>
				</div>
			</div>
			<!-- end main title -->
			<!-- users -->
			<div class="col-12">
				<div class="main__table-wrap">
					<table class="main__table">
						<thead>
							<tr>
								<th>ID</th>
								<th>THÔNG TIN CƠ BẢN</th>
								<th>USERNAME</th>
								<th>BÌNH LUẬN</th>
								<th>THÍCH</th>
								<th>NGÀY THAM GIA</th>
								<th>THAO TÁC</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="userItem" items="${users}">
								<tr>
									<td>
										<div class="main__table-text">${userItem.id}</div>
									</td>
									<td>
										<div class="main__user">
											<div class="main__avatar">
												<img src="${applicationScope.rootpath}/img/user.svg" alt="">
											</div>
											<div class="main__meta">
												<h3>${userItem.fullname}</h3>
												<span>${userItem.email}</span>
											</div>
										</div>
									</td>
									<td>
										<div class="main__table-text">${userItem.username}</div>
									</td>
									<td>
										<div class="main__table-text">${fn:length(userItem.comments)}</div>
									</td>
									<td>
										<div class="main__table-text">${fn:length(userItem.favorites)}</div>
									</td>
									<!-- 
									<td>
										<div class="main__table-text main__table-text--${userItem.accept?'green':'red'}">${userItem.accept?'Chấp nhận':'Từ chốit'}</div>
									</td> -->
									<td>
										<div class="main__table-text">
											<fmt:formatDate value="${userItem.registerDate}" pattern="dd-MM-yyyy" />
										</div>
									</td>
									<td>
										<div class="main__table-btns">
											<!-- 	<a href="#modal-status-user-${userItem.id}" class="main__table-btn main__table-btn--banned open-modal"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
														<path
														d="M12,13a1,1,0,0,0-1,1v3a1,1,0,0,0,2,0V14A1,1,0,0,0,12,13Zm5-4V7A5,5,0,0,0,7,7V9a3,3,0,0,0-3,3v7a3,3,0,0,0,3,3H17a3,3,0,0,0,3-3V12A3,3,0,0,0,17,9ZM9,7a3,3,0,0,1,6,0V9H9Zm9,12a1,1,0,0,1-1,1H7a1,1,0,0,1-1-1V12a1,1,0,0,1,1-1H17a1,1,0,0,1,1,1Z"
													/>
													</svg>
											</a>  -->
											<a href="${applicationScope.rootpath}/admin/list-user?action=edit&edit-id=${userItem.id}&current-tab=profile" class="main__table-btn main__table-btn--edit"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
														<path
														d="M5,18H9.24a1,1,0,0,0,.71-.29l6.92-6.93h0L19.71,8a1,1,0,0,0,0-1.42L15.47,2.29a1,1,0,0,0-1.42,0L11.23,5.12h0L4.29,12.05a1,1,0,0,0-.29.71V17A1,1,0,0,0,5,18ZM14.76,4.41l2.83,2.83L16.17,8.66,13.34,5.83ZM6,13.17l5.93-5.93,2.83,2.83L8.83,16H6ZM21,20H3a1,1,0,0,0,0,2H21a1,1,0,0,0,0-2Z"
													/>
													</svg>
											</a> <a href="#modal-delete-user-${userItem.id}" class="main__table-btn main__table-btn--delete open-modal"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
														<path d="M20,6H16V5a3,3,0,0,0-3-3H11A3,3,0,0,0,8,5V6H4A1,1,0,0,0,4,8H5V19a3,3,0,0,0,3,3h8a3,3,0,0,0,3-3V8h1a1,1,0,0,0,0-2ZM10,5a1,1,0,0,1,1-1h2a1,1,0,0,1,1,1V6H10Zm7,14a1,1,0,0,1-1,1H8a1,1,0,0,1-1-1V8H17Z" />
													</svg>
											</a>
										</div>
									</td>
								</tr>
								<!-- modal status -->
								<div id="modal-status-user-${userItem.id}" class="zoom-anim-dialog mfp-hide modal">
									<h6 class="modal__title">Thay đổi trạng thái</h6>
									<p class="modal__text">Bạn có chắc muốn thay đổi trạng thái người dùng?</p>
									<input type="hidden" id="delete-id" value="${userItem.id}">
									<div class="modal__btns">
										<button class="modal__btn modal__btn--apply" type="button" id="confirm-change">Áp dụng</button>
										<button class="modal__btn modal__btn--dismiss" type="button">Bỏ qua</button>
									</div>
								</div>
								<!-- end modal status -->
								<!-- modal delete -->
								<div id="modal-delete-user-${userItem.id}" class="zoom-anim-dialog mfp-hide modal">
									<h6 class="modal__title">Xóa người dùng</h6>
									<p class="modal__text">Bạn có chắc muốn xóa người dùng?</p>
									<div class="modal__btns">
										<button class="modal__btn modal__btn--dismiss" type="button">Bỏ qua</button>
										<button class="modal__btn modal__btn--apply confirm-delete" type="button" value="${userItem.id}">Xóa</button>
									</div>
								</div>
								<!-- end modal delete -->
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- end users -->
			<!-- paginator -->
			<div class="col-12">
				<div class="paginator-wrap">
					<span>10 trong số ${total}</span>
					<div class="row">
						<!-- paginator -->
						<ul class="paginator">
							<li id="page-home-first" class="paginator__item paginator__item--prev"><a href="${applicationScope.rootpath}/admin/list-user?page=1&sort-id=${sortBy}&search=${searchInUserList}"> <svg xmlns="http://www.w3.org/2000/svg"
										enable-background="new 0 0 24 24" viewBox="0 0 24 24"
									>
									<path d="M8.5,12.8l5.7,5.6c0.4,0.4,1,0.4,1.4,0c0,0,0,0,0,0c0.4-0.4,0.4-1,0-1.4l-4.9-5l4.9-5c0.4-0.4,0.4-1,0-1.4c-0.2-0.2-0.4-0.3-0.7-0.3c-0.3,0-0.5,0.1-0.7,0.3l-5.7,5.6C8.1,11.7,8.1,12.3,8.5,12.8C8.5,12.7,8.5,12.7,8.5,12.8z" />
								</svg>
							</a></li>
							<c:choose>
								<c:when test="${lastPage==2}">
									<li id="page-home-prev" class="paginator__item ${currentPage==1?' paginator__item--active':''}"><c:choose>
											<c:when test="${currentPage==1}">
												<a href="${applicationScope.rootpath}/admin/list-user?page=1&sort-id=${sortBy}&search=${searchInUserList}">1</a>
											</c:when>
											<c:when test="${currentPage==lastPage}">
												<a href="${applicationScope.rootpath}/admin/list-user?page=${currentPage-1}&sort-id=${sortBy}&search=${searchInUserList}">${currentPage-1}</a>
											</c:when>
										</c:choose></li>
								</c:when>
								<c:otherwise>
									<li id="page-home-prev" class="paginator__item ${currentPage==1?' paginator__item--active':''}"><c:choose>
											<c:when test="${currentPage==1}">
												<a href="${applicationScope.rootpath}/admin/list-user?page=1&sort-id=${sortBy}&search=${searchInUserList}">1</a>
											</c:when>
											<c:when test="${currentPage==lastPage}">
												<a href="${applicationScope.rootpath}/admin/list-user?page=${currentPage-2}&sort-id=${sortBy}&search=${searchInUserList}">${currentPage-2}</a>
											</c:when>
											<c:otherwise>
												<a href="${applicationScope.rootpath}/admin/list-user?page=${currentPage-1}&sort-id=${sortBy}&search=${searchInUserList}">${currentPage-1}</a>
											</c:otherwise>
										</c:choose></li>
								</c:otherwise>
							</c:choose>
							<c:if test="${lastPage>1}">
								<c:choose>
									<c:when test="${lastPage==2}">
										<li id="page-home-current" class="paginator__item ${currentPage==2?'paginator__item--active':''}"><c:choose>
												<c:when test="${currentPage==1}">
													<a href="${applicationScope.rootpath}/admin/list-user?page=2&sort-id=${sortBy}&search=${searchInUserList}">2</a>
												</c:when>
												<c:when test="${currentPage==lastPage}">
													<a href="${applicationScope.rootpath}/admin/list-user?page=${currentPage}&sort-id=${sortBy}&search=${searchInUserList}">${currentPage}</a>
												</c:when>
												<c:otherwise>
													<a>${currentPage}</a>
												</c:otherwise>
											</c:choose></li>
									</c:when>
									<c:otherwise>
										<li id="page-home-current" class="paginator__item ${currentPage>1&&currentPage<lastPage?'paginator__item--active':''}"><c:choose>
												<c:when test="${currentPage==1}">
													<a href="${applicationScope.rootpath}/admin/list-user?page=2&sort-id=${sortBy}&search=${searchInUserList}">2</a>
												</c:when>
												<c:when test="${currentPage==lastPage}">
													<a href="${applicationScope.rootpath}/admin/list-user?page=${currentPage-1}&sort-id=${sortBy}&search=${searchInUserList}">${currentPage-1}</a>
												</c:when>
												<c:otherwise>
													<a>${currentPage}</a>
												</c:otherwise>
											</c:choose></li>
									</c:otherwise>
								</c:choose>
								<c:if test="${lastPage>2}">
									<li id="page-home-next" class="paginator__item ${currentPage==lastPage&&currentPage!=1?'paginator__item--active':''}"><c:choose>
											<c:when test="${currentPage==1}">
												<a href="${applicationScope.rootpath}/admin/list-user?page=${currentPage+2}&sort-id=${sortBy}&search=${searchInUserList}">${currentPage+2}</a>
											</c:when>
											<c:when test="${currentPage==lastPage}">
												<a href="${applicationScope.rootpath}/admin/list-user?page=${currentPage}&sort-id=${sortBy}&search=${searchInUserList}">${currentPage}</a>
											</c:when>
											<c:otherwise>
												<a href="${applicationScope.rootpath}/admin/list-user?page=${currentPage+1}&sort-id=${sortBy}&search=${searchInUserList}">${currentPage+1}</a>
											</c:otherwise>
										</c:choose></li>
								</c:if>
							</c:if>
							<li id="page-home-last" class="paginator__item paginator__item--next"><a href="${applicationScope.rootpath}/admin/list-user?page=${lastPage}&sort-id=${sortBy}&search=${searchInUserList}"> <svg xmlns="http://www.w3.org/2000/svg"
										viewBox="0 0 24 24"
									>
									<path d="M15.54,11.29,9.88,5.64a1,1,0,0,0-1.42,0,1,1,0,0,0,0,1.41l4.95,5L8.46,17a1,1,0,0,0,0,1.41,1,1,0,0,0,.71.3,1,1,0,0,0,.71-.3l5.66-5.65A1,1,0,0,0,15.54,11.29Z" />
								</svg>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- end paginator -->
		</div>
	</div>
</main>
<!-- end main content -->
<!-- modal status -->
<div id="modal-status" class="zoom-anim-dialog mfp-hide modal">
	<h6 class="modal__title">Thay đổi trạng thái</h6>
	<p class="modal__text">Bạn có chắc muốn thay đổi trạng thái người dùng?</p>
	<div class="modal__btns">
		<button class="modal__btn modal__btn--apply" type="button">Áp dụng</button>
		<button class="modal__btn modal__btn--dismiss" type="button">Bỏ qua</button>
	</div>
</div>
<!-- end modal status -->
<!-- modal delete -->
<div id="modal-delete" class="zoom-anim-dialog mfp-hide modal">
	<h6 class="modal__title">Xóa người dùng</h6>
	<p class="modal__text">Bạn có chắc muốn xóa người dùng?</p>
	<div class="modal__btns">
		<button class="modal__btn modal__btn--apply" type="button">Xóa</button>
		<button class="modal__btn modal__btn--dismiss" type="button">Bỏ qua</button>
	</div>
</div>
<!-- end modal delete -->
