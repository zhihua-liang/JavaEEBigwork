<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>

	
		<div id="menu_bar">
			<div>
			<a href="${pageContext.request.contextPath}/admin/addprod">> 添加商品</a>
			</div>
			<div><a href="${ pageContext.request.contextPath }/showchart">> 商品统计</a></div>
			<div><a href="${pageContext.request.contextPath}/makeExcel">> 销售榜单</a></div>
			<div><a href="#">> 用户管理</a></div>
			<div><a href="#">> 权限管理</a></div>
			<div><a href="#">> 订单管理</a></div>
			<div><a href="${pageContext.request.contextPath}/index.jsp">> 返回前台</a></div>
		</div>		
		{message}
