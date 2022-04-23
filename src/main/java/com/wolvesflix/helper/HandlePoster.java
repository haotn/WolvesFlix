package com.wolvesflix.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class HandlePoster {

	/**
	 * Generate Filename
	 * 
	 * @param filename
	 * @return filename (after handle)
	 */
	private static String generateFilename(String filename) {
		// Generate new SimpleDataFormat
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		// Get filename
		String name = filename.substring(0, filename.lastIndexOf("."));
		// Get file extension
		String extension = filename.substring(filename.lastIndexOf("."));
		// Assign new value
		filename = name + "-" + format.format(new Date()) + extension;
		return filename;
	}

	/**
	 * Handle poster Video
	 * 
	 * @param request
	 */

	public static String handlePoster(HttpServletRequest request) {
		// Get path to folder poster
		File dir = new File(request.getSession().getServletContext().getRealPath("/img/poster"));
		try {
			// Get photo from form data
			Part photo = request.getPart("poster");
			// Handle file name
			String filename = generateFilename(photo.getSubmittedFileName());
			// Create new file
			File photoFile = new File(dir, filename);
			// Write file to poster folder
			photo.write(photoFile.getAbsolutePath());
			return filename;
		} catch (IOException | ServletException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	public static String handlePoster(HttpServletRequest request, String old) {
		File dir = new File(request.getServletContext().getRealPath("/img/poster"));
		try {
			// Get photo from form data
			Part photo = request.getPart("poster");
			if (photo.getSize() > 0) {
//				System.out.println("Change poster");
//				File file = XForm.save("poster", "/img/poster");
				// Handle file name
				String filename = generateFilename(photo.getSubmittedFileName());
				// Create new file
				File photoFile = new File(dir, filename);
				// Write file to poster folder
				photo.write(photoFile.getAbsolutePath());
				return filename;
//				return file.getName();
			} else {
				return null;
			}
		} catch (IOException | ServletException e1) {
			e1.printStackTrace();
			return null;
		}
	}

}
