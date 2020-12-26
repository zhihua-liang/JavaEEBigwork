package easymall.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.pojo.Products;

@Repository
@Mapper
public interface ProductsDao {
	//查找商品类型
	public List<String> allcategorys();
	
	//查找商品
	public List<Products> prodlist(Map<String,Object> map);

	//查找一个商品
	public Products oneProduct(String pid);
	
	//查找一类商品
	public List<Products> prodclass(String category);
	
	//返回所有的商品
	public List<Products> selectAllProducts();
}
