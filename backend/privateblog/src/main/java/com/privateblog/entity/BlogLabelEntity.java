package com.privateblog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("p_blog_label")
@Data
@SuppressWarnings("unused")
public class BlogLabelEntity {
	
	@TableId(value = "P_Blog_ID")
	public int P_Blog_ID;

	@TableId(value = "P_Label_ID")
	public int P_Label_ID;
	
}
