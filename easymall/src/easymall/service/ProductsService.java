package easymall.service;

import java.util.List;
import java.util.Map;

import easymall.pojo.Products;

public interface ProductsService {
	//查找商品类别
	public List<String> allcategorys();
	//查找商品
	public List<Products> prodlist(Map<String,Object> map);
	//查找单个商品
	public Products oneProducts(String pid);
	//查找一类商品
	public List<Products> prodclass(String category);
}
