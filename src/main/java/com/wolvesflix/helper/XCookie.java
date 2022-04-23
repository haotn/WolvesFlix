package com.wolvesflix.helper;

import javax.servlet.http.Cookie;

public class XCookie {
	/**
	 * Create and send Cookie to Client
	 * 
	 * @param name  -> Cookie name
	 * @param value -> Cookie value
	 * @param hours -> Cookie age
	 */
	public static void add(String name, String value, int hours) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(hours * 60 * 60);
		cookie.setPath("/");
		RRShare.response().addCookie(cookie);
	}

	/**
	 * Read Cookie value
	 * 
	 * @param name   -> Cookie name
	 * @param defval -> Default value
	 * @return Cookie value (if Cookie is exist) or null (if Cookie is not exist)
	 */

	public static String get(String name, String defval) {
		Cookie[] cookies = RRShare.request().getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return defval;
	}
}
