package com.et.model.desk;

import java.util.List;
import java.util.Map;

public class MyShowTable {
	/**
	 *ÏÔÊ¾²Í×À·½·¨
	 */
	public List<Map> getTableListAll(){
		String sql="select * from desk";
		return dbUtils.DbUtils.query(sql);
	}
}
