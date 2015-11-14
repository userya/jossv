package com.jossv.framework.dao.annotaion;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.jossv.framework.dao.type.ColumnType;

@Target({ METHOD, FIELD })
@Retention(RUNTIME)
public @interface Column {

	String name() default ""; //columnname

	ColumnType type() default ColumnType.STRING;

	boolean unique() default false;

	boolean nullable() default true;

	int length() default 255;

	int precision() default 0;

	int scale() default 0;

	String label() default "";

//	String columnName() default "";

	String codeNumber() default "";

	String dateFormat() default "";

}
