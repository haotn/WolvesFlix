package com.wolvesflix.helper;

public class HandleYoutubeLink {
	public static String handelYoutubeLink(String youtubeLink) {
		if (youtubeLink.contains("www.youtube.com/watch?v=")) {
			if (youtubeLink.contains("&")) {
				return youtubeLink.substring(youtubeLink.indexOf("=") + 1, youtubeLink.indexOf("&"));
			} else {
				return youtubeLink.substring(youtubeLink.indexOf("=") + 1);
			}
		}
		return youtubeLink;
	}
}
