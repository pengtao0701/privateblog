package com.privateblog.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.privateblog.entity.UserLoginLogEntity;

@Mapper
public interface UserLoginLogMapper  extends BaseMapper<UserLoginLogEntity>{

}
