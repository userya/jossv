package com.jossv.framework.dao.type;

import java.util.HashMap;
import java.util.Map;

import com.jossv.framework.dao.type.impl.BigDecimalTypeHandler;
import com.jossv.framework.dao.type.impl.BlobTypeHandler;
import com.jossv.framework.dao.type.impl.BooleanTypeHandler;
import com.jossv.framework.dao.type.impl.ClobTypeHandler;
import com.jossv.framework.dao.type.impl.CodeTypeHandler;
import com.jossv.framework.dao.type.impl.IntegerTypeHandler;
import com.jossv.framework.dao.type.impl.LongTextTypeHandler;
import com.jossv.framework.dao.type.impl.LongTypeHandler;
import com.jossv.framework.dao.type.impl.StringTypeHandler;
import com.jossv.framework.dao.type.impl.TimestampTypeHandler;

public class TypeHandlerUtils {

	private static Map<ColumnType, TypeHandler> handlerMap = new HashMap<ColumnType, TypeHandler>();

	static
	{
		handlerMap.put(ColumnType.BIG_DECIMAL, new BigDecimalTypeHandler());
		handlerMap.put(ColumnType.BLOB, new BlobTypeHandler());
		handlerMap.put(ColumnType.BOOLEAN, new BooleanTypeHandler());
		handlerMap.put(ColumnType.CLOB, new ClobTypeHandler());
		handlerMap.put(ColumnType.INTEGER, new IntegerTypeHandler());
		handlerMap.put(ColumnType.LONG, new LongTypeHandler());
		handlerMap.put(ColumnType.STRING, new StringTypeHandler());
		handlerMap.put(ColumnType.TIMESTAMP, new TimestampTypeHandler());
		handlerMap.put(ColumnType.CODE, new CodeTypeHandler());
		handlerMap.put(ColumnType.LONG_TEXT, new LongTextTypeHandler());
	}

	public static TypeHandler getTypeHandler(ColumnType type) {
		return handlerMap.get(type);
	}

}
