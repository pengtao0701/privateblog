package com.privateblog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.power.common.model.CommonResult;
import com.privateblog.common.ErrorCodeEnum;
import com.privateblog.common.SuccessCodeEnum;
import com.privateblog.common.utils.TokenUtil;
import com.privateblog.common.utils.Utils;
import com.privateblog.common.utils.ValidateCodeUtil;
import com.privateblog.config.ConstantProperties;
import com.privateblog.entity.UserInfoEntity;
import com.privateblog.model.LoginModel;
import com.privateblog.model.SigninModel;
import com.privateblog.model.UserInfoModel;
import com.privateblog.service.UserService;

@RestController
@RequestMapping("/api/User")
public class UserController {

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@Autowired(required = true)
	private UserService userService;
	public UserInfoEntity userInfo;



	/**
	 *  用户登录API
	 * @param model
	 * @return
	 */
	@PostMapping("/Login")
	@ResponseBody
	public CommonResult<Object> Login(@RequestBody LoginModel model) {
		UserInfoModel result = new UserInfoModel();
		if (model.username != "" && model.password != "" && model.username != null && model.password != null) {
			// 判断是否为邮箱
			if (Utils.isEmail(model.username)) {
				model.mailaddres = model.username;
				model.username = "";
			}

			// 取得userinfo
			List<UserInfoEntity> userList = userService.getUserInfo(model);
			if (userList.size() > 0) {
				for (UserInfoEntity user : userList) {
					result.uuid = user.P_UUID;
					result.nickname = user.P_NickName;
					result.mailaddres = user.P_MailAddres;
					result.username = user.P_UserName;

				}
			} else {
				// CommonResult.ok().setResult(book);
				// 未找到该用户
				return CommonResult.fail(ErrorCodeEnum.USER_Not_Found_ERROR);
			}

			// 创建Token
			result.token = TokenUtil.sign(result);

		} else {
			// 用户名密码为空
			return CommonResult.fail(ErrorCodeEnum.LOGIN_ERROR);
		}
		return CommonResult.ok(SuccessCodeEnum.PARAM_EMPTY).setResult(result);
	}


	/**
	 *  用户注册API
	 * @param request
	 * @param signin
	 * @return
	 */
	@PostMapping("/Signin")
	@ResponseBody
	public CommonResult<Object> Signin(HttpServletRequest request, @RequestBody SigninModel signin) {

		HttpSession session = request.getSession();
		UserInfoModel result = new UserInfoModel();

		// 表单是否为空
		if (signin.equals(null) || signin.equals("") || 
				signin.username.equals(null) || signin.username.equals("") || 
				signin.password.equals(null) || signin.password.equals("") ||
				signin.mailaddres.equals(null) || signin.mailaddres.equals("") ||
				signin.vcode.equals(null) || signin.vcode.equals("") ) {
			return CommonResult.fail(ErrorCodeEnum.SIGNIN_FORM_ERROR);
		}
		
		// 判断是否为邮箱
		if (!Utils.isEmail(signin.mailaddres)) {
			return CommonResult.fail(ErrorCodeEnum.MAIL_ERROR);
		}
		
		// 校验验证码
		if (getCheckCaptcha(signin.vcode, session)) {
			return CommonResult.fail(ErrorCodeEnum.VCODE_ERROR);
		}

		// 验证用户名是否存在
		if(!userService.checkUserVaild(signin)) {
			return CommonResult.fail(ErrorCodeEnum.USER_ISEXIST_ERROR);
		}
		// 验证邮箱是否存在
		if(!userService.checkMailVaild(signin)) {
			return CommonResult.fail(ErrorCodeEnum.MAIL_ISEXIST_ERROR);
		}
		
		signin.uuid = Utils.GetUUID();
		signin.nickname = "未设置用户昵称";
		// 登录用户数据库
		userService.insertUserInfo(signin);

		// 创建Token
		result.token = TokenUtil.sign(result);

		// 获取用户信息
		LoginModel model = new LoginModel();
		model.username = signin.username;
		model.password = signin.password;
		List<UserInfoEntity> userList = userService.getUserInfo(model);
		if (userList.size() > 0) {
			for (UserInfoEntity user : userList) {
				result.uuid = user.P_UUID;
				result.nickname = user.P_NickName;
				result.mailaddres = user.P_MailAddres;
				result.username = user.P_UserName;
			}
		}else {
			return CommonResult.fail(ErrorCodeEnum.SIGNIN_INSERT_ERROR);
		}

		return CommonResult.ok(SuccessCodeEnum.PARAM_EMPTY).setResult(result);
	}

	/**
	 * 	  获取验证码API
	 * @param request
	 * @param response
	 * @param session
	 */
	@GetMapping("/Getvcode")
	@ResponseBody
	public void Getvcode(HttpServletRequest request, HttpServletResponse response) {

		try {

			response.setContentType("image/png");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expire", "0");
			response.setHeader("Pragma", "no-cache");
			ValidateCodeUtil validateCode = new ValidateCodeUtil();
			// getRandomCodeImage方法会直接将生成的验证码图片写入response
			validateCode.getRandomCodeImage(request, response);
			// System.out.println("session里面存储的验证码为："+session.getAttribute("JCCODE"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean getCheckCaptcha(String code, HttpSession session) {

		try {
			// toLowerCase() 不区分大小写进行验证码校验
			String sessionCode = String.valueOf(session.getAttribute(ConstantProperties.sessionVcode)).toLowerCase();
			System.out.println("session里的验证码："+sessionCode);
			String receivedCode = code.toLowerCase();
			System.out.println("用户的验证码："+receivedCode);
			return sessionCode.equals("") && !receivedCode.equals("") && sessionCode.equals(receivedCode);

		} catch (Exception e) {

			return false;
		}

	}
}
