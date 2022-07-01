package com.privateblog.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.privateblog.entity.BlogCommentEntity;
import com.privateblog.mapper.BlogCommentMapper;
import com.privateblog.service.BlogCommentService;

@Service
@Transactional
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogCommentEntity> implements BlogCommentService{

	@Autowired
	private BlogCommentMapper blogCommentMapper;
}
