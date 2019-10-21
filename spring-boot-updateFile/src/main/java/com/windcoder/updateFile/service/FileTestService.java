package com.windcoder.updateFile.service;

import com.windcoder.updateFile.config.FileUploadProperties;
import com.windcoder.updateFile.entity.TUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
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
	private static final String ENCODE = "UTF-8";

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
		log.error("redFile读取开始~~~~~~~~~~~~~~~~~");
		try {
			fileInputStream = new FileInputStream(path);
			isr = new InputStreamReader(fileInputStream, "UTF-8");
			// 创建一个使用指定大小输入缓冲区的缓冲字符输入流。
			reader = new BufferedReader(isr, 5*1024*1024);
			String tem = "";
			int i = 0;
//			long lineCount = 0;
			while ((tem = reader.readLine()) != null) {
				user = new TUser();
				fillUser(user, tem);
				log.info(user.toString());
				userList.add(user);
				//由于虚拟机内存原因，list一次选择放入一万条数据后就清空
				if (userList.size() == 10000) {
					//插入数据到数据库

					userList.clear();
				}
				System.out.println(i);
				i++;
			}

			log.error("redFile读取结束~~~~~~~~~~~~~~~~~,lineCount:{}",i);
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
		//读取所用时间
		long timer = System.currentTimeMillis();



		BufferedInputStream is = null;
		int offset = 0;
		try {
			fileInputStream = new FileInputStream(path);
			is = new BufferedInputStream(fileInputStream);
			while ( (offset = is.read()) != -1) {
//				log.info(new String(temp,ENCODE).toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Nio版
	 * @param path
	 * @return
	 */
	public long redFile3( String path) {
		path = StringUtils.isEmpty(path) ? DEFAULT_PATH : path;
		//读取所用时间
		long timer = System.currentTimeMillis();

		FileInputStream fileInputStream = null;
		FileChannel fileChannel = null;
		int offset = 0;
		long  startIndex = 0;
		long endIndex = 0;
		try {
			ByteBuffer rbuf = ByteBuffer.allocate(1024);
//			RandomAccessFile raf = new RandomAccessFile(path,"r");
			fileInputStream = new FileInputStream(path);
			fileChannel = fileInputStream.getChannel();
			endIndex = fileChannel.size(); // 读取文件结束位置
			fileChannel.position(startIndex); // 文件开始位置
			byte[] temp = new byte[0]; // 缓存上次读取剩下的部分
			Boolean isWindows = false;
			boolean isEnd = false; // 用于判断数据是否读取完
			boolean isWholeLine = false; // 用于判断第一行读取到的是否为完整的一行
			long lineCount = 0; // 行数统计
			long endLineIndex = startIndex; // 当前处理字节所在位置
			long lineCount2 = 0;
			TUser user = null;
			int CF = "\n".getBytes()[0];
			log.error("redFile3读取开始~~~~~~~~~~~~~~~~~");
			while (fileChannel.read(rbuf) != -1) {
				int position = rbuf.position();
				byte[] rbyte = new byte[position];
				rbuf.flip();
				rbuf.get(rbyte);
				int startNum = 0; // 每行的起始位下标，相当于前档所读取到的byte数组
				// 判断是否有换行符
				for (int i = 0; i<rbyte.length; i++) {
					endLineIndex++;
					if (rbyte[i]== CF) {
						if (fileChannel.position() == startIndex) {
							isWholeLine = true;
							startNum = i + 1;
						}else {
							byte[] line = new byte[temp.length + i - startNum +1];
							System.arraycopy(temp, 0, line, 0, temp.length);
							System.arraycopy(rbyte,startNum, line, temp.length, i- startNum +1);
							startNum = i + 1;
							lineCount++;
							temp = new byte[0];
							// 处理数据
							if (startIndex  != 0) {
								if (lineCount == 1) {
									if (isWholeLine) {
										lineCount2++;
										user = new TUser();
										fillUser(user, new String(line,ENCODE).toString());
										log.info(user.toString());
									}
								} else {
									lineCount2++;
									user = new TUser();
									fillUser(user, new String(line,ENCODE).toString());
									log.info(user.toString());
								}
							}else {
								lineCount2++;
								user = new TUser();
								fillUser(user, new String(line,ENCODE).toString());
								log.info(user.toString());
							}
							// 结束读取判断
							if (endLineIndex >= endIndex) {
								isEnd = true;
								break;
							}

						}
					}
				}
				if (!isEnd && startNum < rbyte.length) {
					byte[] temp2 = new byte[temp.length+rbyte.length - startNum];
					System.arraycopy(temp,0 ,temp2, 0, temp.length);
					System.arraycopy(rbyte, startNum, temp2, temp.length, rbyte.length - startNum);
					temp = temp2;
				}
				rbuf.clear();
			}
			if (temp.length>0) {
				lineCount2++;
				user = new TUser();
				fillUser(user, new String(temp,ENCODE).toString());
				log.info(user.toString());
			}

			log.error("redFile3读取结束~~~~~~~~~~~~~~~~~,lineCount:{},lineCount2:{}",lineCount,lineCount2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			timer = System.currentTimeMillis() - timer;
			log.info("log 处理时间：" + timer);
			return timer;
		}
	}


	private void fillUser(TUser user, String tem){
//		log.info("tem:{}",tem);
		if (StringUtils.isNotEmpty(tem)) {
			String[] strs = tem.split("~~");
			user.setCode(strs[0]);
			user.setDisplayName(strs[1]);
			user.setPhoneNumber(strs[2]);
			user.setPracticing(strs[3].equals("执业会员")? 5 : 4);
			user.setStatus(strs[4].equals("0") ? TUser.AccountStatus.ACTIVE : TUser.AccountStatus.LOCKED);
		}
	}


	/**
	 * windows：\r\n
	 * U(L)inux：\r
	 *
	 * @param charObj
	 * @return
	 */
	private boolean isLine(int charObj, Boolean isWindows){
		int LF = "\r".getBytes()[0]; // 换行符
		int CF = "\n".getBytes()[0];
		if (charObj == CF) {
			isWindows = true;
			return true;
		} else if (charObj == LF && !isWindows) {
			return true;
		}
		return false;
	}
}
