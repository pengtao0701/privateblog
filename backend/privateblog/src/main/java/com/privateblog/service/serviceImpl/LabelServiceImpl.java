package com.privateblog.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.privateblog.entity.LabelEntity;
import com.privateblog.mapper.LabelMapper;
import com.privateblog.model.LabelModel;
import com.privateblog.service.LabelService;

@Service
@Transactional
public class LabelServiceImpl extends ServiceImpl<LabelMapper, LabelEntity> implements LabelService {

	@Autowired
	private LabelMapper labelMapper;
	
	@Transactional
	public List<LabelEntity> getAllLabel() {
		
		// selectList(null)查询所有记录
		return labelMapper.selectList(null);
	}

	@Transactional
	public void insertLabel(LabelModel labelmodel) {
		LabelEntity labelEntity = new LabelEntity();
		labelEntity.P_Label_Name = labelmodel.labelName;
		labelEntity.P_Label_Alias = labelmodel.labelAlias;
		labelEntity.P_Label_Description = labelmodel.labelDescription;
		
		labelMapper.insert(labelEntity);
		
	}

	@Transactional
	public boolean checkLabelVaild(String labelName) {
		QueryWrapper<LabelEntity> condition = new QueryWrapper<>();
		condition.eq("P_Label_Name", labelName).last("limit 1");
		Integer integer = labelMapper.selectCount(condition);
		if(integer.equals(0)) {
			return true;
		}
		return false;
	}

	@Transactional
	public int getLabelIDbyName(String blogLabelName) {
		
		LabelEntity result = new LabelEntity();
		QueryWrapper<LabelEntity> wrapper = new QueryWrapper<>();
		wrapper.eq("P_Label_Name", blogLabelName).last("limit 1");
		result = getOne(wrapper);
		
		return result.P_Label_ID;
	}

}
