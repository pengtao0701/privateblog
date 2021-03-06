package com.privateblog.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.privateblog.entity.UserEntity;
import com.privateblog.model.LoginModel;
import com.privateblog.model.SigninModel;

@Repository
public interface UserService extends IService<UserEntity> {

	public List<UserEntity> getUserInfo(LoginModel loginModel);
	
	public UserEntity getUserInfobyUUID(String UUID);
	
	public void insertUserInfo(SigninModel signin);
	
	
	
	public Boolean checkUserVaild(SigninModel signinModel );
	
	public Boolean checkMailVaild(SigninModel signinModel );
}
