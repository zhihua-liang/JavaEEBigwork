package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.pojo.Cart;
import easymall.pojo.MyCart;

@Repository
@Mapper
public interface CartDao {
	//添加商品到购物车
	public int addCart(Cart cart);
	//查找购物车是否存在该商品
	public Cart findCart(Cart cart);
	//增加购物车商品数量	
	public int updateCart(Cart cart);
	//显示购物车内所有商品	
	public List<MyCart> showCart(int user_id);
	//修改购物车商品数量
	public void updateBuyNum(Cart cart);
	//删除购物车的商品
	public void delCart(Integer cartID);
	
	//根据cartID查找购物车
	public MyCart findByCartID(Integer cartID);
}	
