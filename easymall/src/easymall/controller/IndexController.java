package easymall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	//��ת����¼ҳ��
	@RequestMapping(value="/index/login")
	public String login(Model model) {
		return "login";
	}
	
	
	//�˳���¼����ת����ҳ
	@RequestMapping(value="/index/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.jsp";
	}
	
	//��ת��ע��ҳ��
	@RequestMapping(value="/index/regist")
	public String register() {
		return "regist";
	}
}
