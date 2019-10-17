package com.windcoder.updateFile.service;

import com.windcoder.updateFile.config.FileUploadProperties;
import com.windcoder.updateFile.entity.TUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@Service
@Slf4j
@SuppressWarnings("Duplicates")
public class FileTestService {

	private static final  String DEFAULT_PATH = FileUploadProperties.CONTENTPATH + FileUploadProperties.DEFAULTSAVEPATH+ "/北京20191012.txt";


	/**
	 * BufferedReader 版
	 * @param path
	 * @return
	 */
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
		// InputStreamReader类是从字节流到字符流的桥接器：它使用指定的字符集读取字节并将它们解码为字符
		InputStreamReader isr = null;
		try {
			fileInputStream = new FileInputStream(path);
			isr = new InputStreamReader(fileInputStream, "UTF-8");
			// 创建一个使用指定大小输入缓冲区的缓冲字符输入流。
			reader = new BufferedReader(isr, 5*1024*1024);
			String tem = "";
			int i = 1;
			while ((tem = reader.readLine()) != null) {
				user = new TUser();
				fillUser(user, tem);

				userList.add(user);
				//由于虚拟机内存原因，list一次选择放入一万条数据后就清空
				if (userList.size() == 10000) {
					//插入数据到数据库

					userList.clear();
				}
				System.out.println(i);
				i++;
			}


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

	public long redFile2( String path) {
		path = StringUtils.isEmpty(path) ? DEFAULT_PATH : path;
		FileInputStream fileInputStream = null;




		BufferedInputStream is = null;
		int offset = 0;
		try {
			fileInputStream = new FileInputStream(path);
			is = new BufferedInputStream(fileInputStream);
			while ( (offset = is.read()) != -1) {

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long redFile3( String path) {
		path = StringUtils.isEmpty(path) ? DEFAULT_PATH : path;
		FileInputStream fileInputStream = null;
		FileChannel fileChannel = null;
		int offset = 0;
		long  startIndex = 0;
		long endIndex =
		try {
//			RandomAccessFile raf = new RandomAccessFile(path,"r");
			fileInputStream = new FileInputStream(path);
			fileChannel = fileInputStream.getChannel();
			fileChannel.size();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
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
