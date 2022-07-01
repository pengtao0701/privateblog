package com.privateblog.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("p_blog")
@Data
@SuppressWarnings("unused")
public class BlogEntity {
	
	@TableId(value = "P_Blog_ID", type = IdType.AUTO)
	public int P_Blog_ID;
		
	public String P_UUID;
	
	public String P_Blog_Title;
	
	public String P_Blog_Cover_Img;
	
	public String P_Blog_Synopsis;
	
	public String P_Blog_Content;
	
	public int P_Blog_Views;
	
	public int P_Blog_Comment_Count;
	
	public int P_Blog_Like_Count;
	
	public LocalDateTime P_Blog_Date;
	
	
}
