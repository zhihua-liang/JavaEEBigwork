package easymall.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import easymall.pojo.Category;
import easymall.pojo.MyProducts;
import easymall.pojo.Products;

public interface ProductsService {
	//查找商品类别
	public List<Category> allcategorys();
	//查找商品
	public List<Products> prodlist(Map<String,Object> map);
	//查找单个商品
	public Products oneProducts(String pid);
	//查找一类商品
	public List<Products> prodclass(Integer category);
	//查找所有商品
	public List<Products> selectAllProducts();
	
	//查找所有商品,根据销售数量返回
	public List<Products> selectAllProductsBysaleNum();
	
	
	public String save(MyProducts myproducts,HttpServletRequest request) ;
}
