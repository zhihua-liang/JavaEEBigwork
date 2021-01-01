<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>商品管理</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style type="text/css">
	body{
		font-family: "微软雅黑";
		background-color: #EDEDED;
	}
	h2{
		text-align: center;
	}
	table{ 
		margin: 0 auto; 
		/* width: 96%; */
		text-align: center;
		border-collapse:collapse;
	}
	td, th{ padding: 7px;}
	th{
		background-color: #DCDCDC;
	}
	th.ths{
		width: 100px;
	} 
	input.pnum{
		width:80px;
		height:25px;
		font-size: 18px;
		text-align:center;
	}
	
</style>

<!--引入jquery的js库-->
<script src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<h2>商品管理</h2>
	<table border="1">
		<tr>
			<th>商品图片</th>
			<th width="200px">商品ID</th>
			<th class="ths">商品名称</th>
			<th class="ths">商品种类</th>
			<th class="ths">商品单价</th>
			<th class="ths">库存数量</th>
			<th>描述信息</th>
			<th width="50px">操 作</th>
		</tr>

		<!-- 模版数据 -->
		<tr>
			<td>
				<img width="120px" height="120px" src="../pics/1.jpg" alt="" >
			</td>
			<td>a7184417-5aa2-4de0-8237-a4c0f53972a1</td>
			<td>三坑手机</td>
			<td>电子数码</td>
			<td>3000.0</td>
			<td>
				<input type="text" id="#" class="pnum" value="188"/>
			</td>
			<td>三坑手机, 专业坑人三十年, 从未被超越...!!!</td>
			<td><a class="del" href="javascript:void(0)">删 除</a></td>
		</tr>
		<tr>
			<td>
				<img width="120px" height="120px" src="../pics/1.jpg" alt="" >
			</td>
			<td>a7184417-5aa2-4de0-8237-a4c0f53972a1</td>
			<td>三坑手机</td>
			<td>电子数码</td>
			<td>3000.0</td>
			<td>
				<input type="text" id="#" class="pnum" value="188"/>
			</td>
			<td>三坑手机, 专业坑人三十年, 从未被超越...!!!</td>
			<td><a class="del" href="javascript:void(0)">删 除</a></td>
		</tr>
		<tr>
			<td>
				<img width="120px" height="120px" src="../pics/1.jpg" alt="" >
			</td>
			<td>a7184417-5aa2-4de0-8237-a4c0f53972a1</td>
			<td>三坑手机</td>
			<td>电子数码</td>
			<td>3000.0</td>
			<td>
				<input type="text" id="#" class="pnum" value="188"/>
			</td>
			<td>三坑手机, 专业坑人三十年, 从未被超越...!!!</td>
			<td><a class="del" href="javascript:void(0)">删 除</a></td>
		</tr>
	</table>
</body>
</html>
