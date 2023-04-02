package com.windcoder.updateFile.controller;

import com.windcoder.updateFile.entity.TUser;
import com.windcoder.updateFile.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
	@Autowired
	private TUserService userService;


	@ModelAttribute("UgcChapterForUpdate")
	public TUser getUser(@RequestParam(name = "id", required = false) Long id) {
		if(id != null) {
			TUser chapter = this.userService.findById(id).get();
			chapter.setCode(null);
			return chapter;
		}
		return new TUser();
	}

	@GetMapping("")
	public TUser dele(@RequestParam(name = "id") Long id){
		return userService.dele(id);
	}
}
