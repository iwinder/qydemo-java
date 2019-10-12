package com.windcoder.updateFile.service;

import com.windcoder.updateFile.config.FileUploadProperties;
import com.windcoder.updateFile.entity.TUser;
import com.windcoder.updateFile.repository.TUserRepository;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TUserService extends BaseService<TUser, Long, TUserRepository> {


	public void redFile( String path){
		path = StringUtils.isEmpty(path) ?  FileUploadProperties.CONTENTPATH + FileUploadProperties.DEFAULTSAVEPATH+ "/北京20191012.txt" : path;
		BufferedReader reader = null;
		//读取数据拼接字符串
		String laststr = "";
		List<TUser> reviewList = new ArrayList<>();
		//读取所用时间
		long timer = System.currentTimeMillis();

		try {
			FileInputStream fileInputStream = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
