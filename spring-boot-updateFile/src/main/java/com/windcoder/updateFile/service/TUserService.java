package com.windcoder.updateFile.service;

import com.windcoder.updateFile.config.FileUploadProperties;
import com.windcoder.updateFile.entity.TUser;
import com.windcoder.updateFile.repository.TUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TUserService extends BaseService<TUser, Long, TUserRepository> {


	public void redFile( String path){
		path = StringUtils.isEmpty(path) ?  FileUploadProperties.CONTENTPATH + FileUploadProperties.DEFAULTSAVEPATH+ "/北京20191012.txt" : path;
		BufferedReader reader = null;
		//读取数据拼接字符串
		String laststr = "";
		List<TUser> userList = new ArrayList<>();
		//读取所用时间
		long timer = System.currentTimeMillis();
		TUser user = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(isr);
			String tem = "";
			int i = 1;
			while ((tem = reader.readLine()) != null) {
				user = new TUser();
				fillUser(user, tem);
				userList.add(user);
				//由于虚拟机内存原因，list一次我选择放入一万条数据后就清空
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
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

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

}
