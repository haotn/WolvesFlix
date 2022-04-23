<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- main content -->
<main class="main">
	<input type="hidden" id="current-page" value="${currentPage}"> <input type="hidden" id="search-in-list-video" value="${searchInVideoList}"> <input type="hidden" id="sort-by" value="${sortBy}">
	<div class="container-fluid">
		<div class="row">
			<!-- main title -->
			<div class="col-12">
				<div class="main__title">
					<h2>Danh sách phim</h2>
					<span class="main__title-stat">Tổng ${total} </span>
					<div class="main__title-wrap">
						<!-- filter sort -->
						<div class="filter" id="filter__sort">
							<span class="filter__item-label">Sắp xếp theo:</span>
							<c:set var="orderby" value="ID" />
							<c:choose>
								<c:when test="${sortBy==2}">
									<c:set var="orderby" value="Tên" />
								</c:when>
								<c:when test="${sortBy==3}">
									<c:set var="orderby" value="Ngày thêm" />
								</c:when>
								<c:when test="${sortBy==4}">
									<c:set var="orderby" value="Lượt xem" />
								</c:when>
							</c:choose>
							<div class="filter__item-btn dropdown-toggle" role="navigation" id="filter-sort" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<input id="show-sort" type="button" value="${orderby}"> <span></span>
							</div>
							<ul class="filter__item-menu dropdown-menu scrollbar-dropdown" aria-labelledby="filter-sort">
								<li class="video__sort-by" value="1">ID</li>
								<li class="video__sort-by" value="2">Tên</li>
								<li class="video__sort-by" value="3">Ngày thêm</li>
								<li class="video__sort-by" value="4">Lượt xem</li>
							</ul>
						</div>
						<!-- end filter sort -->
						<!-- search -->
						<form class="main__title-form">
							<input type="text" id="search" placeholder="Tìm phim / Series ..." value="${searchInVideoList}">
							<button type="button" id="btn-search-video">
								<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
									<path d="M21.71,20.29,18,16.61A9,9,0,1,0,16.61,18l3.68,3.68a1,1,0,0,0,1.42,0A1,1,0,0,0,21.71,20.29ZM11,18a7,7,0,1,1,7-7A7,7,0,0,1,11,18Z"></path></svg>
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
								<th>TÊN PHIM</th>
								<th>LƯỢT BÌNH LUẬN</th>
								<th>THỂ LOẠI</th>
								<th>LƯỢT XEM</th>
								<th>TRẠNG THÁI</th>
								<th>NGÀY THÊM</th>
								<th>THAO TÁC</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="video" items="${videos}">
								<tr>
									<td>
										<div class="main__table-text">${video.id}</div>
									</td>
									<td style="max-width: 300px;">
										<div class="main__table-text">${video.title}</div>
									</td>
									<td><c:set var="lengthComment" value="${fn:length(video.comments)}" /> <c:set var="lengthReply" value="${fn:length(video.replys)}" />
										<div class="main__table-text">${lengthComment+lengthReply}</div></td>
									<td>
										<div class="main__table-text">
											<c:forEach var="vdgenre" items="${video.videoGenres}">
											${vdgenre.genre.genreName} &
										</c:forEach>
										</div>
									</td>
									<td>
										<div class="main__table-text">${fn:length(video.views)}</div>
									</td>
									<td>
										<div class="main__table-text main__table-text--${video.active?'green':'red'}">${video.active?'Hiển thị':'Ẩn'}</div>
									</td>
									<td>
										<div class="main__table-text">
											<fmt:formatDate value="${video.addDate}" pattern="dd-MM-yyyy" />
										</div>
									</td>
									<td>
										<div class="main__table-btns">
											<a href="#modal-status-video-manage-${video.id}" class="main__table-btn main__table-btn--banned open-modal"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
												<path
														d="M12,13a1,1,0,0,0-1,1v3a1,1,0,0,0,2,0V14A1,1,0,0,0,12,13Zm5-4V7A5,5,0,0,0,7,7V9a3,3,0,0,0-3,3v7a3,3,0,0,0,3,3H17a3,3,0,0,0,3-3V12A3,3,0,0,0,17,9ZM9,7a3,3,0,0,1,6,0V9H9Zm9,12a1,1,0,0,1-1,1H7a1,1,0,0,1-1-1V12a1,1,0,0,1,1-1H17a1,1,0,0,1,1,1Z"
													/></svg>
											</a> <a href="${applicationScope.rootpath}/home/index?action=watch&watch-id=${video.id}" class="main__table-btn main__table-btn--view"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
												<path
														d="M21.92,11.6C19.9,6.91,16.1,4,12,4S4.1,6.91,2.08,11.6a1,1,0,0,0,0,.8C4.1,17.09,7.9,20,12,20s7.9-2.91,9.92-7.6A1,1,0,0,0,21.92,11.6ZM12,18c-3.17,0-6.17-2.29-7.9-6C5.83,8.29,8.83,6,12,6s6.17,2.29,7.9,6C18.17,15.71,15.17,18,12,18ZM12,8a4,4,0,1,0,4,4A4,4,0,0,0,12,8Zm0,6a2,2,0,1,1,2-2A2,2,0,0,1,12,14Z"
													/></svg>
											</a> <a href="${applicationScope.rootpath}/admin/list-video?action=edit&edit-id=${video.id}" class="main__table-btn main__table-btn--edit"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
												<path
														d="M5,18H9.24a1,1,0,0,0,.71-.29l6.92-6.93h0L19.71,8a1,1,0,0,0,0-1.42L15.47,2.29a1,1,0,0,0-1.42,0L11.23,5.12h0L4.29,12.05a1,1,0,0,0-.29.71V17A1,1,0,0,0,5,18ZM14.76,4.41l2.83,2.83L16.17,8.66,13.34,5.83ZM6,13.17l5.93-5.93,2.83,2.83L8.83,16H6ZM21,20H3a1,1,0,0,0,0,2H21a1,1,0,0,0,0-2Z"
													/></svg>
											</a> <a href="#modal-delete-video-manage-${video.id}" class="main__table-btn main__table-btn--delete open-modal"> <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
												<path d="M20,6H16V5a3,3,0,0,0-3-3H11A3,3,0,0,0,8,5V6H4A1,1,0,0,0,4,8H5V19a3,3,0,0,0,3,3h8a3,3,0,0,0,3-3V8h1a1,1,0,0,0,0-2ZM10,5a1,1,0,0,1,1-1h2a1,1,0,0,1,1,1V6H10Zm7,14a1,1,0,0,1-1,1H8a1,1,0,0,1-1-1V8H17Z" /></svg>
											</a>
										</div>
									</td>
								</tr>
								<!-- modal status -->
								<div id="modal-status-video-manage-${video.id}" class="zoom-anim-dialog mfp-hide modal">
									<h6 class="modal__title">${video.active?' Ẩn':' Hiện'}Video</h6>
									<p class="modal__text">
										Bạn có chắc muốn ${video.active?' ẩn':' hiện'} Video có ID là <b>${video.id}</b> ?
									</p>
									<div class="modal__btns">
										<button class="modal__btn modal__btn--dismiss" type="button">Bỏ qua</button>
										<form action="${applicationScope.rootpath}/admin/list-video" method="post">
											<input name="action" value="hidden" type="hidden"> <input name="page" value="${currentPage}" type="hidden"> <input name="search" value="${searchInVideoList}" type="hidden"> <input name="hidden-id"
												value="${video.id}" type="hidden"
											>
											<button class="modal__btn modal__btn--apply" type="submit">Áp dụng</button>
										</form>
									</div>
								</div>
								<!-- end modal status -->
								<!-- modal delete -->
								<div id="modal-delete-video-manage-${video.id}" class="zoom-anim-dialog mfp-hide modal">
									<h6 class="modal__title">Xóa mục này</h6>
									<p class="modal__text">Bạn có chắc muốn xóa Video có ID là ${video.id}?</p>
									<div class="modal__btns">
										<button class="modal__btn modal__btn--dismiss" type="button">Bỏ qua</button>
										<form action="${applicationScope.rootpath}/admin/list-video" method="post">
											<input name="action" value="delete" type="hidden"> <input name="page" value="${currentPage}" type="hidden"> <input name="search" value="${searchInVideoList}" type="hidden"> <input name="delete-id"
												value="${video.id}" type="hidden"
											>
											<button class="modal__btn modal__btn--apply" type="submit">Xóa</button>
										</form>
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
							<li id="page-home-first" class="paginator__item paginator__item--prev"><a href="${applicationScope.rootpath}/admin/list-video?page=1&sort-id=${sortBy}&search=${searchInVideoList}"> <svg xmlns="http://www.w3.org/2000/svg"
										enable-background="new 0 0 24 24" viewBox="0 0 24 24"
									>
									<path d="M8.5,12.8l5.7,5.6c0.4,0.4,1,0.4,1.4,0c0,0,0,0,0,0c0.4-0.4,0.4-1,0-1.4l-4.9-5l4.9-5c0.4-0.4,0.4-1,0-1.4c-0.2-0.2-0.4-0.3-0.7-0.3c-0.3,0-0.5,0.1-0.7,0.3l-5.7,5.6C8.1,11.7,8.1,12.3,8.5,12.8C8.5,12.7,8.5,12.7,8.5,12.8z" />
								</svg>
							</a></li>
							<c:choose>
								<c:when test="${lastPage==2}">
									<li id="page-home-prev" class="paginator__item ${currentPage==1?' paginator__item--active':''}"><c:choose>
											<c:when test="${currentPage==1}">
												<a href="${applicationScope.rootpath}/admin/list-video?page=1&sort-id=${sortBy}&search=${searchInVideoList}">1</a>
											</c:when>
											<c:when test="${currentPage==lastPage}">
												<a href="${applicationScope.rootpath}/admin/list-video?page=${currentPage-1}&sort-id=${sortBy}&search=${searchInVideoList}">${currentPage-1}</a>
											</c:when>
										</c:choose></li>
								</c:when>
								<c:otherwise>
									<li id="page-home-prev" class="paginator__item ${currentPage==1?' paginator__item--active':''}"><c:choose>
											<c:when test="${currentPage==1}">
												<a href="${applicationScope.rootpath}/admin/list-video?page=1&sort-id=${sortBy}&search=${searchInVideoList}">1</a>
											</c:when>
											<c:when test="${currentPage==lastPage}">
												<a href="${applicationScope.rootpath}/admin/list-video?page=${currentPage-2}&sort-id=${sortBy}&search=${searchInVideoList}">${currentPage-2}</a>
											</c:when>
											<c:otherwise>
												<a href="${applicationScope.rootpath}/admin/list-video?page=${currentPage-1}&sort-id=${sortBy}&search=${searchInVideoList}">${currentPage-1}</a>
											</c:otherwise>
										</c:choose></li>
								</c:otherwise>
							</c:choose>
							<c:if test="${lastPage>1}">
								<c:choose>
									<c:when test="${lastPage==2}">
										<li id="page-home-current" class="paginator__item ${currentPage==2?'paginator__item--active':''}"><c:choose>
												<c:when test="${currentPage==1}">
													<a href="${applicationScope.rootpath}/admin/list-video?page=2&sort-id=${sortBy}&search=${searchInVideoList}">2</a>
												</c:when>
												<c:when test="${currentPage==lastPage}">
													<a href="${applicationScope.rootpath}/admin/list-video?page=${currentPage}&sort-id=${sortBy}&search=${searchInVideoList}">${currentPage}</a>
												</c:when>
												<c:otherwise>
													<a>${currentPage}</a>
												</c:otherwise>
											</c:choose></li>
									</c:when>
									<c:otherwise>
										<li id="page-home-current" class="paginator__item ${currentPage>1&&currentPage<lastPage?'paginator__item--active':''}"><c:choose>
												<c:when test="${currentPage==1}">
													<a href="${applicationScope.rootpath}/admin/list-video?page=2&sort-id=${sortBy}&search=${searchInVideoList}">2</a>
												</c:when>
												<c:when test="${currentPage==lastPage}">
													<a href="${applicationScope.rootpath}/admin/list-video?page=${currentPage-1}&sort-id=${sortBy}&search=${searchInVideoList}">${currentPage-1}</a>
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
												<a href="${applicationScope.rootpath}/admin/list-video?page=${currentPage+2}&sort-id=${sortBy}&search=${searchInVideoList}">${currentPage+2}</a>
											</c:when>
											<c:when test="${currentPage==lastPage}">
												<a href="${applicationScope.rootpath}/admin/list-video?page=${currentPage}&sort-id=${sortBy}&search=${searchInVideoList}">${currentPage}</a>
											</c:when>
											<c:otherwise>
												<a href="${applicationScope.rootpath}/admin/list-video?page=${currentPage+1}&sort-id=${sortBy}&search=${searchInVideoList}">${currentPage+1}</a>
											</c:otherwise>
										</c:choose></li>
								</c:if>
							</c:if>
							<li id="page-home-last" class="paginator__item paginator__item--next"><a href="${applicationScope.rootpath}/admin/list-video?page=${lastPage}&sort-id=${sortBy}&search=${searchInVideoList}"> <svg xmlns="http://www.w3.org/2000/svg"
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
	<p class="modal__text">Bạn có chắc muốn thay đổi trạng thái?</p>
	<div class="modal__btns">
		<button class="modal__btn modal__btn--apply" type="button">Áp dụng</button>
		<button class="modal__btn modal__btn--dismiss" type="button">Bỏ qua</button>
	</div>
</div>
<!-- end modal status -->
<!-- modal delete -->
<div id="modal-delete" class="zoom-anim-dialog mfp-hide modal">
	<h6 class="modal__title">Xóa mục này</h6>
	<p class="modal__text">Bạn có chắc muốn xóa mục này?</p>
	<div class="modal__btns">
		<button class="modal__btn modal__btn--apply" type="button">Xóa</button>
		<button class="modal__btn modal__btn--dismiss" type="button">Bỏ qua</button>
	</div>
</div>
<!-- end modal delete -->
