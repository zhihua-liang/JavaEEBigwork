package easymall.service;

import java.util.List;
import java.util.Map;

import easymall.pojo.Products;

public interface ProductsService {
	//������Ʒ���
	public List<String> allcategorys();
	//������Ʒ
	public List<Products> prodlist(Map<String,Object> map);
	//���ҵ�����Ʒ
	public Products oneProducts(String pid);
	//����һ����Ʒ
	public List<Products> prodclass(String category);
}
