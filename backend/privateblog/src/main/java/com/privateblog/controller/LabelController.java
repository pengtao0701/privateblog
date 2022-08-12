package com.privateblog.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.power.common.model.CommonResult;
import com.privateblog.common.SuccessCodeEnum;
import com.privateblog.entity.LabelEntity;
import com.privateblog.model.GetAllLabelResultModel;
import com.privateblog.model.LabelModel;
import com.privateblog.service.LabelService;

@RestController
@RequestMapping("/api/Label")
public class LabelController {

	private static final Logger log = LogManager.getLogger(LabelController.class);
	
	@Autowired(required = true)
	public LabelService labelService;
	
	/**
	 *  取得所有分类API
	 * @return EditBlogInitShowResult
	 */
	@GetMapping("/GetAllLabel")
	@ResponseBody
	public CommonResult<Object> GetAllSort(){
		GetAllLabelResultModel result = new GetAllLabelResultModel();
		
		List<LabelEntity> labelEntity = labelService.getAllLabel();
		for (LabelEntity le : labelEntity) {
			LabelModel label = new LabelModel();
			label.labelID = le.P_Label_ID;
			label.labelName = le.P_Label_Name;
			label.labelAlias = le.P_Label_Alias;
			label.labelDescription = le.P_Label_Description;
			
			result.labelModel.add(label);
		}
		
		return CommonResult.ok(SuccessCodeEnum.PARAM_EMPTY).setResult(result);
	}
	
}
