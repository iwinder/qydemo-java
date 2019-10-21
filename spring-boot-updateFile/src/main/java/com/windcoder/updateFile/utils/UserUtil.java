package com.windcoder.updateFile.utils;

import com.windcoder.updateFile.entity.TUser;
import org.apache.commons.lang3.StringUtils;

public class UserUtil {

	public static void fillUser(TUser user, String tem){
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
