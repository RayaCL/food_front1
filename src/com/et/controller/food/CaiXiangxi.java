package com.et.controller.food;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.food.MyFood;
import com.et.model.foodType.MyFoodType;

/**
 * Servlet implementation class CaiXiangxi
 */
public class CaiXiangxi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaiXiangxi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    MyFood mf=new MyFood();
    MyFoodType foodType=new MyFoodType();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//²ËÏµ
		String typeid="";
		if(request.getParameter("typeid")!=null){
			typeid=request.getParameter("typeid");
		}
//		request.setAttribute("foodTypeList", foodType.getFoodTypeList());
//		System.out.println(foodType.getFoodTypeList());
		//²ËÏêÏ¸
		String foodid=request.getParameter("foodid");
		List<Map> list=mf.getCaiXiangxi(foodid);
		request.setAttribute("list", list);
		request.getRequestDispatcher("detail/caixiangxi.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
