package com.privateblog.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("p_user_login_log")
@Data
@SuppressWarnings("unused")
public class UserLoginLogEntity {
	@TableId(value = "P_ID", type = IdType.AUTO)
	public int P_ID;

	public String P_UUID;

	public String P_Token;
	
	public String P_IP_Addres;
	
	public String P_IP_Posation;
	
	public LocalDateTime P_Last_Login_Date;

}
