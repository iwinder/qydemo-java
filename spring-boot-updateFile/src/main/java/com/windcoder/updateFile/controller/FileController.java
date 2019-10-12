package com.windcoder.updateFile.controller;

import com.windcoder.updateFile.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FileController {
	@Autowired
	private FileService fileService;

	@GetMapping("outputFile")
	public String outputFile(@RequestParam(value = "name", required = false) String savePath,
							 @RequestParam(value = "isAppend", defaultValue = "true") boolean isAppend,
							 @RequestParam(value = "name", defaultValue = "100000000") int total){

		try {
			fileService.outputFile(savePath, isAppend, total);
			return "success";
		}catch (Exception e) {
			return "fail";
		}


	}
}
