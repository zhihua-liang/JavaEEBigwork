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
		//1.�����ﳵ�е�����ѡ�е�cartID����������ַ������Ϊ����
		String[] arrCartIds = cartIds.split(",");
		List<MyCart> carts = new ArrayList<>();
		//2.�������飬����cartID��Ų��ҹ��ﳵ����ӵ�carts��
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
		//ȷ����������ʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df .format(new Date());
		Timestamp timestamp = Timestamp.valueOf(time);
		
		//����һ�����������
		User user = (User)session.getAttribute("user");
		String orderId = UUID.randomUUID().toString();
		
		Orders myorder = new Orders();
		myorder.setId(orderId);
		//myorder.setMoney(null);
		myorder.setPaystate(0);//0����δ����
		myorder.setReceiverinfo(receiverinfo);
		myorder.setOrdertime(timestamp);
		myorder.setUser_id(user.getId());
		
		//��Ӷ���
		orderService.addOrder(cartIds,myorder);
		return "forward:/order/showorder";
	}
	
	@RequestMapping("showorder")
	public String showOrder(HttpSession session,Model model) {
		//1.��ȡ��ǰ��¼�û�
		User user = (User)session.getAttribute("user");
		//2.�����û�id��ѯ�û������ж�����Ϣ����ѯorders��
		List<OrderInfo> orderInfoList = findOrderInfoByUserId(user.getId());
		//3.���û������ж�����Ϣ��list���ϴ���request���У�ת����order_list.jsp����ʾ
		model.addAttribute("orderInfos", orderInfoList);
		return "order_list";
	}
	
	//����һ���û������ж���
	private List<OrderInfo> findOrderInfoByUserId(int user_Id){
		List<OrderInfo> orderInfoList = new ArrayList<>();
		
		//����ͬһ�û������ж���
		List<Orders> orderList = orderService.findOrderByUserId(user_Id);
		//����ÿ����������ÿ�������Ķ�����Ͷ�����Ϣ���orderinfo���뵽list��
		for(Orders order : orderList) {
			//����������������ж�����
			List<OrderItem> orderitems = orderService.orderitem(order.getId());
			
			//����Ʒ��Ϣ����Ʒ�������뵽map��
			Map<Products,Integer> map = new HashMap<>();
			for(OrderItem orderItem : orderitems) {
				Products product = productsService.oneProducts(orderItem.getProduct_id());
				map.put(product, orderItem.getBuynum());
			}
			
			//����Orderinfo���󲢼��뵽list��
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
