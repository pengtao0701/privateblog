package com.privateblog.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.privateblog.entity.SortEntity;

@Repository
public interface SortService  extends IService<SortEntity> {

	public List<SortEntity> getAllSort();
}
