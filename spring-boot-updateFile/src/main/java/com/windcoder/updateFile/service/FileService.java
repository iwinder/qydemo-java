package com.windcoder.updateFile.service;

import com.windcoder.updateFile.config.FileUploadProperties;
import com.windcoder.updateFile.entity.TUser;
import com.windcoder.updateFile.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@SuppressWarnings("Duplicates")
public class FileService {
	private long totalNow = 0;

	private static final  String DEFAULT_PATH = FileUploadProperties.CONTENTPATH + FileUploadProperties.DEFAULTSAVEPATH+ "/北京20191012.txt";
	private static final String ENCODE = "UTF-8";

	@Autowired
	TUserService userService;

	/**
	 * 生成测试数据，写入txt文件
	 * @param savePath
	 * @param isAppend
	 * @param total
	 */
	public long outputFile(String savePath, boolean isAppend, int total){
		long timer = System.currentTimeMillis();
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
		totalNow = userService.countAllByCodeIsNotNull();
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
			timer = System.currentTimeMillis() - timer;
			return timer;
		}
	}

	/**
	 * BufferedReader 版
	 * @param path
	 * @return
	 */
	public long readFileByBufferedReader( String path){
		path = StringUtils.isEmpty(path) ? DEFAULT_PATH : path;
		BufferedReader reader = null;
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
			while ((tem = reader.readLine()) != null) {
				user = new TUser();
				UserUtil.fillUser(user, tem);
				log.info(user.toString());
				i++;
			}

			log.error("redFile读取结束~~~~~~~~~~~~~~~~~,lineCount:{}",i);
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


	/**
	 * 单线程 nio
	 * @param path
	 * @return
	 */
	public long readFileByFileChannel( String path) {
		path = StringUtils.isEmpty(path) ? DEFAULT_PATH : path;
		//读取所用时间
		long timer = System.currentTimeMillis();

		FileInputStream fileInputStream = null;
		FileChannel fileChannel = null;
		long  startIndex = 0;
		long endIndex = 0;
		try {
			// 分配一个新的字节缓冲区。
			/**
			 * 新缓冲区的位置将为零，其界限将是其容量，其标记将是未定义的，并且其每个元素都将初始化为零。
			 * 它将有一个底层实现数组，其数组偏移量将为零。
			 */
			ByteBuffer rbuf = ByteBuffer.allocate(1024);
			// RandomAccessFile raf = new RandomAccessFile(path,"r");
			fileInputStream = new FileInputStream(path);
			// 获取通道
			fileChannel = fileInputStream.getChannel();
			// 读取文件结束位置，即此通道文件的当前大小
			endIndex = fileChannel.size();
			// 缓存上次读取剩下的部分
			byte[] temp = new byte[0];
			// 用于判断数据是否读取完
			boolean isEnd = false;
			// 行数统计
			long lineCount = 0;
			// 当前处理字节所在位置
			long endLineIndex = startIndex;
			// 统计数据行数
			long lineCount2 = 0;
			TUser user = null;
			// 换行符，目前需手动指定
			int CF = "\n".getBytes()[0];
			log.error("redFile3读取开始~~~~~~~~~~~~~~~~~");
			// 循环读取通道中的数据并放入rbuf中
			while (fileChannel.read(rbuf) != -1) {
				// 创建与rbuf容量一样大的数组
				int position = rbuf.position();
				byte[] rbyte = new byte[position];
				/**
				 * 读/写指针position指到缓冲区头部，并且设置了最多只能读出之前写入的数据长度，这里即fileChannel写入rbuf中的数据长度。
				 * Buffer有两种模式，写模式和读模式。在写模式下调用flip()之后，Buffer从写模式变成读模式。
				 */
				rbuf.flip();
				// 将rbuf中的数据传输到rbyte中
				rbuf.get(rbyte);
				// 每行的起始位下标，相当于当前所读取到的byte数组
				int startNum = 0;
				// 判断是否有换行符
				for (int i = 0; i<rbyte.length; i++) {
					endLineIndex++;
					// 当存在换行符时
					if (rbyte[i]== CF) {
						// 创建临时数组用于保存整行数据
						byte[] line = new byte[temp.length + i - startNum +1];
						// 将数组temp从0位置开始temp.length长度的数据复制到line数组0位置开始temp.length长度的数据
						System.arraycopy(temp, 0, line, 0, temp.length);
						// 将数组rbyte从startNum位置开始i- startNum +1长度的数据复制到line数组从temp.length位置开始i- startNum +1长度的数据
						System.arraycopy(rbyte,startNum, line, temp.length, i- startNum +1);
						startNum = i + 1;
						lineCount++;
						temp = new byte[0];
						// 处理数据
						lineCount2++;
						user = new TUser();
						UserUtil.fillUser(user, new String(line,ENCODE));
						log.info(user.toString());

						// 结束读取判断
//						if (endLineIndex >= endIndex) {
//							isEnd = true;
//							break;
//						}
					}
				}
				// 说明rbyte最后还剩不完整的一行
				if (startNum < rbyte.length) {
					byte[] temp2 = new byte[temp.length+rbyte.length - startNum];
					System.arraycopy(temp,0 ,temp2, 0, temp.length);
					System.arraycopy(rbyte, startNum, temp2, temp.length, rbyte.length - startNum);
					temp = temp2;
				}
				rbuf.clear();
			}
			// 兼容最后一行没有换行的情况
			if (temp.length>0) {
				lineCount2++;
				user = new TUser();
				UserUtil.fillUser(user, new String(temp,ENCODE));
				log.info(user.toString());
			}

			log.error("redFile3j读取结束~~~~~~~~~~~~~~~~~,lineCount:{},lineCount2:{}",lineCount,lineCount2);
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
