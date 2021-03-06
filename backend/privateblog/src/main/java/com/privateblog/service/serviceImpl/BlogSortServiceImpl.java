package com.privateblog.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.privateblog.entity.BlogSortEntity;
import com.privateblog.mapper.BlogSortMapper;
import com.privateblog.model.EditBloguploadModel;
import com.privateblog.service.BlogSortService;

@Service
@Transactional
public class BlogSortServiceImpl extends ServiceImpl<BlogSortMapper, BlogSortEntity> implements BlogSortService {

	@Autowired
	private BlogSortMapper blogSortMapper;

	@Transactional
	public void insertSort(EditBloguploadModel blogUpload) {
		BlogSortEntity blogSortEntity  = new BlogSortEntity ();
		
		blogSortEntity.P_Blog_ID = blogUpload.blogID;
		blogSortEntity.P_Sort_ID = blogUpload.blogSortID;
		
		blogSortMapper.insert(blogSortEntity);
	}

	@Transactional
	public List<BlogSortEntity> getAllBlogSort() {
		return  blogSortMapper.selectList(null);
	}
}
