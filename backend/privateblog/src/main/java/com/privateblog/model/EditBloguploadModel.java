package com.privateblog.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public class EditBloguploadModel {

	@Getter
	@Setter
	// 用户UUID
	public String UUID;
	
	public int blogID;
	
	@Getter
	@Setter
	// 博客标题
	public String blogTitle;
	
	@Getter
	@Setter
	// 博客封面图
	public String coverimg;
	
	@Getter
	@Setter
	// 博客简介
	public String synopsis;
	
	@Getter
	@Setter
	// 博客内容
	public String blogContent;
	
	@Getter
	@Setter
	// 分类
	public int blogSortID;
	
	@Getter
	@Setter
	// 标签
	public String blogLabelName;
	
	@Getter
	@Setter
	// 博客浏览量
	public int blogViews;
	
	@Getter
	@Setter
	// 博客评论数
	public int blogCommentCount;
	
	@Getter
	@Setter
	// 博客点赞数
	public int blogLikeCount;
	
	@Getter
	@Setter
	// 博客创作日期
	public LocalDateTime blogDate;
}
