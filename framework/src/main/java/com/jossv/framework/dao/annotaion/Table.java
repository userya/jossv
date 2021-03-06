package com.jossv.framework.dao.annotaion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
	
	String name() ;
	
//	String tableName() default "";
	
	String pkColumnName() default "id";
	
	String label() default "";
	
//	String alias() default "";
	
}
