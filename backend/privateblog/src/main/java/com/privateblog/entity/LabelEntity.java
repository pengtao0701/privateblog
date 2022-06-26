package com.privateblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@TableName("p_label")
@Data
@SuppressWarnings("unused")
public class LabelEntity {

	@TableId(value = "P_Label_ID", type = IdType.AUTO)
	public int P_Label_ID;
	
	public String P_Label_Name;
	
	public String P_Label_Alias;
	
	public String P_Label_Description;
	
	
}
