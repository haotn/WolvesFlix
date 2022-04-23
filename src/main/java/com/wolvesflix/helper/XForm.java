package com.wolvesflix.helper;

import static com.wolvesflix.helper.RRShare.request;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

public class XForm {
	/**
	 * Check if parameter exist
	 * 
	 * @param name -> Parameter name
	 * @return true (if exist) or false (not exist)
	 */
	public static Boolean exist(String name) {
		return request().getParameter(name) != null;
	}

	/**
	 * Get String value of parameter
	 * 
	 * @param name   -> Parameter name
	 * @param defval -> Default value
	 * @return Parameter value (if value is not null) or default value (if value is
	 *         null)
	 */
	public static String getString(String name, String defval) {
		String value = request().getParameter(name);
		return value == null ? defval : value;
	}

	/**
	 * Get Integer value of parameter
	 * 
	 * @param name   -> Parameter name
	 * @param defval -> Default value
	 * @return Parameter value (if value is not null) or default value (if value is
	 *         null)
	 */
	public static Integer getInt(String name, int defval) {
		String value = getString(name, String.valueOf(defval));
		return Integer.parseInt(value);
	}

	/**
	 * Get Double value of parameter
	 * 
	 * @param name   -> Parameter name
	 * @param defval ->Default value
	 * @return Parameter value (if value is not null) or default value (if value si
	 *         null)
	 */
	public static Double getDouble(String name, double defval) {
		String value = getString(name, String.valueOf(defval));
		return Double.parseDouble(value);
	}

	/**
	 * Get Boolean of parameter
	 * 
	 * @param name   -> Parameter name
	 * @param defval -> Default value
	 * @return Parameter value (if value is not null) or default value (if value is
	 *         null)
	 */
	public static Boolean getBoolean(String name, boolean defval) {
		String value = getString(name, String.valueOf(defval));
		return Boolean.parseBoolean(value);
	}

	/**
	 * Get Date value of parameter
	 * 
	 * @param name   -> Parameter name
	 * @param defval -> Default value
	 * @return Parameter value (if value is not null) or default value (if value is
	 *         null)
	 */

	public static Date getDate(String name, Date defval) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		String date = getString(name, formater.format(defval));
		try {
			return formater.parse(date);
		} catch (Exception e) {
			return defval;
		}
	}

	/**
	 * Save upload file to directory with unique file name
	 * 
	 * @param name   -> Parameter name
	 * @param folder -> Directory name
	 * @return File (if there are file upload) or null (if there are not upload
	 *         file)
	 */
	public static File save(String name, String folder) {
		File dir = new File(request().getServletContext().getRealPath(folder));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			Part part = request().getPart(name);
			if (part != null && part.getSize() > 0) {
				String fn = System.currentTimeMillis() + part.getSubmittedFileName();
				String filename = Integer.toHexString(fn.hashCode()) + fn.substring(fn.lastIndexOf("."));
				File file = new File(dir, filename);
				part.write(file.getAbsolutePath());
				return file;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get all fields in form
	 * 
	 * @param <T>   -> Entity
	 * @param clazz Entity.class
	 * @return instance of Entity
	 */

	public static <T> T getBean(Class<T> clazz) {
		DateTimeConverter dtc = new DateConverter(new Date());
		dtc.setPattern("yyyy/MM/dd");
		ConvertUtils.register(dtc, Date.class);
		try {
			T bean = clazz.newInstance();
			BeanUtils.populate(bean, request().getParameterMap());
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
