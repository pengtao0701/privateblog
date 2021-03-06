package com.privateblog.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.privateblog.common.utils.Utils;
import com.privateblog.entity.UserEntity;
import com.privateblog.mapper.UserMapper;
import com.privateblog.model.LoginModel;
import com.privateblog.model.SigninModel;
import com.privateblog.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Transactional
	public List<UserEntity> getUserInfo(LoginModel loginModel) {
		List<UserEntity> result = new ArrayList<UserEntity>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (!loginModel.username.equals("") && !loginModel.username.equals(null)) {
			map.put("P_UserName", loginModel.username);
			map.put("P_PassWord", loginModel.password);
			result = userMapper.selectByMap(map);
		} else if (!loginModel.mailaddres.equals("") && !loginModel.mailaddres.equals(null)) {
			map.put("P_MailAddres", loginModel.mailaddres);
			map.put("P_PassWord", loginModel.password);
			result = userMapper.selectByMap(map);
		}

		return result;
	}
	
	@Transactional
	public void insertUserInfo(SigninModel signin) {
		UserEntity user = new UserEntity();
		user.P_UUID = signin.uuid;
		user.P_NickName = signin.nickname;
		user.P_UserName = signin.username;
		user.P_PassWord = signin.password;
		user.P_MailAddres = signin.mailaddres;
		user.P_UserProfilePhoto = "";
		user.P_UserIntroduction = "未设置个人介绍";
		// 0: 禁用 ,1: 邮箱未验证 2: 启用
		user.P_Status = 1;
		// 0: admin ,1: user
		user.P_Role = 1;
		// TODO: 获取用户IP
		user.P_IPAdd = "";
		user.P_CreateUser = signin.username;
		user.P_CreateTime = Utils.GetDateNowFormat();
		user.P_UpdateUser = signin.username;
		user.P_UpdateTime = Utils.GetDateNowFormat();
		
		
		userMapper.insert(user);
		
		
	}
	
	

	@Transactional
	public Boolean checkUserVaild(SigninModel signinModel) {
		QueryWrapper<UserEntity> condition = new QueryWrapper<>();
		condition.eq("P_UserName", signinModel.username).last("limit 1");
		Integer integer = userMapper.selectCount(condition);
		if(integer.equals(0)) {
			return true;
		}
		return false;
	}

	@Transactional
	public Boolean checkMailVaild(SigninModel signinModel) {
		QueryWrapper<UserEntity> condition = new QueryWrapper<>();
		condition.eq("P_MailAddres", signinModel.mailaddres).last("limit 1");
		Integer integer = userMapper.selectCount(condition);
		if(integer.equals(0)) {
			return true;
		}
		return false;
	}

	@Transactional
	public UserEntity getUserInfobyUUID(String UUID) {
		QueryWrapper<UserEntity> condition = new QueryWrapper<>();
		condition.eq("P_UUID", UUID).last("limit 1");
		return userMapper.selectOne(condition);
	}

	
	

}
