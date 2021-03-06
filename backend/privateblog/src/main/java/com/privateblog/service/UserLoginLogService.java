package com.privateblog.service;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.privateblog.entity.UserLoginLogEntity;

@Repository
public interface UserLoginLogService  extends IService<UserLoginLogEntity>{

	public void insertUserLoginLog(UserLoginLogEntity loginEntity);
	
	public UserLoginLogEntity selectLogbyToken(UserLoginLogEntity loginEntity);
	
	public boolean checkLoginLogVaild(UserLoginLogEntity loginEntity);
	
	public void deleteUserLoginLog(UserLoginLogEntity loginEntity);
}
