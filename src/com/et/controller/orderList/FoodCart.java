 package com.et.controller.orderList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.et.model.order.MyOrder;

/**
 * Servlet implementation class FoodCart
 */
public class FoodCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodCart() {
        super();
        // TODO Auto-generated constructor stub
    }
    MyOrder order=new MyOrder();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		request.setCharacterEncoding("UTF-8");
		String flag=request.getParameter("flag");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		if("delete".equals(flag)){
			String fid=request.getParameter("cid");
			session.removeAttribute(fid);
			int sum = 0;
			Enumeration en  = session.getAttributeNames();
			while(en.hasMoreElements()){
				String key = (String) en.nextElement();
				if(key.startsWith("cart_")){
					Map map = (Map) session.getAttribute(key);
					int countTmp = Integer.parseInt(map.get("count").toString());
					int priceTmp = Integer.parseInt(map.get("price").toString());
					sum = sum+countTmp*priceTmp;
				}
			}
			session.setAttribute("zong", sum);
			request.getRequestDispatcher("/detail/clientCart.jsp").forward(request, response);
		}else if("order".equals(flag)){
			String deskId=(String) session.getAttribute("deskId");
			Integer sum=(Integer) session.getAttribute("zong");
			int state=0;
			Integer orderId=order.saveOrder(deskId, state,sum);
			//循环session中所有的见 cart_1
			Enumeration<String> keys=session.getAttributeNames();
			while(keys.hasMoreElements()){
				String key=keys.nextElement();
				if(key.startsWith("cart_")){
					String fid=key.split("cart_")[1];
					Map map=(Map) session.getAttribute(key);
					Integer count=(Integer) map.get("count");
					order.saveOrderDetail(orderId, Integer.parseInt(fid), count);
				}
				
			}
			session.invalidate();
			
			out.print("<script>alert('下单成功');</script>");
			
			response.setHeader("refresh", "1;url="+request.getContextPath()+"/ShowDeskServlet");
		}else{
			String foodId=request.getParameter("fid");
			String foodName=request.getParameter("fname");
			String price=request.getParameter("price");
			//当前菜品第一次被加入餐车 session不存在 添加菜品session中数量为1
			
			Object food =session.getAttribute("cart_"+foodId);
			
			//第一次被加入
			if(food==null){
				Map map=new HashMap();
				map.put("foodName", foodName);
				map.put("price", price);
				map.put("count", 1);
				session.setAttribute("cart_"+foodId, map);
				
			}else{
				Map map=(Map) session.getAttribute("cart_"+foodId);
				Integer in=(Integer)map.get("count");
				map.put("count", in+1);
				//分布式环境
				
				session.setAttribute("cart_"+foodId, map);
			}
			int sum = 0;
			Enumeration en  = session.getAttributeNames();
			while(en.hasMoreElements()){
				String key = (String) en.nextElement();
				if(key.startsWith("cart_")){
					Map map = (Map) session.getAttribute(key);
					int countTmp = Integer.parseInt(map.get("count").toString());
					int priceTmp = Integer.parseInt(map.get("price").toString());
					sum = sum+countTmp*priceTmp;
				}
			}
			session.setAttribute("zong", sum);
			request.getRequestDispatcher("/detail/clientCart.jsp?").forward(request, response);
		}
		
		
		
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
