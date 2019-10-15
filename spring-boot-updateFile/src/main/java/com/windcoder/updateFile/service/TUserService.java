package com.windcoder.updateFile.service;

import com.windcoder.updateFile.config.FileUploadProperties;
import com.windcoder.updateFile.config.WdFtpProperties;
import com.windcoder.updateFile.entity.TUser;
import com.windcoder.updateFile.repository.TUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TUserService extends BaseService<TUser, Long, TUserRepository> {

	private static final  String DEFAULT_PATH = FileUploadProperties.CONTENTPATH + FileUploadProperties.DEFAULTSAVEPATH+ "/北京20191012.txt";
	@Autowired
	private FtpService ftpService;
	@Autowired
	private WdFtpProperties properties;

	public long redFile( String path){
		path = StringUtils.isEmpty(path) ? DEFAULT_PATH : path;
		BufferedReader reader = null;
		//读取数据拼接字符串
		String laststr = "";
		List<TUser> userList = new ArrayList<>();
		//读取所用时间
		long timer = System.currentTimeMillis();
		TUser user = null;
		FileInputStream fileInputStream = null;
		InputStreamReader isr = null;
		try {
			fileInputStream = new FileInputStream(path);
			isr = new InputStreamReader(fileInputStream, "UTF-8");
//			reader = new BufferedReader(isr);
			reader = new BufferedReader(isr, 5*1024*1024);
			String tem = "";
			int i = 1;
			while ((tem = reader.readLine()) != null) {
				user = new TUser();
				fillUser(user, tem);
				if (countByCode(user.getCode())>0){
					continue;
				}
				userList.add(user);
				//由于虚拟机内存原因，list一次选择放入一万条数据后就清空
				if (userList.size() == 10000) {
					//插入数据到数据库
					saveAll(userList);
					userList.clear();
				}
				System.out.println(i);
				i++;
			}
			saveAll(userList);
			System.out.println(userList.size());
//			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null){
					reader.close();
				}
				if (fileInputStream != null) {
					fileInputStream.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			timer = System.currentTimeMillis() - timer;
			log.info("log 处理时间：" + timer);
			return timer;
		}

	}


	public long countAllByCodeIsNotNull(){

//		return repository.countAllByCodeIsNotNull();
		return repository.count();
	}

	public int countByCode(String code){
		return repository.countByCode(code);
	}

	private void fillUser(TUser user, String tem){
		if (StringUtils.isNotEmpty(tem)) {
			String[] strs = tem.split("~~");
			user.setCode(strs[0]);
			user.setDisplayName(strs[1]);
			user.setPhoneNumber(strs[2]);
			user.setPracticing(strs[3].equals("执业会员")? 5 : 4);
			user.setStatus(strs[4].equals("0") ? TUser.AccountStatus.ACTIVE : TUser.AccountStatus.LOCKED);
		}


	}


	public long downloadFile(){
		long timer = System.currentTimeMillis();
		FTPClient client = null;
		try {
			client =  ftpService.connect(properties.getHostname(), properties.getPort(), properties.getUsername(), properties.getPassword());
			String dayStr = getDayStr();
			String fileName = "北京"+dayStr+".zip";
			String path = properties.getHome()+"/北京/"+fileName;
			ftpService.downloadFile(client, path,properties.getCachePath()+"/users",fileName, false, true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client!=null){
				try {
					ftpService.disconnect(client);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			timer = System.currentTimeMillis() - timer;
			log.info("log 下载时间：" + timer);
			return timer;
		}
	}

//	public long redFileByBufferedReader(String path){
//		path = StringUtils.isEmpty(path) ? DEFAULT_PATH : path;
//		File file = new File(path);
//		BufferedInputStream fis = null;
//		try {
//			 fis = new BufferedInputStream(new FileInputStream(file));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//	}

	private String getDayStr(){
		DateTime dte = DateTime.now();
		String monthStr;
		if (dte.getMonthOfYear() > 9) {
			monthStr = String.valueOf(dte.getMonthOfYear());
		} else {
			monthStr = "0" + String.valueOf(dte.getMonthOfYear());
		}
		String dayStr;
		if (dte.getDayOfMonth() > 9) {
			dayStr = String.valueOf(dte.getDayOfMonth());
		} else {
			dayStr = "0" + String.valueOf(dte.getDayOfMonth());
		}
		return String.valueOf(dte.getYear()) + monthStr + dayStr;
	}


}
