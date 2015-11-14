package com.jossv.framework.dao;

import java.util.HashMap;

public class BaseObject extends HashMap<String, Object> {

	private static final long serialVersionUID = 6722718231506045499L;

	/**
	 * @param el  a.b.c  a 
	 * @param value 
	 */
	public void setValue(String el, Object value) {
		BaseObject toPut = this;
		int fromIndex = 0; 
		int index = el.indexOf('.');
		String name = el;
		while(index != -1) {
			String p = el.substring(fromIndex, index);
			BaseObject o = (BaseObject)toPut.get(p);
			if(o == null) {
				o = new BaseObject();
				toPut.put(p, o);
			}
			fromIndex = index + 1;
			toPut = o;
			name = el.substring(fromIndex);
			index = el.indexOf('.', index + 1);
			
		}
		toPut.put(name, value);
	}
	
	public Object getValue(String el) {
		BaseObject toPut = this;
		int fromIndex = 0;
		int index = el.indexOf('.');
		String name = el;
		while (index != -1) {
			String p = el.substring(fromIndex, index);
			BaseObject o = (BaseObject) toPut.get(p);
			if (o == null) {
				return null;
			}
			fromIndex = index + 1;
			toPut = o;
			name = el.substring(fromIndex);
			index = el.indexOf('.', index + 1);
		}
		return toPut.get(name);
	}
	
	
	public static void main(String[] args) {
		BaseObject n = new BaseObject();
		n.setValue("a", "1");
		n.setValue("b.c", 222);
		n.setValue("b.e.d", "GGG");
		n.setValue("b.e.F", "GGGGGDD");
		System.out.println(n);
	}

}
