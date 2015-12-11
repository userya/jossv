//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.12.11 时间 06:23:15 PM CST 
//


package com.jossv.model.table;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>columnType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="columnType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INTEGER"/>
 *     &lt;enumeration value="LONG"/>
 *     &lt;enumeration value="SHORT"/>
 *     &lt;enumeration value="FLOAT"/>
 *     &lt;enumeration value="DOUBLE"/>
 *     &lt;enumeration value="BOOLEAN"/>
 *     &lt;enumeration value="STRING"/>
 *     &lt;enumeration value="TIMESTAMP"/>
 *     &lt;enumeration value="BIG_DECIMAL"/>
 *     &lt;enumeration value="CLOB"/>
 *     &lt;enumeration value="BLOB"/>
 *     &lt;enumeration value="CODE"/>
 *     &lt;enumeration value="LONG_TEXT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "columnType")
@XmlEnum
public enum ColumnType {

    INTEGER,
    LONG,
    SHORT,
    FLOAT,
    DOUBLE,
    BOOLEAN,
    STRING,
    TIMESTAMP,
    BIG_DECIMAL,
    CLOB,
    BLOB,
    CODE,
    LONG_TEXT;

    public String value() {
        return name();
    }

    public static ColumnType fromValue(String v) {
        return valueOf(v);
    }

}
