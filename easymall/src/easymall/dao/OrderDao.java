package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.pojo.Orders;

@Mapper
@Repository("orderDao")
public interface OrderDao {
	//添加订单
	void addOrder(Orders myorder);
	//显示订单
	List<Orders> findOrderByUserId(Integer user_id);
	//删除订单
	void delorder(String id);
	//订单支付
	void payorder(String id);
}
