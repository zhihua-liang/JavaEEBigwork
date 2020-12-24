<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="common_head">
	<div id="line1">
		<div id="content">
		
		  <c:if test="${ empty sessionScope.user }">
		 	<a href="${ pageContext.request.contextPath }/index/login">登录</a>	 &nbsp;	
			 <a href="${ pageContext.request.contextPath }/index/regist">注册</a>			 
		 </c:if>	
		 	 
		 <c:if test="${ !(empty sessionScope.user) }">
		 	 	"欢迎${ sessionScope.user.username }回来~~&nbsp;"
		 	<a href="${ pageContext.request.contextPath }/index/logout">退出</a>		 
		 </c:if>		
		</div>	
	</div>
	<div id="line2">
		<img id="logo" src="${pageContext.request.contextPath}/img/head/logo.jpg"/>
		<input type="text" name=""/>
		<input type="button" value="搜 索"/>
		<span id="goto">
			<a id="goto_order" href="${pageContext.request.contextPath}/order/showorder">我的订单</a>
			<a id="goto_cart" href="${pageContext.request.contextPath}/cart/showCart">我的购物车</a>
		</span>
		<img id="erwm" src="${ pageContext.request.contextPath }/img/head/qr.jpg"/>
	</div>
	<div id="line3">
		<div id="content">
			<ul>
				<li><a href="${pageContext.request.contextPath}/index">首页</a></li>
				<li><a href="${pageContext.request.contextPath}/prodlist">全部商品</a></li>
				<li><a href="${pageContext.request.contextPath}/prodclass/手机数码">手机数码</a></li>
				<li><a href="${pageContext.request.contextPath}/prodclass/电脑平板">电脑平板</a></li>
				<li><a href="${pageContext.request.contextPath}/prodclass/家用电器">家用电器</a></li>
				<li><a href="${pageContext.request.contextPath}/prodclass/汽车用品">汽车用品</a></li>
				<li><a href="${pageContext.request.contextPath}/prodclass/食品饮料">食品饮料</a></li>
				<li><a href="${pageContext.request.contextPath}/prodclass/图书杂志">图书杂志</a></li>
				<li><a href="${pageContext.request.contextPath}/prodclass/服装服饰">服装服饰</a></li>
				<li><a href="${pageContext.request.contextPath}/prodclass/理财产品">理财产品</a></li>
			</ul>
		</div>
	</div>
</div>