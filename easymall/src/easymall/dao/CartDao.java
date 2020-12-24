package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.pojo.Cart;
import easymall.pojo.MyCart;

@Repository
@Mapper
public interface CartDao {
	//�����Ʒ�����ﳵ
	public int addCart(Cart cart);
	//���ҹ��ﳵ�Ƿ���ڸ���Ʒ
	public Cart findCart(Cart cart);
	//���ӹ��ﳵ��Ʒ����	
	public int updateCart(Cart cart);
	//��ʾ���ﳵ��������Ʒ	
	public List<MyCart> showCart(int user_id);
	//�޸Ĺ��ﳵ��Ʒ����
	public void updateBuyNum(Cart cart);
	//ɾ�����ﳵ����Ʒ
	public void delCart(Integer cartID);
	
	//����cartID���ҹ��ﳵ
	public MyCart findByCartID(Integer cartID);
}	
