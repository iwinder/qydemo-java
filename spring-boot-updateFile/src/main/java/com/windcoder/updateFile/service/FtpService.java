package com.windcoder.updateFile.service;

import com.windcoder.updateFile.config.WdFtpProperties;
import com.windcoder.updateFile.exception.FtpException;
import com.windcoder.updateFile.utils.CommonConsts;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.io.CopyStreamEvent;
import org.apache.commons.net.io.CopyStreamListener;
import org.apache.commons.net.io.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 *
 */
@Service
@Slf4j
public class FtpService {
	@Autowired
	private WdFtpProperties properties;

	public static final int FTP_FILE_NOT_FOUND = 550;
	public static final int FTP_PATH_CREATED = 257;
	public static final long ONE_GB = 1024 * 1024 * 1024;

	public static final int DEFAULT_TCP_BUFFER_SIZE = 1024 * 1024 * 5;
	private static final String COULD_NOT_FIND_FILE_MESSAGE = "Could not find file: %s";
	private static final String FILE_DOWNLOAD_FAILURE_MESSAGE = "Unable to download file %s";
	private static final String FILE_STREAM_OPEN_FAIL_MESSAGE = "Unable to write to local directory %s";
	private static final String FILE_LISTING_ERROR_MESSAGE = "Unable to list files in directory %s";
	private static final String NO_SUCH_DIRECTORY_MESSAGE = "The directory %s doesn't exist on the remote server.";
	private static final String UNABLE_TO_CD_MESSAGE = "Remote server was unable to change directory.";
	private String ftpModificationTimePattern = "yyyyMMddHHmmss";

	public FTPClient connect(String hostname, Integer port, String username, String password) throws IOException {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect(hostname, port);
		ftpClient.setControlEncoding(properties.getEncoding());
		log.error("connect=>\t"+ftpClient.getReplyString());
		ftpClient.login(username, password);
		log.error("login=>\t"+ftpClient.getReplyString());
		return ftpClient;
	}

	public void disconnect(FTPClient ftpClient) throws IOException {
		ftpClient.logout();
		log.error("disLogin=>\t"+ftpClient.getReplyString());
		ftpClient.disconnect();
		log.error("disConnect=>\t"+ftpClient.getReplyString());
	}

	public void downloadFile(FTPClient ftpClient, String remoteFilePath, String localDirectoryPath, String localFileName,
							 Boolean compareTime, Boolean logProcess) throws Exception {


		// 开启被动模式
		ftpClient.enterLocalPassiveMode();
		// 设置以二进制方式传输
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		ftpClient.setBufferSize(DEFAULT_TCP_BUFFER_SIZE);
		if (properties.getEncoding().equals(CommonConsts.CHARSET_GBK)) {
			remoteFilePath = new String(remoteFilePath.getBytes("GBK"), "ISO-8859-1");
		} else if (properties.getEncoding().equals(CommonConsts.CHARSET_GB2312)) {
			remoteFilePath = new String(remoteFilePath.getBytes("GB2312"), "ISO-8859-1");
		} else {
			remoteFilePath = new String(remoteFilePath.getBytes("UTF-8"), "ISO-8859-1");
		}

		FTPFile[] files =  ftpClient.listFiles(remoteFilePath);

		//检查远程文件是否存在
		if (!(files.length == 1)) {
			throw new FtpException("Unable to download file : " + remoteFilePath + " does not exist.");
		}
		//如果本地文件夹不存在，可以递归创建
		String localFilePath;

		// 如果本地文件夹不存在，可以递归创建
		// mkdirs 可以递归创建文件夹
		if (!Files.exists(Paths.get(localDirectoryPath)))
			Paths.get(localDirectoryPath).toFile().mkdirs();

		if (localFileName == null) {
			localFilePath = localDirectoryPath + File.separator + getName(remoteFilePath);
		} else
			localFilePath = localDirectoryPath + File.separator + localFileName;

		log.info("localFilePath={}", localFilePath);
		File localFile = Paths.get(localFilePath).toFile();
		FTPFile remoteFile = files[0];
		long localSize = localFile.length();
		long remoteSize = remoteFile.getSize();

		if (localSize == 0){
			log.info("本地文件不存在，准备下载 ...");
		}

		if (remoteSize == 0) {
			log.info("远程 ftp 文件不存在，退出 ...");
			return;
		}
		if (localSize == remoteSize) {
//			if (compareTime) {
//				if ()
//			}
		}

		if (localSize > remoteSize) {
			log.info("本地文件比服务器文件大，有误差: {}B <--> {}B，退出下载...", remoteSize, localSize);
			return;
		}

		if (localSize > 0 && remoteSize > localSize) {
			log.info("本地文件已经存在，准备续传 :{}B <--> {}B...", remoteSize, localSize);
		}




		FileOutputStream out = new FileOutputStream(localFile, true);
		ftpClient.setRestartOffset(localFile.length());
		InputStream in = ftpClient.retrieveFileStream(remoteFilePath);

		if (logProcess){
			final long step = remoteSize / 100;
			final long[] process = {localSize / step};
			final long[] readBytes = {localSize};
			CopyStreamListener listener = new CopyStreamListener() {
				@Override
				public void bytesTransferred(CopyStreamEvent event) {

				}

				@Override
				public void bytesTransferred(long totalBytesTransferred, int bytesTransferred, long streamSize) {
					readBytes[0] += bytesTransferred;
					if (readBytes[0]/step != process[0]) {
						process[0] = readBytes[0] / step;
						log.info("下载文件完成进度：" + process[0] +"%");
					}
				}
			};
			Util.copyStream(in,out, DEFAULT_TCP_BUFFER_SIZE, ftpClient.getBufferSize(), listener, true);
		}else {
			if (remoteSize - localSize >= 2 * FileUtils.ONE_GB) {
				IOUtils.copyLarge(in, out);
			}else {
				IOUtils.copy(in, out);
			}
		}
		out.flush();
		IOUtils.closeQuietly(in);
		IOUtils.closeQuietly(out);
		ftpClient.completePendingCommand();

		// 修改下载的文件的最后修改日期为 ftp 文件时间
//		localFile.setLastModified(MyDateUtilsJdk8.asLong(getModificationTime(remoteFilePath)));
		log.info("下载完成 {} <--> {}B...", localFile.getAbsolutePath(), localFile.length());

	}

