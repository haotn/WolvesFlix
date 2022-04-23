<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<main class="main">
	<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	<div class="container-fluid">
		<div class="row">
			<!-- main title -->
			<div class="col-12">
				<div class="main__title">
					<h2>Bảng điều khiển</h2>
					<a href="${applicationScope.rootpath}/admin/list-video?action=create" class="main__title-link">Thêm phim </a>
				</div>
			</div>
			<!-- end main title -->
		</div>
		<div class="row row--grid">
			<!-- stats -->
			<div class="col-12 col-sm-6 col-xl-3">
				<div class="stats">
					<span>Lượt xem trong tháng</span>
					<p>${viewCount}</p>
					<img src="img/graph-bar.svg" alt="">
				</div>
			</div>
			<!-- end stats -->
			<!-- stats -->
			<div class="col-12 col-sm-6 col-xl-3">
				<div class="stats">
					<span>Số phim đã thêm trong tháng</span>
					<p>${videoCount}</p>
					<img src="img/film.svg" alt="">
				</div>
			</div>
			<!-- end stats -->
			<!-- stats -->
			<div class="col-12 col-sm-6 col-xl-3">
				<div class="stats">
					<span>Người dùng mới trong tháng</span>
					<p>${userCount}</p>
					<img src="img/comments.svg" alt="">
				</div>
			</div>
			<!-- end stats -->
			<!-- stats -->
			<div class="col-12 col-sm-6 col-xl-3">
				<div class="stats">
					<span>Lượt thích mới trong tháng</span>
					<p>${likeCount}</p>
					<img src="img/star-half-alt.svg" alt="">
				</div>
			</div>
			<!-- end stats -->
			<ul class="nav nav-tabs profile__tabs" id="dashboard__tabs" role="tablist">
				<li class="nav-item" role="presentation"><a class="nav-link ${currentTab==1?'active':''}" data-toggle="tab" href="#tab-1" role="tab" aria-controls="tab-1" aria-selected="true">Video Favorite</a></li>
				<li class="nav-item" role="presentation"><a class="nav-link ${currentTab==2?'active':''}" data-toggle="tab" href="#tab-2" role="tab" aria-controls="tab-2" aria-selected="false">User Like Video</a></li>
				<li class="nav-item" role="presentation"><a class="nav-link ${currentTab==3?'active':''}" data-toggle="tab" href="#tab-3" role="tab" aria-controls="tab-3" aria-selected="false">Shares</a></li>
			</ul>
			<!-- content tabs -->
			<div class="tab-content">
				<!-- dashbox -->
				<div class="tab-pane fade ${currentTab==1?'active show':''}" id="tab-1" role="tabpanel" aria-labelledby="1-tab">
					<div class="col-12 col-xl-12">
						<div class="dashbox">
							<div class="dashbox__title">
								<h3>
									<img src="img/award.svg" alt=""> Người yêu thích theo video
								</h3>
								<div class="dashbox__wrap">
									<a class="dashbox__refresh" href="#"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
											<path d="M21,11a1,1,0,0,0-1,1,8.05,8.05,0,1,1-2.22-5.5h-2.4a1,1,0,0,0,0,2h4.53a1,1,0,0,0,1-1V3a1,1,0,0,0-2,0V4.77A10,10,0,1,0,22,12,1,1,0,0,0,21,11Z" />
										</svg></a> <a class="dashbox__more" href="movies-manage.html">Xem tất cả</a>
								</div>
							</div>
							<div class="dashbox__table-wrap">
							<div class="search-box">
							
							</div>
								<table class="main__table main__table--dash .data-tables">
									<thead>
										<tr>
											<th>TÊN PHIM</th>
											<th>LƯỢT THÍCH</th>
											<th>MỚI NHẤT</th>
											<th>CŨ NHẤT</th>
										</tr>
									</thead>
									<tbody style="height: 200px; overflow-y: auto;">
										<c:forEach var="item" items="${videoReport}">
											<tr>
												<td>
													<div class="main__table-text">${item.title}</div>
												</td>
												<td>
													<div class="main__table-text">${item.likeCount}</div>
												</td>
												<td>
													<div class="main__table-text">${item.newest}</div>
												</td>
												<td>
													<div class="main__table-text">${item.oldest}</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- end dashbox -->
				<!-- dashbox -->
				<div class="tab-pane fade ${currentTab==2?'active show':''}" id="tab-2" role="tabpanel" aria-labelledby="2-tab">
					<div class="col-12 col-xl-12">
						<div class="dashbox">
							<div class="dashbox__title">
								<h3>
									<img src="img/film.svg" alt=""> Thống kê theo người xem
								</h3>
								<div class="dashbox__wrap">
									<a class="dashbox__refresh" href="#"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
											<path d="M21,11a1,1,0,0,0-1,1,8.05,8.05,0,1,1-2.22-5.5h-2.4a1,1,0,0,0,0,2h4.53a1,1,0,0,0,1-1V3a1,1,0,0,0-2,0V4.77A10,10,0,1,0,22,12,1,1,0,0,0,21,11Z" />
										</svg></a> <a class="dashbox__more" href="movies-manage.html">Xem tất cả</a>
								</div>
							</div>
							<div class="select video">
								<div class="col-12 col-lg-12">
									<select class="js-example-basic-multiple" id="videoid-report-user">
										<c:forEach var="item" items="${videos}">
											<option value="${item.id}" ${item.id==currentVideo1?'selected':''}>${item.title}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="dashbox__table-wrap">
								<table class="main__table main__table--dash .data-tables">
									<thead>
										<tr>
											<th>USERNAME</th>
											<th>FULLNAME</th>
											<th>EMAIL</th>
											<th>LIKEDATE</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${userReport}">
											<tr>
												<td>
													<div class="main__table-text">${item.username}</div>
												</td>
												<td>
													<div class="main__table-text">${item.fullname}</div>
												</td>
												<td>
													<div class="main__table-text">${item.email}</div>
												</td>
												<td>
													<div class="main__table-text">${item.likeDate}</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- end dashbox -->
				<!-- dashbox -->
				<div class="tab-pane fade ${currentTab==3?'active show':''}" id="tab-3" role="tabpanel" aria-labelledby="3-tab">
					<div class="col-12 col-xl-12">
						<div class="dashbox">
							<div class="dashbox__title">
								<h3>
									<img src="img/user-circle.svg" alt=""> Chia sẻ
								</h3>
								<div class="dashbox__wrap">
									<a class="dashbox__refresh" href="#"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
											<path d="M21,11a1,1,0,0,0-1,1,8.05,8.05,0,1,1-2.22-5.5h-2.4a1,1,0,0,0,0,2h4.53a1,1,0,0,0,1-1V3a1,1,0,0,0-2,0V4.77A10,10,0,1,0,22,12,1,1,0,0,0,21,11Z" />
										</svg></a> <a class="dashbox__more" href="movies-manage.html">Xem tất cả</a>
								</div>
							</div>
							<div class="select video">
								<div class="col-12 col-lg-12">
									<select class="js-example-basic-multiple" id="videoid-report-share">
										<c:forEach var="item" items="${videos}">
											<option value="${item.id}" ${item.id==currentVideo2?'selected':''}>${item.title}</option>
										</c:forEach>
									</select>
									<div class="invalid-feedback">Vui lòng chọn thể loại</div>
								</div>
							</div>
							<div class="dashbox__table-wrap">
								<table class="main__table main__table--dash">
									<thead>
										<tr>
											<th>USERNAME</th>
											<th>FULLNAME</th>
											<th>EMAIL</th>
											<th>EMAIL TO</th>
											<th>SHARE DATE</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${shareReport}">
											<tr>
												<td>
													<div class="main__table-text">${item.username}</div>
												</td>
												<td>
													<div class="main__table-text">${item.fullname}</div>
												</td>
												<td>
													<div class="main__table-text">${item.useremail}</div>
												</td>
												<td>
													<div class="main__table-text">${item.mailto}</div>
												</td>
												<td>
													<div class="main__table-text">${item.shareDate}</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- end dashbox -->
			</div>
		</div>
	</div>
</main>