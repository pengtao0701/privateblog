package com.privateblog.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.privateblog.entity.BlogSortEntity;
import com.privateblog.model.EditBloguploadModel;

@Repository
public interface BlogSortService  extends IService<BlogSortEntity>{

	public void insertSort(EditBloguploadModel blogUpload);

	public List<BlogSortEntity> getAllBlogSort();

}
