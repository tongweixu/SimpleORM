package com.szu.SimpleORM.po;

import java.sql.*;
import java.util.*;

public class Test {

	private Integer test2;
	private java.sql.Date test1;


	public Integer getTest2(){
		return test2;
	}
	public java.sql.Date getTest1(){
		return test1;
	}
	public void setTest2(Integer test2){
		this.test2=test2;
	}
	public void setTest1(java.sql.Date test1){
		this.test1=test1;
	}
}
