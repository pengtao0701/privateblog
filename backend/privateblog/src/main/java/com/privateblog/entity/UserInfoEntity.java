package com.privateblog.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("p_user")
@Data
@SuppressWarnings("unused")
public class UserInfoEntity {

	@TableId(value = "P_ID", type = IdType.AUTO)
	public int P_ID;

	public String P_UUID;

	public String P_NickName;

	public String P_UserName;

	public String P_PassWord;

	public String P_MailAddres;

	public int P_Status;

	public int P_Role;

	public String P_IPAdd;

	public String P_CreateUser;

	public LocalDateTime P_CreateTime;

	public String P_UpdateUser;

	public LocalDateTime P_UpdateTime;
}
