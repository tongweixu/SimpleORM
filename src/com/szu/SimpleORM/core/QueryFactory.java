package com.szu.SimpleORM.core;

import com.szu.SimpleORM.bean.Configuration;

/**
 * 工厂模式
 * 根据配置信息返回对应数据库的Query对象
 */
public class QueryFactory {
	private static Configuration conf=DBManager.getConf(); //获取配置信息
	
	public static Query createQuery(){
		Query query=null;
		String usingDB=conf.getUsingDB();
		if(usingDB.equals("mysql")){
			query=new MySqlQuery();
		}else if(usingDB.equals("oracel")){
			query=new OracelSqlQuery();
		}//后续可自定义其他类型数据库
		return query;
	}
	
}
