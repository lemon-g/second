package org.taru.lanqiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.service.ProductServiceImpl;
import org.taru.lanqiao.util.JsonWrite;
import org.taru.lanqiao.vo.JsonResult;

import com.google.gson.stream.JsonWriter;
@WebServlet("/api/delProduct")
public class DelProductServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String id=request.getParameter("productId");
		JsonResult result;
		try {
			ProductServiceImpl impl=new ProductServiceImpl();
			boolean bool=impl.delProduct(id);
			System.out.println(bool);
			if(bool==true){
				result=new JsonResult("200","商品废除成功",bool);
			}else{
				result=new JsonResult("404","请确定",bool);
			}
		} catch (Exception e) {
			 result=new JsonResult("500","商品废除失败",e.getMessage());
		}
		JsonWrite.write(response, result);
	}
}
