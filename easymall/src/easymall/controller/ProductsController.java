package easymall.controller;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import easymall.pojo.Category;
import easymall.pojo.POI;
import easymall.pojo.Products;
import easymall.service.ProductsService;

@Controller("productsController")
public class ProductsController {
	@Autowired
	private ProductsService productsService;
	
	@RequestMapping("/index")
	public String index() {
		return "forward:index.jsp";
	}
	
	@RequestMapping("/prodlist")
	public String prodlist(String name,Integer category,
			String minprice,String maxprice,Model model) {
		
		//��ѯ��Ʒ���е�������Ʒ���
		List<Category> categorys = productsService.allcategorys();
		model.addAttribute("categorys", categorys);
		
		//�Դ���������ֵ��Χ���д���������Ҫ�����Ĭ��Ҫ����
		double _minPrice = 0;
		double _maxPrice = Double.MAX_VALUE;
		
		String reg = "^\\d+$";//ֻ����������
		if(minprice != null && !"".equals(minprice.trim())
				&& minprice.matches(reg)) {
			_minPrice = Double.parseDouble(minprice);
		}
		
		if(maxprice != null && !"".equals(maxprice.trim())
				&& maxprice.matches(reg)) {
			if(Double.parseDouble(maxprice) >= _minPrice) {
				_maxPrice = Double.parseDouble(maxprice);
			}
		}
		
		//����map�����ڴ�Ų�ѯ����
		Map<String,Object> map = new HashMap<>();
		map.put("name", name);
		map.put("category", category);
		map.put("minPrice", _minPrice);
		map.put("maxPrice", _maxPrice);
		
		//����������ѯ������������Ʒ��Ϣ
		List<Products> products = productsService.prodlist(map);
		
		//�����ѯ����
		model.addAttribute("name",name);
		model.addAttribute("minPrice",_minPrice);
		model.addAttribute("maxPrice", _maxPrice);
		model.addAttribute("products", products);
		
		return "prod_list";
	}
	
	@RequestMapping("/prodInfo")
	public String prodInfo(String pid,Model model) {
		Products product = productsService.oneProducts(pid);
		model.addAttribute("product",product);
		return "prod_info";
	}
	
	@RequestMapping(value="/prodclass/{category}",method=RequestMethod.GET)
	public String prodclass(@PathVariable Integer category,Model model) {
		List<Products> products = productsService.prodclass(category);
		model.addAttribute("products", products);
		return "forward:/WEB-INF/jsp/prod_list.jsp";
	}
	
	//���ڴ���echarts
	@RequestMapping("/showchart")
	public String showChart(Model model) {
		return "chart";
	}
	
	//��json��ʽ����������Ʒ������
	@ResponseBody
	@RequestMapping("/getProducts")
	public List<Products> getProducts(){
		//����������Ʒ
		List<Products> products = productsService.selectAllProducts();
		return products;
	}
	
	@RequestMapping("/makeExcel")
	public String makeExcel(Model model){
		//System.out.println("׼������execl��");
		List<Products> products = productsService.selectAllProductsBysaleNum();
		
//		for(int i = 0 ; i <products.size() ; i++ ) {
//			System.out.println(products.get(i));
//		}
		POI pt = new POI();
		pt.showExcel(products);
		//System.out.println("success");
		model.addAttribute("message", "�ɹ���ӡ���۰�");
		return "redirect:/admin/manager";
	}
}
