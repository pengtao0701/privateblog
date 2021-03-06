package com.privateblog.model;

import lombok.Getter;
import lombok.Setter;

public class UserInfoModel {

	@Getter
	@Setter
	// 唯一UUID
	public String uuid;

	@Getter
	@Setter
	// 用户昵称
	public String nickname;

	@Getter
	@Setter
	// 用户名
	public String username;

	@Getter
	@Setter
	// 邮箱
	public String mailaddres;
	
	@Getter
	@Setter
	// 用户头像
	public String userprofilephoto;
	
	@Getter
	@Setter
	// 用户介绍
	public String introduction;
	
	@Getter
	@Setter
	// 用户状态
	public int status;

	@Getter
	@Setter
	public String token;
	


}
