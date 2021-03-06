package com.privateblog.service.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.privateblog.entity.UserLoginLogEntity;
import com.privateblog.mapper.UserLoginLogMapper;
import com.privateblog.service.UserLoginLogService;

@Service
@Transactional
public class UserLoginLogSereviceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLogEntity> implements UserLoginLogService{

	@Autowired
	private UserLoginLogMapper UserLoginLogMapper;

	@Transactional
	public void insertUserLoginLog(UserLoginLogEntity loginEntity) {
		
		UserLoginLogMapper.insert(loginEntity);
	}

	@Transactional
	public UserLoginLogEntity selectLogbyToken(UserLoginLogEntity loginEntity) {
		QueryWrapper<UserLoginLogEntity> wrapper = new QueryWrapper<>();
		wrapper.eq("P_Token", loginEntity.P_Token).last("limit 1");
		return UserLoginLogMapper.selectOne(wrapper);
	}

	@Transactional
	public void deleteUserLoginLog(UserLoginLogEntity loginEntity) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("P_UUID", loginEntity.P_UUID);
		UserLoginLogMapper.deleteByMap(map);
		
	}

	@Transactional
	public boolean checkLoginLogVaild(UserLoginLogEntity loginEntity) {
		QueryWrapper<UserLoginLogEntity> condition = new QueryWrapper<>();
		condition.eq("P_UUID", loginEntity.P_UUID).last("limit 1");
		Integer integer = UserLoginLogMapper.selectCount(condition);
		if(integer.equals(0)) {
			return true;
		}
		return false;
	}
	
	
}
