package easymall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import easymall.exception.UserLoginNoException;


public class BaseController {
	@ModelAttribute
	public void isLogin(HttpSession session,HttpServletRequest request)throws UserLoginNoException {
		if(session.getAttribute("user") == null) {
			throw new UserLoginNoException(" Ã»ÓÐµÇÂ¼£¬ÇëÏÈµÇÂ¼£¡");
		}
	}
}
