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
import org.taru.lanqiao.vo.PageResult;
@WebServlet("/api/queryList")
public class QueryListServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResult result=null;
		try {
			int pageNum=Integer.parseInt(request.getParameter("pageNum"));
			int pageSize=Integer.parseInt(request.getParameter("pageSize"));
			ProductServiceImpl impl=new ProductServiceImpl();
			PageResult pResult=impl.queryListAll(pageNum, pageSize);
			result=new JsonResult("200", "查询成功", pResult);
		} catch (Exception e) {
			// TODO: handle exception
			result=new JsonResult("500", "查询异常", e.getMessage());     
		}
		JsonWrite.write(response, result);
	}
}
