package easymall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	//跳转到登录页面
	@RequestMapping(value="/index/login")
	public String login(Model model) {
		return "login";
	}
	
	
	//退出登录，跳转到主页
	@RequestMapping(value="/index/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.jsp";
	}
	
	//跳转到注册页面
	@RequestMapping(value="/index/regist")
	public String register() {
		return "regist";
	}
}
