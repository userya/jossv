package com.jossv.framework.dao.sql;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.SqlParameterValue;

public class ParamUtils {
	
	static Pattern pattern = Pattern.compile(":[\\w\\.]+");

	public static String complie(String template, Map<String, SqlParameterValue> params, List<Object> list) {
		Matcher matcher = pattern.matcher(template);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			System.out.println(matcher.group(0));
			String a = matcher.group(0);
			String key = a.replace(":", "").trim();
			if(!params.containsKey(key)) {
				throw new RuntimeException("sql:\n["+ template + "]\n error compile , can not found [" + key + "]");
			}
			list.add(params.get(key));
			matcher.appendReplacement(sb, "?");
		}
		matcher.appendTail(sb);
		// System.out.println(sb.toString());
		return sb.toString();
	}
	
	
	
	public static void main(String[] args) {
		
	}
	
	
}
