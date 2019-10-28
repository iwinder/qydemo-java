package com.windcoder.updateFile.service;

import com.windcoder.updateFile.config.FileUploadProperties;
import com.windcoder.updateFile.config.WdFtpProperties;
import com.windcoder.updateFile.entity.TUser;
import com.windcoder.updateFile.repository.TUserRepository;
import com.windcoder.updateFile.utils.DateUtil;
import com.windcoder.updateFile.utils.FtpService;
import com.windcoder.updateFile.utils.UserUtil;
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

	/**
	 * 读取并持久化用户
	 * @param path
	 * @return
	 */
	public long redFile( String path){
		path = StringUtils.isEmpty(path) ? DEFAULT_PATH : path;

		//读取数据拼接字符串
		String laststr = "";
		List<TUser> userList = new ArrayList<>();
		//读取所用时间
		long timer = System.currentTimeMillis();
		TUser user = null;

		try (FileInputStream fileInputStream = new FileInputStream(path);
			 InputStreamReader isr = new InputStreamReader(fileInputStream, "GBK");
			 BufferedReader reader = new BufferedReader(isr, 5*1024*1024);){
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
			timer = System.currentTimeMillis() - timer;
			log.info("log 处理时间：" + timer);
			return timer;
		}

	}



	/**
	 * 检测数据库已有用户数
	 * @return
	 */
	public long countAllByCodeIsNotNull(){

//		return repository.countAllByCodeIsNotNull();
		return repository.count();
	}

	/**
	 * 检测code是否存在
	 * @param code
	 * @return
	 */
	public int countByCode(String code){
		return repository.countByCode(code);
	}

	/**
	 * 将String转为TUser对象，填充TUser用户信息
	 * @param user
	 * @param tem
	 */
	private void fillUser(TUser user, String tem) throws UnsupportedEncodingException {
		if (StringUtils.isNotEmpty(tem)) {
			String tmp = new String(tem.getBytes("GBK"), "UTF-8");
			String[] strs = tmp.split("~~");
			user.setCode(strs[0]);
			user.setDisplayName(strs[1]);
			user.setPhoneNumber(strs[2]);
			user.setPracticing(strs[3].equals("执业会员")? 5 : 4);
			user.setStatus(strs[4].equals("0") ? TUser.AccountStatus.ACTIVE : TUser.AccountStatus.LOCKED);
		}
	}

	/**
	 * 下载文件
	 * @return
	 */
	public long downloadFile(){
		long timer = System.currentTimeMillis();
		FTPClient client = null;
		try {
			client =  ftpService.connect(properties.getHostname(), properties.getPort(), properties.getUsername(), properties.getPassword());
			String dayStr = DateUtil.getDayStr();
			String fileName = "注协"+dayStr+".zip";
			String path = properties.getHome()+"/"+fileName;
			ftpService.downloadFile(client, properties.getHome(),properties.getCachePath()+"/userFiles",fileName, null,  true);
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

	public long updateFile(){
		long timer = System.currentTimeMillis();
		FTPClient client = null;
		try {
			client =  ftpService.connect(properties.getHostname(), properties.getPort(), properties.getUsername(), properties.getPassword());
			String fileName = "CpaOpenPullUserController.java";
			ftpService.uploadFile(client, properties.getCachePath()+"/userFiles/CpaOpenPullUserController.java", properties.getHome(),fileName);
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




//	public void getUserPath(){
//		properties.getCachePath()+"/userFiles/" + ""
//	}


}
