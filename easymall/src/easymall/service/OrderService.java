package easymall.service;

import java.util.List;

import easymall.pojo.OrderItem;
import easymall.pojo.Orders;

public interface OrderService {
	//添加订单
	public void addOrder(String cartIds,Orders myOrder);
	//查找所有的订单
	public List<Orders> findOrderByUserId(Integer user_id);
	//查找同一订单的所有订单项
	public List<OrderItem> orderitem(String order_id);
	
	//删除订单
	public void delorder(String id);
	//订单支付
	public void payorder(String id);
}
