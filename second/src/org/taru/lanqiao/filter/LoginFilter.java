package org.taru.lanqiao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.taru.lanqiao.util.JsonWrite;
import org.taru.lanqiao.vo.JsonResult;
//@WebFilter("/api/*")
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest res, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response=(HttpServletResponse)resp;
		HttpServletRequest request=(HttpServletRequest)res;
		String path=request.getRequestURI();
		System.out.println(path);
		if("/second/api/login".equals(path)){
			chain.doFilter(request, response);
			return;
		}
		HttpSession session=request.getSession(false);
		System.out.println(session);
		if(session!=null){
			Object object=session.getAttribute("userLoginKey");
			if(object!=null){
				chain.doFilter(request, response);
				return;
			}
		}
		JsonResult result=new JsonResult("500", "没有登录，无法访问", "");
		JsonWrite.write(response, result);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
