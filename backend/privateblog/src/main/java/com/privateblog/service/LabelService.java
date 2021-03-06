package com.privateblog.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.privateblog.entity.LabelEntity;
import com.privateblog.model.LabelModel;

@Repository
public interface LabelService extends IService<LabelEntity> {

	public List<LabelEntity> getAllLabel();
	
	public int getLabelIDbyName(String blogLabelName);
	
	public void insertLabel(LabelModel labelmodel);
	
	public boolean checkLabelVaild(String labelName);
}
