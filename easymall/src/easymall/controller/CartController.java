package easymall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.pojo.Cart;
import easymall.pojo.MyCart;
import easymall.pojo.User;
import easymall.service.CartService;

@Controller("cartController")
@RequestMapping("/cart")
public class CartController extends BaseController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/addCart")
	public String addCart(String pid,Integer buyNum,HttpSession session) {
		System.out.println("pid: " + pid + "buyNum: " + buyNum);
		User user = (User)session.getAttribute("user");
		Cart cart = new Cart(user.getId(),pid,buyNum);
		Cart _cart = cartService.findCart(cart);
		if(_cart == null) {
			cartService.addCart(cart);
		}else {
			_cart.setCartID(cart.getCartID());
			cartService.updateCart(cart);
		}
		return "redirect:/cart/showCart";
	}
	
	@RequestMapping("/showCart")
	public String showCart(HttpSession session,Model model) {
		User user = (User)session.getAttribute("user");
		List<MyCart> carts = cartService.showcart(user.getId());
		model.addAttribute("carts",carts);
		return "cart";
	}
	
	@RequestMapping("/updateBuyNum")
	public void updateBuyName(Integer cartID,Integer buyNum,HttpSession session) {
		Cart newcart = new Cart();
		newcart.setCartID(cartID);
		newcart.setNum(buyNum);
		cartService.updateBuyNum(newcart);
		System.out.println("Ö´ÐÐÍê³É");
	}
	
	@RequestMapping("/delCart")
	public void delCart(Integer cartID,HttpSession session) {
		cartService.delCart(cartID);
	}
}
