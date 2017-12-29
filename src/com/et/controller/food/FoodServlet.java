package com.et.controller.food;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.et.model.PageTools;
import com.et.model.food.MyFood;
import com.et.model.foodType.MyFoodType;

/**
 * Servlet implementation class FoodTypeServlet
 */
public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    MyFoodType foodType=new MyFoodType();
    MyFood mf=new MyFood();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String typeid="";
		if(request.getParameter("typeid")!=null){
			typeid=request.getParameter("typeid");
		}
		//当前点击餐桌编号存入session
		HttpSession session=request.getSession();
		if(request.getParameter("deskId")!=null){
			session.setAttribute("deskId", request.getParameter("deskId"));
		}
		//菜系
//		request.setAttribute("foodTypeList", foodType.getFoodTypeList());
//		System.out.println(foodType.getFoodTypeList());
		//菜单
		String curPage=request.getParameter("curPage");
		String foodName=request.getParameter("foodName");
		int curPageInt=1;
		if(curPage!=null){
			curPageInt=Integer.valueOf(curPage);
		}
		PageTools foodlist=mf.getFood(curPageInt,typeid,foodName);
		request.setAttribute("foodlist", foodlist);
		request.getRequestDispatcher("/detail/caidan.jsp").forward(request, response);
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