	public boolean existsFile(FTPClient ftpClient, String remoteFilePath)  {

		FTPFile[] ftpFiles = new FTPFile[1];
		try {
			ftpFiles = ftpClient.listFiles(remoteFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ftpFiles.length == 1;
	}


	public String getName(String path) {
		int start = path.lastIndexOf("\\");
		start = start > 0 ? start :  path.lastIndexOf("/");
		return path.substring(start+1);
	}
//
//	public boolean isSync(String remoteFtpFilePath, String localFilePath) {
//
//		logger.info("compare remoteFtpFile ({}) and localFile ({}) . ", remoteFtpFilePath, localFilePath);
//
//		/**
//		 *本地文件
//		 */
//		File localTempFile = Paths.get(localFilePath).toFile();
//
//		//检查本地文件是否存在
//		if (!localTempFile.exists()) {
//			throw new FtpException("local file : " + localFilePath + " does not exist.");
//		}
//		//为了便于理解，添加时区标志，实际上不添加，也会调用默认时区。
//		LocalDateTime localTimeStamp = MyDateUtilsJdk8.asLocalDateTime(localTempFile.lastModified());
//		/**
//		 * ftp server 文件
//		 */
//
//		FTPFile[] ftpFiles = new FTPFile[1];
//		try {
//			ftpFiles = client.listFiles(remoteFtpFilePath);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		//检查远程文件是否存在
//		if (ftpFiles.length == 0) {
//			throw new FtpException("remote file : " + remoteFtpFilePath + " does not exist.");
//		}
//
//		LocalDateTime serverTimeStamp = getModificationTime(remoteFtpFilePath);
//
//		boolean time = localTimeStamp.getSecond() == serverTimeStamp.getSecond();
//		if (!time) {
//			logger.info("文件创建时间不一致，需要同步 ...");
//			return true;
//		}
//		boolean size = localTempFile.length() == ftpFiles[0].getSize();
//		if (!size) {
//			logger.info("文件大小不一致，需要同步 ...");
//			return true;
//		}
//		logger.info("文件相同，不需要同步 ...");
//		return false;
//
//	}
//
//	private LocalDateTime getModificationTime(String remoteFilePath) {
//
//		try {
//			//  不用 Long ftpServerTimeStamp = ftpFile.getLastModified().getMillis();
//			// commons net ftp 中，文件的 .getLastModified() 方法，获取的 FTPFile 的时间，利用的是 GregorianCalendar ，没有秒属性。
//			String ts = client.getModificationTime(remoteFilePath).trim();
//			// logger.info("ftp file time ({})", ts);
//			/**
//			 * ftp server 上的文件的最后创建时间。
//			 * ftp MDTM 返回的是 GMT （格林威治时间），yyyyMMddHHmmss 格式，仅能精确到秒
//			 */
//			return MyDateUtilsJdk8.parseLocalDateTime(ts, ftpModificationTimePattern);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//			throw new FtpException(String.format("getTimeDiff exception"), e);
//		}
//	}
}
