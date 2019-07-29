package org.taru.lanqiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.service.ProductServiceImpl;
import org.taru.lanqiao.util.JsonWrite;
import org.taru.lanqiao.vo.JsonResult;

@WebServlet("/api/queryServlet")
public class ProductQueryServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id=request.getParameter("productId");		
		JsonResult result=null;	 
		try {
			ProductServiceImpl impl=new ProductServiceImpl();
			
			Product product=impl.queryById(id);
			 result=new JsonResult("200","查询商品信息成功",product);
		} catch (Exception e) {
			 result=new JsonResult("500","查询商品信息失败",e.getMessage());
		}
		JsonWrite.write(response, result);
		
	}
}
