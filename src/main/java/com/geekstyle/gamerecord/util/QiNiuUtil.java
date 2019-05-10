package com.geekstyle.gamerecord.util;

import com.geekstyle.gamerecord.service.common.ResponseService;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiNiuUtil {
	private static String ACCESS_KEY = "F7tDN5pXl8ynDPKlMm_E4CjwYaH4cKTUCEleP-wI";
	private static String SECRET_KEY = "lS0rcleezGCD0BjOojjp42_ccVY-q7WbO3IOK47o";
	private static String BUCKET_NAME = "gamerecord-package";
	
	private static UploadManager uploadManager;
	private static Auth auth;

	static {
		uploadManager = new UploadManager();
		auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	}

	public static String upload(String filePath,String fileNameSavedInQiNiu){
		try {
			String uploadToken = auth.uploadToken(BUCKET_NAME);
			uploadManager.put(filePath, fileNameSavedInQiNiu, uploadToken);
			return "http://oboew07kf.bkt.clouddn.com/" + fileNameSavedInQiNiu;
		} catch (QiniuException e) {
			e.printStackTrace();
			return ResponseService.SERVER_ERROR;
		}
	}

//	public static void main(String args[]) throws IOException {
//		upload("E:/!testfilefolder/img/6048ff14471243c7815435816cde6227.jpg", "1111112.jpg");
//	}

}
