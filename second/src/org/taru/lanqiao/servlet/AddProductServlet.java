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
import org.taru.lanqiao.util.IdUtil;
import org.taru.lanqiao.util.JsonWrite;
import org.taru.lanqiao.vo.JsonResult;

@SuppressWarnings("serial")
@WebServlet("/api/addProduct")
public class AddProductServlet extends HttpServlet {
	@SuppressWarnings("unused")
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product=new Product();
		//获取数据
		product.setProductName(request.getParameter("productName"));
		product.setPoductPhoto(request.getParameter("productPhoto"));
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
		product.setProductId(IdUtil.getUuid());
		ProductServiceImpl impl=new ProductServiceImpl();
		impl.addProduct(product);
		JsonResult result=null;
		try {
			if(product!=null){
				result=new JsonResult("200","添加商品成功",product);
			}else{ 
				result=new JsonResult("404","添加商品不合法",product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			result=new JsonResult("500","添加商品异常",e.getMessage());
		}
		JsonWrite.write(response, result);
	}
}
