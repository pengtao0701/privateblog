package com.privateblog.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.privateblog.entity.BlogCommentEntity;

@Mapper
public interface BlogCommentMapper extends BaseMapper<BlogCommentEntity>{

}
