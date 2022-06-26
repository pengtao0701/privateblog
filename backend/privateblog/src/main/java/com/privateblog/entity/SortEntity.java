package com.privateblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("p_sort")
@Data
@SuppressWarnings("unused")
public class SortEntity {
	
	@TableId(value = "P_Sort_ID", type = IdType.AUTO)
	public int P_Sort_ID;
	
	public String P_Sort_Name;
	
	public String P_Sort_Alias;
	
	public String P_Sort_Description;
	
	public int P_Parent_Sort_ID;
}
