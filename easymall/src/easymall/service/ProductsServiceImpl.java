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
		//��ȡͼƬ���ƣ���׺����
		String originName = myproducts.getImgurl().getOriginalFilename();
		
		//��ȡ��׺��.png ���� .jpg�ȣ�
		String extName = originName.substring(originName.lastIndexOf("."));
		System.out.println("extName: " + extName);
		System.out.println("�����" + ".jpg".equalsIgnoreCase(extName));
		//���ͼƬ��׺�Ƿ�Ϸ�
		if(!(".jpg".equalsIgnoreCase(extName) || ".png".equalsIgnoreCase(extName))
				|| ".gif".equalsIgnoreCase(extName)) {
			return "ͼƬ��׺���Ϸ�";
		}
		
		//����ϴ����ļ��Ƿ���ͼƬ
		try {
			BufferedImage bufImage = ImageIO.read(myproducts.getImgurl().getInputStream());
			bufImage.getHeight();
			bufImage.getWidth();
		}catch(Exception e) {
			return "���ļ�����ͼƬ";
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
		
		//�ϴ��ļ�
		try {
			myproducts.getImgurl().transferTo(file);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//ƴ��ͼƬ�������ݿ��·��
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
			System.out.println("imgurl�� " + imgurl);
			productsDao.save(products);
		}
		return "��Ʒ��ӳɹ�";
	}

	@Override
	public List<Products> selectAllProductsBysaleNum() {
		return productsDao.selectAllProductsBysaleNum();
	}

}
