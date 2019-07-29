package org.taru.lanqiao.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.taru.lanqiao.model.User;
import org.taru.lanqiao.service.UserServiceImpl;
import org.taru.lanqiao.util.JsonWrite;
import org.taru.lanqiao.util.SecurityUtl;
import org.taru.lanqiao.vo.JsonResult;
/**
 * 访问控制：使用Filter过滤器实现
 * 问题：过滤什么？
 * 难点：服务端如何记录登录状态（Htttp是无状态的）
 * 解决方法：
 * 	①传统方案：基于服务端的Session-cookie（会话）有状态认证
 * 		Session和Cookie的区别？
 * 			1、Session是服务端内存对象，API是setArribute和GetArribute
 * 			2、Cookie是客户端的文件，只能存储少量的文本类型
 * 			3、Session由于存储在服务端安全性比Cookie要高的多
 * 				Cookie的使用场景： 记住用户名密码 两周内自动登录 购物车 存储Token
 * 			4、Session创建之后，会产生一个SessionId，这个ID随着响应回到客户端，存储在Cookie中，下次访问同样的path的时候，会携带SessionID，Tomcat容器
 * 					根据携带来的SessionId，找到同一个会话内相同的Session。
 * 				同一个会iu话之内的所有请求，共享一个Session。
 * 			5、Cookie:分为临时cookie和永久性的cookie 通过max-age进行区分。当关闭浏览器时，临时cookie消失，存储在临时cookie中的JSessionId就没了，
 * 				再次访问时，就无法找到服务器上一次的Session。不被使用的sessin过期后自动销毁
 * 			6、Cookie被禁用的解决方式： 
 * 				6.1URL重写
 * 				6.2 隐藏表单域 把sessionid存储在表单中，
 * 	②流行方法：基于客户端的token（令牌）无状态认证
 * @author lenovo
 *
 */

/**
 *   <filter>
  	<filter-name>CrosFilter</filter-name>
  	<filter-class>org.taru.lanqiao.filter.CrosFilter</filter-class>
  </filter>
  <filter-mapping>
  	<filter-name>CrosFilter</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
 * @author lenovo
 *
 */
@WebServlet("/api/login")
public class LoginServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		User user=null;
		
		UserServiceImpl impl=new UserServiceImpl();
		JsonResult result;
		try {
			 user=impl.login(name, password);
			if(user==null){
				result=new JsonResult("404", "用户名和密码错误", null);
			}else{
				result=new JsonResult("200", "登录成功", user);
				//创建Session
				
				HttpSession session=request.getSession(true);
				session.setAttribute("userLoginKey", user);
				//创建cookie
				Cookie token=new Cookie("token",SecurityUtl.getMd5String("lanqiao"));
				//
				Cookie nameStr=new Cookie("username",name);
				token.setMaxAge(60*60);
				nameStr.setMaxAge(60*60);
				token.setPath("/");
				nameStr.setPath("/");
				response.addCookie(token);
				response.addCookie(nameStr);
				
			}
		} catch (Exception e) {
			System.out.println("500");
			result=new JsonResult("500", "登录异常", e.getMessage());
		}
		JsonWrite.write(response, result);
	}
}
