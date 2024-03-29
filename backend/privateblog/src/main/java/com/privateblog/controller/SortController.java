package com.privateblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.power.common.model.CommonResult;
import com.privateblog.common.SuccessCodeEnum;
import com.privateblog.entity.SortEntity;
import com.privateblog.model.GetAllSortResultModel;
import com.privateblog.model.SortModel;
import com.privateblog.service.SortService;

@RestController
@RequestMapping("/api/Sort")
public class SortController {
	
	@Autowired(required = true)
	public SortService sortService;

	/**
	 *  取得所有分类API
	 * @return EditBlogInitShowResult
	 */
	@GetMapping("/GetAllSort")
	@ResponseBody
	public CommonResult<Object> GetAllSort(){
		GetAllSortResultModel result = new GetAllSortResultModel();
		
		
		List<SortEntity> sortEntity = sortService.getAllSort();
		for (SortEntity se : sortEntity) {
			SortModel sort = new SortModel();
			sort.sortID = se.P_Sort_ID;
			sort.sortName = se.P_Sort_Name;
			sort.sortAlias = se.P_Sort_Alias;
			sort.sortDescription = se.P_Sort_Description;
			sort.parentSortID = se.P_Parent_Sort_ID;
			
			result.sortModel.add(sort);
		}
		
		return CommonResult.ok(SuccessCodeEnum.PARAM_EMPTY).setResult(result);
	}
}
