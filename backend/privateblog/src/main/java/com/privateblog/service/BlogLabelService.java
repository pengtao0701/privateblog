package com.privateblog.service;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.privateblog.entity.BlogLabelEntity;
import com.privateblog.model.BlogLabelModel;

@Repository
public interface BlogLabelService extends IService<BlogLabelEntity>{

	public void insertBlogLabel(BlogLabelModel blogLabelmodel);

}
