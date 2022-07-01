package com.privateblog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("p_blog_sort")
@Data
@SuppressWarnings("unused")
public class BlogSortEntity {
	
	@TableId(value = "P_Sort_ID")
	public int P_Sort_ID;
	
	public int P_Blog_ID;

}
