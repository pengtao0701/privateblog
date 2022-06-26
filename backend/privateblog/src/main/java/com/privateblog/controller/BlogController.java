package com.privateblog.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.power.common.model.CommonResult;
import com.privateblog.common.ErrorCodeEnum;
import com.privateblog.common.SuccessCodeEnum;
import com.privateblog.common.utils.FileUtils;
import com.privateblog.model.ImageUploadResult;

@RestController
@RequestMapping("/api/Blog")
public class BlogController {

	private static final Logger LOGGER = LogManager.getLogger(BlogController.class);
	
	@PostMapping(value = "/fileUpload", headers = "content-type=multipart/*")
	@ResponseBody
	public CommonResult<Object> fileUpload(@RequestParam("images") MultipartFile image){
		
		ImageUploadResult result = new ImageUploadResult();
		
		if(!image.isEmpty()) {
			LOGGER.info("上传图片名 :" + image.getOriginalFilename());
			try {
				result = FileUtils.ImgUpload(image);
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
