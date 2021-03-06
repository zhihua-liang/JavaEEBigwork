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
		
		//查询商品表中的所有商品类别
		List<Category> categorys = productsService.allcategorys();
		model.addAttribute("categorys", categorys);
		
		//对传过来的数值范围进行处理，不符合要求的则按默认要求处理
		double _minPrice = 0;
		double _maxPrice = Double.MAX_VALUE;
		
		String reg = "^\\d+$";//只能输入数字
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
		
		//创建map，用于存放查询条件
		Map<String,Object> map = new HashMap<>();
		map.put("name", name);
		map.put("category", category);
		map.put("minPrice", _minPrice);
		map.put("maxPrice", _maxPrice);
		
		//根据条件查询符合条件的商品信息
		List<Products> products = productsService.prodlist(map);
		
		//回填查询数据
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
	
	//用于处理echarts
	@RequestMapping("/showchart")
	public String showChart(Model model) {
		return "chart";
	}
	
	//以json方式返回所有商品的数据
	@ResponseBody
	@RequestMapping("/getProducts")
	public List<Products> getProducts(){
		//查找所有商品
		List<Products> products = productsService.selectAllProducts();
		return products;
	}
	
	@RequestMapping("/makeExcel")
	public String makeExcel(Model model){
		//System.out.println("准备生成execl！");
		List<Products> products = productsService.selectAllProductsBysaleNum();
		
//		for(int i = 0 ; i <products.size() ; i++ ) {
//			System.out.println(products.get(i));
//		}
		POI pt = new POI();
		pt.showExcel(products);
		//System.out.println("success");
		model.addAttribute("message", "成功打印销售榜单");
		return "redirect:/admin/manager";
	}
}
