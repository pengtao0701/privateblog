package com.privateblog.model;

import lombok.Getter;
import lombok.Setter;


/**
 *  标签Model
 * @author pengt
 * 
 */
public class LabelModel {

	@Getter
	@Setter
	public int labelID;
	
	@Getter
	@Setter
	public String labelName;
	
	@Getter
	@Setter
	public String labelAlias;
	
	@Getter
	@Setter
	public String labelDescription;
}
