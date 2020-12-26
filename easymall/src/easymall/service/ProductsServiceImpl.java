package easymall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.ProductsDao;
import easymall.pojo.Products;

@Service("productsService")
public class ProductsServiceImpl implements ProductsService {
	@Autowired
	private ProductsDao productsDao;
	
	@Override
	public List<String> allcategorys() {
		List<String> categorys = productsDao.allcategorys();
		return categorys;
	}

	@Override
	public List<Products> prodlist(Map<String, Object> map) {
		List<Products> products = productsDao.prodlist(map);
		return products;
	}

	@Override
	public Products oneProducts(String pid) {
		return productsDao.oneProduct(pid);
	}

	@Override
	public List<Products> prodclass(String category) {
		return productsDao.prodclass(category);
	}

	@Override
	public List<Products> selectAllProducts() {
		return productsDao.selectAllProducts();
	}

}
