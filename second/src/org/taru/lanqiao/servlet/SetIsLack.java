package org.taru.lanqiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.taru.lanqiao.service.ProductServiceImpl;
import org.taru.lanqiao.util.JsonWrite;
import org.taru.lanqiao.vo.JsonResult;
@WebServlet("/api/isLack")
public class SetIsLack extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("productId");
//		String lack=request.getParameter("isLack");
		
		JsonResult result=null;	 
		try {
			ProductServiceImpl impl=new ProductServiceImpl();
			boolean isLack=impl.setIsLack(id);
			System.out.println(isLack);
			if(isLack==true){
				result=new JsonResult("200","商品缺货状态修改成功",isLack);
			}else{
				result=new JsonResult("404","请确定",isLack);
			}
			
		} catch (Exception e) {
			 result=new JsonResult("500","商品缺货状态修改失败",e.getMessage());
		}
		JsonWrite.write(response, result);
	}
}
