package com.windcoder.updateFile.controller;

import com.windcoder.updateFile.config.FileUploadProperties;
import com.windcoder.updateFile.service.FileTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class FileTestController {

	@Autowired
	private FileTestService fileService;

	/**
	 * 测试传统io读取文件
	 * @return
	 */
	@RequestMapping("/user1")
	public String printUser() {
		long timer = fileService.redFile(FileUploadProperties.CONTENTPATH+FileUploadProperties.DEFAULTSAVEPATH+"/北京20191012.txt");
		return "处理时间：" + timer;
	}

	/**
	 * 测试nio读取文件
	 * @return
	 */
	@RequestMapping("/user2")
	public String printUser3() {
		long timer = fileService.redFile3(FileUploadProperties.CONTENTPATH+FileUploadProperties.DEFAULTSAVEPATH+"/北京20191012.txt");
		return "处理时间：" + timer;
	}
}
