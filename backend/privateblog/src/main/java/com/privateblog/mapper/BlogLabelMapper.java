package com.privateblog.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.privateblog.entity.BlogLabelEntity;

@Mapper
public interface BlogLabelMapper extends BaseMapper<BlogLabelEntity> {

}
