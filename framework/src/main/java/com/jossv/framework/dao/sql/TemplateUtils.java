package com.jossv.framework.dao.sql;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateUtils {

	static Pattern pattern = Pattern.compile("[$][{][\\w,\\.,\\s]+[}]");

	public static String complie(String template, Map<String, Object> params) {
		Matcher matcher = pattern.matcher(template);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
//			System.out.println(matcher.group(0));
			String a = matcher.group(0);
			String key = a.replace("${", "").replace("}", "").trim();
			if(!params.containsKey(key)) {
				throw new RuntimeException("sql:\n[" + template+ "] \nerror compile , can not found [${" + key + "}]");
			}
			matcher.appendReplacement(sb, params.get(key).toString());
		}
		matcher.appendTail(sb);
		// System.out.println(sb.toString());
		return sb.toString();
	}

}
