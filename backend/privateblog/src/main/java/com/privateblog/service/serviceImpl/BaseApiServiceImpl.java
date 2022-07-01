package com.privateblog.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.privateblog.entity.UserEntity;
import com.privateblog.entity.UserLoginLogEntity;
import com.privateblog.model.UserInfoModel;
import com.privateblog.service.BaseApiService;
import com.privateblog.service.UserLoginLogService;
import com.privateblog.service.UserService;

@Service
@Transactional
public class BaseApiServiceImpl implements BaseApiService{

	@Autowired(required = true)
	UserLoginLogService userLoginLogService;
	@Autowired(required = true)
	UserService userService;
	
	@Override
	public UserInfoModel GetUserInfo(String tokenID) {
		UserLoginLogEntity loginEntity =  new UserLoginLogEntity();
		UserEntity userEntity = new UserEntity();
		UserInfoModel result = new UserInfoModel();
		
		loginEntity.P_Token = tokenID;
		loginEntity =  userLoginLogService.selectLogbyToken(loginEntity);
		
		userEntity = userService.getUserInfobyUUID(loginEntity.P_UUID);
		result.uuid = userEntity.P_UUID;
		result.nickname = userEntity.P_NickName;
		result.username = userEntity.P_UserName;
		result.mailaddres = userEntity.P_MailAddres;
		result.userprofilephoto = userEntity.P_UserProfilePhoto;
		result.introduction = userEntity.P_UserIntroduction;
		result.token = tokenID;
	
		return result;
	}

}
