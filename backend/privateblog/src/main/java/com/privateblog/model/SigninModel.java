package com.privateblog.model;

import lombok.Getter;
import lombok.Setter;

public class SigninModel {
	@Getter
	@Setter
	public String uuid;

	@Getter
	@Setter
	public String username;
	
	@Getter
	@Setter
	public String nickname;

	@Getter
	@Setter
	public String password;

	@Getter
	@Setter
	public String mailaddres;

	@Getter
	@Setter
	public String vcode;

}
