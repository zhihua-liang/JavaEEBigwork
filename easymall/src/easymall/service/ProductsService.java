package easymall.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import easymall.pojo.Category;
import easymall.pojo.MyProducts;
import easymall.pojo.Products;

public interface ProductsService {
	//������Ʒ���
	public List<Category> allcategorys();
	//������Ʒ
	public List<Products> prodlist(Map<String,Object> map);
	//���ҵ�����Ʒ
	public Products oneProducts(String pid);
	//����һ����Ʒ
	public List<Products> prodclass(Integer category);
	//����������Ʒ
	public List<Products> selectAllProducts();
	
	//����������Ʒ,����������������
	public List<Products> selectAllProductsBysaleNum();
	
	
	public String save(MyProducts myproducts,HttpServletRequest request) ;
}
