package com.privateblog.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 博客分类Model
 * @author pengt 
 * 
 */
public class SortModel {

	@Getter
	@Setter
	public int sortID;

	@Getter
	@Setter
	public String sortName;
	
	@Getter
	@Setter
	public String sortAlias;
	
	@Getter
	@Setter
	public String sortDescription;
	
	@Getter
	@Setter
	public int parentSortID;
}
