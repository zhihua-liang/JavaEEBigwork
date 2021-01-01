<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<title>商品分析</title>
<script src="${ pageContext.request.contextPath }/js/echarts.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
	/* 文档就绪函数 */
	$(function() {
		/*
			发起ajax请求获取商品数据，然后加以处理用图表显示		
		*/
		$.ajax({
			type: "post",
			url : "${ pageContext.request.contextPath }/getProducts",
			dataType : "json",
			success : function(data){
				// 基于准备好的dom，初始化echarts实例
				var myChart1 = echarts.init(document.getElementById('demo_echarts1'));
				var myChart2 = echarts.init(document.getElementById('demo_echarts2'));
				var len = data.length;
				//console.log(data)
				var names = new Array()
				var prices = new Array()
				var category = new Array()
				var servicedata = new Array()
				for(var i=0; i<len; i++){
					var obj = new Object();
					names[i] = data[i].name;
					prices[i] = data[i].price;
					obj.name = data[i].name;
					obj.value = data[i].pnum;
					servicedata[i] = obj;
				}
				 // 指定图表的配置项和数据
			    var option1 = {
			        title: {
			            text: '商品金额分析'
			        },
			        grid: {},
			        legend: {
			            data:['金额']
			        },
			        tooltip: {},
			        xAxis: {
			            data: names
			        },
			        yAxis: {},
			        series: [{
			            name: '金额',
			            type: 'bar',
			            data: prices
			        }]
			    };
				 
			    var option2 = {
				        title: {
				            text: '商品占比分析'
				        },
				        tooltip: {
					        trigger: 'item',
					        formatter: '{a} <br/>{b} : {c} ({d}%)',
					    },
				        series: [{
				            name: '数目',
				            type: 'pie',
				            data: servicedata
				        }]
				    };
			 
			    // 使用刚指定的配置项和数据显示图表。
			    myChart1.setOption(option1);
			    myChart2.setOption(option2, true);
			}
		})
	});
	</script>	
</head>
<body>
	<div id="demo_echarts1" style="width: 100%;height:400px;"></div>
	<div id="demo_echarts2" style="width: 100%;height:400px;"></div>
</body>
</html>
