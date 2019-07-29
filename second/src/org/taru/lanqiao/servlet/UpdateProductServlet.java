package org.taru.lanqiao.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.service.ProductServiceImpl;
import org.taru.lanqiao.util.JsonWrite;
import org.taru.lanqiao.vo.JsonResult;
@WebServlet("/api/updateProduct")
public class UpdateProductServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("productId");
		Product product=new Product();
		Calendar calendar= Calendar.getInstance();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		product.setProductName(request.getParameter("productName"));
//		product.setPoductPhoto(request.getParameter("productPhoto"));
		product.setProductBrandId(request.getParameter("productBrandId"));
		product.setProductCategoryId(request.getParameter("productCategoryId"));
		product.setProductCode(request.getParameter("productCode"));
		
//		product.setProductDateTime(calendar.getTime());
		product.setProductDateTime(request.getParameter("productDateTime"));
		product.setProductIsLuck(request.getParameter("productIsLuck"));
		product.setProductIsSale(request.getParameter("productIsSale"));
		product.setProductLargePrice(request.getParameter("productLargePrice"));
		product.setProductLargeStandard(request.getParameter("productLargeStandard"));
		
		product.setProductLargUnit(request.getParameter("productLargUnit"));
		product.setProductOrder(request.getParameter("productOrder"));
		product.setProductSmallPrce(request.getParameter("productSmallPrce"));
		product.setProductSmallUnit(request.getParameter("productSmallUnit"));
		product.setProductStatus(request.getParameter("productStatus"));
		
		product.setProductStandard(request.getParameter("productStandard"));
		product.setProductSuggestPrice(request.getParameter("productSuggestPrice"));
		
		JsonResult result=null;
		try {
			
			ProductServiceImpl impl=new ProductServiceImpl();
			
			product =impl.updateProduct(id,product);
			System.out.println("kkk");
			if(product!=null){
				System.out.println("yyy");
				 result=new JsonResult("200","修改商品信息成功",product);	
			}else
				result=new JsonResult("404","修改商品信息错误",product);	
		} catch (Exception e) {
			result=new JsonResult("500","修改商品信息异常",e.getMessage());	
		}
		JsonWrite.write(response, result);
	}
}
