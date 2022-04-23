package com.wolvesflix.helper;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

public class RRShare {
	private static Map<Long, HttpServletRequest> requests = new HashedMap();
	private static Map<Long, HttpServletResponse> responses = new HashedMap();

	public static void add(HttpServletRequest request, HttpServletResponse response) {
		requests.put(Thread.currentThread().getId(), request);
		responses.put(Thread.currentThread().getId(), response);
	}

	public static void remove() {
		requests.remove(Thread.currentThread().getId());
		responses.remove(Thread.currentThread().getId());
	}

	public static HttpServletRequest request() {
		return requests.get(Thread.currentThread().getId());
	}

	public static HttpServletResponse response() {
		return responses.get(Thread.currentThread().getId());
	}

}
