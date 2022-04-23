package com.wolvesflix.data;

import java.util.ArrayList;
import java.util.List;

import com.wolvesflix.dao.VideoDAO;
import com.wolvesflix.entity.Video;

public class VideoHelper {
	private static DataGenerator dataGenerator = new DataGenerator();

//	String title, String youtubeID, String poster, Date addDate, String description, Boolean active
	private static List<Video> getVideos() {
		List<Video> videos = new ArrayList<Video>();
		videos.add(new Video("Hà Anh Tuấn - Tháng Tư Là Lời Nói Dối Của Em (Official MV)", "UCXao7aTDQM",
				"thang-tu-la-loi-noi-doi-cua-em.webp", dataGenerator.generateDate(2022, 2022),
				dataGenerator.generateDescription(200), true));
		videos.add(
				new Video("HIEUTHUHAI - Bật Nhạc Lên ft. Harmonie (prod. by NEMYA)", "O2ZfBvJAt94", "bat-nhac-len.webp",
						dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("Kygo, Zara Larsson, Tyga - Like It Is", "TOamHghGCfg", "like-it-is.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("Passenger - Let Her Go (Official Video)", "PBZfCmlRIVs", "let-her-go.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("MONSTAR - GIỮ LẤY LÀM GÌ | Official Music Video", "BYgzu_r_RmE", "giu-lay-lam-gi.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("24H | OFFICIAL MUSIC VIDEO | LYLY ft MAGAZINE", "IpniN1Wq68Y", "24-h.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("JustaTee - Love you too much (Official MV)", "KhTCatAKVpk", "love-you-too-much.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("Ex's Hate Me - B Ray x Masew (Ft AMEE) | Official MV", "95ahbau-rJk", "ex-hate-me.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("Đen - Lối Nhỏ ft. Phương Anh Đào (M/V)", "KKc_RMln5UY", "loi-nho.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("Đen - Trốn Tìm ft. MTV band (M/V)", "Ws-QlpSltr8", "tron-tim.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("YÊU ĐƠN PHƯƠNG | ONLYC x KARIK | OFFICIAL MV", "DTosuuoN74A", "yeu-don-phuong.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("EM NGỦ CHƯA? | OFFICIAL MV | Trịnh Thăng Bình x OSAD", "4m3fPhwcGRA", "em-ngu-chua.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("Kha - Em Không Cô Đơn (Official MV)", "KZjCY4DMtAU", "em-khong-co-don.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("Long Cao - BÊN ẤY BÊN NÀY (Audio)", "x9mw18QHcHY", "ben-ay-ben-nay.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("Chuyện Đôi Ta - Emcee L (Da LAB) ft Muộii (Official MV)", "6eONmnFB9sw",
				"chuyen-doi-ta.webp", dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200),
				true));
		videos.add(new Video("VÌ MỘT CÂU NÓI - HOÀNG DŨNG, MÀU NƯỚC BAND | 25 MÉT VUÔNG - EP.3", "Ho0MobHCEgY",
				"vi-mot-cau-noi.webp", dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200),
				true));
		videos.add(
				new Video("Chẳng Nói Nên Lời - Acoustic Session | Hoàng Dũng", "9cPJLlNwO-w", "chang-noi-nen-loi.webp",
						dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("ĐỢI // VŨ. (ORIGINAL)", "V6pLnQdGA_c", "doi.webp", dataGenerator.generateDate(2020, 2022),
				dataGenerator.generateDescription(200), true));
		videos.add(new Video("NGHE EM", "EN1bplFXfIw", "nghe-em.webp", dataGenerator.generateDate(2020, 2022),
				dataGenerator.generateDescription(200), true));
		videos.add(new Video("Thắc Mắc (MĐX)", "YTQ-n0SgdiY", "thac-mac.webp", dataGenerator.generateDate(2020, 2022),
				dataGenerator.generateDescription(200), true));
		videos.add(new Video("THÓI QUEN - HOÀNG DŨNG, GDUCKY, MÀU NƯỚC BAND | 25 MÉT VUÔNG - EP.1", "nTtpHxnO9zA",
				"thoi-quen.webp", dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200),
				true));
		videos.add(new Video("SUYT NUA THI | OFFICIAL OST | CHUYEN DI CUA THANH XUAN | ANDIEZ x BITI'S HUNTER",
				"cUmpJ2zwfVU", "suyt-nua-thi.webp", dataGenerator.generateDate(2020, 2022),
				dataGenerator.generateDescription(200), true));
		videos.add(
				new Video("NƠI NÀY CÓ ANH | OFFICIAL MUSIC VIDEO | SƠN TÙNG M-TP", "FN7ALfpGxiI", "noi-nay-co-anh.webp",
						dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("Finn Askew - Roses (Official Video)", "H8G-bOzO5AY", "rose.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));
		videos.add(new Video("LẠC TRÔI | OFFICIAL MUSIC VIDEO | SƠN TÙNG M-TP", "Llw9Q6akRo4", "lac-troi.webp",
				dataGenerator.generateDate(2020, 2022), dataGenerator.generateDescription(200), true));

		return videos;
	}

	/**
	 * Create List Video
	 */
	public static Boolean createVideos() {
		VideoDAO dao = new VideoDAO();
		for (Video video : getVideos()) {
			dao.create(video);
		}
		return true;
	}
}
