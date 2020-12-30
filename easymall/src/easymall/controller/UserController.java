package easymall.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import easymall.pojo.User;
import easymall.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	//得到一个用来记录日志的对象，这样打印信息的时候能够标记打印的是那个类的信息
	//private static final Log logger = LogFactory.getLog(UserController.class);
	//处理登录
	@RequestMapping("/login")
	public String login(User user, HttpSession session, Model model) {
		User muser = userService.login(user);
		
		if(muser != null) {
			//校验账号是否激活
			if(muser.getActiveState() == 0) {
				model.addAttribute("message","账号未激活");
				return "login";
			}
			session.setAttribute("user", muser);
			
			//是管理员则跳转到后台管理界面
			String nickName = muser.getNickname();
			if("管理员".equals(nickName) || "超级管理员".equals(nickName)) {
				return "redirect:/admin/manager";
			}
				
			return "redirect:/index.jsp";
		}else {
			model.addAttribute("message","用户名或密码错误");
			return "login";
		}
	}
	//处理注册
	@RequestMapping("/regist")
	public String regist(User user,String valistr,HttpSession session,Model model) {
		//检验用户名是否为空
		if (user.getUsername() == null || user.getUsername() == "") {
			model.addAttribute("msg", "用户名不能为空");
			return "regist";
		}

		// 检验密码是否为空
		if (user.getPassword() == null || user.getPassword() == "") {
			model.addAttribute("msg", "密码不能为空");
			return "regist";
		}
		
		//利用正则表达式（可改进）验证邮箱是否符合邮箱的格式
	    if(!user.getEmail().matches("^\\w+@(\\w+\\.)+\\w+$")){
	    	model.addAttribute("msg", "邮箱格式错误");
	    	return "regist";
	    }
	    
		
		//校验验证码
		if(!valistr.equalsIgnoreCase(session.getAttribute("code").toString())) {
			model.addAttribute("msg","验证码错误");
			return "regist";
		}
		
		//检验数据库中是否存在这样的一个用户
		if(userService.regist(user) > 0) {
			model.addAttribute("msg","注册成功");
			return "login";
		}else {
			model.addAttribute("msg","注册失败");
			return "regist";
		}
	}
	
	//更新用户激活码，激活用户
	@RequestMapping("/updatecode")
	public String updatecode(String code,String email) {
		User user = new User();
		user.setCode(code);
		user.setEmail(email);
		user.setActiveState(1);
		
		userService.updatecode(user);
		return "confirm";
	}
	
	//校验用户名是否可以使用
	@RequestMapping(value="/checkUser",method=RequestMethod.POST)
	public void check(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		if(userService.checkUsername(username)) {
			response.getWriter().print("用户名" + username + "已经被注册");
		}else {
			response.getWriter().print("恭喜你" + username + "可以使用");
		}
	}
	
}

