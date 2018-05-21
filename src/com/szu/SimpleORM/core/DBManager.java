package com.szu.SimpleORM.core;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.szu.SimpleORM.bean.Configuration;

/**
 * 根据配置信息，维持连接对象的管理(增加连接池功能)
 * @author Administrator
 *
 */
public class DBManager {
	private static Configuration conf; //配置信息对象
	private static DBConnPool pool;    //数据库连接池
	
	static {  //静态代码块
		Properties pros = new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		conf = new Configuration();
		conf.setDriver(pros.getProperty("driver"));
		conf.setPoPackage(pros.getProperty("poPackage"));
		conf.setPwd(pros.getProperty("pwd"));
		if(pros.getProperty("srcPath").equals("") || pros.getProperty("srcPath")==null){//获取当前工作空间src绝对路径
			File file=new File(".\\src");
			String srcPath="";
			try {
				srcPath=file.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
			conf.setSrcPath(srcPath);
		}else{
			conf.setSrcPath(pros.getProperty("srcPath"));
		}
		conf.setUrl(pros.getProperty("url"));
		conf.setUser(pros.getProperty("user"));
		conf.setUsingDB(pros.getProperty("usingDB"));
		conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("maxPoolSize")));
		conf.setPoolMinSize(Integer.parseInt(pros.getProperty("minPoolSize")));
		
	}
	
	public static Connection createConn(){
		try {
			Class.forName(conf.getDriver());
			return DriverManager.getConnection(conf.getUrl(),
					conf.getUser(),conf.getPwd());     
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Connection getConn(){
		if(pool==null){
			pool=new DBConnPool();
		}
		return pool.getConnection();
	}
	
	
	public static void close(ResultSet rs,Statement ps,Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(ps!=null){
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement ps,Connection conn){
		try {
			if(ps!=null){
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Connection conn){
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回Configuration对象
	 * @return
	 */
	public static Configuration getConf(){
		return conf;
	}
	
	
}
