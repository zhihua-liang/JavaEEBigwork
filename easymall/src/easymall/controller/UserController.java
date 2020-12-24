package easymall.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import easymall.pojo.User;
import easymall.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	//�õ�һ��������¼��־�Ķ���������ӡ��Ϣ��ʱ���ܹ���Ǵ�ӡ�����Ǹ������Ϣ
	//private static final Log logger = LogFactory.getLog(UserController.class);
	//�����¼
	@RequestMapping("/login")
	public String login(User user, HttpSession session, Model model) {
		User muser = userService.login(user);
		if(muser != null) {
			session.setAttribute("user", muser);
			return "redirect:/index.jsp";
		}else {
			model.addAttribute("messageError","�û������������");
			return "login";
		}
	}
	//����ע��
	@RequestMapping("/regist")
	public String regist(User user,String valistr,HttpSession session,Model model) {
		//�����û����Ƿ�Ϊ��
		if (user.getUsername() == null || user.getUsername() == "") {
			model.addAttribute("msg", "�û�������Ϊ��");
			return "regist";
		}

		// ���������Ƿ�Ϊ��
		if (user.getPassword() == null || user.getPassword() == "") {
			model.addAttribute("msg", "���벻��Ϊ��");
			return "regist";
		}
		
		//����������ʽ���ɸĽ�����֤�����Ƿ��������ĸ�ʽ
	    if(!user.getEmail().matches("^\\w+@(\\w+\\.)+\\w+$")){
	    	model.addAttribute("msg", "�����ʽ����");
	    	return "regist";
	    }
	    
		
		//У����֤��
		if(!valistr.equalsIgnoreCase(session.getAttribute("code").toString())) {
			model.addAttribute("msg","��֤�����");
			return "regist";
		}
		
		//�������ݿ����Ƿ����������һ���û�
		if(userService.regist(user) > 0) {
			model.addAttribute("msg","ע��ɹ�");
			return "login";
		}else {
			model.addAttribute("msg","ע��ʧ��");
			return "regist";
		}
	}
	
	//�����û�������
	@RequestMapping("/updatecode")
	public String updatecode(User user) {
		user.setActiveState(1);
		userService.updatecode(user);
		return "confirm";
	}
	
	//У���û����Ƿ����ʹ��
	@RequestMapping(value="/checkUser",method=RequestMethod.POST)
	public void check(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		if(userService.checkUsername(username)) {
			response.getWriter().print("�û���" + username + "�Ѿ���ע��");
		}else {
			response.getWriter().print("��ϲ��" + username + "����ʹ��");
		}
	}
	
}

