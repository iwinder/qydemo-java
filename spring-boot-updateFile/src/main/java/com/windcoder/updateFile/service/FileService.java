package com.windcoder.updateFile.service;

import com.windcoder.updateFile.config.FileUploadProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@Service
public class FileService {
	private long totalNow = 0;

	/**
	 * 生成测试数据，写入txt文件
	 * @param savePath
	 * @param isAppend
	 * @param total
	 */
	public void outputFile(String savePath, boolean isAppend, int total){
		savePath = StringUtils.isEmpty(savePath) ? FileUploadProperties.DEFAULTSAVEPATH : savePath;
		if (!savePath.startsWith("/")){
			savePath = "/" + savePath;
		}
		String basePath = FileUploadProperties.CONTENTPATH;
		String parentPath = basePath + savePath;
		String targetFileName = "/北京20191012.txt";
		File parentDir = new File(parentPath);
		if (!parentDir.exists()){
			parentDir.mkdirs();
		}
		File txtFile = new File(parentDir.getAbsolutePath(), targetFileName);
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			if (!txtFile.exists()) {
				txtFile.createNewFile();
			}
			fw = new FileWriter(txtFile, isAppend);
			pw = new PrintWriter(fw);
			for (int i=0;i<total;i++){
				pw.println((++totalNow) + "~~" + ("张三"+i) + "~~" + getRandom(11) + "~~" + getRandomIsVip() + "~~" + getRandomIsUse());
			}
			pw.flush();
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw!=null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (pw!=null) {
				pw.close();
			}
		}
	}



	private static long getRandom(long len) {
		long min = 1,max = 9;
		for (int i = 1; i < len; i++) {
			min *= 10;
			max *= 10;
		}
		long rangeLong = (((long) (new Random().nextDouble() * (max - min)))) + min ;
		return rangeLong;
	}

	private static String getRandomIsVip(){
		int i = new Random().nextInt(100);
		return i<49? "执业会员":"非执业会员";
	}
	private static int getRandomIsUse(){
		int i = new Random().nextInt(100);
		return i<49? 1:0;
	}

}
