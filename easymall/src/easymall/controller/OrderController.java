package easymall.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.pojo.MyCart;
import easymall.pojo.OrderInfo;
import easymall.pojo.OrderItem;
import easymall.pojo.Orders;
import easymall.pojo.Products;
import easymall.pojo.User;
import easymall.service.CartService;
import easymall.service.OrderService;
import easymall.service.ProductsService;

@Controller("orderController")
@RequestMapping("/order")
public class OrderController extends BaseController {
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductsService productsService;
	
	@RequestMapping("/order_add")
	public String order_add(String cartIds,Model model) {
		//1.将购物车中的所有选中的cartID组合起来的字符串拆分为数组
		String[] arrCartIds = cartIds.split(",");
		List<MyCart> carts = new ArrayList<>();
		//2.遍历数组，根据cartID编号查找购物车，添加到carts中
		for(int i = 0 ; i < arrCartIds.length ; i++) {
			//System.out.println("3");
			Integer cartID = Integer.valueOf(arrCartIds[i]);
			//System.out.println("4");
			MyCart cart = cartService.findByCartID(cartID);
			carts.add(cart);
		}
		model.addAttribute("carts",carts);
		model.addAttribute("cartIds", cartIds);
		return "order_add";
	}
	
	@RequestMapping("/addOrder")
	public String addOrder(String receiverinfo,String cartIds,HttpSession session) {
		//确定订单产生时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df .format(new Date());
		Timestamp timestamp = Timestamp.valueOf(time);
		
		//产生一个随机订单号
		User user = (User)session.getAttribute("user");
		String orderId = UUID.randomUUID().toString();
		
		Orders myorder = new Orders();
		myorder.setId(orderId);
		//myorder.setMoney(null);
		myorder.setPaystate(0);//0代表未付款
		myorder.setReceiverinfo(receiverinfo);
		myorder.setOrdertime(timestamp);
		myorder.setUser_id(user.getId());
		
		//添加订单
		orderService.addOrder(cartIds,myorder);
		return "forward:/order/showorder";
	}
	
	@RequestMapping("showorder")
	public String showOrder(HttpSession session,Model model) {
		//1.获取当前登录用户
		User user = (User)session.getAttribute("user");
		//2.根据用户id查询用户的所有订单信息，查询orders表
		List<OrderInfo> orderInfoList = findOrderInfoByUserId(user.getId());
		//3.将用户的所有订单信息的list集合存入request域中，转发到order_list.jsp中显示
		model.addAttribute("orderInfos", orderInfoList);
		return "order_list";
	}
	
	//查找一个用户的所有订单
	private List<OrderInfo> findOrderInfoByUserId(int user_Id){
		List<OrderInfo> orderInfoList = new ArrayList<>();
		
		//查找同一用户的所有订单
		List<Orders> orderList = orderService.findOrderByUserId(user_Id);
		//遍历每个订单，将每个订单的订单项和订单信息组成orderinfo加入到list中
		for(Orders order : orderList) {
			//查找这个订单的所有订单项
			List<OrderItem> orderitems = orderService.orderitem(order.getId());
			
			//将商品信息和商品数量加入到map中
			Map<Products,Integer> map = new HashMap<>();
			for(OrderItem orderItem : orderitems) {
				Products product = productsService.oneProducts(orderItem.getProduct_id());
				map.put(product, orderItem.getBuynum());
			}
			
			//生成Orderinfo对象并加入到list中
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrder(order);
			orderInfo.setMap(map);
			orderInfoList.add(orderInfo);
		}
		return orderInfoList;
	}
	
	@RequestMapping("delorder")
	public String delorder(String id,Model model) {
		orderService.delorder(id);
		return "redirect:/order/showorder";
	}
	
	@RequestMapping("/payorder")
	public String payorder(String id,Model model) {
		orderService.payorder(id);
		return "redirect:/order/showorder";
	}
}
