package com.wolvesflix.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wolvesflix.dao.UserDAO;
import com.wolvesflix.entity.User;

public class UserHelper {
	private static DataGenerator dataGenerator = new DataGenerator();

	/**
	 * Generate User
	 * 
	 * @return User
	 */
	public static User generateUser() {
		String fullname = dataGenerator.generateFullname();
		String email = dataGenerator.generateEmailName(fullname);
		String username = dataGenerator.generateUsename(email);
		String password = dataGenerator.generatePassword(8);
		User user = new User();
		user.setAccept(true);
		user.setAdmin(false);
		user.setCookieId(dataGenerator.generateCookieID());
		user.setEmail(email);
		user.setFullname(fullname);
		user.setPassword(password);
		user.setRegisterDate(dataGenerator.generateDate(2019, 2021));
		user.setUsername(username);
		return user;
	}

	/**
	 * Generate Users
	 * 
	 * @param int size
	 * @return List<User>
	 */
	public static List<User> generateUsers(int size) {
		List<User> users = new ArrayList<User>();
		List<String> fullnames = dataGenerator.generateListFullname(size, false);
		Map<String, String> emailAndFullname = (HashMap<String, String>) dataGenerator.generateMapEmail(fullnames);
		Map<String, String> emailAndUsername = dataGenerator.generateMapUsername(emailAndFullname);
		int i = 0;
		for (String key : emailAndFullname.keySet()) {
			User user = new User();
			int accept = (int) (1 * Math.random());
			switch (accept) {
			case 0:
				user.setAccept(true);
				break;
			case 1:
				user.setAccept(false);
				break;
			}
			if (i < 3) {
				user.setAdmin(true);
			} else {
				user.setAdmin(false);
			}
			int remember = (int) (1 * Math.random());
			switch (remember) {
			case 0:
				user.setCookieId(null);
				break;
			case 1:
				String cookieId = dataGenerator.generateCookieID();
				if (users.size() > 0) {
					User exist = null;
					for (User u : users) {
						if (u.getCookieId().equals(cookieId)) {
							exist = u;
							break;
						}
					}
					while (exist.getCookieId().equals(cookieId)) {
						cookieId = dataGenerator.generateCookieID();
					}
				}
				user.setCookieId(cookieId);
				break;
			}
			user.setEmail(key);
			user.setFullname(emailAndFullname.get(key));
			user.setPassword(dataGenerator.generatePassword(dataGenerator.randomMinMax(7, 10)));
			user.setRegisterDate(dataGenerator.generateDate(2019, 2021));
			user.setUsername(emailAndUsername.get(key));
			user.setAvatar("user-circle.svg");
			users.add(user);
			i++;
		}
		return users;
	}

	/**
	 * Create List User
	 * 
	 * @param List<User>
	 */
	public static Boolean createUsers(List<User> users) {
		UserDAO userDao = new UserDAO();
		for (User user : users) {
			userDao.create(user);
		}
		return true;
	}
}
