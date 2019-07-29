package org.taru.lanqiao.servlet;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.model.Detail;
import org.taru.lanqiao.service.OrderDetailService;
import org.taru.lanqiao.util.JsonWrite;
import org.taru.lanqiao.vo.JsonResult;

@WebServlet("/api/orderDetailById")
public class OrderDetailServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId=request.getParameter("orderId");
		OrderDetailService service=new OrderDetailService();
		List<Detail> list=service.queryDetailByOrderId(orderId);
		JsonResult result;
		try {
			if(list.size()>0){
				result=new JsonResult("200", "订单详情查询成功", list);
			}else{
				result=new JsonResult("404", "输入的订单id号不存在", list);
			}
		} catch (Exception e) {
			result=new JsonResult("500", "订单详情查询异常", e.getMessage());
			System.out.println(e.getMessage());
		}
		JsonWrite.write(response, result);
	}
}
