package com.jossv.framework.dao.sql;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
//		Pattern pattern = Pattern.compile("[$][{][\\w,\\.]+[}]");
//		Map<String, String> p = new HashMap<String, String>();
//		p.put("a.b", "a_b");
//		p.put("ac.s", "ac_s");
//		
//		Matcher matcher = pattern.matcher("正则表达式\" $${a.b}, ${ac.s}1 正则表{aa}达式 Hello World");
//		StringBuffer sb = new StringBuffer();
//		//替换第一个符合正则的数据
//		while(matcher.find()) {
//			System.out.println(matcher.group(0));
//			String a = matcher.group(0);
//			matcher.appendReplacement(sb, p.get(a.replace("${", "").replace("}", "")));
//		}
//		matcher.appendTail(sb);
//		
//		System.out.println(sb.toString());
//		
		
		
		
		Pattern pattern = Pattern.compile(":[\\w]+");
		Map<String, String> p = new HashMap<String, String>();
		p.put("a.b", "a_b");
		p.put("ac.s", "ac_s");
		
		Matcher matcher = pattern.matcher(":a正则表达式\" $${a.b}, ${ac.s}1 正:x则表{aa}达式 Hello World :aa 11");
		StringBuffer sb = new StringBuffer();
		//替换第一个符合正则的数据
		System.out.println(matcher.replaceAll("?"));
		while(matcher.find()) {
			System.out.println(matcher.group(0));
			String a = matcher.group(0);
			matcher.appendReplacement(sb, p.get(a.replace("${", "").replace("}", "")));
		}
		matcher.appendTail(sb);
		
		System.out.println(sb.toString());
	}
	
}
