package com.privateblog.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class GetAllLabelResultModel {

	@Getter
	@Setter
	// 标签
	public List<LabelModel> labelModel = new ArrayList<LabelModel>();
}
