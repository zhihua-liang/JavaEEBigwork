package easymall.service;

import java.util.List;

import easymall.pojo.Cart;
import easymall.pojo.MyCart;

public interface CartService {
	//�����Ʒ�����ﳵ
	public int addCart(Cart cart);
	//���ҹ��ﳵ�Ƿ���ڸ���Ʒ
	public Cart findCart(Cart cart);
	//�޸Ĺ��ﳵ��Ʒ����
	public int updateCart(Cart cart);
	//��ʾ���ﳵ�е�������Ʒ
	public List<MyCart> showcart(int user_id);
	//�޸Ĺ��ﳵ��Ʒ����
	public void updateBuyNum(Cart cart);
	//ɾ�����ﳵ��Ʒ
	public void delCart(Integer cartID);
	
	//����cartID���ҹ��ﳵ
	public MyCart findByCartID(Integer cartID);
}
