package org.taru.lanqiao.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.util.JsonWrite;
import org.taru.lanqiao.util.SecurityUtl;
import org.taru.lanqiao.vo.JsonResult;
@WebFilter("/api/*")
public class LoginCookieFilter implements Filter{

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
		if("/second/api/login".equals(path)||"/second/api/reguster".equals(path)){
			chain.doFilter(request, response);
			return;
		}
		Cookie[] cookies=request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("token")){
					if(cookie.getValue().equals(SecurityUtl.getMd5String("lanqiao"))){
						chain.doFilter(request, response);
						return;
					}
				}
			}
		}
		JsonResult result=new JsonResult("500", "没有登录，无法访问Cookie", "");
		JsonWrite.write(response, result);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
