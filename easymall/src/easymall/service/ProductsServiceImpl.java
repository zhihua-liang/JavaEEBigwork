package easymall.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.ProductsDao;
import easymall.pojo.Category;
import easymall.pojo.MyProducts;
import easymall.pojo.Products;

@Service("productsService")
public class ProductsServiceImpl implements ProductsService {
	@Autowired
	private ProductsDao productsDao;
	
	@Override
	public List<Category> allcategorys() {
		List<Category> categorys = productsDao.allcategorys();
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
	public List<Products> prodclass(Integer category) {
		return productsDao.prodclass(category);
	}

	@Override
	public List<Products> selectAllProducts() {
		return productsDao.selectAllProducts();
	}

	@Override
	public String save(MyProducts myproducts, HttpServletRequest request) {
		//获取图片名称，后缀名称
		String originName = myproducts.getImgurl().getOriginalFilename();
		
		//截取后缀（.png 或者 .jpg等）
		String extName = originName.substring(originName.lastIndexOf("."));
		System.out.println("extName: " + extName);
		System.out.println("结果：" + ".jpg".equalsIgnoreCase(extName));
		//检查图片后缀是否合法
		if(!(".jpg".equalsIgnoreCase(extName) || ".png".equalsIgnoreCase(extName))
				|| ".gif".equalsIgnoreCase(extName)) {
			return "图片后缀不合法";
		}
		
		//检测上传的文件是否是图片
		try {
			BufferedImage bufImage = ImageIO.read(myproducts.getImgurl().getInputStream());
			bufImage.getHeight();
			bufImage.getWidth();
		}catch(Exception e) {
			return "该文件不是图片";
		}
		
		String imgurl = "";
		for(int i = 0 ; i < 8 ; i++) {
			imgurl += "/" + Integer.toHexString(new Random().nextInt(16));  
		}
		String realpath = request.getServletContext().getRealPath("/WEB-INF");
		realpath += "/upload";
		
		System.out.println("realpath: " + realpath);
		
		File file = new File(realpath + imgurl,originName);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//上传文件
		try {
			myproducts.getImgurl().transferTo(file);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//拼接图片存入数据库的路径
		imgurl = "/upload" + imgurl + "/" + originName;
		String id = UUID.randomUUID().toString();
		
		Products products = new Products();
		products.setId(id);
		products.setName(myproducts.getName());
		products.setCategory(myproducts.getCategory());
		products.setPrice(myproducts.getPrice());
		products.setPnum(myproducts.getPnum());
		products.setImgurl(imgurl);
		products.setDescription(myproducts.getDescription());
		
		if(productsDao.findByImgurl(products.getImgurl()) == null) {
			productsDao.save(products);
		}else {
			String fname = imgurl.substring(0,imgurl.lastIndexOf("."));
			imgurl = fname + System.currentTimeMillis() + extName;
			products.setImgurl(imgurl);
			System.out.println("imgurl： " + imgurl);
			productsDao.save(products);
		}
		return "商品添加成功";
	}

	@Override
	public List<Products> selectAllProductsBysaleNum() {
		return productsDao.selectAllProductsBysaleNum();
	}

}
