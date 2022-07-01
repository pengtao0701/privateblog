package com.privateblog.service;

import org.springframework.stereotype.Repository;

import com.privateblog.model.UserInfoModel;

@Repository
public interface BaseApiService {

	public UserInfoModel GetUserInfo(String tokenID);
}
