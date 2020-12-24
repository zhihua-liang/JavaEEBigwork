package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.pojo.Orders;

@Mapper
@Repository("orderDao")
public interface OrderDao {
	//��Ӷ���
	void addOrder(Orders myorder);
	//��ʾ����
	List<Orders> findOrderByUserId(Integer user_id);
	//ɾ������
	void delorder(String id);
	//����֧��
	void payorder(String id);
}
