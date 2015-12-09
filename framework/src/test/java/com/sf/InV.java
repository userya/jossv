package com.sf;

public class InV {

	public String hello(Object o) {

		System.out.println("hello");
		return "header";
	}

	public String before(Object o) {

		return "<replaced header>";
		
	}

	public String after(Object o) {
		return "</replaced header>";
	}
}
