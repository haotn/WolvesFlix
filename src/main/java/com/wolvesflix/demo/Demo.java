package com.wolvesflix.demo;

import java.util.List;

import com.wolvesflix.dao.VideoGenreDAO;
import com.wolvesflix.entity.Comment;
import com.wolvesflix.entity.Favorite;
import com.wolvesflix.entity.Reply;
import com.wolvesflix.entity.Share;
import com.wolvesflix.entity.User;
import com.wolvesflix.entity.View;
import com.wolvesflix.data.CommentHelper;
import com.wolvesflix.data.FavoriteHelper;
import com.wolvesflix.data.GenreHelper;
import com.wolvesflix.data.ReplyHelper;
import com.wolvesflix.data.ShareHelper;
import com.wolvesflix.data.UserHelper;
import com.wolvesflix.data.VideoGenreHelper;
import com.wolvesflix.data.VideoHelper;
import com.wolvesflix.data.ViewHelper;

public class Demo {
	public static void main(String[] args) {
		addData();
	}

	private static void addData() {
		List<User> users = UserHelper.generateUsers(100);

		if (UserHelper.createUsers(users) && VideoHelper.createVideos()) {
			if (GenreHelper.createGenres()) {
				VideoGenreHelper.createVideoGenres();
			}

			List<Comment> comments = CommentHelper.generateComments(200);
			if (CommentHelper.createComments(comments)) {
				List<Reply> replys = ReplyHelper.generateReplys(500);
				ReplyHelper.createReplys(replys);
			}
			List<Share> shares = ShareHelper.generateShares(200);
			List<Favorite> favorites = FavoriteHelper.generateFavorites(FavoriteHelper.PER90);
			List<View> views = ViewHelper.generateViews(2000);
			ViewHelper.createViews(views);
			FavoriteHelper.createFavorites(favorites);
			ShareHelper.createShares(shares);
			System.out.println("Done!");
		}
	}
}
