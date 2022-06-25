package com.privateblog.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.util.HtmlUtils;

public class Utils {

	// 新用户昵称
	public static String GetCreateNickName(String uuid ) {
		String nicktemp ="用户";
		return nicktemp + "_" + uuid.substring( 0, 6 );
	}
	
	
	// 获取UUID
	public static String GetUUID() {
		
		return "UUID" + "_" +  UUID.randomUUID().toString();
	}
	
	// HTML转Hex存储
	public String Escapehtml(String html) {
		return HtmlUtils.htmlEscapeHex(html);
	}

	// Hex存储转string型HTML
	public String Unescapehtml(String html) {
		return HtmlUtils.htmlUnescape(html);
	}

	// 获取当前时间LocalDateTime
	public static LocalDateTime GetDateNowFormat() {
		Date date = new Date();
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();

		return instant.atZone(zoneId).toLocalDateTime();
	}

	// LocalDateTime 转 Date
	public Date GetDateNowFormat(LocalDateTime localDatetime) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDatetime.atZone(zoneId);

		return Date.from(zdt.toInstant());
	}

	// Date 转 LocalDateTime
	public LocalDateTime Date2LocalDateTIme(Date date ) {
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();

		return instant.atZone(zoneId).toLocalDateTime();
	}

	// 判读邮箱合法
	public static boolean isEmail(String string) {
		if (string == null)
			return false;
		String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p;
		Matcher m;
		p = Pattern.compile(regEx1);
		m = p.matcher(string);
		if (m.matches())
			return true;
		else
			return false;
	}
}
