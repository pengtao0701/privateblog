package com.privateblog.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.power.common.model.CommonResult;
import com.privateblog.common.ErrorCodeEnum;
import com.privateblog.common.SuccessCodeEnum;
import com.privateblog.common.utils.FileUtils;
import com.privateblog.common.utils.Utils;
import com.privateblog.entity.BlogEntity;
import com.privateblog.model.BlogLabelModel;
import com.privateblog.model.EditBloguploadModel;
import com.privateblog.model.ImageUploadResult;
import com.privateblog.model.LabelModel;
import com.privateblog.model.UserInfoModel;
import com.privateblog.service.BaseApiService;
import com.privateblog.service.BlogLabelService;
import com.privateblog.service.BlogService;
import com.privateblog.service.BlogSortService;
import com.privateblog.service.LabelService;

@RestController
@RequestMapping("/api/Blog")
public class BlogController {

	private static final Logger LOGGER = LogManager.getLogger(BlogController.class);
	
	@Autowired(required = true)
	public BlogService blogService;
	@Autowired(required = true)
	public LabelService labelService;
	@Autowired(required = true)
	public BlogSortService blogSortService;
	@Autowired(required = true)
	public BlogLabelService blogLabelService;
	@Autowired(required = true)
	public BaseApiService baseApiService;
	
	
	
	/**
	 *  编辑博客保存API
	 * @return EditBlogUploadResult
	 */
	@PostMapping("/EditBlogSave")
	@ResponseBody
	public CommonResult<Object> EditBlogSave(@RequestBody EditBloguploadModel blogUpload, HttpServletRequest request){
		
		UserInfoModel userinfo = new UserInfoModel();
		String tokenID = request.getHeader("Authorization");
		userinfo = baseApiService.GetUserInfo(tokenID);
		
		// 保存博客
		blogUpload.UUID = userinfo.uuid;
		blogUpload.blogViews = 0;
		blogUpload.blogCommentCount = 0;
		blogUpload.blogLikeCount = 0;
		blogUpload.blogDate = Utils.GetDateNowFormat();
		blogService.InsertBlog(blogUpload);
		
		// 获得保存的博客
		List<BlogEntity> blog = blogService.GetBlogIDByName(blogUpload.blogTitle);
		
		if( blog.size() > 0 ) {
			for (BlogEntity blogEntity : blog) {
				blogUpload.blogID = blogEntity.P_Blog_ID;
			}
			
			// 保存博客分类
			blogSortService.insertSort(blogUpload);
			
			// 创建标签
			List<String> blogLabelList = Arrays.asList(blogUpload.blogLabelName.split(","));
			for (String labelName :  blogLabelList) {
				
				LabelModel labelmodel = new LabelModel();
				BlogLabelModel bloglabelmodel = new BlogLabelModel();
				
				labelmodel.labelName = labelName;
				if(labelService.checkLabelVaild(labelName)) {
					// 插入新规标签
					labelService.insertLabel(labelmodel);
					
					// 获取blogID
					bloglabelmodel.blogID = blogUpload.blogID;
					// 获取新规标签ID by name
					bloglabelmodel.labelID = labelService.getLabelIDbyName(labelName);
					// 保存博客标签
					blogLabelService.insertBlogLabel(bloglabelmodel);
				}else {
					// 获取blogID
					bloglabelmodel.blogID = blogUpload.blogID;
					// 获取新规标签ID by name
					bloglabelmodel.labelID = labelService.getLabelIDbyName(labelName);
					// 保存博客标签
					blogLabelService.insertBlogLabel(bloglabelmodel);
				}
				continue;
			}
			
		}else {
			return CommonResult.fail(ErrorCodeEnum.BlogSave_ERROR);
		}
		
		return CommonResult.ok(SuccessCodeEnum.PARAM_EMPTY);
	}
	
	
	/**
	 *  编辑博客更新API
	 * @return EditBlogUploadResult
	 */
	@GetMapping("/BlogUpdate")
	@ResponseBody
	public CommonResult<Object> BlogUpdate(@RequestBody EditBloguploadModel blogUpload){
		
		return CommonResult.ok(SuccessCodeEnum.PARAM_EMPTY);
	}
	
	/**
	 *  编辑博客删除API
	 * @return EditBlogUploadResult
	 */
	@GetMapping("/EditBlogDelete")
	@ResponseBody
	public CommonResult<Object> EditBlogDelete(@RequestBody EditBloguploadModel blogUpload){
		
		return CommonResult.ok(SuccessCodeEnum.PARAM_EMPTY);
	}
	
	
	
	/**
	 * 编辑博客图片上传API
	 * @param image
	 * @return
	 */
	@PostMapping(value = "/FileUpload", headers = "content-type=multipart/*")
	@ResponseBody
	public CommonResult<Object> FileUpload(@RequestParam("file") MultipartFile file){
		
		ImageUploadResult result = new ImageUploadResult();
		
		if(!file.isEmpty()) {
			LOGGER.info("上传图片名 :" + file.getOriginalFilename());
			try {
				result = FileUtils.ImgUpload(file);
				if(!result.fileName.isEmpty() && !result.urlPath.isEmpty()) {
					
					return CommonResult.ok(SuccessCodeEnum.PARAM_EMPTY).setResult(result);
				}else {
					return CommonResult.fail(ErrorCodeEnum.IMAGES_UPLOAD_ERROR);
				}
				
				
			}catch (Exception e) {
				return CommonResult.fail("500",e.getMessage());
			}
            
        }else {
        	return CommonResult.fail(ErrorCodeEnum.IMAGES_UPLOAD_ERROR);
		}
		
		
	}
}
