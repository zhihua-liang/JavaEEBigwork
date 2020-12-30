package easymall.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.pojo.Category;
import easymall.pojo.Products;

@Repository
@Mapper
public interface ProductsDao {
	//查找商品类型
	public List<Category> allcategorys();
	
	//查找商品
	public List<Products> prodlist(Map<String,Object> map);

	//查找一个商品
	public Products oneProduct(String pid);
	
	//查找一类商品
	public List<Products> prodclass(Integer category);
	
	//返回所有的商品
	public List<Products> selectAllProducts();
	
	//返回所有的商品,排序返回
	public List<Products> selectAllProductsBysaleNum();

	//插入商品
	public void save(Products products);
	
	//通过imgrul查找商品
	public Products findByImgurl(String imgurl); 
}
