package com.windcoder.updateFile.repository;

import com.windcoder.updateFile.entity.TUser;

public interface TUserRepository extends SupportRepository<TUser, Long> {
	long countAllByCodeIsNotNull();
	int countByCode(String code);
}
