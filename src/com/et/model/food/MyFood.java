package com.et.model.food;

import java.util.List;
import java.util.Map;

import com.et.model.PageTools;

public class MyFood {
//	public Integer getFoodCount(){
//		String sql="select count(rowid) as rw from food";
//		List<Map> foodList =dbUtils.DbUtils.query(sql);
//		Integer count=Integer.parseInt(foodList.get(0).get("RW").toString());
//		return count;
//	}
	public Integer getFoodCount(String typeid){
		String sql="select count(rowid) as rw from food where 1=1";
		if(typeid!=null && !typeid.equals("")){
			sql=sql+" and typeid="+typeid;
		}
		
		List<Map> foodList =dbUtils.DbUtils.query(sql);
		Integer count=Integer.parseInt(foodList.get(0).get("RW").toString());
		return count;
	}
	
	public PageTools getFood(Integer curPage,String typeid,String foodName){
		Integer totalCount=getFoodCount(typeid);
		PageTools pt=new PageTools(curPage, 6, totalCount);
		String sql="select * from (select g.*,ft.typename,rownum rn from food g inner join foodtype ft on g.typeid=ft.typeid) where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		if(typeid!=null && !typeid.equals("")){
			sql="select * from (select g.*,ft.typename,rownum rn from food g inner join foodtype ft on g.typeid=ft.typeid where ft.typeid="+typeid+") where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		}else if(foodName!=null){
			sql="select * from (select g.*,ft.typename,rownum rn from food g inner join foodtype ft on g.typeid=ft.typeid where foodname like '%"+foodName+"%') where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		}
		
		List<Map> list=dbUtils.DbUtils.query(sql);
		pt.setData(list);
		return pt;
	}
	/**
	 * ²éÑ¯ÏêÏ¸²Ë
	 */
	public List<Map> getCaiXiangxi(String foodid){
		String sql="select * from food where foodid="+foodid;
		List<Map> list=dbUtils.DbUtils.query(sql);
		return list;
	}
	
}
















