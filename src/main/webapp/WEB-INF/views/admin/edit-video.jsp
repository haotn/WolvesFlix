<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- main content -->
<!DOCTYPE html>
<main class="main">
	<div class="container-fluid">
		<div class="row">
			<!-- main title -->
			<div class="col-12">
				<div class="main__title">
					<h2>Thêm phim mới</h2>
				</div>
			</div>
			<!-- end main title -->
			<!-- form -->
			<div class="col-12">
				<form action="${applicationScope.rootpath}/admin/video/${actionType}" class="form" method="post" enctype="multipart/form-data">
					<div class="row">
						<div class="col-12 col-md-5 form__cover">
							<div class="row">
								<div class="col-12 col-sm-6 col-md-12">
									<div class="form__img">
										<label for="form__img-upload">Ảnh banner (320 x 180)</label> <input id="form__img-upload" name="poster" type="file" accept=".webp, .png, .jpg, .jpeg"> <img style="width: 380px; height: 180px;'" id="form__img"
											src="${applicationScope.rootpath}/img/poster/${form.poster}" alt=" "
										>
									</div>
									<div style="color: red; display: ${validPoster==null?'hidden':'visible'};">${validPoster}</div>
								</div>
							</div>
						</div>
						<div class="col-12 col-md-7 form__content">
							<div class="row">
								<input type="hidden" name="id" value="${form.id}">
								<div class="col-12">
									<input type="text" class="form__input" name="title" placeholder="Tên phim" value="${form.title}" required="required">
									<div style="color: red; display: ${validTitle==null?'hidden':'visible'};">${validTitle}</div>
								</div>
								<div class="col-12">
									<textarea id="text" name="description" class="form__textarea" placeholder="Mô tả">${form.description}</textarea>
								</div>
								<div class="col-12 col-lg-6">
									<select class="js-example-basic-multiple" id="genre" multiple="multiple" name="genre" required="required">
										<c:forEach var="genre" items="${genres}">
											<c:set var="contains" value="false" />
											<c:forEach var="fvdgenre" items="${form.videoGenres}">
												<c:if test="${fvdgenre.genre.id==genre.id}">
													<c:set var="contains" value="true" />
												</c:if>
											</c:forEach>
											<option value="${genre.id}" ${contains?'selected':''}>${genre.genreName}</option>
										</c:forEach>
									</select>
									<div style="color: red; display: ${validGenre==null?'hidden':'visible'};">${validGenre}</div>
								</div>
								<div class="col-12">
									<div class="row">
										<div class="col-12">
											<input type="text" class="form__input" name="youtubeID" placeholder="Youtube Link" value="${form.youtubeID}" required="required">
											<div style="color: red; visibility ${validYoutubeId==null?'hidden':'visible'};">${validYoutubeId}</div>
										</div>
										<c:choose>
											<c:when test="${empty form.active}">
												<input type="hidden" value="true" name="active">
											</c:when>
											<c:otherwise>
												<input type="hidden" value="${form.active}" name="active">
											</c:otherwise>
										</c:choose>
										<div class="col-12">
											<button type="submit" class="form__btn">${btnText}</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<!-- end form -->
		</div>
	</div>
</main>
<!-- end main content -->