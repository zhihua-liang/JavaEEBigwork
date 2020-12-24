package easymall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import easymall.dao.CartDao;
import easymall.dao.OrderDao;
import easymall.dao.OrderItemDao;
import easymall.pojo.MyCart;
import easymall.pojo.OrderItem;
import easymall.pojo.Orders;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private CartDao cartDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;

	/**
	 * 根据选择的商品添加订单以及订单项
	 */
	@Override
	@Transactional
	public void addOrder(String cartIds, Orders myOrder) {
		String[] arrCartIds = cartIds.split(",");
		double sum = 0.0;//订单总金额

		//遍历提交待确定订单的内容
		for(String cartID : arrCartIds) {
			int cid = Integer.parseInt(cartID);
			System.out.println("cid: " + cid);
			MyCart mycart = cartDao.findByCartID(cid);
			String pid = mycart.getPid();
			int buynum = mycart.getNum();
			
			double price = mycart.getPrice();
			sum += buynum * price;//计算订单总金额
			
			//为一个订单的每一个订单项商品建立新订单项
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder_id(myOrder.getId());
			orderItem.setProduct_id(pid);
			orderItem.setBuynum(buynum);
			orderItemDao.addOrderItem(orderItem);//添加订单项
			cartDao.delCart(cid);//删除购物车已经添加为订单的商品
		}
		myOrder.setMoney(sum);//设置订单金额
		orderDao.addOrder(myOrder);//添加订单
	}

	/**
	 * 查找同一个用户的所有订单
	 */
	@Override
	public List<Orders> findOrderByUserId(Integer user_id) {
		return orderDao.findOrderByUserId(user_id);
	}

	/**
	 * 查找同一个订单的所有订单项
	 */
	@Override
	public List<OrderItem> orderitem(String order_id) {
		return orderItemDao.orderitem(order_id);
	}

	@Override
	@Transactional
	public void delorder(String id) {
		orderItemDao.delorderitem(id);
		orderDao.delorder(id);
	}

	@Override
	public void payorder(String id) {
		orderDao.payorder(id);
	}

}
