package com.privateblog.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("p_blog_comments")
@Data
@SuppressWarnings("unused")
public class BlogCommentEntity {

	@TableId(value = "P_Comment_ID", type = IdType.AUTO)
	public int P_Comment_ID;

	public String P_UUID;
	
	public String P_Blog_ID;
	
	public int P_Comment_Like_Count;
	
	public LocalDateTime P_Comment_Date;
	
	public String P_Comment_Content;
	
	public int P_Parent_Comment_ID;
	
}
