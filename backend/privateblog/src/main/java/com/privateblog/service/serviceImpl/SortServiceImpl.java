package com.privateblog.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.privateblog.entity.SortEntity;
import com.privateblog.mapper.SortMapper;
import com.privateblog.service.SortService;

@Service
@Transactional
public class SortServiceImpl extends ServiceImpl<SortMapper, SortEntity> implements SortService {

	@Autowired
	private SortMapper sortMapper;
	
	
	@Transactional
	public List<SortEntity> getAllSort() {
		List<SortEntity> result = sortMapper.selectList(null);
		return result;
	}

	
}
