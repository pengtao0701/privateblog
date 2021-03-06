package com.privateblog.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.privateblog.entity.BlogLabelEntity;
import com.privateblog.mapper.BlogLabelMapper;
import com.privateblog.model.BlogLabelModel;
import com.privateblog.service.BlogLabelService;

@Service
@Transactional
public class BlogLabelServiceImpl  extends ServiceImpl<BlogLabelMapper, BlogLabelEntity> implements BlogLabelService{

	@Autowired
	private BlogLabelMapper blogLabelMapper;

	@Override
	public void insertBlogLabel(BlogLabelModel blogLabelmodel) {
		
		BlogLabelEntity blogLabel = new BlogLabelEntity();
		
		blogLabel.P_Blog_ID = blogLabelmodel.blogID;
		blogLabel.P_Label_ID = blogLabelmodel.labelID;
			
		blogLabelMapper.insert(blogLabel);
	
		
		
	}
}
