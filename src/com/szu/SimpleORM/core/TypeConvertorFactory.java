package com.szu.SimpleORM.core;

/**
 * 数据库类型与封装数据bena之间的类型转换工厂
 *
 */
public class TypeConvertorFactory {
	private static String usingDB=DBManager.getConf().getUsingDB();
	
	public static TypeConvertor getTypeConvertor(){
		TypeConvertor typeConvertor=null;
		if(usingDB.equals("mysql")){
			typeConvertor=new MySqlTypeConvertor();
		}else if(usingDB.equals("oracel")){
			typeConvertor=new OracelTypeConvertor();
		}//后续自定义其他数据库类型转换
		return typeConvertor;
	}

}
