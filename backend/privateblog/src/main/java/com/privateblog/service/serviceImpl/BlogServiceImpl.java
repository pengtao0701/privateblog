package com.privateblog.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.privateblog.entity.BlogEntity;
import com.privateblog.mapper.BlogMapper;
import com.privateblog.model.EditBloguploadModel;
import com.privateblog.service.BlogService;

@Service
@Transactional
public class BlogServiceImpl  extends ServiceImpl<BlogMapper, BlogEntity> implements BlogService{

	@Autowired
	private BlogMapper blogMapper;
	
	@Transactional
	public void InsertBlog(EditBloguploadModel blogUpload) {
		BlogEntity blogEntity = new BlogEntity();
		blogEntity.P_UUID = blogUpload.UUID;
		blogEntity.P_Blog_Title = blogUpload.blogTitle;
		blogEntity.P_Blog_Content = blogUpload.blogContent;
		blogEntity.P_Blog_Cover_Img = blogUpload.coverimg;
		blogEntity.P_Blog_Like_Count = blogUpload.blogLikeCount;
		blogEntity.P_Blog_Views = blogUpload.blogViews;
		blogEntity.P_Blog_Comment_Count = blogUpload.blogCommentCount;
		blogEntity.P_Blog_Date = blogUpload.blogDate;
		
		blogMapper.insert(blogEntity);
	}

	@Transactional
	public List<BlogEntity> GetBlogIDByName(String blogname) {
		List<BlogEntity> result = new ArrayList<BlogEntity>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("P_Blog_Title", blogname);
		
		result = blogMapper.selectByMap(map);
		return result;
	}

}
