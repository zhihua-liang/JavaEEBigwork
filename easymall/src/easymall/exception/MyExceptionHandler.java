package easymall.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import easymall.pojo.User;

public class MyExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception ex) {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("ex", ex);
		if(ex instanceof UserLoginNoException) {
			request.setAttribute("user", new User());
			request.setAttribute("msg", model);
			return new ModelAndView("login",model);
		}else if(ex instanceof Exception){//到时再删掉
			System.out.println("出现了异常！");
			ex.printStackTrace();
			return null;
		}
		else {
			return new ModelAndView("error",model);
		}
	}

}
