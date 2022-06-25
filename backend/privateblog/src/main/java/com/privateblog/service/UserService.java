package com.privateblog.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.privateblog.entity.UserInfoEntity;
import com.privateblog.model.LoginModel;
import com.privateblog.model.SigninModel;

@Repository
public interface UserService extends IService<UserInfoEntity> {

	public List<UserInfoEntity> getUserInfo(LoginModel loginModel);
	
	public void insertUserInfo(SigninModel signin);
	
	
	
	public Boolean checkUserVaild(SigninModel signinModel );
	
	public Boolean checkMailVaild(SigninModel signinModel );
}
