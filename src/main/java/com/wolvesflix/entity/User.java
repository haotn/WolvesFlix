package com.wolvesflix.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NamedQueries;

@NamedQueries({
		@NamedQuery(name = "User.findAll", query = "SELECT o FROM User o WHERE o.accept=true AND o.id!=:currentUserId"),
		@NamedQuery(name = "User.findMinId", query = "SELECT MIN(o.id) FROM User o"),
		@NamedQuery(name = "User.findMaxId", query = "SELECT MAX(o.id) FROM User o"),
		@NamedQuery(name = "User.findRemember", query = "SELECT o FROM User o WHERE o.cookieId=:cid AND o.accept=true"),
		@NamedQuery(name = "User.findByKeywordAndSortById", query = "SELECT o FROM User o WHERE (o.fullname LIKE :keyword OR o.username LIKE :keyword OR o.email LIKE :keyword) AND o.accept=true AND o.id!=:currentUserId ORDER  BY o.id ASC"),
		@NamedQuery(name = "User.findByKeywordAndSortUsername", query = "SELECT o FROM User o WHERE (o.fullname LIKE :keyword OR o.username LIKE :keyword OR o.email LIKE :keyword) AND o.accept=true AND o.id!=:currentUserId ORDER BY o.username ASC"),
		@NamedQuery(name = "User.findByKeywordAndSortByRegisterDate", query = "SELECT o FROM User o WHERE (o.fullname LIKE :keyword OR o.username LIKE :keyword OR o.email LIKE :keyword) AND o.accept=true AND o.id!=:currentUserId ORDER BY o.registerDate DESC"),
		@NamedQuery(name = "User.countByMonth", query = "SELECT COUNT(o) FROM User o WHERE MONTH(o.registerDate) = MONTH(GETDATE())") })
@Entity
@Table(name = "`USER`")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "FULLNAME")
	private String fullname;
	@Column(name = "REGISTERDATE")
	private Date registerDate = new Date();
	@Column(name = "COOKIEID")
	private String cookieId;
	@Column(name = "ACCEPT")
	private Boolean accept;
	@Column(name = "ADMIN")
	private Boolean admin;
	@Column(name = "AVATAR")
	private String avatar;
	@OneToMany(mappedBy = "user")
	private List<Favorite> favorites;
	@OneToMany(mappedBy = "user")
	private List<Comment> comments;
	@OneToMany(mappedBy = "user")
	private List<Share> shares;
	@OneToMany(mappedBy = "user")
	private List<View> views;
	@OneToMany(mappedBy = "user")
	private List<Reply> replys;
	@OneToMany(mappedBy = "user")
	private List<AccessHistory> accessHistorys;

	@Override
	public String toString() {
		return this.username + " - " + this.password + " - " + this.email + " - " + this.fullname + " - " + this.admin
				+ " ' " + this.registerDate + " - " + this.cookieId;
	}

	public User() {
	}

	public User(String username, String password, String email, String fullname, Date registerDate, String cookieId,
			Boolean accept, Boolean admin, String avatar) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.registerDate = registerDate;
		this.cookieId = cookieId;
		this.accept = accept;
		this.admin = admin;
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public List<View> getViews() {
		return views;
	}

	public void setViews(List<View> views) {
		this.views = views;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getCookieId() {
		return cookieId;
	}

	public void setCookieId(String cookieId) {
		this.cookieId = cookieId;
	}

	public Boolean getAccept() {
		return accept;
	}

	public void setAccept(Boolean accept) {
		this.accept = accept;
	}

	public static User parseUser(Object o) {
		if (o instanceof User) {
			return (User) o;
		}
		return null;
	}

	public List<Reply> getReplys() {
		return replys;
	}

	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<AccessHistory> getAccessHistorys() {
		return accessHistorys;
	}

	public void setAccessHistorys(List<AccessHistory> accessHistorys) {
		this.accessHistorys = accessHistorys;
	}

}
