package com.szu.SimpleORM.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.szu.SimpleORM.core.Query;
import com.szu.SimpleORM.core.QueryFactory;
import com.szu.SimpleORM.po.Emp;

public class test {
	public static Query query=QueryFactory.createQuery();
	
	/**
	 * 测试删除
	 */
	public static void testDelete() {
		Emp e = new Emp();
		e.setEmpname("lily");
		e.setBirthday(new java.sql.Date(System.currentTimeMillis()));
		e.setAge(30);
		e.setSalary(3000.8);
		e.setId(9l);
		e.setBonus(1231.2);
		e.setDeptId(12);
		query.delete(e);
	}
	/**
	 * 测试插入
	 */
	public static void testInsert(){
		Emp e = new Emp();
		e.setEmpname("lily");
		e.setBirthday(new java.sql.Date(System.currentTimeMillis()));
		e.setAge(30);
		e.setSalary(3000.8);
		e.setId(9l);
		e.setBonus(1231.2);
		e.setDeptId(12);
		query.insert(e);
	}
	/**
	 * 测试更新
	 */
	/**
	 * 
	 */
	public static void testUpdate(){
		Emp e = new Emp();
		e.setEmpname("lily");
		e.setBirthday(new java.sql.Date(System.currentTimeMillis()));
		e.setAge(30);
		e.setSalary(3000.8);
		e.setId(2l);
		query.update(e,new String[]{"empname","age","salary"});
	}
	/**
	 * 测试查询多列
	 */
	@SuppressWarnings("unchecked")
	public static void testQueryRows(){
		List<Emp> list = query.queryRows("select id,empname,age from emp where age>? and salary<?",
				Emp.class, new Object[]{10,5000});
		
		for(Emp e:list){
			System.out.println(e.getEmpname());
		}
	}
	/**
	 *测试查询单列
	 */
	public static void testQueryUniqueRow(){
		Emp e=(Emp) query.queryUniqueRow("select id,empname,age from emp where age=? ", Emp.class,new Object[]{30});
		System.out.println(e);
	}
	
	/**
	 * 测试查询单行单列
	 */
	public static void testQueryValue(){
		Object object=query.queryValue("select empname  from emp where age=? ", new Object[]{30});
		System.out.println(object);
	}
	
	/**
	 * 测试查询单行单列(count等一般数字类型)
	 */
	public static void testQueryNumber(){
		Number number=query.queryNumber("select count(*) from emp", null);
		System.out.println(number);
	}
	public static void main(String[] args) {
		testInsert();
	}
	

}
