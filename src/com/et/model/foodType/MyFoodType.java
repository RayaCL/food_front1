package com.et.model.foodType;

import java.util.List;
import java.util.Map;

public class MyFoodType {
	/**
	 * œ‘ æ≤Àœµ
	 */
	public List<Map> getFoodTypeList(){
		String sql="select * from foodtype";
		return dbUtils.DbUtils.query(sql);
	}
}
