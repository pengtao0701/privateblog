package com.privateblog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.privateblog.entity.UserEntity;
import com.privateblog.mapper.UserMapper;

@SpringBootTest
public class mybatisplustest {
	@Autowired
	private UserMapper userMapper;

//	@Test
//	public void insert() {
//		// java.util.List<UserInfo> result = userMapper.selectList(null);
//		UserInfo insertInfo = new UserInfo();
//		insertInfo.P_UserName = "test001";
//		insertInfo.P_PassWord = "test001";
//		userMapper.insert(insertInfo);
//		// result.forEach(System.out::println);
//	}

	@Test
	public void select() {
		QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>();
		wrapper.eq("P_ID", 1);

		UserEntity result = userMapper.selectOne(wrapper);

		System.out.println(result);
	}
}
