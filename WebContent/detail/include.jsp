<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="mft" class="com.et.model.foodType.MyFoodType"></jsp:useBean>
<c:forEach var="v" items="${pageScope.mft.foodTypeList}"> 
		<li>
			<a href="${pageContext.request.contextPath}/FoodServlet?typeid=${pageScope.v.TYPEID}">${pageScope.v.TYPENAME}</a>
		</li>
</c:forEach>