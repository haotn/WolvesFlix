package com.wolvesflix.servlet.admin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.wolvesflix.dao.CommentDAO;
import com.wolvesflix.dao.FavoriteDAO;
import com.wolvesflix.dao.GenreDAO;
import com.wolvesflix.dao.ReplyDAO;
import com.wolvesflix.dao.ShareDAO;
import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.dao.VideoGenreDAO;
import com.wolvesflix.dao.ViewDAO;
import com.wolvesflix.entity.Comment;
import com.wolvesflix.entity.Favorite;
import com.wolvesflix.entity.Genre;
import com.wolvesflix.entity.Reply;
import com.wolvesflix.entity.Share;
import com.wolvesflix.entity.Video;
import com.wolvesflix.entity.VideoGenre;
import com.wolvesflix.entity.View;
import com.wolvesflix.helper.FormValidator;
import com.wolvesflix.helper.HandlePoster;
import com.wolvesflix.helper.HandleYoutubeLink;
import com.wolvesflix.helper.RRShare;
import com.wolvesflix.helper.ServletHelper;

@MultipartConfig()
@WebServlet({ "/admin/list-video", "/admin/video/create", "/admin/video/update" })
public class VideoManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletHelper servletControl;

	public VideoManageServlet() {
		super();
		servletControl = new ServletHelper();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		GenreDAO genreDao = new GenreDAO();
		String paramAction = request.getParameter("action");
		String action = "";
		if (paramAction != null) {
			action = paramAction;
		}
		switch (action) {
		case "create":
			servletControl.toAddItem();
			break;
		case "edit":
			servletControl.toAddItem();
			break;
		case "hidden":
			Long id = Long.parseLong(request.getParameter("hidden-id"));
			hiddenVideo(id);
			break;
		default:
			servletControl.toVideoManage();
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		// Get Genres from parameter
		String[] genres = request.getParameterValues("genre");
//		boolean edition = false;
		String paramAction = request.getParameter("action");
		String action = "";
		if (paramAction != null) {
			action = paramAction;
		}
		if (uri.contains("create")) {
			try {
				if (validFormVideo(true)) {
//					edition = false;
					// Poster's file name
					String poster = HandlePoster.handlePoster(request);
					// Create Video
					Video video = createVideo(request, poster);
					// Create VideoGenre
					createVideoGenres(video, genres);
					servletControl.toVideoManage();
				}
			} catch (IllegalAccessException | InvocationTargetException | IOException | ServletException e) {
				e.printStackTrace();
			}
		} else if (uri.contains("update")) {
			try {
				if (validFormVideo(false)) {
//					edition = false;
					updateVideo(request, genres);
					System.out.println("Update success");
					servletControl.toVideoManage();
				}
			} catch (IllegalAccessException | InvocationTargetException | IOException | ServletException e) {
				e.printStackTrace();
			}

		} else {
			switch (action) {
			case "hidden":
				Long id = Long.parseLong(request.getParameter("hidden-id"));
				hiddenVideo(id);
				break;
			case "delete":
				deleteVideo(request, response);
				servletControl.toVideoManage();
				break;
			default:
				servletControl.toVideoManage();
				break;
			}
		}
	}

	/**
	 * Handle hide Video
	 * 
	 * @param videoId
	 */
	private void hiddenVideo(Long videoId) {
		VideoDAO dao = new VideoDAO();
		Video video = dao.findByID(videoId);
		video.setActive(!video.getActive());
		dao.update(video);
		servletControl.toVideoManage();
	}

	/**
	 * Create Video
	 * 
	 * @param request
	 * @param poster
	 * @return video
	 */
	public Video createVideo(HttpServletRequest request, String poster) {
		// Video
		Video video = new Video();
		// DAO
		VideoDAO dao = new VideoDAO();
		try {
			BeanUtils.populate(video, request.getParameterMap());
			video.setId(null);
			video.setPoster(poster);
			String youtubeId = HandleYoutubeLink.handelYoutubeLink(video.getYoutubeID());
			video.setYoutubeID(youtubeId);
			dao.create(video);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		Video vdReturn = dao.findByYoutubeId(video.getYoutubeID());
		// Return object
		System.err.println(vdReturn);
		return vdReturn;
	}

	public Boolean validFormVideo(boolean isInsert)
			throws IOException, ServletException, IllegalAccessException, InvocationTargetException {
		HttpServletRequest request = RRShare.request();
		Boolean isValid = true;
		Video form = new Video();
		VideoDAO dao = new VideoDAO();
		Part poster = request.getPart("poster");
		BeanUtils.populate(form, request.getParameterMap());
		String youtubeId = HandleYoutubeLink.handelYoutubeLink(form.getYoutubeID());
		if (isInsert && poster == null) {
			request.setAttribute("validPoster", "Vui lòng chọn Poster phim!");
			isValid = false;
		}
		if (!FormValidator.isTextNotEmpty(form.getTitle())) {
			request.setAttribute("validTitle", "Vui lòng nhập tên phim!");
			isValid = false;
		}
		if (!FormValidator.isTextNotEmpty(form.getYoutubeID())) {
			request.setAttribute("validYoutubeId", "Vui lòng nhập YoutubeID!");
		}
		List<Video> videos = dao.findAll();
		for (int i = 0; i < videos.size(); i++) {
			System.out.println(videos.get(i).getId() + " - " + form.getId());
			if (isInsert) {
				if (videos.get(i).getYoutubeID().equals(form.getYoutubeID())) {
					isValid = false;
					request.setAttribute("validYoutubeId", "Youtube ID đã tồn tại!");
				}
			} else {
				if (videos.get(i).getId() != form.getId()) {
					if (videos.get(i).getYoutubeID().equals(youtubeId)) {
						isValid = false;
						request.setAttribute("validYoutubeId", "Youtube ID đã tồn tại!");
					}
				}
			}
		}
		if (isValid) {
			return isValid;
		} else {
			request.setAttribute("form", form);
			servletControl.toAddItem();
			return isValid;
		}
	}

	/**
	 * Update Video
	 * 
	 * @param request
	 * @return
	 */
	private void updateVideo(HttpServletRequest request, String[] genres) {
		// Video
		Video video = new Video();
		// DAO
		VideoDAO dao = new VideoDAO();

		try {
			BeanUtils.populate(video, request.getParameterMap());
			Video inDB = dao.findByID(video.getId());
			// Get array Old VideoGenre
			String[] oldGenre = new String[inDB.getVideoGenres().size()];
			for (int i = 0; i < inDB.getVideoGenres().size(); i++) {
				oldGenre[i] = String.valueOf(inDB.getVideoGenres().get(i).getGenre().getId());
			}
			// Handle Poster
			String poster = HandlePoster.handlePoster(request, inDB.getPoster());
			// If select new Poster
			if (poster != null) {
				video.setPoster(poster);
				File dir = new File(request.getServletContext().getRealPath("/img/poster"));
				File myObj = new File(dir + "/" + inDB.getPoster());
				myObj.delete();
				video.setPoster(poster);
			} else {
				video.setPoster(inDB.getPoster());
			}
			// Handle YoutubeId
			String youtubeId = HandleYoutubeLink.handelYoutubeLink(video.getYoutubeID());
			video.setYoutubeID(youtubeId);
			video.setAddDate(inDB.getAddDate());
			// And Update Video to database
			dao.update(video);
			// Get Video after update
			video = dao.findByID(video.getId());
			// Update table VideoGenre
			updateTableVideoGenre(video, oldGenre, genres);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private void updateTableVideoGenre(Video video, String[] oldGenre, String[] newGenre) {
		List<String> listHold = new ArrayList<String>();
		List<String> listRemove = new ArrayList<String>();
		GenreDAO genreDao = new GenreDAO();
		VideoGenreDAO vdgDao = new VideoGenreDAO();
		// Loop array old genre
		for (String old : oldGenre) {
			boolean exist = false;
			// Loop array new genre
			for (String newg : newGenre) {
				// If found genreId in array new genre
				if (Integer.parseInt(old) == Integer.parseInt(newg)) {
					exist = true;
					break;
				}
			}
			// If not found genreId in array new genre
			if (!exist) {
				// Add genre Id to list will be remove
				listRemove.add(old);
			} else {
				// Add old genre Id to list will be check for create or hold
				listHold.add(old);
			}
		}
		// Loop array new genre
		for (String n : newGenre) {
			boolean exist = false;
			// Loop list hold genres that will be create or hold
			for (String l : listHold) {
				// If not found
				if (Integer.parseInt(n) == Integer.parseInt(l)) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				listHold.add(n);
			}
		}
		for (String n : listHold) {
			boolean exist = false;
			for (String o : oldGenre) {
				if (Integer.parseInt(n) == Integer.parseInt(o)) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				VideoGenre vdg = new VideoGenre();
				vdg.setGenre(genreDao.findByID(Long.parseLong(n)));
				vdg.setVideo(video);
				vdgDao.create(vdg);
			}
		}
		for (String rm : listRemove) {
			Genre genre = genreDao.findByID(Long.parseLong(rm));
			VideoGenre vdgRemove = vdgDao.findByVideoIdAndGenreId(video.getId(), genre.getId());
			vdgDao.remove(vdgRemove.getId());
		}
	}

	/**
	 * Create Genres
	 * 
	 * @param video
	 * @param genreIds
	 */

	public void createVideoGenres(Video video, String[] genreIds) {
		GenreDAO genreDao = new GenreDAO();
		VideoGenreDAO vdgDao = new VideoGenreDAO();
		for (String genreIdString : genreIds) {
			// Handle genreId
			Long genreIdInt = Long.parseLong(genreIdString);
			// Select Genre from database
			Genre genre = genreDao.findByID(genreIdInt);
			// Generate new VideoGenre
			VideoGenre vdg = new VideoGenre(video, genre);
			// Insert VideoGenre to database
			vdgDao.create(vdg);
		}
	}

	/**
	 * Handle delete Video
	 * 
	 * @param request
	 * @param response
	 * @return isSuccess
	 */
	private Boolean deleteVideo(HttpServletRequest request, HttpServletResponse response) {
		VideoDAO videoDao = new VideoDAO();
		Long videoId = Long.parseLong(request.getParameter("delete-id"));
		Video video = videoDao.findByID(videoId);
		Boolean rmViewSuccess = deleteViews(video);
		Boolean rmShareSuccess = deleteShare(video);
		Boolean rmVideoGenreSuccess = deleteVideoGenre(video);
		Boolean rmFavoriteSuccess = deleteFavorite(video);

		if (rmViewSuccess && rmShareSuccess && rmFavoriteSuccess && rmVideoGenreSuccess) {
			Boolean rmReplySuccess = deleteReply(video);
			if (rmReplySuccess) {
				Boolean rmCommentSuccess = deleteComment(video);
				if (rmCommentSuccess) {
					Boolean rmVideoSuccess = videoDao.remove(videoId);
					return rmVideoSuccess;
				}
			}
		}
		return false;
	}

	/**
	 * Handle delete VideoGenre of Video
	 * 
	 * @param video
	 * @return
	 */

	private Boolean deleteVideoGenre(Video video) {
		VideoGenreDAO vdgDao = new VideoGenreDAO();
		for (VideoGenre vdg : video.getVideoGenres()) {
			if (!vdgDao.remove(vdg.getId())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Handle delete Favorite of Video
	 * 
	 * @param video
	 * @return isSuccess
	 */
	private Boolean deleteFavorite(Video video) {
		FavoriteDAO fDao = new FavoriteDAO();
		for (Favorite f : video.getFavorites()) {
			if (!fDao.remove(f.getId())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Handle delete Share of Video
	 * 
	 * @param video
	 * @return isSuccess
	 */
	private Boolean deleteShare(Video video) {
		ShareDAO shareDao = new ShareDAO();
		for (Share sr : video.getShares()) {
			if (!shareDao.remove(sr.getId())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Handle delete Reply of video
	 * 
	 * @param video
	 * @return isSuccess
	 */
	private Boolean deleteReply(Video video) {
		ReplyDAO rpDao = new ReplyDAO();
		for (Reply rp : video.getReplys()) {
			if (!rpDao.remove(rp.getId())) {
				return false;
			}
		}
		for (Comment cm : video.getComments()) {
			for (Reply rpl : cm.getReplys()) {
				if (!rpDao.remove(rpl.getId())) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Handle delete Comment of Video
	 * 
	 * @param video
	 * @return isSuccess
	 */

	private Boolean deleteComment(Video video) {
		CommentDAO cmDao = new CommentDAO();
		for (Comment cm : video.getComments()) {
			if (!cmDao.remove(cm.getId())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Handle delete View of Video
	 * 
	 * @param video
	 * @return isSuccess
	 */
	private Boolean deleteViews(Video video) {
		ViewDAO viewDao = new ViewDAO();
		for (View view : video.getViews()) {
			if (!viewDao.remove(view.getId())) {
				return false;
			}
		}
		return true;
	}

	public Boolean validFormVideo(Boolean isInsert) throws IOException {
		HttpServletRequest request = RRShare.request();
		HttpServletResponse response = RRShare.response();
		Boolean isValid = true;
		Video video = new Video();
		VideoDAO dao = new VideoDAO();
		try {
			BeanUtils.populate(video, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		if (video != null) {

		}
		if (isValid) {
			return isValid;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/list-video?action=edit&edit-id=" + video.getId());
			return isValid;
		}
	}
}
