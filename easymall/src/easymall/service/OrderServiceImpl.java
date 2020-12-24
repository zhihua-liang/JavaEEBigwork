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
	 * ����ѡ�����Ʒ��Ӷ����Լ�������
	 */
	@Override
	@Transactional
	public void addOrder(String cartIds, Orders myOrder) {
		String[] arrCartIds = cartIds.split(",");
		double sum = 0.0;//�����ܽ��

		//�����ύ��ȷ������������
		for(String cartID : arrCartIds) {
			int cid = Integer.parseInt(cartID);
			System.out.println("cid: " + cid);
			MyCart mycart = cartDao.findByCartID(cid);
			String pid = mycart.getPid();
			int buynum = mycart.getNum();
			
			double price = mycart.getPrice();
			sum += buynum * price;//���㶩���ܽ��
			
			//Ϊһ��������ÿһ����������Ʒ�����¶�����
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder_id(myOrder.getId());
			orderItem.setProduct_id(pid);
			orderItem.setBuynum(buynum);
			orderItemDao.addOrderItem(orderItem);//��Ӷ�����
			cartDao.delCart(cid);//ɾ�����ﳵ�Ѿ����Ϊ��������Ʒ
		}
		myOrder.setMoney(sum);//���ö������
		orderDao.addOrder(myOrder);//��Ӷ���
	}

	/**
	 * ����ͬһ���û������ж���
	 */
	@Override
	public List<Orders> findOrderByUserId(Integer user_id) {
		return orderDao.findOrderByUserId(user_id);
	}

	/**
	 * ����ͬһ�����������ж�����
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
