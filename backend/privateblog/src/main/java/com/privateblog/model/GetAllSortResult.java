package com.privateblog.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 取得所有分类返回model
 * @author pengt
 */
public class GetAllSortResult {

	@Getter
	@Setter
	
	// 分类
	public List<SortModel> sortModel = new ArrayList<SortModel>();
	
}
