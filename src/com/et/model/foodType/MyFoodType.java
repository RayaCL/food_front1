package com.et.model.foodType;

import java.util.List;
import java.util.Map;

public class MyFoodType {
	/**
	 * ��ʾ��ϵ
	 */
	public List<Map> getFoodTypeList(){
		String sql="select * from foodtype";
		return dbUtils.DbUtils.query(sql);
	}
}
