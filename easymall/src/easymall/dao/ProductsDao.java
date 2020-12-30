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
	//������Ʒ����
	public List<Category> allcategorys();
	
	//������Ʒ
	public List<Products> prodlist(Map<String,Object> map);

	//����һ����Ʒ
	public Products oneProduct(String pid);
	
	//����һ����Ʒ
	public List<Products> prodclass(Integer category);
	
	//�������е���Ʒ
	public List<Products> selectAllProducts();
	
	//�������е���Ʒ,���򷵻�
	public List<Products> selectAllProductsBysaleNum();

	//������Ʒ
	public void save(Products products);
	
	//ͨ��imgrul������Ʒ
	public Products findByImgurl(String imgurl); 
}
