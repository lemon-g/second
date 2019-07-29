package org.taru.lanqiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.model.User;
import org.taru.lanqiao.service.UserServiceImpl;
import org.taru.lanqiao.util.IdUtil;
import org.taru.lanqiao.util.JsonWrite;
import org.taru.lanqiao.util.StringUtil;
import org.taru.lanqiao.vo.JsonResult;
@WebServlet("/api/reguster")
public class RegusterServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		user.setUserName(request.getParameter("username"));
		user.setUserPassword(request.getParameter("userpassword"));
		user.setUserAdd(request.getParameter("useraddress"));
		user.setUserTel(request.getParameter("usertel"));
		user.setUserShopName(request.getParameter("userShopName"));
		user.setUserStatus(request.getParameter("userStatus"));
		user.setUserComment(request.getParameter("userComment"));
		user.setUserId(IdUtil.getUuid());
		JsonResult result=null;
		
		try {
			UserServiceImpl impl = new UserServiceImpl();
			int row =impl.reguster(user);
			if(row!=0){
				if(user!=null){
					result=new JsonResult("200", "注册成功", user);
				}
			}else{
				result=new JsonResult("404", "注册失败", null);
				System.out.println(result.getMessage());
			}
			
		} catch (Exception e) {
			result=new JsonResult("500", "注册异常", e.getMessage());
			
		}
		JsonWrite.write(response, result);
	
	}
}
