package com.wolvesflix.recommendator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.entity.Video;
import com.wolvesflix.helper.RRShare;

public class Recommendator {
	static String s;

	public static String runScript(Long videoId, String path) {
		prepareData(videoId, path);
		String result = "";
		try {
//			String argv = RRShare.request().getServletContext().getRealPath("Recommendator");
			File file = new File(path + "/script.py");
			String pathToFile = file.getAbsolutePath();

			Process p = Runtime.getRuntime().exec("python " + pathToFile + " " + path);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			while ((s = stdInput.readLine()) != null) {
				result = s;
			}
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void prepareData(Long videoId, String path) {
		VideoDAO dao = new VideoDAO();
		List<Video> videos = dao.findAll();
		String string_db = "";
		string_db += videoId + "{seperator}";
		for (int j = 0; j < videos.size(); j++) {
			string_db += String.valueOf(videos.get(j).getId()) + "{col}";
			string_db += videos.get(j).getTitle() + "{col}";
			string_db += videos.get(j).getAddDate() + "{col}";
			string_db += videos.get(j).getComments().size() + "{col}";
			string_db += videos.get(j).getReplys().size() + "{col}";
			string_db += videos.get(j).getFavorites().size() + "{col}";
			string_db += videos.get(j).getShares().size() + "{col}";
			string_db += videos.get(j).getViews().size() + "{col}";
			for (int i = 0; i < videos.get(j).getVideoGenres().size(); i++) {
				string_db += videos.get(j).getVideoGenres().get(i).getGenre().getGenreName();
				if (i < videos.get(j).getVideoGenres().size() - 1) {
					string_db += "-";
				}
			}
			if (j < videos.size() - 1) {
				string_db += "{row}";
			}
		}
		File file = new File(path + "/input.txt");
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(string_db);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String a = "E:\\Eclipse-Workspace-Java4\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\PC01545_TieuNhutHao_Assignment\\Recommendator";
		runScript(5l, a);
	}
}
