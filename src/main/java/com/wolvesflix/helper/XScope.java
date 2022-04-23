package com.wolvesflix.helper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class XScope {
	public static HttpServletRequest request() {
		return RRShare.request();
	}

	public static HttpSession session() {
		return request().getSession();
	}

	public static ServletContext application() {
		return request().getServletContext();
	}

	/**
	 * Set attribute in request scope
	 * 
	 * @param name  -> Attribute name
	 * @param value -> Attribute value
	 */
	public static void setRequest(String name, Object value) {
		request().setAttribute(name, value);
	}

	/**
	 * Get request attribute value
	 * 
	 * @param <T>
	 * @param name ->Attribute name
	 * @return value or null
	 */
	public static <T> T getRequest(String name) {
		return (T) request().getAttribute(name);
	}

	/**
	 * Remove request attribute
	 * 
	 * @param name -> attribute name
	 */
	public static void removeRequest(String name) {
		request().removeAttribute(name);
	}

	/**
	 * Set Session attribute
	 * 
	 * @param name  -> Attribute name
	 * @param value -> Attribute value
	 */
	public static void setSession(String name, Object value) {
		session().setAttribute(name, value);
	}

	/**
	 * Get Session attribute
	 * 
	 * @param <T>
	 * @param name - Attribute name
	 * @return Attribute value or null
	 */
	public static <T> T getSesion(String name) {
		return (T) session().getAttribute(name);
	}

	/**
	 * Remove Session Attribute
	 * 
	 * @param name -> Attribute name
	 */
	public static void removeSession(String name) {
		session().removeAttribute(name);
	}

	/**
	 * Set attribute for Application Scope
	 * 
	 * @param name  -> Attribute name
	 * @param value -> Attribute value
	 */
	public static void setApplication(String name, Object value) {
		application().setAttribute(name, value);
	}

	/**
	 * Get Application Scope value
	 * 
	 * @param <T>
	 * @param name -> Attribute name
	 * @return Attribute value or null
	 */
	public static <T> T getApplication(String name) {
		return (T) application().getAttribute(name);
	}

	/**
	 * Remove Application Scope attribute
	 * 
	 * @param name -> Attribute name
	 */

	public static void removeApplication(String name) {
		application().removeAttribute(name);
	}

}
