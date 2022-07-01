package com.privateblog.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.privateblog.entity.BlogEntity;
import com.privateblog.model.EditBloguploadModel;

@Repository
public interface BlogService  extends IService<BlogEntity>{
	
	public void InsertBlog(EditBloguploadModel blogUpload);
	
	public List<BlogEntity> GetBlogIDByName(String blogname);
}
