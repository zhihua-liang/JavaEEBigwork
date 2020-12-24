package easymall.service;

import java.util.List;

import easymall.pojo.OrderItem;
import easymall.pojo.Orders;

public interface OrderService {
	//��Ӷ���
	public void addOrder(String cartIds,Orders myOrder);
	//�������еĶ���
	public List<Orders> findOrderByUserId(Integer user_id);
	//����ͬһ���������ж�����
	public List<OrderItem> orderitem(String order_id);
	
	//ɾ������
	public void delorder(String id);
	//����֧��
	public void payorder(String id);
}
