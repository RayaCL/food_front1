package com.et.model.order;

import java.util.List;
import java.util.Map;

public class MyOrder {
	public Integer getOrderId(){
		String sql="select nvl(max(orderid),0)+1 as myid from foodorder";
		List<Map> foodList =dbUtils.DbUtils.query(sql);
		Integer count=Integer.parseInt(foodList.get(0).get("MYID").toString());
		return count;
	}
	/**
	 * ≤Â»Î∂©µ•
	 * @param deskId
	 * @param state
	 * @return
	 */
	public Integer saveOrder(String deskId,int state,int zong){
		Integer orderId=getOrderId();
		String sql="insert into foodorder values("+orderId+","+Integer.parseInt(deskId)+",sysdate,"+state+","+zong+")";
		dbUtils.DbUtils.execute(sql);
		
		return orderId;
	}
	/**
	 * œÍœ∏∂©µ•
	 */
	public void saveOrderDetail(Integer orderId,Integer foodId,Integer count){
			String sql="insert into foodorderdetail values((select nvl(max(detailid),0)+1 as myid from foodorderdetail)"
					+ ",'"+orderId+"','"+foodId+"','"+count+"')";
			dbUtils.DbUtils.execute(sql);
			
		
	}
	












}
