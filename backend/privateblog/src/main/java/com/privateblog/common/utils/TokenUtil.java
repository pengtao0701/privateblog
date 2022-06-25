package com.privateblog.common.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.privateblog.config.ConstantProperties;
import com.privateblog.model.UserInfoModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtil {

	private static final long EXPIRE_TIME = 10 * 60 * 60 * 1000;
	// 密钥
	private static final String PRIVATE_KEY = ConstantProperties.tokenPrivateKey;

	/**
	 * 签名生成
	 * 
	 * @param user
	 * @return
	 */
	public static String sign(UserInfoModel user) {
		String token = null;
		Map<String, Object> header = new HashMap<>();
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		Map<String, Object> claims = new HashMap<>();
		// 自定义有效载荷部分
		claims.put("account", user.uuid);
		token = Jwts.builder()
				// 发证人
				.setIssuer("auth")
				// Jwt头
				.setHeader(header)
				// 有效载荷
				.setClaims(claims)
				// 设定签发时间
				.setIssuedAt(new Date())
				// 设定过期时间
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
				// 使用HS256算法签名，PRIVATE_KEY为签名密钥
				.signWith(SignatureAlgorithm.HS256, PRIVATE_KEY).compact();
		return token;
	}

	/**
	 * 验证 token信息 是否正确
	 * 
	 * @param token 被解析 JWT
	 * @return 是否正确
	 */
	public static boolean verify(String token) {
		// 获取签名密钥
		// String key = userEntity.getUserPassword();
		// 获取DefaultJwtParser
		try {
			Jwts.parser()
					// 设置 密钥
					.setSigningKey(PRIVATE_KEY)
					// 设置需要解析的 token
					.parseClaimsJws(token).getBody();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
