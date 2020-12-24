package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.pojo.OrderItem;

@Mapper
@Repository("orderItemDao")
public interface OrderItemDao {
	//��Ӷ��� ��Ʒ��Ϣ
	void addOrderItem(OrderItem orderItem);
	//��ʾ���� ��Ʒ��Ϣ
	List<OrderItem> orderitem(String order_id);
	//ɾ��������
	void delorderitem(String id);

}
